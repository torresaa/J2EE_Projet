/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

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
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = 'En cours' OR o.status = 'En attente'");
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
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = 'En cours'");
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
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = 'Effectue' OR o.status = 'En Cours'");
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
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = 'En attente'");
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
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.status = 'Effectue'");
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
    
}
