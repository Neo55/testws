/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testws.services;

import com.mycompany.testws.dao.PersonDao;
import com.mycompany.testws.model.Person;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author FARHAD
 */
@Path("service")
public class Service {
//    
//    //simulate db 
//    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();
//    
//    //insert test data in db
//    
//    static {
//    for (int i = 0;i<10;i++){
//    Person person = new Person();
//    int id = i+1;
//    person.setId(id);
//    person.setFullName("My wonderful Person " + id);
//    person.setAge(new Random().nextInt(40) + 1);
//    
//    persons.put(id, person);
//    
//    }
//    }

    private PersonDao personDao = new PersonDao();

    //method which return a single person in xml
    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id") int id) {
        return personDao.getPersonById(id);
    }

    //return in JSON
    @GET
    @Path("/getPersonByIdJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("id") int id) {
        return personDao.getPersonById(id);
    }

    //method return list all person
    @GET
    @Path("/getAllJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersonsInJSONroo() {
        return personDao.getAllPersons();
    }
    
       @GET
    @Path("sort/ageAsc")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getSortAgeAsc() {
        return personDao.getAsc();
    }
    
       @GET
    @Path("sort/idDesc")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getSortIdDesc() {
        return personDao.getDesc();
    }
    
     @GET
    @Path("/getHeaders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getHeaders() {
        return personDao.getHeaders();
    }

    //insert
    @GET
    @Path("/insertPerson/{fullName}/{age}")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveNewPerson(@PathParam("fullName") String fullName, @PathParam("age") int age) {
        Person person = new Person();

        person.setFullName(fullName);
        person.setAge(age);

        if (!personDao.savePerson(person)) {
            return "{\"status\":\"ok\"}  id=" + person.getId();
        } else {
            return "{\"status\":\"not ok\"}";
        }

    }
    //update

    @GET
    @Path("/insertPerson/{id}/{fullName}/{age}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePerson(@PathParam("id") int id, @PathParam("fullName") String fullName, @PathParam("age") int age) {
        Person person = new Person();
        person.setId(id);
        person.setFullName(fullName);
        person.setAge(age);

        if (!personDao.savePerson(person)) {
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"not ok\"}";
        }

    }
    
        
    @DELETE
    @Path("/del/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") int id) {
        Person person = new Person();
        person.setId(id);

        if (!personDao.deleteById(person)){
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"not ok\"}";
        }

    }

    @GET
    @Path("/getPage/{page}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPaging(@PathParam("page") int page, @PathParam("size") int size) {
        // return personDao.getAbsentDetails(page, size) + "\"%n\"";
        return personDao.getPaging(page, size);
    }

}
