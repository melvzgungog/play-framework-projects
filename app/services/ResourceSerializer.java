package services;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class ResourceSerializer {
	private static Serializer SERIALIZER = new Persister();
	
	public static ResourceSerializer instance() {
		return new ResourceSerializer();
	}
	public <T> T read (Class <T> c, String xml) {
		try {
			return SERIALIZER.read(c, xml);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
