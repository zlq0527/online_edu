import com.atguigu.eduservice.EduApplication;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = EduApplication.class)
@RunWith(SpringRunner.class)
public class EduControllerTest {

    @Autowired
    EduTeacherService teacherService;
    @Test
    public void test() {
        QueryWrapper<EduTeacher> last = new QueryWrapper<EduTeacher>().last("limit 5,5");
        List<EduTeacher> list = teacherService.list(last);
        Map<String, List<EduTeacher>> collect = list.stream().collect(Collectors.groupingBy(EduTeacher::getName));
        System.out.println(collect);
    }

}
