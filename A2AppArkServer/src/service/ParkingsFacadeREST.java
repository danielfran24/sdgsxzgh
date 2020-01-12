/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.ParkingTools;
import entity.Parkings;
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
 * @author kirek
 */
@Stateless
@Path("entity.parkings")
public class ParkingsFacadeREST extends AbstractFacade<Parkings> {

    @PersistenceContext(unitName = "AppArkServerPU")
    private EntityManager em;

    public ParkingsFacadeREST() {
        super(Parkings.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Parkings entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Parkings entity) {
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
    public Parkings find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Parkings> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Parkings> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("parkingReview")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Parkings> findParkingWithPositiveReviews() {
       
       List<Parkings> parkings;
       Query q = this.em.createQuery("Select P from Parkings p INNER JOIN Reviews r ON p.ID = r.parking where  r.usefulness >0 ");
      
       parkings = q.getResultList();
        return parkings;
    }
    
    
    @GET
    @Path("parkingReviewScore/{score}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Parkings> findParkingAccordingToReviewsScore(@PathParam("score") Integer score) {
       List<Parkings> parkings;
       Query q = this.em.createQuery("select P from Parkings p INNER JOIN Reviews r ON p.ID = r.parking where r.score = :sc");
       q.setParameter("sc", score);
       parkings = q.getResultList();
        return parkings;
    }
    
       @GET
    @Path("parkingScorePositive")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Parkings> fiindParkingScorePositive(@PathParam("score") Integer score) {
       List<Parkings> parkings;
       Query q = this.em.createQuery("select P from Parkings p INNER JOIN Reviews r ON p.ID = r.parking where r.score > 0");
       parkings = q.getResultList();
        return parkings;
    }
    @GET
    @Path("parkingList")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Parkings> initParkingList() {
       List<Parkings> parkings;
       ParkingTools ptools=new ParkingTools();
       parkings = ptools.parkingList();
        return parkings;
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
