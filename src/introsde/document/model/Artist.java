package introsde.document.model;

//import introsde.document.dao.*;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@NamedQueries({
	
	@NamedQuery(name="Artist.getArtistNameById",
		query="SELECT a FROM Artist a WHERE a.id=:Artist"),
	
	@NamedQuery(name="Artist.findAll",
		query="SELECT p FROM Artist p")
	
})

@Entity(name="Artist")
@Table(name="\"Artist\"")
public class Artist implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue( strategy=GenerationType.AUTO )
	@OneToMany(mappedBy="artist")
	@Column(name="\"artistId\"")
    private String id;
	@Column(name="\"artistName\"")
	private String name;

	public Artist(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Artist() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}	
	
	public void setId(String id) {
		this.id = id;
	}	
		
}