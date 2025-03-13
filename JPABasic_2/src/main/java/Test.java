import java.util.HashMap;

import org.hibernate.jpa.HibernatePersistenceProvider;

import config.MyPersistenceUnitInfo;
import entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Test {

	public static void main(String[] args) {
		// EntityManager <= EntittyManagerFactory
		// src/main/resources/META-INF/persistence.xml
		EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
				new MyPersistenceUnitInfo(), new HashMap<>()
		); // my persistence unit
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		// persistence 작업
		// class   - table 
		// Product - product
		
		Product p = new Product();
		p.setId(2L);
		p.setName("Book");
		
		em.persist(p); // 영속화 (이 시점에 insert 되지 않는다.)
		
		
		em.getTransaction().commit(); // 이 시점에 insert 된다.
		
		
		em.close();
	}

}
