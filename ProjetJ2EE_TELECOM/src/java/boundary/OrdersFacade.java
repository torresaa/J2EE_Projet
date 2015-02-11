/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Dvd;
import entities.Orders;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> {
    @PersistenceContext(unitName = "ProjetJ2EE_TELECOMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }
    
    public List<Orders> getOrdersForAdmin(){
        List<Orders> list = new ArrayList<>();
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = :st OR o.status = :stt")
                .setParameter("st", "En cours").setParameter("stt", "En attente");
        try {
            q.getSingleResult();
        } catch (NoResultException e1) {
            return list;
        } catch (NonUniqueResultException e2) {
            list = q.getResultList();
            return list;
        }
        list = q.getResultList();
        return list;
    }
    
    public List<Orders> getOrdersEnCours(){
        List<Orders> list = new ArrayList<>();
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = :st")
                .setParameter("st", "En cours");
        try {
            q.getSingleResult();
        } catch (NoResultException e1) {
            return list;
        } catch (NonUniqueResultException e2) {
            list = q.getResultList();
            return list;
        }
        list = q.getResultList();
        return list;
    }
    
    public List<Orders> getDispatchedOrders(){
        List<Orders> list = new ArrayList<>();
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = :st OR o.status = :stt")
                .setParameter("st", "Effectue").setParameter("stt", "En cours");
        try {
            q.getSingleResult();
        } catch (NoResultException e1) {
            return list;
        } catch (NonUniqueResultException e2) {
            list = q.getResultList();
            return list;
        }
        list = q.getResultList();
        return list;

    }
    
    public List<Orders> getOrdersEnAttente(){
        List<Orders> list = new ArrayList<>();
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = :st").setParameter("st", "En attente");
        try {
            q.getSingleResult();
        } catch (NoResultException e1) {
            return list;
        } catch (NonUniqueResultException e2) {
            list = q.getResultList();
            return list;
        }
        list = q.getResultList();
        return list;
    }
    
    public List<Orders> getOrdersEffectue(){
        List<Orders> list = new ArrayList<>();
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = :st")
                .setParameter("st", "Effectue");
        try {
            q.getSingleResult();
        } catch (NoResultException e1) {
            return list;
        } catch (NonUniqueResultException e2) {
            list = q.getResultList();
            return list;
        }
        list = q.getResultList();
        return list;
    }
    
    public void changeStatus(Dvd dvd){
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.dvdidDVDs.idDVDs = :id AND o.status = :st AND o.quantity < :q")
                .setParameter("id", dvd.getIdDVDs()).setParameter("st", "En attente")
                .setParameter("q", dvd.getQuantity());
        List<Orders> list = new ArrayList<>();
        list = q.getResultList();
        if (!list.isEmpty()){
            for (int i = 0; i < list.size(); i++){
                if(dvd.getQuantity()>0 && list.get(i).getQuantity() < dvd.getQuantity()){
                    q = em.createQuery("UPDATE Orders o SET o.status = :st WHERE o.idOrder = :id")
                            .setParameter("id", list.get(i).getIdOrder()).setParameter("st", "En cours");
                    q.executeUpdate();
                    q = em.createQuery("UPDATE Dvd d SET d.quantity = d.quantity - :q WHERE d.idDVDs = :id")
                            .setParameter("q", list.get(i).getQuantity()).setParameter("id", dvd.getIdDVDs());
                    q.executeUpdate();
                    dvd.setQuantity(dvd.getQuantity()-list.get(i).getQuantity());
                }
            }
        }
    }
}
