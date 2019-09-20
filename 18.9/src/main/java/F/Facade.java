/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package F;

import E.Person;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    /**
     *
     * @return
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person addPerson(String fName, String lName, String phone) {
        Person p = new Person(fName, lName, phone);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return p;

    }

    public Person deletePerson(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Person P = Get(id);
        em.remove(P);
        return P;
    }

    public Person Get(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Person out = em.find(Person.class, id);
        if(out == null) throw new Exception("no Person found");
        return out;
    }

    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Person.getAll").getResultList();
    }

    public Person editPerson(Person p) throws Exception {
        EntityManager em = emf.createEntityManager();
        Person OP = Get(p.getId());
        OP = p;
        try {
            em.getTransaction().begin();
            em.persist(OP);
            em.getTransaction().commit();
        } finally {
            em.close();
            return OP;
        }
        
    }

}
