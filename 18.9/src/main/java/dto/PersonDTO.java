/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import E.Person;

/**
 *
 * @author Martin
 */
public class PersonDTO {
    private String fname,lName,phone;
    private long id;

    public PersonDTO(E.Person p) {
        this.fname = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhone();           
    }

    public String getFname() {
        return fname;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }
    
    
    
}
