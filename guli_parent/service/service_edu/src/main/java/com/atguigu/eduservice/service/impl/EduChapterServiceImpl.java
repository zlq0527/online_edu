package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-19
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
	@Autowired
	EduVideoService eduVideoService;
	@Override
	public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
		QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("course_id", courseId);
		List<EduChapter> chapterList = baseMapper.selectList(queryWrapper);

		//2.根据课程id查询所有小节
		QueryWrapper<EduVideo> VideoVoQueryWrapper = new QueryWrapper<>();
		VideoVoQueryWrapper.eq("course_id",courseId);
		final List<EduVideo> videoList = eduVideoService.list(VideoVoQueryWrapper);

		//3.封装chapterList的所有章节
		List<ChapterVo> finalChapterVideoList = new ArrayList<>();
		for (int i = 0; i < chapterList.size(); i++) {
			EduChapter eduChapter = chapterList.get(i);
			ChapterVo chapterVo = new ChapterVo();
			BeanUtils.copyProperties(eduChapter,chapterVo);
			finalChapterVideoList.add(chapterVo);
			//4.封装videoList的所有小节
			List<VideoVo> videoVoList = new ArrayList<>();
			for (int j = 0; j < videoList.size(); j++) {
				EduVideo eduVideo = videoList.get(j);
				if (eduChapter.getId().equals(eduVideo.getChapterId())){
					VideoVo videoVo = new VideoVo();
					BeanUtils.copyProperties(eduVideo,videoVo);
					videoVoList.add(videoVo);
				}
			}
			chapterVo.setChildren(videoVoList);
		}
		return finalChapterVideoList;
	}
}
