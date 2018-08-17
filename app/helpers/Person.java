package helpers;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import models.Contact;

@Root(name = "person", strict = false)
public class Person {
	
	@Element(name = "first-name")
	private String firstName;
	
	@Element(name = "last-name")
	private String lastName;
	
	@Element(name = "tags")
	private ChildTag tags;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setTags(ChildTag tags) {
		this.tags = tags;
	}
	public ChildTag getTags() {
		return tags;
	}
	
	public Contact toContactModel() {
		Contact contact = new Contact(this.firstName + ' ' + this.lastName, this.tags.toTagModelList());
		
		return contact;
	}
}
