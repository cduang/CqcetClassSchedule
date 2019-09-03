package com.ckong.schedule.utils.interfaces;

import com.ckong.schedule.entity.Course;

import java.util.List;
import java.util.Map;

public interface ICourseProcessor {

    /**
     * 处理html,将其转换成Course List 集合
     * @param html String型的html网页
     * @return 处理后的Course List集合
     */
    Map<Integer, List<Course>> processor(String html);
}
