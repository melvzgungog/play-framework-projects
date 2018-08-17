package controllers;

import play.*;
import play.data.validation.Required;
import play.db.jpa.JPA;
import play.mvc.*;
import services.HighRiseResource;
import services.ResourceSerializer;

import java.util.*;

import javax.persistence.Query;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import helpers.Companies;
import helpers.Company;
import helpers.People;
import helpers.ParentTag;
import helpers.ChildTag;
import models.*;
import models.Contact;
public class Application extends Controller {

	public static void index() {
		render("Contact/index.html");
	}

	/**
	 *  Pulls resource from HighRise API then converts to JPA entity > save to database.
	 * */
	public static void searchContactBytag(@Required String tagName) {
	   if(validation.hasErrors()) {
		   render("Contact/index.html");
        }
		
		String tags = HighRiseResource.instance().getResource(HighRiseResource.TAGS_RESOURCE);
		ParentTag tagDto = ResourceSerializer.instance().read(ChildTag.class, tags).getTagByName(tagName);

		if (tagDto == null) {
			boolean noTagFound = true;
			render("Contact/index.html", noTagFound, tagName);
			return;
		}

		String people = HighRiseResource.instance()
				.getResource(HighRiseResource.PEOPLE_BY_TAG_RESOURCE + tagDto.getID());
		String companies = HighRiseResource.instance()
				.getResource(HighRiseResource.COMPANIES_BY_TAG_RESOURCE + tagDto.getID());
		System.out.println(companies);
		People peopleDto = ResourceSerializer.instance().read(People.class, people);
		Companies companiesDto = ResourceSerializer.instance().read(Companies.class, companies);
		List<Contact> contacts = new ArrayList<>();
		contacts.addAll(peopleDto.toContactList());
		contacts.addAll(companiesDto.toContactList());

		for (Contact contact : contacts) {
			contact.save();
		}
		
		String q = "SELECT c.* FROM contact c left join contact_tag ct on ct.contact_id = c.id "
				+ " left join tag t on t.id = ct.tag_id where t.tagid = :tagId";
		Tag tag = Tag.find("byTagId", tagDto.getID()).first();
		
	    Query query = JPA.em().createNativeQuery(q, Contact.class).setParameter("tagId", tag.getTagID());
	    
	    contacts = query.getResultList(); // Return the contacts that are saved in the database.
	    
	    render("Contact/index.html", contacts);
	}

}