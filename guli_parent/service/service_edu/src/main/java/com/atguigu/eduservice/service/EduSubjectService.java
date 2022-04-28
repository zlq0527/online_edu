package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
@Service
public interface EduSubjectService extends IService<EduSubject> {

	void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

	List<OneSubject> getallonetwoSubject();
}
