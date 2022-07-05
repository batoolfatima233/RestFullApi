package com.resources;
import com.example.newrestapi.Teacher;
import com.example.newrestapi.emptyNameException;
import com.example.newrestapi.invalidAgeException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/hello-world")
public class restResources {
    @GET
    @Path("/general-get")
    public String hello() {
        return "Hello, batool fatima!";
    }

    @GET
    @Path("/JSONObj")
    public Response getJSON() throws  Exception
    {
        JsonObject newObject = new JsonObject();
        newObject.addProperty("CONNECTION",true);
        newObject.addProperty("MESSAGE","Hii.. welcome to new app");
        return Response.ok(newObject.toString()).build();
    }

    @POST
    @Path("/JSON")
    @Produces("text/JSON")
    public Response postJSON(String payload) throws Exception
    {
        //System.out.println(payload);
        try
        {
            Teacher newTeacher = new Gson().fromJson(payload,Teacher.class);
            if(newTeacher.getName().equals(""))
            {
                //throw new Exception();
                throw new emptyNameException();
            }
            if(newTeacher.getAge() <=0)
            {
               throw new invalidAgeException();
            }
            else
            {
                System.out.println(newTeacher.toString());
                return Response.ok(newTeacher.toString()).build();
            }
        }
        catch(Exception e)
        {
                return Response.status(400).entity(e.toString()).build();
        }
       // return  Response.noContent().build();
    }

    @PUT
    @Path("/JSON/PUT")
//    @Produces("")
    public Response putJSON(@QueryParam("name") String name, @QueryParam("subject") String subject,@QueryParam("age") int age)
    {
        try
        {

           //if( (((Object)age).getClass().getSimpleName()) != "int")
            if(name.equals("") || subject.equals("") || age <= 0)
           {
               throw new Exception();
           }
           else
           {
               Teacher newTeacher = new Teacher(name,subject,age);
               newTeacher.setName(name);
               return Response.ok(newTeacher.toString()).build();
           }

        }
        catch(Exception e)
        {
            return Response.status(400).entity("incorrect parameters entered").build();
        }
    }
    @PUT
    @Path("/JSON/login")

    public Response putLOGIN(@QueryParam("name") String name,@QueryParam("email") String email,@QueryParam("password") String password)
    {
        try
        {
         if(name.equals("Batool")  && email.equals("batool@gmail.com") && password.equals("123"))
         {
             return Response.ok("successfully login").build();

         }
         else
         {
             throw new Exception();
         }
        }
        catch(Exception e)
        {
            return Response.status(401).entity("invalid login credentials").build();
        }
    }

}





