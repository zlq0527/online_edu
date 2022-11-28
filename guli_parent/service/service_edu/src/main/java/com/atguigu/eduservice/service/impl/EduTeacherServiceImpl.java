package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

	@Override
	public Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage) {
		QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
		wrapper.orderByAsc("id");
		baseMapper.selectPage(teacherPage,wrapper);

		List<EduTeacher> records = teacherPage.getRecords();
		long pages = teacherPage.getPages();
		long current = teacherPage.getCurrent();
		long size = teacherPage.getSize();
		long total = teacherPage.getTotal();
		boolean hasNext = teacherPage.hasNext();
		boolean hasPrevious = teacherPage.hasPrevious();
		Map<String,Object> map = new HashMap<>();
		map.put("items", records);
		map.put("current", current);
		map.put("page", pages);
		map.put("size", size);
		map.put("total", total);
		map.put("hasNext", hasNext);
		map.put("hasPrevious", hasPrevious);
		return map;
	}
}
