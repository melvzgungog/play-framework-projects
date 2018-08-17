package helpers;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import models.Contact;

@Root(name = "people", strict = false)
public class People {
	
	@ElementList(inline = true, required = false)
	private List<Person> personList;
	
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	public List<Person> getPersonList() {
		return personList;
	}
	
	public List<Contact> toContactList() {
		
		List<Contact> contacts = new ArrayList<>();
		
		if(this.personList != null) {
			for(Person person : this.personList) {
				contacts.add(person.toContactModel());
			}
		}
		
		return contacts;
	}
	
}
