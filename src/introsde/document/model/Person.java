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
	
	@NamedQuery(name="Person.getPersonByUser",
		query="SELECT a FROM Person a WHERE a.userId=:Person"),
	
	@NamedQuery(name="Person.findAll",
		query="SELECT p FROM Person p")
	
})

@Entity(name="Person")
@Table(name="\"Person\"")
public class Person {
    
	@Id 
	@GeneratedValue( strategy=GenerationType.AUTO )
    @Column(name="\"userId\"")
    private String userId;
    
    @Column(name="\"name\"")
    private String name;
    
    public Person() {
    	
    }
    
    public Person(String userId) {
    	this.setUserId(userId);
    }
    
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}