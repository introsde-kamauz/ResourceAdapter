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

@NamedQueries({
	
	@NamedQuery(name="Preference.getPreferencesByUserIdAndArtistId",
		query="SELECT a FROM Preference a WHERE a.userId=:Person AND a.artistId=:idArtist"),
	
	@NamedQuery(name="Preference.getPreferencesByUser",
		query="SELECT a FROM Preference a WHERE a.userId=:Person"),
	
	@NamedQuery(name="Preference.findAll",
		query="SELECT p FROM Preference p")
	
})
public enum PreferenceDao {
	instance;
	private EntityManagerFactory emf;
	
	private PreferenceDao() {
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
	
	public static void add(Preference p) {
		try {
			EntityManager em = PreferenceDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();        
	        em.persist(p);
	        tx.commit();
	        PreferenceDao.instance.closeConnections(em);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void remove(Preference p) {
	    try {
	        EntityManager em = PreferenceDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        p=em.merge(p); //
	        em.remove(p);
	        tx.commit();
	        PreferenceDao.instance.closeConnections(em);
	    } catch (Exception err) {
	    	err.printStackTrace();
	    }
	}
	
	// Person related operations could also directly go into the "Person" Model
	// Check Methods in LifeStaus as example
	public static List<Preference> getPreferencesById(String uid) {
		try {
			EntityManager em = instance.createEntityManager();
			
		    List<Preference> list = em.createNamedQuery("Preference.getPreferencesByUserId", Preference.class)
		    		.setParameter("Person", uid)
		    		.getResultList();
		    		
		    instance.closeConnections(em);
			return list;
		} catch (Exception err) {
			err.printStackTrace();
		}
		return new ArrayList<Preference>();
	}
	
	public static Preference getPreferencesByUserIdAndArtistId(String uid, Integer artistId) {
		try {
			EntityManager em = instance.createEntityManager();
		    Preference list = em.createNamedQuery("Preference.getPreferencesByUserIdAndArtistId", Preference.class)
		    		.setParameter("Person", uid)
		    		.setParameter("artistId", artistId)
		    		.getSingleResult();
		    instance.closeConnections(em);
		    return list;
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Preference> getPreferencesByUser(String uid) {
		try {
			EntityManager em = instance.createEntityManager();
		    List<Preference> list = em.createNamedQuery("Preference.getPreferencesByUser", Preference.class)
		    		.setParameter("Person", uid)
		    		.getResultList();
		    instance.closeConnections(em);
		    return list;
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Preference> getAll() {
		try {
			EntityManager em = instance.createEntityManager();
		    List<Preference> list = em.createNamedQuery("Preference.findAll", Preference.class).getResultList();
		    instance.closeConnections(em);
		    return list;
		} catch (Exception err) {
			err.printStackTrace();
		}
		return new ArrayList<Preference>();
	}
	
	// add other database global access operations

}
