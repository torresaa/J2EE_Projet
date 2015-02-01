/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Director; 
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class DirectorFacade extends AbstractFacade<Director> {
    @PersistenceContext(unitName = "ProjetJ2EE_TELECOMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DirectorFacade() {
        super(Director.class);
    }
    public Director findByName(String name){
        Query q = em.createQuery("SELECT d FROM Director d WHERE d.directorName= :name").
                setParameter("name", name);
        try{
            return (Director) q.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }    
}
