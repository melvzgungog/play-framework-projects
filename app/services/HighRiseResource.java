package services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class HighRiseResource {
	
	private static String TOKEN = "a5704a24df46b77da6bc1cc0486b781a";
	private static String PASSWORD = "X";
	private static HighRiseResource singleInstance;
	public static String URL = "https://cebuinstituteoftechnologyuniversity1.highrisehq.com/";
	
	public static String TAGS_RESOURCE = URL + "tags.xml";
	private static String TAG_ID = "?tag_id=";
	
	private static String PEOPLE_RESOURCE = URL + "people.xml";
	public static String PEOPLE_BY_TAG_RESOURCE = PEOPLE_RESOURCE + TAG_ID;
	
	private static String COMPANIES_RESOURCE = URL + "companies.xml";
	public static String COMPANIES_BY_TAG_RESOURCE = COMPANIES_RESOURCE + TAG_ID;
	
	private Client client;
	
	private HighRiseResource() {
		this.client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(TOKEN,PASSWORD));
	}
	public static HighRiseResource instance() {
		if(singleInstance != null) {
			return singleInstance;
		}
		return singleInstance = new HighRiseResource();
	}
	public String getResource(String resource) {
		return client.resource(resource).accept("application/xml").get(ClientResponse.class).getEntity(String.class);
	}
	
}
