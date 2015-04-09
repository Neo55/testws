/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testws.dao;

import com.mycompany.testws.model.Person;
import com.mycompany.testws.util.HibernateUtil;
import static com.mycompany.testws.util.HibernateUtil.getSessionFactory;
import java.util.Iterator;
import java.util.List;
import static javassist.CtMethod.ConstParameter.string;
import javax.mail.FetchProfile.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.jgroups.tests.perf.Test;

/**
 *
 * @author FARHAD
 */
public class PersonDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Person> getAllPersons() {
        List<Person> persons = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            persons = session.createQuery("from Person p").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return persons;
    }

    public boolean savePerson(Person person) {
        Session session = null;
        boolean hasErrors = false;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(person);
            session.getTransaction().commit();

        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            hasErrors = true;

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return hasErrors;
    }

    public Person getById(int id) {
        Person person = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            person = (Person) session.createQuery("from Person p where p.id = :ID").setParameter("ID", id).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return person;
    }
    
    public Person getByCity(String city) {
        Person person = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            person = (Person) session.createQuery("from Person p where p.city = :CITY").setParameter("CITY", city).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return person;
    }
    
    public Person getByGender(String gender) {
        Person person = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            person = (Person) session.createQuery("from Person p where p.gender = :GENDER").setParameter("GENDER", gender).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return person;
    }

    
     public List<Person> getAsc() {
        List<Person> persons = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            persons = session.createQuery("from Person p order by age ASC").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return persons;
    }

    public List<Person> getDesc() {
        List<Person> persons = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            persons = session.createQuery("from Person p order by id DESC").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return persons;
    }

    public boolean deleteById(Person person) {
        Session session = null;
        boolean hasErrors = false;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();

        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            hasErrors = true;

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return hasErrors;
    }
    
    
    
    
    public List<Person> getPaging(int Page, int Size) {
        List<Person> persons = null;
        Session session = null;
        int pageSize = Size;
        int pageNumber = Page;

        session = sessionFactory.openSession();
        session.beginTransaction();
        String countQ = "Select count (p.id) from Person p";

        Query countQuery = session.createQuery(countQ);

        Long countResults = (Long) countQuery.uniqueResult();
        Query selectQuery = session.createQuery("from Person p");
        selectQuery.setFirstResult((pageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);

        return selectQuery.list();

    }

//return selectQuery.list();
//session.getTransaction().commit();
    //    persons;
   
    public String getHeaders() {
        String z = null;
        String w = null;
        String[] e = null;

       ClassMetadata classMetadata = sessionFactory.getClassMetadata(Person.class);
        z = classMetadata.getEntityName();
        e = classMetadata.getPropertyNames(); 
        return z;
    }

       public ClassMetadata getOperations() {
        ClassMetadata z = null;
        String w = null;
        String[] e = null;
        Session session = null;

        
       ClassMetadata classMetadata = sessionFactory.getClassMetadata(Person.class);
        z = sessionFactory.getClassMetadata(Person.class);
        e = classMetadata.getPropertyNames(); 
     
        return z;

         
    }
    
}
