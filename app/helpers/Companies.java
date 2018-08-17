package helpers;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import models.Contact;

@Root(name = "companies", strict = false)
public class Companies {
	
	@ElementList(inline = true, required = false)
	private List<Company> companyList;
	
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
	public List<Company> getCompanyList() {
		return companyList;
	}
	
	public List<Contact> toContactList() {

		List<Contact> contacts = new ArrayList<>();

		if (this.companyList != null) {
			for (Company company : this.companyList) {
				contacts.add(company.toContactModel());
			}
		}

		return contacts;
	}
}
