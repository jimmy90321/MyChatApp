package entities.service;

import entities.ChatRooms;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("entities.chatrooms")
public class ChatRoomsFacadeREST extends AbstractFacade<ChatRooms> {

    @PersistenceContext(unitName = "MyChatAppWebServerPU")
    private EntityManager em;

    public ChatRoomsFacadeREST() {
        super(ChatRooms.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(ChatRooms entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Produces({"application/json"})
    public void edit(@PathParam("id") Integer id, ChatRooms entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public ChatRooms find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<ChatRooms> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<ChatRooms> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
