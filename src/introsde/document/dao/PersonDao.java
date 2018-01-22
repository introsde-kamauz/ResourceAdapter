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
	
	@NamedQuery(name="Person.getPersonByUser",
		query="SELECT a FROM Person a WHERE a.userId=:Person"),
	
	@NamedQuery(name="Person.findAll",
		query="SELECT p FROM Person p")
	
})

public enum PersonDao {
	instance;
	private EntityManagerFactory emf;
	
	private PersonDao() {
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
	
	public static void add(Person p) {
		try {
			EntityManager em = PersonDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();        
	        em.persist(p);
	        tx.commit();
	        PersonDao.instance.closeConnections(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void remove(Person p) {
	    	
		try {
	        EntityManager em = PersonDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        p=em.merge(p); //
	        em.remove(p);
	        tx.commit();
	        PersonDao.instance.closeConnections(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Person related operations could also directly go into the "Person" Model
	// Check Methods in LifeStaus as example
	public static Person getPersonById(String uid) {
		try {
			EntityManager em = instance.createEntityManager();
			
			Person list = em.createNamedQuery("Person.getPersonByUserId", Person.class)
		    		.setParameter("Person", uid)
		    		.getSingleResult();
		    		
		    instance.closeConnections(em);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Person> getAll() {
		try {
			EntityManager em = instance.createEntityManager();
		    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		    instance.closeConnections(em);
		    return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Person>();
	}

}
