/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Author;
import entities.Dvd;
import java.util.List;
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
public class AuthorFacade extends AbstractFacade<Author> {
    @PersistenceContext(unitName = "ProjetJ2EE_TELECOMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    public Author findByName(String name){       
        Query q = em.createQuery("SELECT a FROM Author a WHERE a.authorName= :name").
                setParameter("name", name);
        try{
            return (Author) q.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

}
