package  com.ckong.schedule.utils.cqcet;

import com.ckong.schedule.entity.Course;
import com.ckong.schedule.utils.interfaces.ICourseProcessor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.*;

/**
 * 解析重庆电子工程职业学院课程表网页
 * @author kongzhiqiang
 */
public class CourseProcessorCqcetImpl implements ICourseProcessor {

    @Override
    public Map<Integer, List<Course>> processor(String html) {
        Document document = Jsoup.parse(html, "utf-8");
        document.getElementsByTag("tr").first().remove();
        document.getElementsByTag("tr").last().remove();
        document.getElementsByClass("kbcontent1").remove();

        Elements trs = document.getElementsByTag("tr");

        Map<Integer, List<Course>> processorResult = new LinkedHashMap<>();

        processorResult.put(1, new LinkedList<>());
        processorResult.put(2, new LinkedList<>());
        processorResult.put(3, new LinkedList<>());
        processorResult.put(4, new LinkedList<>());
        processorResult.put(5, new LinkedList<>());
        processorResult.put(6, new LinkedList<>());
        processorResult.put(7, new LinkedList<>());

        for (Element ele: trs) {

            List<Course> lineCourse = processorLine(ele);

            if (lineCourse.size() != 0) {

                for (Course c: lineCourse) {
                    int index = Integer.parseInt(c.getDayOfWeek());
                    if (index >=1 && index <= 7) {
                        processorResult.get(index).add(c);
                    }
                }
            }
        }

        return processorResult;
    }


    /**
     * 解析重电课程表中的一行
     * @param rowHtml String 类型的html
     * @return 一行的课程(一天中上课时间中相同的课程)集合的长度为0则表示没有课程
     */
    private List<Course> processorLine(Element rowHtml) {

        List<Course> lineCourse = new ArrayList<>();
        Integer classTime = null;

        switch (rowHtml.getElementsByTag("th").text().trim()) {
            case "1,2节": classTime = 1; break;
            case "3,4节": classTime = 3; break;
            case "5,6节": classTime = 5; break;
            case "7,8节": classTime = 7; break;
            case "9,10,11节": classTime = 9; break;
            case "中午(12,13节)": classTime = 12; break;
            case "傍晚(14,15节)": classTime = 13; break;
            default: break;
        }

        Elements tds = rowHtml.getElementsByClass("kbcontent");

        int dayOfWeek = 1;
        for (Element td: tds) {
            Course course = new Course();
            if (!"&nbsp;".equals(td.childNode(0).toString().trim())) {

                course.setCourseName(td.childNode(0).toString().trim());
                course.setClassTime(classTime);
                course.setDayOfWeek(String.valueOf(dayOfWeek));
                course.setTeacher(td.getElementsByAttributeValue("title", "老师").text());
                course.setClassRoom(td.getElementsByAttributeValue("title", "教室").text());
                course.setWeekRange(td.getElementsByAttributeValue("title", "周次(节次)").text());
                lineCourse.add(course);
            }
            dayOfWeek ++;
        }

        return lineCourse;
    }


}