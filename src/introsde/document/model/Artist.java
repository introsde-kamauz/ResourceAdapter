package introsde.document.model;

//import introsde.document.dao.*;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Artist implements Serializable {
	
    private String id;
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