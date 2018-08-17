package models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.*;
import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class Tag extends Model {
	@Column
	private Integer tagID;
	
	@Column
	private String tagName;
	
	public Tag(Integer tagID, String tagName) {
		this.tagID = tagID;
		this.tagName = tagName;
	}
	
	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}
	public Integer getTagID() {
		return tagID;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagName() {
		return tagName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getTagID(), this.getTagName());
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(!(other instanceof Tag)) 
			return false;
		
		Tag otherTag  = (Tag) other;
		
		return otherTag.getId().equals(this.getTagID()) && otherTag.getTagName().equalsIgnoreCase(this.tagName);
	}
	
	@Override
	public <T extends JPABase> T save() {
		Tag tag = this.find("byTagName", this.tagName).first();

		if (tag == null) {
			super.save();
		}

		return (T) tag;
	}
}
