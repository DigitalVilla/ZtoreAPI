package com.ztore.apis;

import com.ztore.collections.Users;
import com.ztore.connection.DBusers;
import com.ztore.resources.CartItem;
import com.ztore.resources.User;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("users")
public class APIusers {

    private DBusers db = new DBusers();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsers() {
        return db.getUsers();
    }

    @GET
    @Path("{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUser(@PathParam("username") String username) {
        return db.getUser(username);
    }

    @GET
    @Path("{username}/type")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserType(@PathParam("username") String username) {
        return db.getUser(username).getType();
    }

    //exception to validate user and avoid sending credentials over the net
    @POST
    @Path("valid")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean validateUser(User user) {
        return db.validUser(user);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int newUser(User newUser) {
        return db.newUser(newUser);
    }

    @PUT
    @Path("batch")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int updateUser(Users users) {
        int affected = 0;
        List<User> list = users.getUsers();
        for (int i = 0; i < list.size(); i++) 
            affected += db.newUser(list.get(i));
        return affected;
    }

    @PUT
    @Path("{username}/password")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int updatePassword(@PathParam("username") String username,String password) {
        return db.updatePassword(username,password);
    }

    @PUT
    @Path("{username}/type")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int changeType(@PathParam("username") String username) {
        return db.changeType(username);
    }

    @PUT
    @Path("{username}/reset")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int resetPassword(@PathParam("username") String username,String password) {
        return db.updatePassword(username, "password");
    }
    
    @DELETE
    @Path("{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int deleteUser(@PathParam("username") String username) {
        return db.deleteUser(username);
    }
}
