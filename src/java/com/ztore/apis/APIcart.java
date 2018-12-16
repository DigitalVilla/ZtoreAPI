package com.ztore.apis;

import com.ztore.collections.CartItems;
import com.ztore.connection.DBcart;
import com.ztore.resources.CartItem;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("cart")
public class APIcart {

    private DBcart db = new DBcart();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItem> getUsers() {
        return db.getCart();
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItem> getUser(@PathParam("username") String username) {
        return db.getCartFor(username);
    }

    @GET
    @Path("{username}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CartItem getItem(@PathParam("username") String username, @PathParam("id") int id) {
        return db.getItem(username, id);
    }

    @POST
    @Path("{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int newItem(@PathParam("username") String username, CartItem newItem) {
        return db.newItem(newItem);
    }

    @POST
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON})
    public int newItems(CartItems list) {
        int counter = 0;
        List<CartItem> items = list.getCartItems();
        for (int i = 0; i < items.size(); i++) {
            counter += db.newItem(items.get(i));
        }
        return counter;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int updateItem(CartItem item) {
        return db.updateItem(item);
    }

    @PUT
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON})
    public int updateItems(CartItems list) {
        int counter = 0;
        List<CartItem> items = list.getCartItems();
        for (int i = 0; i < items.size(); i++) {
            counter += db.updateItem(items.get(i));
        }
        return counter;
    }

    @PUT
    @Path("{username}/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int updateItems(@PathParam("username") String username, @PathParam("id") int id, int qty) {
        return db.updateItem(username, id, qty);
    }

    @DELETE
    @Path("{username}/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int deleteItem(@PathParam("username") String username, @PathParam("id") int id) {
        return db.deleteItem(username, id);
    }
    
     @DELETE
    @Path("{username}/all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int deleteAll(@PathParam("username") String username) {
        return db.deleteAll(username);
    }

    @DELETE
    @Path("/batch/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public int deleteItems() {
           return db.deleteCart();
    }

}
