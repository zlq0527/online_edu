package com.atguigu.eduservice.listener;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 10:18 2022/4/23
 * @ Description：
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


	//因为SubjectExcelListener不能交给spring进行管理，
	// 需要自己new，不能注入其他对象
	// 不能实现数据库操作
	public EduSubjectService subjectService;

	public SubjectExcelListener(EduSubjectService subjectService) {
		this.subjectService = subjectService;
	}
	public SubjectExcelListener() {}


	@Override //一行行内容读取
	public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
		if(subjectData==null){
			throw new GuliException(20001,"文件数据为空");
		}
		//判断一级分类是否重复
		EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
		if (existOneSubject == null){  //表示没有相同的一级分类,进行添加
			existOneSubject = new EduSubject();
			existOneSubject.setParentId("0");
			existOneSubject.setTitle(subjectData.getOneSubjectName());
			subjectService.save(existOneSubject);
		}
		//获取一级分类的ID值
		//判断二级分类是否重复
		String pid = existOneSubject.getId();
		EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
		if (existTwoSubject == null){  //表示没有相同的一级分类,进行添加
			existTwoSubject = new EduSubject();
			existTwoSubject.setParentId(pid);
			existTwoSubject.setTitle(subjectData.getTwoSubjectName());
			subjectService.save(existTwoSubject);
		}

	}

	//判断是否是一级分类 不能重复添加
	private EduSubject existOneSubject(EduSubjectService subjectService,String name){
		QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
		wrapper.eq("title",name);
		wrapper.eq("parent_id","0");
		EduSubject oneSubject = subjectService.getOne(wrapper);
		return oneSubject;
	}
	//判断二级分类不能重复添加
	private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
		QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
		wrapper.eq("title",name);
		wrapper.eq("parent_id",pid);
		EduSubject twoSubject = subjectService.getOne(wrapper);
		return twoSubject;
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}
}
