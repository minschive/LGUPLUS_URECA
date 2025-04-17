import java.util.HashMap;
import java.util.Map;

import entity.Orders;
import org.hibernate.jpa.HibernatePersistenceProvider;

import config.MyPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;


// join + Subquery
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
		
		// #1. join
////		String jpql = "select p, o from Product p inner join p.orders o"; // inner 생략 가능
////		String jpql = "select p, o from Product p, Orders o where p.id = o.product.id"; // where id 필드 비교
////		String jpql = "select p, o from Product p, Orders o where p = o.product"; // where 객체 비교
//		String jpql = "select o, p from Product p, Orders o where p = o.product"; // select 객체 순서 변경
//
//		em.createQuery(jpql, Object[].class)
//			.getResultList()
//			.forEach( objArray -> {
//				System.out.println(objArray[0]);
//				System.out.println(objArray[1]);
//			});
		
		// 결과 1
		// String jpql = "select p, o from Product p inner join p.orders o";
		/*
		Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity,o1_0.id,o1_0.customer_id,o1_0.order_date,o1_0.order_quantity from Product p1_0 join Orders o1_0 on p1_0.id=o1_0.product_id
		Hibernate: select c1_0.id,c1_0.gender,c1_0.name,c1_0.phone from Customer c1_0 where c1_0.id=?
		Hibernate: select c1_0.id,c1_0.gender,c1_0.name,c1_0.phone from Customer c1_0 where c1_0.id=?
		Hibernate: select c1_0.id,c1_0.gender,c1_0.name,c1_0.phone from Customer c1_0 where c1_0.id=?
		Product [id=1, name=제품1, price=1000, quantity=15, country=korea]
		Orders [id=1, orderQuantity=2, orderDate=2025-04-16]
		Product [id=2, name=제품2, price=3000, quantity=10, country=usa]
		Orders [id=2, orderQuantity=5, orderDate=2025-04-16]
		Product [id=2, name=제품2, price=3000, quantity=10, country=usa]
		Orders [id=3, orderQuantity=3, orderDate=2025-04-16]
		Product [id=3, name=제품3, price=5000, quantity=20, country=korea]
		Orders [id=4, orderQuantity=10, orderDate=2025-04-16]
		Product [id=3, name=제품3, price=5000, quantity=20, country=korea]
		Orders [id=5, orderQuantity=5, orderDate=2025-04-16]
		 */
		
		// 결과 2 orders 에 @ManyToOne(fetch=FetchType.LAZY) 추가
		// String jpql = "select p, o from Product p inner join p.orders o";
		/*
		Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity,o1_0.id,o1_0.customer_id,o1_0.order_date,o1_0.order_quantity from Product p1_0 join Orders o1_0 on p1_0.id=o1_0.product_id
		Product [id=1, name=제품1, price=1000, quantity=15, country=korea]
		Orders [id=1, orderQuantity=2, orderDate=2025-04-16]
		Product [id=2, name=제품2, price=3000, quantity=10, country=usa]
		Orders [id=2, orderQuantity=5, orderDate=2025-04-16]
		Product [id=2, name=제품2, price=3000, quantity=10, country=usa]
		Orders [id=3, orderQuantity=3, orderDate=2025-04-16]
		Product [id=3, name=제품3, price=5000, quantity=20, country=korea]
		Orders [id=4, orderQuantity=10, orderDate=2025-04-16]
		Product [id=3, name=제품3, price=5000, quantity=20, country=korea]
		Orders [id=5, orderQuantity=5, orderDate=2025-04-16]
		 */
		
		// 결과 3 
		// String jpql = "select p, o from Product p, Orders o where p.id = o.product.id";
		/*
		Hibernate: select p1_0.id,p1_0.country,p1_0.name,p1_0.price,p1_0.quantity,o1_0.id,o1_0.customer_id,o1_0.order_date,o1_0.order_quantity,o1_0.product_id from Product p1_0,Orders o1_0 where p1_0.id=o1_0.product_id
		Product [id=1, name=제품1, price=1000, quantity=15, country=korea]
		Orders [id=1, orderQuantity=2, orderDate=2025-04-16]
		Product [id=2, name=제품2, price=3000, quantity=10, country=usa]
		Orders [id=2, orderQuantity=5, orderDate=2025-04-16]
		Product [id=2, name=제품2, price=3000, quantity=10, country=usa]
		Orders [id=3, orderQuantity=3, orderDate=2025-04-16]
		Product [id=3, name=제품3, price=5000, quantity=20, country=korea]
		Orders [id=4, orderQuantity=10, orderDate=2025-04-16]
		Product [id=3, name=제품3, price=5000, quantity=20, country=korea]
		Orders [id=5, orderQuantity=5, orderDate=2025-04-16]
		 */

		// #2. left outer join
		// Customer 기준
//		String jpql = "select c, o from Customer c left join c.orders o";
//
//		em.createQuery(jpql, Object[].class)
//				.getResultList()
//				.forEach( objArray -> {
//					System.out.println(objArray[0] + ", " + objArray[1]);
//				});

		// #3. left outer join + count + group by
		// Customer 기준
		// orders 에서 모든 고객을 대상으로 몇 건 주문했는지 확인 ( 주문하지 않은 고객도 포함 )
//		String jpql = "select c, count(o) from Customer c left join c.orders o group by c";
//
//		em.createQuery(jpql, Object[].class)
//				.getResultList()
//				.forEach( objArray -> {
//					System.out.println(objArray[0] + ", " + objArray[1]);
//				});

		// #4. join + 조건
//		String jpql =
//				"""
//				select p, o
//				from Product p join p.orders o
//				where p.price > 1000
//				  and p.quantity > 10
//				  and o.orderQuantity = 10
//				""";
//
//		em.createQuery(jpql, Object[].class)
//				.getResultList()
//				.forEach( objArray -> {
//					System.out.println(objArray[0] + ", " + objArray[1]);
//				});

		// #5. join + 조건 + 일부 필드만 가져옴
//		String jpql =
//				"""
//				select p.id, p.name, p.price, o.orderQuantity, o.orderDate
//				from Product p join p.orders o
//				where p.price > 1000
//				  and p.quantity > 10
//				  and o.orderQuantity = 10
//				""";
//
//		em.createQuery(jpql, Object[].class)
//				.getResultList()
//				.forEach( objArray -> {
//					// 대응되는 Dto 객체를 생성 Spring 자동 처리 등
//					System.out.println(objArray[0] + ", " + objArray[1] + ", " + objArray[2] + ", " + objArray[3] + ", " + objArray[4]);
//				});

		// #6. subquery + where
		// 주문 건 중에 제품 가격이 5000원 미만인 건에 대해서 select, subquery 이용
//		String jpql =
//				"""
//					select o
//					from Orders o
//					where o.product in ( select p from Product p where p.price < 5000 )
//				""";
//
//		em.createQuery(jpql, Orders.class)
//				.getResultList()
//				.forEach( orders -> System.out.println(orders));

		// #7. subquery + select
		// 모든 주문 건에 대해 주문한 고객의 이름을 함께 select
		// 아래 jpql 중 select o, ( select c.name from Customer ) as customerName 로 처리하면
		// java.sql.SQLException: Subquery returns more than 1 row 오류 발생
		String jpql =
				"""
					select o, ( select c.name from Customer c where o.customer = c ) as customerName
					from Orders o
				""";

		em.createQuery(jpql, Object[].class)
				.getResultList()
				.forEach( objArray -> System.out.println( objArray[0] + ", " + objArray[1]));
		
		em.getTransaction().commit(); // 이 시점에 테이블에 반영한다. 
		
		em.close();

	}

}
