import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import config.MyPersistenceUnitInfo;
import entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;


// N + 1
// 어떤 Entity A 가 연관관계를 가진 상태 Entity B 에 대해 FetchType 이Lazy 일 때,
// A 의 목록을 가져오는 경우, B 는 가져오지 않는다.
// A 의 목록을 가져와서 각각의 A 에 대해 연관관계에 있는 B 를 사용하면 B 를 가져올 수 밖에 없다.
// 이 때 A 의 목록에 포함된 A 의 수만큼 B 를 가져오는 select 수행 
// 결국 이 과정에서 A 목록 가져오는 select 1회, A 목록 수(N) 만큼 B 를 select N 회 수행 된다. => N + 1

// 유사품 주의!
// jpa 를 이용하는 데, 비효율적인 코드를 바로 잡는 상황
// EAGER -> LAZY, LAZY -> EAGER 해결 (X)

// N + 1 은 join fetch 를 통해서 해결
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
		// N + 1 문제 확인
//		String jpql = "select p from Post p";
//		// em.createQuery(jpql, Post.class).getResultList(); // Post 목록
//		List<Post> postList = em.createQuery(jpql, Post.class).getResultList();
//		// 결과
//		// Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 // 3건
//		
//		postList.forEach(post -> post.getComments().size()); // Post 목록을 순회, 연관관계의 Comment 객체를 확인
//		
//		// 3건에 대한 select 추가 수행 ( n + 1 상황 )
//		// 결과
//		/*
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=? 
//		*/
		
		// #2 
		// 해결 시도 : Post 의 comment 에 대한 연관관계를 FetchType.EAGER
		// 여전히 N + 1 문제가 발생한다.
//		String jpql = "select p from Post p";
//		// em.createQuery(jpql, Post.class).getResultList(); // Post 목록
//		List<Post> postList = em.createQuery(jpql, Post.class).getResultList();
//		// 결과
//		// Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 // 3건
//		
//		// 3건에 대한 select 추가 수행 -> 미리 가져오긴 하는데 join 을 통한 한번의 select 가 아닌, 여전히 n + 1 발생
//		/*
//		Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		 */
//		
//		// postList.forEach(post -> post.getComments().size()); // Post 목록을 순회, 연관관계의 Comment 객체를 확인
//		
//		// 3건에 대한 select 추가 수행 ( n + 1 상황 )
//		// 결과
//		/*
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=? 
//		*/
		
		// #3
		// 해결 시도 : Post 의 comment 에 대한 연관관계를 FetchType.EAGER
		// #2 에 대한 결과를 가지고 , jpql 대신 find() 와 비교, find() 형태의 목록을 가져오는 메소드 X
//		// find() != jpql 
//		em.find(Post.class, 1);
//		em.find(Post.class, 2);
//		em.find(Post.class, 3);
//		// 결과
//		/*
//		Hibernate: select p1_0.id,p1_0.content,p1_0.title,c1_0.post_id,c1_0.id,c1_0.content from Post p1_0 left join Comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
//		Hibernate: select p1_0.id,p1_0.content,p1_0.title,c1_0.post_id,c1_0.id,c1_0.content from Post p1_0 left join Comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
//		Hibernate: select p1_0.id,p1_0.content,p1_0.title,c1_0.post_id,c1_0.id,c1_0.content from Post p1_0 left join Comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
//		*/
		
		// #4 
		// Post 목록 대신 PK로 조건을 줘서 select <= find() 와 동일하게 EAGER 로 가져오는지 확인
//		String jpql = "select p from Post p where p.id = 1";
//		// em.createQuery(jpql, Post.class).getResultList(); // Post 목록
//		List<Post> postList = em.createQuery(jpql, Post.class).getResultList();
//		// 결과
//		// Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=1 // 1건
//		
//		postList.forEach(post -> post.getComments().size()); // Post 목록을 순회, 연관관계의 Comment 객체를 확인
//		
//		// 1건에 대한 select 추가 수행 ( n + 1 상황 )
//		// 결과
//		/*
//		Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
//		*/
//		
//		// 중간 결론
//		// #2, #3, #4 의 테스트 결과
//		// FetchType 을 LAZY -> EAGER 로 변경하는 방법은 실패 
//		
//		// 최종 결론
//		// Post 목록을 가져와서 Post 만 사용하려는 목적이라면 N + 1 문제는 발생하지 않는다.
//		// Post 목록을 가져와서 Post 외 Post 의 연관관계인 Comment 를 사용하려면 미리 Comment 도 가져오는 것이 N + 1 문제를 해결할 수 있음.
//		//  => 해당 해결 방법 : join fetch
		
		// #5 
		// N + 1 문제에 대해 Join fetch 로 미리 가져와서 해결 
//		String jpql = "select p from Post p join fetch p.comments";
//		// em.createQuery(jpql, Post.class).getResultList(); // Post 목록
//		List<Post> postList = em.createQuery(jpql, Post.class).getResultList();
//		// 결과
//		// Hibernate: select p1_0.id,c1_0.post_id,c1_0.id,c1_0.content,p1_0.content,p1_0.title from Post p1_0 join Comment c1_0 on p1_0.id=c1_0.post_id // 1건 
//		
//		postList.forEach(post -> post.getComments().size()); // Post 목록을 순회, 연관관계의 Comment 객체를 확인
//		// 결과 쿼리 1건만 출력, 기존 3건 더 출력되었던 것이 더이상 출력되지 않음, n+1 문제 해결됨 

		
		em.getTransaction().commit(); // 이 시점에 테이블에 반영한다. 
		
		em.close();

	}

}
