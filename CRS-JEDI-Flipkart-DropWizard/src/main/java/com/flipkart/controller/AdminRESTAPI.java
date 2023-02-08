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
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.service.AdminServiceOperation;
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

}
