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
	
	@NamedQuery(name="Evaluation.findAll",
			query="SELECT p FROM Evaluation p"),
	
	@NamedQuery(name="Evaluation.getEvaluationsByUserId",
		query="SELECT a FROM Evaluation a WHERE a.userId=:Person"),
	
})
@Entity(name="Evaluation")
@Table(name="\"Evaluation\"")
@IdClass(Primary.class)
public class Evaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"userId\"", referencedColumnName="\"userId\"")
	private Person userId;
    
    @Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"artistId\"", referencedColumnName="\"artistId\"")
    private Artist artistId;
    
    @Column(name="\"rate\"")
    private Integer rate;
    
	
    public void setRate(Integer rate) {
    	this.rate = rate;
    }
    
    public Integer getRate() {
    	return this.rate;
    }
    
    public Person getUserId() {
    	return this.userId;
    }
    
    public void setUserId(Person userId) {
    	this.userId = userId;
    }
    
    public Artist getArtistId() {
    	return this.artistId;
    }
    
    public void setArtistId(Artist artistId) {
    	this.artistId = artistId;
    }

}