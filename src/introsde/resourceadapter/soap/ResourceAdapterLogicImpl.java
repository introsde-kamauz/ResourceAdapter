package introsde.resourceadapter.soap;

import introsde.document.dao.EvaluationDao;
import introsde.document.dao.PersonDao;
import introsde.document.dao.PreferenceDao;
import introsde.document.model.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

//Service Implementation

@WebService(endpointInterface = "introsde.resourceadapter.soap.ResourceAdapterLogic", serviceName="ResourceService")
public class ResourceAdapterLogicImpl implements ResourceAdapterLogic {
	
	String SONG_KICK_API_KEY = "b3qfvyih0aG8ZjeB";

	@Override
	public void addArtist(Preference p) {
		
		try {
			System.out.println("Resource adapter -> addArtist");
			PreferenceDao.add(p);
		} catch(Exception err) {
			err.printStackTrace();
		}
	}

	@Override
	public void removeArtist(Preference p) {
		try {
			PreferenceDao.remove(p);
		} catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	@Override
	public Integer getArtistExternalId(String artist) {
		// http://api.songkick.com/api/3.0/search/artists.json?apikey={your_api_key}&query={artist_name}
		URLEncoder encoder = null;
		JsonNode node;
		String URL = "http://api.songkick.com/api/3.0/search/artists.json?apikey="+SONG_KICK_API_KEY+"&query="+encoder.encode(artist);
		
		try {
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(Artist.class);
			Client client = ClientBuilder.newClient(clientConfig);
			
			WebTarget webTarget = client.target(URL);
			Builder request = webTarget.request();
			
			Response response = request.get();
			Assert.assertTrue(response.getStatus() == 200);
			
			String json = response.readEntity(String.class);
			System.out.println(json);
			ObjectMapper mapper = new ObjectMapper();
		
			node = mapper.readTree(json);
			System.out.println(node.asText());
			return Integer.parseInt(node.findValue("id").toString());
			
		} catch (Exception er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Artist> getRecommendedSimilarArtists(String id) {
		
		List<Artist> artList = new ArrayList<Artist>();
		try {
			String URL = "http://api.songkick.com/api/3.0/artists/"+id+"/similar_artists.json?apikey="+SONG_KICK_API_KEY;
			
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(Artist.class);
			Client client = ClientBuilder.newClient(clientConfig);
			
			WebTarget webTarget = client.target(URL);
			Builder request = webTarget.request();
			
			Response response = request.get();
			Assert.assertTrue(response.getStatus() == 200);
			
			String json = response.readEntity(String.class);
			Artist m = new Artist();
			JsonNode node;
			ObjectMapper mapper = new ObjectMapper();
			
			node = mapper.readTree(json);
			ArrayNode arrNode = (ArrayNode) node.get("resultsPage").get("results").get("artist");
			if (arrNode == null) {
				return artList;
			}
			Iterator<JsonNode> iter = arrNode.elements();
			while (iter.hasNext()) {
				
				JsonNode readNode = iter.next();
				Artist a = new Artist();
				a.setName(readNode.get("displayName").toString());
				a.setId(readNode.get("id").toString());
				artList.add(a);
			}
			
			return artList;
			
		} catch (Exception er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Event> recommendEvents(String id) {
		
		List<Event> evenList = new ArrayList<Event>();
		try {
			String URL = "http://api.songkick.com/api/3.0/artists/"+id+"/calendar.json?apikey="+SONG_KICK_API_KEY;
			
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(Artist.class);
			Client client = ClientBuilder.newClient(clientConfig);
			
			WebTarget webTarget = client.target(URL);
			Builder request = webTarget.request();
			
			Response response = request.get();
			Assert.assertTrue(response.getStatus() == 200);
			
			String json = response.readEntity(String.class);
			Artist m = new Artist();
			JsonNode node;
			ObjectMapper mapper = new ObjectMapper();
			
			node = mapper.readTree(json);
			ArrayNode arrNode = (ArrayNode) node.get("resultsPage").get("results").get("event");
			if (arrNode == null) {
				return evenList;
			}
			Iterator<JsonNode> iter = arrNode.elements();
			while (iter.hasNext()) {
				
				JsonNode readNode = iter.next();
				Event a = new Event();
				a.setType(readNode.get("type").toString());
				a.setCity(readNode.get("location").get("city").toString());
				a.setLatitude(readNode.get("location").get("lat").asDouble());
				a.setLongitude(readNode.get("location").get("lng").asDouble());
				a.setStartdate(readNode.get("start").get("date").toString());
				a.setName(readNode.get("displayName").toString());
				a.setVenue(readNode.get("venue").get("displayName").toString());
				evenList.add(a);
			}
			
			return evenList;
		} catch (Exception er) {
			er.printStackTrace();
		}
		
		return evenList;
			
	}
	
	@Override
	public String getMotivation() {
		// TODO Auto-generated method stub
		// http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1
		String quote="";
		try {
			String URL = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1";
			
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(Artist.class);
			Client client = ClientBuilder.newClient(clientConfig);
			
			WebTarget webTarget = client.target(URL);
			Builder request = webTarget.request();
			
			Response response = request.get();
			Assert.assertTrue(response.getStatus() == 200);
			
			String json = response.readEntity(String.class);
			Artist m = new Artist();
			JsonNode node;
			ObjectMapper mapper = new ObjectMapper();
			
			node = mapper.readTree(json);
			ArrayNode arrNode = (ArrayNode) node;
			if (arrNode == null) {
				return quote;
			}
			Iterator<JsonNode> iter = arrNode.elements();
			JsonNode readNode = iter.next();
			Event a = new Event();
			quote = readNode.get("content").toString();
			
			return quote;
		} catch (Exception er) {
			er.printStackTrace();
		}
		
		return quote;
	}

	@Override
	public List<Preference> getProfilePreferences(String p) {
		
		try {
			System.out.println("getProfilePreferences");
			return PreferenceDao.getPreferencesByUser(p);
		} catch (Exception err) {
			return new ArrayList<Preference>();
		}
	}
	
	@Override
	public String getArtistNameFromExternalId(String artist) {
		// http://api.songkick.com/api/3.0/search/artists.json?apikey={your_api_key}&query={artist_name}
		URLEncoder encoder = null;
		JsonNode node;
		try {
			String URL = "http://api.songkick.com/api/3.0/search/artists.json?apikey="+SONG_KICK_API_KEY+"&query="+encoder.encode(artist);
			
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(Artist.class);
			Client client = ClientBuilder.newClient(clientConfig);
			
			WebTarget webTarget = client.target(URL);
			Builder request = webTarget.request();
			
			Response response = request.get();
			Assert.assertTrue(response.getStatus() == 200);
			
			String json = response.readEntity(String.class);
			System.out.println(json);
			ObjectMapper mapper = new ObjectMapper();
		
			node = mapper.readTree(json);
			System.out.println(node.asText());
			return String.format("%s",node.findValue("name").toString());
			
		} catch (Exception er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void evaluateArtistRecommendation(String artistId, String artistName, String id, Integer rate) {
		try {
			Evaluation e = new Evaluation();
			e.setArtistId(artistId);
			e.setUserId(id);
			e.setRate(rate);
			e.setArtistName(artistName);
			EvaluationDao.add(e);
		} catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	@Override
	public List<Evaluation> getEvaluationsByUser(String id) {
		return EvaluationDao.getEvaluationsById(id);
	}
	
	@Override
	public Person getPersonByUser(String id) {
		try {
			return PersonDao.getPersonById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void register(String name) {
		try {
			Person p = new Person();
			p.setName(name);
			PersonDao.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Person> getAllUsers() {
		try {
			return PersonDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Person>();
	}
}