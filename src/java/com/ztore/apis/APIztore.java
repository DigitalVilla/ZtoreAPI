package com.ztore.apis;

import com.ztore.collections.Items;
import com.ztore.connection.DBztore;
import com.ztore.resources.Item;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("ztore")
public class APIztore {

    private DBztore db = new DBztore();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Item> getItems() {
        return db.getItems();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Item getItem(@PathParam("id") int id) {
        return db.getItem(id);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int newItem(Item newItem) {
        return db.newItem(newItem);
    }

    @POST
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON})
    public int newItems(Items list) {
        int counter = 0;
        List<Item> items = list.getItems();
        for (int i = 0; i < items.size(); i++) {
            counter += db.newItem(items.get(i));
        }
        return counter;
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int updateItem(@PathParam("id") int id, Item item) {
        return db.updateItem(item);
    }

    @PUT
    @Path("{id}/qty")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int updateItem(@PathParam("id") int id, int qty) {
        return db.updateItem(id, qty);
    }

    @PUT
    @Path("{id}/desc")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int updateItem(@PathParam("id") int id, String description) {
        return db.updateItem(id, description);
    }

    @PUT
    @Path("{id}/price")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int updateItem(@PathParam("id") int id, double price) {
        return db.updateItem(id, price);
    }

    @PUT
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON})
    public int updateItems(Items list) {
        int counter = 0;
        List<Item> items = list.getItems();
        for (int i = 0; i < items.size(); i++) {
            counter += db.updateItem(items.get(i));
        }
        return counter;
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public int deleteItem(@PathParam("id") int id) {
        return db.deleteItem(id);
    }

    @DELETE
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON})
    public int deleteItems(Items list) {
        int counter = 0;
        List<Item> items = list.getItems();
        for (int i = 0; i < items.size(); i++) {
            counter += db.deleteItem(items.get(i).getId());
        }
        return counter;
    }

    @DELETE
    @Path("batch/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public int deleteItems() {
        return db.deleteItem(0);
    }

}
