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
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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

    @GET
    @Path("/getAllJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersonsInJSON() {
        return personDao.getAllPersons();
    }

    @GET
    @Path("/getAllXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllPersonsInXML() {
        return personDao.getAllPersons();
    }

    
    @POST
    @Path("/add")
   @Produces(MediaType.APPLICATION_JSON)
        public String saveNewPerson(JSONObject person) throws JSONException {  
        
        JSONObject obj = new JSONObject();



        obj.put("fullName",new String());
        obj.put("age",new String());
        obj.put("city",new String());
        obj.put("gender",new String());
        
        
      
        person.put("person", obj);
        obj.toString();

        if (!personDao.savePerson(null)) {
            return "Person create success   id=";
                    
        }
        else {
            return "error, check information for new person";
        }
    }
    
//    @GET
//    @Path("/add/{fullName}/{age}/{city}/{gender}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String saveNewPerson(@PathParam("fullName") String fullName, @PathParam("age") int age, @PathParam("city") String city, @PathParam("gender") String gender) {
//        Person person = new Person();
//
//        person.setFullName(fullName);
//        person.setAge(age);
//        person.setCity(city);
//        person.setGender(gender);
//
//        if (!personDao.savePerson(person)) {
//            return "Person create success   id=" + person.getId();
//        } else {
//            return "error, check information for new person";
//        }
//    }

    @GET
    @Path("/update/{id}/{fullName}/{age}/{city}/{gender}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePerson(@PathParam("id") int id, @PathParam("fullName") String fullName, @PathParam("age") int age, @PathParam("city") String city, @PathParam("gender") String gender) {
        Person person = new Person();
        person.setId(id);
        person.setFullName(fullName);
        person.setAge(age);
        person.setCity(city);
        person.setGender(gender);

        if (!personDao.savePerson(person)) {
            return "Person update success";
        } else {
            return "error, check new information about person";
        }

    }

    @GET
    @Path("/getByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id") int id) {
        return personDao.getById(id);
    }

    @GET
    @Path("/getByCityXML/{city}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getByCityXML(@PathParam("city") String city) {
        return personDao.getByCity(city);
    }

    @GET
    @Path("/getByGenderXML/{gender}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getByGenderXML(@PathParam("gender") String gender) {
        return personDao.getByGender(gender);
    }

    @GET
    @Path("/getByIdJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJSON(@PathParam("id") int id) {
        return personDao.getById(id);
    }

    @GET
    @Path("/getByCityJSON/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getByCityJSON(@PathParam("city") String city) {
        return personDao.getByCity(city);
    }

    @GET
    @Path("/getByGenderJSON/{gender}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getByGenderJSON(@PathParam("gender") String gender) {
        return personDao.getByGender(gender);
    }

    @GET
    @Path("sort/ageAscJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getSortAgeAsc() {
        return personDao.getAsc();
    }

    @GET
    @Path("sort/idDescJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getSortIdDesc() {
        return personDao.getDesc();
    }

    @GET
    @Path("sort/ageAscXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getSortAgeAscXML() {
        return personDao.getAsc();
    }

    @GET
    @Path("sort/idDescXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getSortIdDescXML() {
        return personDao.getDesc();
    }

    @DELETE
    @Path("/del/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") int id) {
        Person person = new Person();
        person.setId(id);

        if (!personDao.deleteById(person)) {
            return "Person delete success";
        } else {
            return "Delete Error, check id of person";
        }

    }

    @GET
    @Path("/getPageJSON/{page}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPaging(@PathParam("page") int page, @PathParam("size") int size) {
        return personDao.getPaging(page, size);
    }
    @GET
    @Path("/getPageXML/{page}/{size}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getPagingXML(@PathParam("page") int page, @PathParam("size") int size) {
        return personDao.getPaging(page, size);
    }
    
    
    @GET
    @Path("/getHeadersJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHeaders() {
        return personDao.getHeaders();
    }
    
    @GET
    @Path("/getHeadersXML")
    @Produces(MediaType.APPLICATION_XML)
    public String getHeadersXML() {
        return personDao.getHeaders();
    }

    @GET
    @Path("/getOperationsJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public Class<? extends PersonDao> getOperationsJSON() {
        return personDao.getClass();
    }

}
