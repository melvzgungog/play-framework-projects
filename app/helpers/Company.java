package helpers;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import models.Contact;

@Root(name = "company", strict = false)
public class Company {
	
	@Element(name = "name")
	private String name;
	
	@Element(name = "tags")
	private ChildTag tags;
	
	public void setCompanyName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return name;
	}
	
	public void setTags(ChildTag tags) {
		this.tags = tags;
	}
	public ChildTag getTags() {
		return tags;
	}
	
	public Contact toContactModel() {
		Contact contact = new Contact(this.name, true, this.tags.toTagModelList());
		
		return contact;
	}
	
}
