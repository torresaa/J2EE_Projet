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
@ManagedBean(name = "AuthorView")
@RequestScoped
public class AuthorView {
    @EJB
    private AuthorFacade authorFacade;
    private Author author;
    private String toFind = "";    
    public String getToFind() {
        return toFind;
    }

    public void setToFind(String toFind) {
        this.toFind = toFind;
    }
    
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

    public String findAuthor(){
        if (authorFacade.find(this.toFind)!= null){            
            return "foundauthor";
        }else{            
            return "nothing";
        }        
    }
    
}
