package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import models.Tag;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class Contact extends Controller {
	public static void index() {
		render();
	}
	
	public static void showList() {
		Set<Tag> tags = new HashSet<>();
		tags.addAll((Collection) Tag.findAll());
		List<models.Contact> contacts = new ArrayList<>();
		contacts.addAll((List)  models.Contact.findAll());
		render(tags, contacts);
	}
	public static void filterContactsByTag(int tagName) {

		List<models.Contact> contacts = new ArrayList<>();
		Set<Tag> tags = new HashSet<>();
		tags.addAll((Collection) Tag.findAll());

		if (tagName == -1) {
			System.out.println("Hello " + tagName);
			contacts.addAll((List) models.Contact.findAll());
			render("Contact/showList.html", contacts, tags);
			return;
		}

		String q = "SELECT c.* FROM contact c left join contact_tag ct on ct.contact_id = c.id "
				+ " left join tag t on t.id = ct.tag_id where t.tagid = :tagID";

		Query query = JPA.em().createNativeQuery(q, models.Contact.class).setParameter("tagID", tagName);

		contacts = query.getResultList(); // Return the contacts that are saved in the database.

		Tag selectedTag = Tag.find("byTagId", tagName).first();
		
		render("Contact/showList.html", contacts, tags, selectedTag);
	}
	
}
