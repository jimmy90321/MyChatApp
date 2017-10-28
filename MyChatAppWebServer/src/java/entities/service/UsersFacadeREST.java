package entities.service;

import apiMessage.ApiResult;
import com.google.gson.Gson;
import entities.Users;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @Consumes({"application/json"})
    public ApiResult apiCreate(Users entity){
        ApiResult apiResult = new ApiResult();
        String email_regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        String userEmail = entity.getUserEmail().toString();
        Pattern pattern = Pattern.compile(email_regex);
        Matcher matcher = pattern.matcher(userEmail);
        //check userEmail
        if(userEmail == null || userEmail.equals("")){
            apiResult.setErrCode(1011);
            apiResult.setErrMsg("帳戶名稱不可為空白");
            return apiResult;
        }else if(!matcher.matches()){
            apiResult.setErrCode(1012);
            apiResult.setErrMsg("帳戶名稱無效(格式錯誤)");
            return apiResult;
        }
        //check userPass
        else if (entity.getUserPw().trim().length() < 4 || entity.getUserPw().trim().length() > 12) {
            apiResult.setErrCode(1013);
            apiResult.setErrMsg("密碼長度未依規定，請重新輸入密碼(4~12碼)");
            return apiResult;
        }
        //check if user is exist
        String email = entity.getUserEmail();
        try{
            em.createNamedQuery("Users.findByUserEmail").setParameter("userEmail", email).getSingleResult();
            apiResult.setErrCode(1001);
            apiResult.setErrMsg("該帳戶已存在");
        }catch(Exception ex){
            apiResult.setErrCode(0);
            Gson gson = new Gson();
            String jsonIn = gson.toJson(entity);
            apiResult.setData(jsonIn);
            super.create(entity);
        }
        return apiResult;
    }

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
