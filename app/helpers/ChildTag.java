package helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import models.Tag;

@Root(name = "tags", strict = false)
public class ChildTag {
	
	@ElementList(inline = true)
	private List<ParentTag> tags;
	
	public void setTagList(List<ParentTag> tags) {
		this.tags = tags;
	}
	public List<ParentTag> getTagList() {
		return tags;
	}
	public ParentTag getTagByName(String tagName) {
		for(ParentTag pt : this.tags) {
			if(pt.getName().equalsIgnoreCase(tagName)) {
				return pt;
			}
		}
		return null;
	}
	
	public Set<Tag> toTagModelList() {
		
		Set<Tag> tags = new HashSet();
		
		for (ParentTag pt : this.tags) {
			tags.add(pt.toTagModel());
		}
		
		return tags;	
	}
	
	
}
