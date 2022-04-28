package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.SubjectData;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.atguigu.eduservice.entity.vo.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

	@Override
	public void saveSubject(MultipartFile multipartFile, EduSubjectService eduSubjectService) {

		try {
			InputStream in = multipartFile.getInputStream();
			EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<OneSubject> getallonetwoSubject() {
		QueryWrapper<EduSubject> queryWrapperone = new QueryWrapper<>();
		queryWrapperone.eq("parent_id", 0);
		List<EduSubject> oneSubjectList = baseMapper.selectList(queryWrapperone);

		QueryWrapper<EduSubject> queryWrappertwo = new QueryWrapper<>();
		queryWrappertwo.ne("parent_id", 0);
		List<EduSubject> twoSubjectList = baseMapper.selectList(queryWrappertwo);
		List<OneSubject> finalSubject = new ArrayList<>();
		for (int i = 0; i < oneSubjectList.size(); i++) {
			EduSubject eduSubject = oneSubjectList.get(i);
			OneSubject oneSubject = new OneSubject();
//            subject.setId(eduSubject.getId());
//            subject.setTitle(eduSubject.getTitle());
			BeanUtils.copyProperties(eduSubject, oneSubject);
			finalSubject.add(oneSubject);
			//4.封装二级分类
			List<TwoSubject> twoFinalSubject = new ArrayList<>();
			for (int j = 0; j < twoSubjectList.size(); j++) {
				EduSubject tSubject1 = twoSubjectList.get(j);
				if (tSubject1.getParentId().equals(eduSubject.getId())) {
					TwoSubject twoSubject = new TwoSubject();
					BeanUtils.copyProperties(tSubject1, twoSubject);
					twoFinalSubject.add(twoSubject);
				}
			}
			oneSubject.setChildren(twoFinalSubject);
		}
		return finalSubject;
	}
}
