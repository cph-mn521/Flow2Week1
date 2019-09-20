/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package R;

import F.Facade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("P")
public class Person {

    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    private Facade F = Facade.Get(emf);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Person
     */
    public Person() {
    }

    /**
     * Retrieves representation of an instance of R.Person
     *
     * @return an instance of java.lang.String
     */
    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") long id) {
        try{
            return GSON.toJson(F.Get(id));
        }catch (Exception e){
            return GSON.toJson(new dto.Error(e.getMessage(),404));
        }
        
    }

    @Path("C")
    @POST
    @Consumes("application/json")
    public String createProductInJSON(String product) {
        PersonDTO PD = GSON.fromJson(product, PersonDTO.class);
        E.Person P = new E.Person(PD);
        return product;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String all() {
        List<E.Person> p = F.getAllPersons();
        List<PersonDTO> out = new ArrayList();

        for (E.Person person : p) {
            out.add(new PersonDTO(person));
        }
        return GSON.toJson(out);
    }

    /**
     * PUT method for updating or creating an instance of Person
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
