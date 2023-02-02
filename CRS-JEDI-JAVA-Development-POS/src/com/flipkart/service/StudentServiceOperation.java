package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.data.TempData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentServiceOperation implements StudentService {
    public List<Course> viewCourseCatalouge()
    {
        List<Course> coursesList = new ArrayList<Course>();
        for (Map.Entry<Integer, Course> cur : TempData.courseCatalogue.entrySet()) {
            coursesList.add(cur.getValue());
        }
        return coursesList;
    }

    public Boolean addCourse(int studentId, int courseId) {
        TempData.studentToCoursePreferenceList.get(studentId).add(courseId);
        return true;
    }

    public Boolean dropCourse(int studentId, int courseId) {
        if(TempData.studentToRegisteredCourseList.get(studentId).contains(courseId)) {
            TempData.studentToRegisteredCourseList.get(studentId).remove(courseId);
            return true;
        }
        return false;
    }

    public List<RegisteredCourse> viewGrades(int studentId) {
        List<RegisteredCourse> registeredCourses = new ArrayList<RegisteredCourse>();
        for(var cur : TempData.studentToRegisteredCourseList.get(studentId)) {
            registeredCourses.add(cur);
        }
        return registeredCourses;
    }

    public boolean signUp(Student student) {
        TempData.isStudentApproved.put(student.getId(), false);
        return true;
    }

    public boolean isApproved(int studentId) {
        return TempData.isStudentApproved.get(studentId);
    }

    public List<RegisteredCourse> viewRegisteredCourse(int studentID) {
        List<RegisteredCourse> registeredCourseList = new ArrayList<>();
        for (var cur : TempData.studentToRegisteredCourseList.get(studentID)) {
            registeredCourseList.add(cur);
        }
        return registeredCourseList;
    }

    public boolean isRegistrationDone(int studentID) {
        return TempData.isStudentApproved.containsKey(studentID);
    }

    @Override
    public boolean isPaymentDone(String studentID) {
        return false;
    }

    @Override
    public float calculateFee(String studentID) {
        return 0;
    }

    @Override
    public void payFee(String studentID, float amount) {
    }

}
