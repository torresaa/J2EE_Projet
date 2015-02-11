/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Dvd;
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
public class DvdFacade extends AbstractFacade<Dvd> {

    @PersistenceContext(unitName = "ProjetJ2EE_TELECOMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DvdFacade() {
        super(Dvd.class);
    }

    public List<Dvd> findByName(String name) {
        List<Dvd> list = new ArrayList<>();
        Query q = em.createQuery("SELECT d FROM Dvd d WHERE d.dvdTitle LIKE :name").
                setParameter("name", "%"+name+"%");
//        try{
//            q.getSingleResult();
//        }catch (NoResultException e1){
//            return list;
//        }catch (NonUniqueResultException e2){
//            list = q.getResultList();
//            return list;
//        }
        list = q.getResultList();
        return list;
    }

    public List<Dvd> findDvdByAuthor(String name) {
        List<Dvd> list = new ArrayList<>();
        Query q = em.createQuery("SELECT d FROM Dvd d, Author a WHERE a.authorName LIKE :name"
                + " AND a.idAuthor= d.authoridAuthor.idAuthor").
                setParameter("name", "%"+name+"%");
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

    public List<Dvd> findDvdByDirector(String name) {
        List<Dvd> list = new ArrayList<>();
        Query q = em.createQuery("SELECT d FROM Dvd d, Director di WHERE di.directorName LIKE :name "
                + "AND di.idDirector = d.directoridDirector.idDirector").
                setParameter("name", "%"+name+"%");
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
    
    public void updateDvdAdd(Dvd dvd){
        Query q = em.createQuery("UPDATE Dvd d SET d.quantity = d.quantity + :quantity WHERE d.idDVDs = :id")
                .setParameter("quantity", dvd.getQuantity()).setParameter("id", dvd.getIdDVDs());
        q.executeUpdate();
    }
    
    public void updateDvdSubstract(Dvd dvd){
        Query q = em.createQuery("UPDATE Dvd d SET d.quantity = d.quantity - :quantity WHERE d.idDVDs = :id")
                .setParameter("quantity", dvd.getQuantity()).setParameter("id", dvd.getIdDVDs());
        q.executeUpdate();
    }
}
