
package entities.service;

import apiMessage.ApiResult;
import entities.Messages;
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
@Path("entities.messages")
public class MessagesFacadeREST extends AbstractFacade<Messages> {

    @PersistenceContext(unitName = "MyChatAppWebServerPU")
    private EntityManager em;

    public MessagesFacadeREST() {
        super(Messages.class);
    }

    @POST
    @Consumes({"application/json"})
    public ApiResult apiCreate(Messages entity) {
        ApiResult apiResult = new ApiResult();
        try{
            super.create(entity);
        }
        catch(Exception ex){
            apiResult.setErrCode(400);
            apiResult.setData(ex.toString());
        }
        return apiResult;
    }

    @PUT
    @Path("{id}")
    @Produces({"application/json"})
    public void edit(@PathParam("id") Integer id, Messages entity) {
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
    public Messages find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Messages> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
   @Produces({"application/json"})
    public List<Messages> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
