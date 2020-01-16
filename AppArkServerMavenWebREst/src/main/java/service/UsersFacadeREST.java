/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.GoogleTools;
import entity.Users;
import exceptions.GoogleException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Francisco
 */
@Stateless
@Path("entity.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "com.mycompany_AppArkServerMavenWebREst_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

     @POST
    @Path("googleSignUp")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createGoogle(Users user) {
    
        GoogleTools tool = new GoogleTools();
        String id = tool.registroGoogle(user.getGoogleToken());

        if(id.length()<=0){
            throw new GoogleException("Index error");
        }else{
            user.setGoogleId(id);
            this.create(user);
        }
        
        
    }
    
    
    @GET
    @Path("findByEmail/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Users findOneByEmail(@PathParam("email") String emailRecibido) {
       Users res;
       List<Users> users;
       Query q = this.em.createQuery("SELECT u FROM Users u WHERE u.email = :dirEmail");
       q.setParameter("dirEmail", emailRecibido);
       users = q.getResultList();
       if(users.isEmpty()){
            res=null;
        }else{
            res=users.get(0);
        }
        return res;
    }
    @GET
    @Path("findOneByGoogle/{idg}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Users findOneByGoogle(@PathParam("idg") String tokenGoogle) {
       GoogleTools tool=new GoogleTools();
       String idGoogle=tool.registroGoogle(tokenGoogle);
       Users usuario=null;
       List<Users> userList;
       Query q = this.em.createQuery("SELECT u FROM Users u WHERE u.googleId = :googleId");
       q.setParameter("googleId", idGoogle);
       userList=q.getResultList();
       if(userList.isEmpty()){
           usuario=null;
       }else{
           usuario=userList.get(0);
       }
       return usuario;
       
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
