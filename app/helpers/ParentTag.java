package helpers;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import models.Tag;

@Root(name = "tag", strict = false)
public class ParentTag {
	
	@Element
	Integer id;
	
	@Element
	String name;
	
	public void setID(Integer ID) {
		this.id = ID;
	}
	public Integer getID() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public Tag toTagModel() {
		Tag tag = new Tag(this.id, this.name);
		
		return tag;
	}
}
