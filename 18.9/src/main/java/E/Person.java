/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E;

import dto.PersonDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Martin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person"),
    @NamedQuery(name = "Person.getAll", query = "SELECT m FROM Person m"),
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String firstName, lastName, phone;
    private Date lastEdited;

    @OneToOne
    private Address adr;
    
    public Person(String firstName, String lastName, String phone) {
        this.lastEdited = new Date(System.currentTimeMillis());
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    
    public Person(PersonDTO P){
        this.lastName = P.getlName();
        this.firstName = P.getFname();
        this.phone = P.getPhone();
    }
    
    public Person() {        
    }
    

    public String getFirstName() {
        
        return firstName;
        
        
    }

    public void setFirstName(String firstName) {
        this.lastEdited = new Date(System.currentTimeMillis());
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastEdited = new Date(System.currentTimeMillis());
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.lastEdited = new Date(System.currentTimeMillis());
        this.phone = phone;
    }
    
    
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "E.Person[ id=" + id + " ]";
    }
    
}
