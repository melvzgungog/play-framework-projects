package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class Contact extends Model {
	@Column
	private String name;
	
	@Column
	private boolean isCompany;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "contact_tag", joinColumns = @JoinColumn(name = "contact_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	
	private Set<Tag> tags = new HashSet<>();
	
	public Contact(String name, boolean isCompany, Set<Tag> tags) {
		this.name = name;
		this.isCompany = isCompany;
		this.tags = tags;
	}
	public Contact(String name, Set<Tag> tags) {
		this.name = name;
		this.tags = tags;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setIsCompany(boolean isCompany) {
		this.isCompany = isCompany;
	}
	public boolean getIsCompany() {
		return isCompany;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}
	public void addTags(Set<Tag> tags) {
		for(Tag tag : tags) {
			this.addTag(tag);
		}
	}
	
	public void saveNoValidate() {
		super.save();
	}
	
	@Override
	public <T extends JPABase> T save() {
		Contact contact = this.find("byName", this.name).first();
		if (contact == null) {
			return super.save();
		}

		contact.addTags(tags);
		contact.saveNoValidate();

		return (T) contact;
	}
}
