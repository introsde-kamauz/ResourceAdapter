package introsde.document.dao;
import introsde.document.model.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@NamedQueries({
	
	@NamedQuery(name="Artist.getArtistNameById",
		query="SELECT a FROM Artist a WHERE a.id=:Artist"),
	
	@NamedQuery(name="Artist.findAll",
		query="SELECT p FROM Artist p")
	
})

public enum ArtistDao {
	instance;
	private EntityManagerFactory emf;
	
	private ArtistDao() {
		if (emf!=null) {
			emf.close();
		}
		emf = Persistence.createEntityManagerFactory("introsde-jpa");
	}
	
	public EntityManager createEntityManager() {
		try {
			return emf.createEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;    
	}

	public void closeConnections(EntityManager em) {
		em.close();
	}

	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	public static void add(Artist p) {
		try {
			EntityManager em = ArtistDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();        
	        em.persist(p);
	        tx.commit();
	        ArtistDao.instance.closeConnections(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void remove(Artist p) {
	    	
		try {
	        EntityManager em = ArtistDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        p=em.merge(p); //
	        em.remove(p);
	        tx.commit();
	        ArtistDao.instance.closeConnections(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Person related operations could also directly go into the "Person" Model
	// Check Methods in LifeStaus as example
	public static Artist getArtistById(String uid) {
		try {
			EntityManager em = instance.createEntityManager();
			
			Artist list = em.find(Artist.class, uid);
		    		
		    instance.closeConnections(em);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Artist> getAll() {
		try {
			EntityManager em = instance.createEntityManager();
		    List<Artist> list = em.createNamedQuery("Artist.findAll", Artist.class).getResultList();
		    instance.closeConnections(em);
		    return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Artist>();
	}

}
