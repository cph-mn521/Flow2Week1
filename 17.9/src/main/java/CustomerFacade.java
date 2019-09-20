
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import E.Customer;
import E.adress;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class CustomerFacade {
        private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    
        public void populate() {
        EntityManager em = emf.createEntityManager();
        try {
            adress adr = new adress("gade", "by");
            adress adr2 = new adress("gade2", "by2");
            
            em.getTransaction().begin();
            em.persist(adr);
            em.persist(adr2);
            Customer CC = new Customer("stig","stig");
            CC.addPhone("123", "1");
            CC.addHobby("reee");
            CC.setAdrs(adr);
            em.persist(CC);
            Customer c = new Customer("stig","stig");
            c.addHobby("something");
            c.addHobby("noget andet.");
            c.addPhone("1", "2");
            c.addPhone("2", "3");
            c.setAdrs(adr2);
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
