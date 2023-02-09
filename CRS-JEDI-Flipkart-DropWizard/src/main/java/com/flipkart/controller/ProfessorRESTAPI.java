package com.flipkart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.StudentNotFoundException;


import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;
import org.hibernate.validator.constraints.Email;

/**
 * @author Aman Jham
 * Rest API class for professor
 */
@Path("/professor")
public class ProfessorRESTAPI {
    /**
     * View all students registered in a given course
     */
    @GET
    @Path("/viewEnrolledStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewStudents(@NotNull
                                              @QueryParam("courseId") int courseId) throws ValidationException {

        List<Student> students = new ArrayList<Student>();

        try{

            students = ProfessorServiceOperation.getInstance().viewStudents(courseId);

        }
        catch(Exception e){
            return null;
        }

        return students;
    }

    /**
     * View all courses alloted to professor
     */
    @GET
    @Path("/viewCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourses(@NotNull @QueryParam("profId") int profId) {
        List<Course> courses=new ArrayList<Course>();
        try{
            courses = ProfessorServiceOperation.getInstance().viewCourses(profId);
        }
        catch(Exception e){
            return null;
        }
        return courses;
    }

    /**
     * Assign Grades to a student
     */
    @PUT
    @Path("/assignGrade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignGrade(
            @NotNull
            @QueryParam("studentId") int studentId,

            @NotNull
            @QueryParam("courseId") int courseId,

            @NotNull
            @Valid Grade grade,

            @NotNull
            @QueryParam("sem") int sem,

            @NotNull
            @QueryParam("profId") int profId)  {

        try {
            List<Course> courses = ProfessorServiceOperation.getInstance().viewCourses(profId);
            Boolean success=false;
            for(Course course:courses) {
                if(course.getCourseId()==courseId) {
                    success=true;
                    break;
                }
            }
            if(!success) {
                return Response.status(400).entity("Course does not match with professor.").build();
            }

            ProfessorServiceOperation.getInstance().assignGrade(studentId, courseId, grade, sem);
        }
        catch(Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("Grade added for student with studentId: "+studentId).build();
    }


    /**
     * Sign up for courses to teach
     */
    @POST
    @Path("/signUpCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUpCourse(
            @NotNull
            @QueryParam("courseId") int courseId,

            @NotNull
            @QueryParam("professorId") int professorId) throws CourseNotFoundException {

        try {
            ProfessorServiceOperation.getInstance().signUpForCourse(courseId, professorId);

        }
        catch(CourseNotFoundException e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        catch(Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("Course registered by student with id + " + courseId).build();
    }



}
