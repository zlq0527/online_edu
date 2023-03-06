.<template>
    <div>
      <!--多条件查询表单-->
      <el-form
        :inline="true"
        class="demo-form-inline"
        style="margin-left: 20px; margin-top: 12px"
      >
        <el-form-item label="名称">
          <el-input
            v-model="bannerQuery.nickname"
            placeholder="请输入名称"
          ></el-input>
        </el-form-item>

        <el-form-item label="添加时间">
        <el-date-picker
          v-model="bannerQuery.gmtCreate"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="bannerQuery.gmtModified"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
        
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getList()"
            >查询</el-button
          >
          <el-button type="default" @click="resetData()">清空</el-button>
        </el-form-item>
      </el-form>
  
      <!--数据显示的表格-->
      <el-table
        :data="list"
        style="width: 100%"
        border
        fit
        highlight-current-row
        element-loading-text="数据加载中"
      >
        <el-table-column prop="date" label="序号" width="70" align="center">
          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="id" label="id" width="80"> </el-table-column>
        <el-table-column prop="courseName" label="课程名称" />
        <el-table-column prop="teacherName" label="教师名称" />
        <el-table-column prop="nickname" label="会员昵称" />
        <el-table-column prop="content" label="评论内容" />
        <el-table-column prop="gmtCreate" label="评论时间" width="160" />
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              @click="removeById(scope.row.id)"
              >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <el-pagination
        background
        layout="prev, pager, next,total,jumper"
        :total="total"
        :page-size="limit"
        style="padding: 30px 0; text-align: center"
        :current-page="page"
        @current-change="getList"
      >
      </el-pagination>
    </div>
  </template>
  
  <script>
  //引入要调用的方法，teacher.js文件
  import banner from "@/api/edu/comment.js";
  
  export default {
    //写核心代码位置
    data() {
      //1、定义变量和初始值
      return {
        list: null, //查询之后给接口返回的数据装的集合
        page: 1, //当前页
        limit: 10, //每页显示记录数
        bannerQuery: {}, //条件封装对象
        total: 0, //总记录数
      };
    },
    created() {
      //页面渲染之前执行，调用method定义的方法
      //调用
      this.getList();
    },
    methods: {
      //调用具体的方法，调用teacher.js定义的方法
      //讲师列表的方法
      getList(page = 1) {
        this.page = page;
        banner
          .getCommentPage(this.page, this.limit, this.bannerQuery)
          .then((resp) => {
            //resp接口返回的数据
            // console.log(resp);
            this.list = resp.data.data;
            this.total = resp.data.total;
          }) //请求成功
          .catch((err) => {
            console.log(err);
          }); //请求失败
      },
      //清空方法
      resetData() {
        //表单输入项数据清空
        this.bannerQuery = {};
        //查询所有讲师数据
        this.getList();
      },
      removeById(id) {
        this.$confirm("此操作将永久删除该评论, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          //点击确定，执行then方法
          banner.removeById(id).then(resp => {
            //删除成功
            //提示信息
            console.log(resp)
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            //刷新页面
            this.getList();
          });
        });
      },
    },
  };
  </script>
  
  <style></style>
  