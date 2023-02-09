package com.flipkart.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Student;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.service.UserServiceOperation;

@Path("/dashboard")
public class CRSApplicationRESTAPI {
    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login (
            @NotNull
            @QueryParam("userId") int userId,
            @NotNull
            @QueryParam("password") String password)
    {
        String role = "";
        try {
            role = UserServiceOperation.getInstance().login(userId, password);
        } catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        finally
        {
//            System.out.println(role);
            if (role.equals("student"))
            {
                    return Response.status(200).entity("Login successful! student id = "+userId ).build();

            }
            else if (role.equals("professor"))
            {
                return Response.status(200).entity("Login successful! professor id = "+userId ).build();
            }
            else if (role.equals("admin"))
            {
                return Response.status(200).entity("Login successful! admin id = "+userId ).build();
            }
            else if(role.equals("UNAPPROVED STUDENT"))
            {
                return Response.status(400).entity("Login unsuccessful! user id = "+userId +
                        " Student not approved. Please contact admin for Approval" ).build();
            }
        }
        return Response.status(500).entity("Login failed").build();
    }

    /**
     * Method for student to signup
     */
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(@Valid Student student) {
        try {
            AdminServiceOperation.getInstance().addStudent(student);
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Registration Successful " + student.getStudentId()).build();
    }

    @PUT
    @Path("/updatePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePassword(@NotNull
                                   @QueryParam("userId") int userId,
                                   @NotNull
                                   @QueryParam("oldPassword") String oldPassword,
                                   @NotNull
                                   @QueryParam("newPassword") String newPassword) {
        try {
            if (UserServiceOperation.getInstance().updatePassword(userId, oldPassword, newPassword)) {
                return Response.status(201).entity("Password updated successfully for userid: " + userId).build();
            } else {
                return Response.status(400).entity("Wrong old password for userid: " + userId).build();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Response.status(500).entity("Update password failed").build();
    }
}
