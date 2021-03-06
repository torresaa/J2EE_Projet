/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuthorFacade;
import entities.Author;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author User
 */
@ManagedBean(name = "authorView")
@RequestScoped
public class AuthorView {
    @EJB
    private AuthorFacade authorFacade;
    private Author author;
    
    /**
     * Creates a new instance of AuthorView
     */
    public AuthorView() {
        this.author = new Author();
    }

    public Author getAuthor() {
        return author;
    }
    
    public String postAuthor(){
        if(author.getAuthorName().equals("")){
            return "notvalidpost";
        }else{
            authorFacade.create(author);
            return "postedauthor";
        }
    }
    
    public int numberOfAuthors(){
        return authorFacade.findAll().size();
    }
    
}
