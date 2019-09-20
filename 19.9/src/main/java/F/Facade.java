/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package F;

import Entity.Semester;
import Entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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

    public List<Student> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Student.findAll").getResultList();
    }

    public List<Student> getStudentByfName(String Name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> tq = em.createNamedQuery("Student.findByFirstname", Student.class);
        tq.setParameter("firstname", "%" + Name + "%");
        return tq.getResultList();
    }

    public Student CreateStudent(Student S) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(S);
            em.getTransaction().commit();
        } finally {
            em.close();
            return S;
        }
    }

    public Student addToSemester(Student S, long semID) {
        EntityManager em = emf.createEntityManager();
        Student SS = em.find(Student.class, S.getId());
        try {
            Semester Sem = em.find(Semester.class, semID);
            if (Sem != null) {
                SS.setSemester(Sem);
            }
            em.getTransaction().begin();
            em.persist(SS);
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
            return SS;
        }
    }

    public List<Student> getStudentBylName(String Name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> tq = em.createNamedQuery("Student.findByLastname", Student.class);
        tq.setParameter("lastname", "%" + Name + "%");
        return tq.getResultList();
    }

    private static long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(m) FROM Student m").getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
