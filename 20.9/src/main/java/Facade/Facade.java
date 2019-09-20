/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Customer;
import Entity.Item;
import Entity._Order;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Martin
 */
public class Facade {

    private static Facade instance;
    private static EntityManagerFactory emf;

    public static Facade Get(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    public void CreateCustomer() {
        EntityManager em = emf.createEntityManager();
        try {
            Customer C = new Customer("Stig", "Stig");
            em.getTransaction().begin();
            em.persist(C);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void CreateOrder() {
        EntityManager em = emf.createEntityManager();
        Customer C = GetID(1);
        _Order O = new _Order(C, 2.22);
        try {
            em.getTransaction().begin();
            em.persist(O);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Customer GetID(int id) {
        EntityManager em = emf.createEntityManager();
        Customer out = em.find(Customer.class, (long) id);
        return out;
    }

    public void createItem() {
        EntityManager em = emf.createEntityManager();
        Item I = new Item("Thing", "det her er helt klart en ting der gør noget. Men den er også dyr", 100000.99);
        try {
            em.getTransaction().begin();
            em.persist(I);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
