package introsde.document.model;

//import introsde.document.dao.*;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
  // indicates that this class is an entity to persist in DB
@XmlRootElement
@NamedQueries({
	
	@NamedQuery(name="Preference.getPreferencesByUserIdAndArtistId",
		query="SELECT a FROM Preference a WHERE a.userId=:Person AND a.artistId=:idArtist"),
	
	@NamedQuery(name="Preference.getPreferencesByUser",
		query="SELECT a FROM Preference a WHERE a.userId=:Person"),
	
	@NamedQuery(name="Preference.findAll",
		query="SELECT p FROM Preference p")
	
})

@Entity(name="Preference")
@Table(name="\"Preference\"")
@IdClass(Primary.class)
public class Preference {
    
    @Id
    @Column(name="\"userId\"")
    private String userId;
    @Id
    @Column(name="\"artistId\"")
    private String artistId;
    
    @Column(name="\"artistName\"")
    private String artistName;
    
    public Preference() {
    	
    }
    
    public Preference(String userId, String artistId) {
    	this.setUserId(userId);
    	this.artistId = artistId;
    }
    
    public String getArtistId() {
    	return this.artistId;
    }
    
    public void setArtistId(String id) {
    	this.artistId = id;
    }
    
    public String getArtistName() {
    	return this.artistName;
    }
    
    public void setArtistName(String name) {
    	this.artistName = name;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}