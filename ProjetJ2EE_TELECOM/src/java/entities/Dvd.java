/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class Dvd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDVDs;
    private String dvdTitle;    
    private Integer quantity;
    @JoinColumn(name = "Author_idAuthor", referencedColumnName = "idAuthor")
    @ManyToOne(optional = false)
    private Author authoridAuthor;
    @JoinColumn(name = "Director_idDirector", referencedColumnName = "idDirector")
    @ManyToOne(optional = false)    
    private Director directoridDirector;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    

    public Director getDirectoridDirector() {
        return directoridDirector;
    }

    public void setDirectoridDirector(Director director) {
        this.directoridDirector = director;
    }

    public Author getAuthoridAuthor() {
        return authoridAuthor;
    }

    public void setAuthoridAuthor(Author author) {
        this.authoridAuthor = author;
    }
    
    public String getDvdTitle() {
        return dvdTitle;
    }

    public void setDvdTitle(String title) {
        this.dvdTitle = title;
    }
    
    public Integer getIdDVDs() {
        return this.idDVDs;
    }

    public void setIdDVDs(Integer id) {
        this.idDVDs = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDVDs != null ? idDVDs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dvd)) {
            return false;
        }
        Dvd other = (Dvd) object;
        if ((this.idDVDs == null && other.idDVDs != null) || (this.idDVDs != null && !this.idDVDs.equals(other.idDVDs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dvd[ id=" + idDVDs + " ]";
    }
    
}
