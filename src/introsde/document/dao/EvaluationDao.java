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
	
	@NamedQuery(name="Evaluation.findAll",
			query="SELECT p FROM Evaluation p"),
	
	@NamedQuery(name="Evaluation.getEvaluationsByUserId",
		query="SELECT a FROM Evaluation a WHERE a.userId=:Person"),
	
})
public enum EvaluationDao {
	instance;
	private EntityManagerFactory emf;
	
	private EvaluationDao() {
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
	
	public static void add(Evaluation p) {
		try {
			EntityManager em = EvaluationDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();        
	        em.persist(p);
	        tx.commit();
	        EvaluationDao.instance.closeConnections(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void remove(Evaluation p) {
	    	
		try {
	        EntityManager em = EvaluationDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        p=em.merge(p); //
	        em.remove(p);
	        tx.commit();
	        EvaluationDao.instance.closeConnections(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Person related operations could also directly go into the "Person" Model
	// Check Methods in LifeStaus as example
	public static List<Evaluation> getEvaluationsById(String uid) {
		try {
			EntityManager em = instance.createEntityManager();
			
		    List<Evaluation> list = em.createNamedQuery("Evaluation.getEvaluationsByUserId", Evaluation.class)
		    		.setParameter("Person", uid)
		    		.getResultList();
		    		
		    instance.closeConnections(em);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Evaluation>();
	}
	
	public static List<Evaluation> getAll() {
		try {
			EntityManager em = instance.createEntityManager();
		    List<Evaluation> list = em.createNamedQuery("Evaluation.findAll", Evaluation.class).getResultList();
		    instance.closeConnections(em);
		    return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Evaluation>();
	}

}
