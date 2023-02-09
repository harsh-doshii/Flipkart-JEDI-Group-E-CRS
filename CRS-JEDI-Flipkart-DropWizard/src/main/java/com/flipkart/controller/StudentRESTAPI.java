package com.flipkart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.PaymentNotification;
import com.flipkart.bean.PreferenceList;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.service.PaymentServiceOperation;
import com.flipkart.service.StudentServiceOperation;
import org.apache.log4j.Logger;

import com.flipkart.bean.*;

@Path("/student")
public class StudentRESTAPI {

    @GET
    @Path("/viewCourseCatalogue")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourseCatalogue(){
        List<Course> lcourse = new ArrayList<Course>();
        try {
            lcourse = StudentServiceOperation.getInstance().viewCourseCatalouge();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lcourse;
    }

    @GET
    @Path("/viewGrades/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegisteredCourse> viewGrades(@PathParam("studentID") int studentID) {
        List<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();
        try {
            courses = StudentServiceOperation.getInstance().viewGrades(studentID);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    //Doubt Post or Get?
    @GET
    @Path("/registerCourses/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public void registerCourses(@PathParam("studentID") int studentID) {
        try {
            StudentServiceOperation.getInstance().registerCourses(studentID);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //doubt return statements?
    @PUT
    @Path("/addCourse/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@PathParam("studentID") int studentID, @QueryParam("courseID") int courseID, @QueryParam("isPrimary") boolean isPrimary) {
        try {
            StudentServiceOperation.getInstance().addCourse(studentID, courseID, isPrimary);
            return Response.status(201).entity( "Course with courseID " + courseID + " has been added.").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/dropCourse/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(@PathParam("studentID") int studentID, @QueryParam("courseID")  int courseID) {
        try {
            StudentServiceOperation.getInstance().dropCourse(studentID, courseID);
            return Response.status(200).entity( "Course with courseID " + courseID + " has been dropped.").build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/viewRegisteredCourses/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> viewRegisteredCourses(@PathParam("studentID") int studentID) {
        List<Integer> courses = new ArrayList<Integer>();
        try {
            courses = StudentServiceOperation.getInstance().viewRegisteredCourse(studentID);
            return courses;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    @PUT
    @Path("/payFee/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response payFee(@PathParam("studentID") int studentID, @QueryParam("mode") String mode) {
        try{
                float amount = PaymentServiceOperation.getInstance().calculateFee(studentID);
                PaymentServiceOperation.getInstance().payFee(studentID, amount, mode);
                return Response.status(202).entity( "Payment successful.").build();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getTotalFeeToPay/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalFeeToPay(@PathParam("studentID") int studentID){
        float amount = 999;
        try{
            amount = PaymentServiceOperation.getInstance().calculateFee(studentID);
            return amount;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return amount;
    }

    @GET
    @Path("/viewNotifications/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaymentNotification> viewNotifications(@PathParam("studentID") int studentID)
    {
        List <PaymentNotification> notifications = new ArrayList<PaymentNotification>();
        try{
           notifications = StudentServiceOperation.getInstance().viewNotifications(studentID);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
            return notifications;
    }

    @GET
    @Path("/viewPreferenceList/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public PreferenceList viewPreferenceList(@PathParam("studentID") int studentID)
    {
        PreferenceList preferenceList = new PreferenceList();
        try {
            preferenceList = StudentDAOImpl.getInstance().viewCoursesInPreferenceList(studentID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return preferenceList;
    }
}
