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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"userId\"", referencedColumnName="\"userId\"")
	private Person userId;
    
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"artistId\"", referencedColumnName="\"artistId\"")
    private Artist artistId;
    
    public Preference() {
    	
    }
    
    public Preference(Person userId, Artist artistId) {
    	this.setUserId(userId);
    	this.artistId = artistId;
    }
    
    public Artist getArtistId() {
    	return this.artistId;
    }
    
    public void setArtistId(Artist id) {
    	this.artistId = id;
    }

	public Person getUserId() {
		return userId;
	}

	public void setUserId(Person userId) {
		this.userId = userId;
	}

}