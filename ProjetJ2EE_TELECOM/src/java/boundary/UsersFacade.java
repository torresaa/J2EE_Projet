/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author User
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "ProjetJ2EE_TELECOMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users findUser(String username, String password){
        Query q = em.createQuery("SELECT u FROM Users u WHERE u.username= :username AND u.password= :password")
                .setParameter("username", username).setParameter("password", password);
        try{
            return (Users) q.getSingleResult();
        }catch (NoResultException e){
            return null;
        }        
    }
    
    public Users findUserByEmail(String email, String password){
        Query q = em.createQuery("SELECT u FROM Users u WHERE u.email= :email AND u.password= :password")
                .setParameter("email", email).setParameter("password", password);
        try{
            return (Users) q.getSingleResult();            
        }catch (NoResultException e){
            return null;
        }
    }
}
