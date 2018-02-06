package introsde.resourceadapter.soap;
import introsde.document.model.*;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface ResourceAdapterLogic {
	
	// 1  OK
	@WebMethod(operationName="addArtist")
    @WebResult(name="addArtist") 
	public void addArtist(Preference p);
	
	// 2  OK
	@WebMethod(operationName="removeArtist")
	@WebResult(name="removeArtist") 
	public void removeArtist(Preference p);
	
	// 3  OK
	@WebMethod(operationName="getRecommendedSimilarArtists")
	@WebResult(name="recommendedSimilarArtistList") 
	public List<Artist> getRecommendedSimilarArtists(String id);
	
	// 4  OK
	@WebMethod(operationName="recommendEvents")
	@WebResult(name="recommendEventsList") 
	public List<Event> recommendEvents(String id);
	
	// 5
	@WebMethod(operationName="getMotivation")
	@WebResult(name="getMotivation") 
	public String getMotivation();
	
	// 6  OK
	@WebMethod(operationName="getProfilePreferences")
	@WebResult(name="getProfilePreferences") 
	public List<Preference> getProfilePreferences(String p);
	
	// 8  OK
	@WebMethod(operationName="getArtistExternalId")
	@WebResult(name="getArtistExternalId")
	public Integer getArtistExternalId(String artist);
	
	// 9  
	@WebMethod(operationName="getArtistNameFromExternalId")
	@WebResult(name="getArtistNameFromExternalId")
	public String getArtistNameFromExternalId(String artist);
	
	// 10
	@WebMethod(operationName="evaluateArtistRecommendation")
	@WebResult(name="evaluateArtistRecommendation")
	public void evaluateArtistRecommendation(String artistId, String artistName, String id, Integer rate);

	// 11
	@WebMethod(operationName="getEvaluationsByUser")
	@WebResult(name="getEvaluationsByUser")
	public List<Evaluation> getEvaluationsByUser(String id);
	
	// 12
	@WebMethod(operationName="getPersonByUser")
	@WebResult(name="getPersonByUser")
	public Person getPersonByUser(String id);
	
	// 13
	@WebMethod(operationName="getArtistById")
	@WebResult(name="getArtistById")
	public Artist getArtistById(String id);
	
	// 14
	@WebMethod(operationName="register")
	@WebResult(name="register")
	public void register(String name);
	
	// 15
	@WebMethod(operationName="getAllUsers")
	@WebResult(name="getAllUsers")
	public List<Person> getAllUsers();
}