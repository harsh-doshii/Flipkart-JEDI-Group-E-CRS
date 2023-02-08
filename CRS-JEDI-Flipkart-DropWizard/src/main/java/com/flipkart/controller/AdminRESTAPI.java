package com.flipkart.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminServiceOperation;

@Path("/admin")
public class AdminRESTAPI {

    /**
     * /admin/viewCourseCatalogue
     * REST-service for getting courses in the catalog
     * @return
     */
    @GET
    @Path("/viewCourseCatalogue")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourseCatalogue() {
        return AdminServiceOperation.getInstance().viewCourse();
    }


    /**
     * /admin/viewProfessors
     * REST-service for getting courses in the catalog
     * @return
     */
    @GET
    @Path("/viewProfessors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> viewProfessors() {
        return AdminServiceOperation.getInstance().viewProfessor();
    }

    /**
     * /admin/viewUnapprovedStudents
     * REST-service for getting courses in the catalog
     * @return
     */
    @GET
    @Path("/viewUnapprovedStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewUnapprovedStudents() {
        List<Student> professors = new ArrayList<>();
        try {
            return AdminServiceOperation.getInstance().viewPending();
        }
        catch (Exception e){
            return professors;
        }

    }
    /**
     * /admin/viewApprovedStudents
     * REST-service for getting courses in the catalog
     * @return
     */
    @GET
    @Path("/viewApprovedStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewApprovedStudents() {
        List<Student> students = new ArrayList<>();
        try {
            return AdminServiceOperation.getInstance().viewApprovedStudents();
        }
        catch (Exception e){
            return students;
        }

    }




    /**
     * /admin/addCourse
     * REST-service for adding a new course in catalog
     * @param course
     * @return
     */
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@Valid Course course) throws ValidationException{

        try {
            AdminServiceOperation.getInstance().addCourse(course);
            return Response.status(201).entity("Course with courseCode: " + course.getCourseId() + " added to catalog").build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }

    }

    /**
     * /admin/removeCourse
     * REST-services for dropping a course from catalog
     * @param courseId
     * @return
     */
    @DELETE
    @Path("/removeCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCourse(  @NotNull
            @QueryParam("courseId") Integer courseId) throws ValidationException{
        try {
            AdminServiceOperation.getInstance().removeCourse(courseId);
            return Response.status(201).entity("Course with courseCode: " + courseId + " deleted from catalog").build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }
    /**
     * /admin/addProf
     * REST-service for addding a new professor
     * @param professor
     * @return
     */
    @POST
    @Path("/addProf")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProf(@Valid Professor professor) throws ValidationException{
        try {
            AdminServiceOperation.getInstance().addProfessor(professor);
            return Response.status(201).entity("Professor with professorId: " + professor.getId() + " added").build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }

    }

    /**
     * /admin/addStudent
     * REST-service for addding a new student
     * @param student
     * @return
     */
    @POST
    @Path("/addStudent")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(@Valid Student student) throws ValidationException{
        try {
            AdminServiceOperation.getInstance().addStudent(student);
            return Response.status(201).entity("Student with StudentID: " + student.getId() + " added").build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/removeProf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProf(
            @NotNull
            @QueryParam("profId") int profId) throws ValidationException{
        try {
            AdminServiceOperation.getInstance().removeProfessor(profId);
            return Response.status(201).entity("Prof with profId: " + profId + " has been removed").build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }


    /**
     * /admin/approveStudent
     * REST-service for approving the student admission
     * @param studentId
     * @return
     */
    @PUT
    @Path("/approveStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            // @Min(value = 1, message = "Student ID should not be less than 1")
            // @Max(value = 9999, message = "Student ID should be less than 10000")
            @NotNull
            @QueryParam("studentId") int studentId) throws ValidationException{
        try {
            AdminServiceOperation.getInstance().approveStudent(studentId);
            return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }

    }

    @PUT
    @Path("/assignProf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignProf(
            // @Size(min = 4 , max = 10 , message = "courseCode length should be between 4 and 10 character")
            @NotNull
            @QueryParam("courseId") int courseId,
            // @Email(message = "Invalid Professor ID: Not in email format") //TODO: idk what this is
            @NotNull
            @QueryParam("profId") int profId) throws ValidationException {
        try {
            AdminServiceOperation.getInstance().assignProfessor(profId, courseId);
            return Response.status(201).entity("courseCode: " + courseId + " assigned to professor: " + profId).build();
        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }



    /**
     * Method handles request to display the grade card for student
     * @return response
     */
    @GET
    @Path("/generateGradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateReport(){
        try {
            AdminServiceOperation.getInstance().generateGradeCard();
            return Response.status(409).entity("Report Card Generated Successfully for all the users!!").build();

        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

}
