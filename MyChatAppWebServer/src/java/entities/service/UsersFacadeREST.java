package entities.service;

import apiMessage.ApiResult;
import com.google.gson.Gson;
import entities.Users;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("entities.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "MyChatAppWebServerPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Users entity) {
        //if(user is exist)
            super.create(entity);    
    }
    
//    @POST
//    @Override
//    @Consumes({"application/json"})
//    public void create(Users entity){
//        em.createNamedQuery("Users.createUser").setParameter("userEmail", entity.getUserEmail()).setParameter("userPw",entity.getUserPw()).setParameter("userName",entity.getUserName()).executeUpdate();
//    }

    @PUT
    @Path("{id}")
    @Produces({"application/json"})
    public void edit(@PathParam("id") Integer id, Users entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Path("{email}")
    @Produces({"application/json"})
    public Users find(@PathParam("email") String useremail) {
          return (Users) em.createNamedQuery("Users.findByUserEmail").setParameter("userEmail", useremail).getSingleResult();
    }

    //http://localhost:11241/MyChatAppWebServer/webresources/entities.users/Test/4567
    @GET
    @Path("/Test/{email}")
    @Produces({"application/json"})
    public ApiResult GetMail(@PathParam("email") String useremail) {
        ApiResult apiResult = new ApiResult();
        try
        {
            Users users = (Users) em.createNamedQuery("Users.findByUserEmail").setParameter("userEmail", useremail).getSingleResult();
            apiResult.setErrCode(0);
            Gson gson = new Gson();
            String jsonIn = gson.toJson(users);
            apiResult.setData(jsonIn);
            return apiResult;
        }
        catch(Exception ex)
        {
            apiResult.setErrCode(500);
            apiResult.setErrMsg(ex.toString());
            return apiResult;
        }
    }

    
    
//    @Path("{id}")
//    @Produces({"application/json"})
//    public Users find(@PathParam("id") Integer id) {
//          return (Users) em.createNamedQuery("Users.findByUserName").setParameter("userName", "test").getSingleResult();      
// return super.find(userName);
//    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
   @Produces({"application/json"})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
