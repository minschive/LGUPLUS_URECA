import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import config.MyPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


// JPQL SELECT
// DB SQL 객체 중심으로 표현 또는 대체 하려는 노력 ( 결과적으로 완전히 대체는 불가능 하다. => 따라서 DB 표준 SQL 을 일부 사용할 수 밖에 없다. @NativeQuery)
public class Test {

	public static void main(String[] args) {

		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
//		props.put("hibernate.hbm2ddl.auto", "create"); // create : drop & create, update : 있으면 건들지 않고 없으면 새로 만든다. 
		
	
		EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
				new MyPersistenceUnitInfo(), props
		);	
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();

		// #1 
//		// 테이블 데이터 -> 객체화
//		// JPQL 사용 안 하고 em 의 메소드를 사용 <= 단 건만 select 가능함
//		Product p = em.find(Product.class, 1);
//		System.out.println(p);
//		
//		// 결과
//		// Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0 where p1_0.id=?
//		// Product [id=1, name=갤럭시 프로, price=1000, quantity=10, country=korea]
//		
//		// 테이블 데이터에서 객체화 ( 목록 ) 는 find() 로 처리가 불가능하다 => 따라서 JPQL 을 이용해야 한다.
//		// JPQL 은 SELECT 만 가능하다.
//		// 표준 SQL 과 비슷하지만, 객체 표현 
		
		// #2 Query
//		String jpql = "select p from Product p";
//		Query q = em.createQuery(jpql);
//		q.getResultList();
//		List<?> productList = q.getResultList();
//		
//		for ( Object object : productList) {
//			Product p = (Product) object;
//			System.out.println(p);
//		}
//		
//		// 결과
//		// Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0
//		// Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0
//		// Product [id=1, name=갤럭시 프로, price=1000, quantity=10, country=korea]
//		// Product [id=2, name=갤럭시 노트, price=2000, quantity=20, country=korea]
//		// Product [id=3, name=갤럭시 폴더, price=3000, quantity=30, country=korea]
//		// Product [id=4, name=갤럭시 플립, price=4000, quantity=40, country=korea]
//		// Product [id=5, name=아이폰 8, price=1000, quantity=15, country=usa]
//		// Product [id=6, name=아이폰 9, price=2000, quantity=25, country=usa]
//		// Product [id=7, name=아이폰 10, price=3000, quantity=35, country=usa]
//		// Product [id=8, name=아이폰 11, price=4000, quantity=45, country=usa]
		
		// #3 TypeQuery ( Type O )
//		String jpql = "select p from Product p";
//		
//		// TypedQuery<Product> q = em.createQuery(jpql, Product.class);
//		// List<Product> productList = q.getResultList();
//		// productList.forEach(product -> System.out.println(product));
//		
//		// 위 코드를 간략하게 표현한 버전
//		em.createQuery(jpql, Product.class).getResultList().forEach(product -> System.out.println(product));
//		
//		// 결과
//		// Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0
//		// Product [id=1, name=갤럭시 프로, price=1000, quantity=10, country=korea]
//		// Product [id=2, name=갤럭시 노트, price=2000, quantity=20, country=korea]
//		// Product [id=3, name=갤럭시 폴더, price=3000, quantity=30, country=korea]
//		// Product [id=4, name=갤럭시 플립, price=4000, quantity=40, country=korea]
//		// Product [id=5, name=아이폰 8, price=1000, quantity=15, country=usa]
//		// Product [id=6, name=아이폰 9, price=2000, quantity=25, country=usa]
//		// Product [id=7, name=아이폰 10, price=3000, quantity=35, country=usa]
//		// Product [id=8, name=아이폰 11, price=4000, quantity=45, country=usa]
		
		// #4 개별 필드 ( id, name, price ) <= 개별필드는 Product 로 받을 수 없다, Object[] 로 처리해야한다.
//		String jpql = "select p.id, p.name, p.price from Product p";
//		
//		// 개별필드는 Product 로 받을 수 없다, Object[] 로 처리해야한다.
//		// em.createQuery(jpql, Product.class).getResultList().forEach(product -> System.out.println(product));
//		em.createQuery(jpql, Object[].class).getResultList().forEach( objArray -> System.out.println( objArray[0] + ", " + objArray[1] + ", " + objArray[2]));
//		
//		// 결과
//		/*
//		Hibernate: select p1_0.id,p1_0.name,p1_0.price from Product p1_0
//		1, 갤럭시 프로, 1000
//		2, 갤럭시 노트, 2000
//		3, 갤럭시 폴더, 3000
//		4, 갤럭시 플립, 4000
//		5, 아이폰 8, 1000
//		6, 아이폰 9, 2000
//		7, 아이폰 10, 3000
//		8, 아이폰 11, 4000
//		*/
		
		// #5 select + where ( 필드를 사용하여 표현한다 -> ex.p.price)
//		String jpql = "select p from Product p where p.price > 2000";
//		
//		em.createQuery(jpql, Product.class)
//			.getResultList()
//			.forEach( product -> System.out.println(product));
//		
//		// 결과
//		/*
//		Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0 where p1_0.price>2000
//		Product [id=3, name=갤럭시 폴더, price=3000, quantity=30, country=korea]
//		Product [id=4, name=갤럭시 플립, price=4000, quantity=40, country=korea]
//		Product [id=7, name=아이폰 10, price=3000, quantity=35, country=usa]
//		Product [id=8, name=아이폰 11, price=4000, quantity=45, country=usa]
//		*/
		
		// #6 select + where + and + param using name ( PreparedStatement 의 ? 에 대응이 되는 )
//		String jpql = "select p from Product p where p.price > :price and p.quantity > :quantity";
//		
//		em.createQuery(jpql, Product.class)
//			.setParameter("price", 2000)
//			.setParameter("quantity", 20)
//			.getResultList()
//			.forEach( product -> System.out.println(product));
//		
//		// 결과
//		/*
//		Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0 where p1_0.price>? and p1_0.quantity>?
//		Product [id=3, name=갤럭시 폴더, price=3000, quantity=30, country=korea]
//		Product [id=4, name=갤럭시 플립, price=4000, quantity=40, country=korea]
//		Product [id=7, name=아이폰 10, price=3000, quantity=35, country=usa]
//		Product [id=8, name=아이폰 11, price=4000, quantity=45, country=usa]
//		*/
		
		// #7 select + where + and + param using index
//		String jpql = "select p from Product p where p.price > ?1 and p.quantity > ?2";
//		
//		em.createQuery(jpql, Product.class)
//			.setParameter(1, 2000)
//			.setParameter(2, 20)
//			.getResultList()
//			.forEach( product -> System.out.println(product));
//		
//		// 결과
//		/*
//		Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0 where p1_0.price>? and p1_0.quantity>?
//		Product [id=3, name=갤럭시 폴더, price=3000, quantity=30, country=korea]
//		Product [id=4, name=갤럭시 플립, price=4000, quantity=40, country=korea]
//		Product [id=7, name=아이폰 10, price=3000, quantity=35, country=usa]
//		Product [id=8, name=아이폰 11, price=4000, quantity=45, country=usa]
//		*/
		
		// #8 select + where + and + param + like
//		String jpql = "select p from Product p where p.price > :price and p.country like :country";
//		
//		em.createQuery(jpql, Product.class)
//			.setParameter("price", 2000)
//			.setParameter("country", "%ko%")
//			.getResultList()
//			.forEach( product -> System.out.println(product));
//		
//		// 결과
//		/*
//		Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity from Product p1_0 where p1_0.price>? and p1_0.country like ? escape ''
//		Product [id=3, name=갤럭시 폴더, price=3000, quantity=30, country=korea]
//		Product [id=4, name=갤럭시 플립, price=4000, quantity=40, country=korea]
//		*/
		
		// #9 select + aggregation function count() ( 정수형 )
//		// aggregation function 은 1 개의 row 를 리턴 한다. getResultList(복수) 대신 getSingleResult(단수) 사용
//		String jpql = "select count(p) from Product p";
//		
//		Long cnt = em.createQuery(jpql, Long.class).getSingleResult(); // getSingleResult 는 Long return
//		System.out.println(cnt);
//
//		// 결과
//		/*
//		Hibernate: select count(p1_0.id) from Product p1_0
//		8
//		*/
		
		// #10 select + aggregation function avg() ( 실수형 )
//		// aggregation function 은 1 개의 row 를 리턴 한다. getResultList(복수) 대신 getSingleResult(단수) 사용
//		String jpql = "select avg(p.price) from Product p";
//		
//		Double avg = em.createQuery(jpql, Double.class).getSingleResult(); // getSingleResult 는 Double return
//		System.out.println(avg);
//
//		// 결과
//		/*
//		Hibernate: select avg(p1_0.price) from Product p1_0
//		2500.0
//		*/
		
		// #11 select + aggregation function sum(), min(), max() ( 한 번에 )
//		// aggregation function 은 1 개의 row 를 리턴 한다. getResultList(복수) 대신 getSingleResult(단수) 사용
//		String jpql = "select sum(p.quantity), min(p.quantity), max(p.quantity) from Product p";
//		
//		Object[] objArray = em.createQuery(jpql, Object[].class).getSingleResult(); // getSingleResult 는 Double return
//		System.out.println(objArray[0] + ", " + objArray[1] + ", " + objArray[2]);
//
//		// 결과
//		/*
//		Hibernate: select sum(p1_0.quantity),min(p1_0.quantity),max(p1_0.quantity) from Product p1_0
//		220, 10, 45
//		*/
		
		// #12 select + aggregation function sum(), min(), max() + group by country
		// aggregation function 은 1 개의 row 를 리턴 한다. getResultList(복수) 대신 getSingleResult(단수) 사용
		String jpql = "select p.country, sum(p.quantity), min(p.quantity), max(p.quantity) from Product p group by p.country";
		
		List<Object[]> objArrayList = em.createQuery(jpql, Object[].class).getResultList(); 
		objArrayList.forEach( objArray -> {
			System.out.println(objArray[0] + ", " + objArray[1] + ", " + objArray[2] + ", " + objArray[3]);
		});
		

		// 결과
		/*
		Hibernate: select p1_0.country,sum(p1_0.quantity),min(p1_0.quantity),max(p1_0.quantity) from Product p1_0 group by p1_0.country
		korea, 100, 10, 40
		usa, 120, 15, 45
		*/

		
		
		em.getTransaction().commit(); // 이 시점에 테이블에 반영한다. 
		
		em.close();

	}

}
