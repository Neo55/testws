/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testws.dao;

import com.mycompany.testws.model.Person;
import com.mycompany.testws.util.HibernateUtil;
import static com.mycompany.testws.util.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jgroups.tests.perf.Test;

/**
 *
 * @author FARHAD
 */
public class PersonDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Person getPersonById(int id) {
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
    
       public List<Person> getHeaders() {
        List<Person> persons = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            persons = session.createQuery("select * from COLUMNS WHERE table_name='person'").list();
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

    public String deleteById(int id) {
        Person person = null;
        Session session = null;

        String hql = "delete from Person where id = :ID";
        Query query = session.createQuery(hql);
        query.setParameter("ID", id);
        int rowCount = query.executeUpdate();

        return "sssss";

//            
//            session = sessionFactory.openSession();
//            session.beginTransaction();
//            person = (Person) session.createQuery("delete from Person where id = :ID").setParameter("ID", id).uniqueResult();
//            session.getTransaction().commit();
    }
}
