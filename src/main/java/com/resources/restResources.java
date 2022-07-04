package com.resources;
import com.example.newrestapi.Teacher;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        Teacher newTeacher = new Gson().fromJson(payload,Teacher.class);
        System.out.println(newTeacher.toString());
        return Response.ok(newTeacher.toString()).build();


       // return  Response.noContent().build();
    }

    @PUT
    @Path("/JSON/PUT")
//    @Produces("")
    public Response putJSON(@QueryParam("name") String name)
    {
        //@PathParam("id") String id;
        Teacher newTeacher = new Teacher("null","null",0);
        newTeacher.setName(name);
        return Response.ok(newTeacher.toString()).build();
    }


}





