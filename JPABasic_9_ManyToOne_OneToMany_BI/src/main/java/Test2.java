import config.MyPersistenceUnitInfo;
import entity.Comment;
import entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

// ManyToOne - OneToMany BI
// 1. ManyToOne 을 가진 테이블이 Owing Entity
// 2. Comment, Post 2개의 테이블이 생성
// 3. 연관관계를 Comment 의 post_id 컬럼으로 처리
// find
public class Test2 {

    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
//        props.put("hibernate.hbm2ddl.auto", "create"); // create : drop & create,  update : 있으면 안 건드리고 없으면 만든다.

        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
                new MyPersistenceUnitInfo(), props
        );
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // #1. FetchType 설정 없이, Post 객체를 find
//        Post post = em.find(Post.class, 1);
        // Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
        // Post 1건 select

        // #2. FetchType 설정 없이, Comment 객체를 find
//        Comment comment = em.find(Comment.class, 1);
        // Hibernate: select c1_0.id,c1_0.content,p1_0.id,p1_0.content,p1_0.title from Comment c1_0 left join Post p1_0 on p1_0.id=c1_0.post_id where c1_0.id=?

        // #3. FetchType 설정 없이, Comment 객체를 find, toString() 으로 comments 사용
//        Post post = em.find(Post.class, 1);
//        System.out.println(post.getComments());
        // StackOverFlowError <- 순환 참조 Post - Comment
        // 양방향에서 Post 에 comments 를 사용하는 코드를 실행할 때, Comment 의 toString() 이 Post 의 toString() 다시 Comment 의 toString() ... 반복
        // 양방향에서는 toString() 내의 상호참조 등에 주의

        // #4. Post 에서 Comment 의 toString() 상호 참조 제거
//        Post post = em.find(Post.class, 1);
//        System.out.println(post.getComments());
        // Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
        // Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
        // [Comment{id=1, content='댓글 1 내용', post=Post{id=1, title='게시글 1 제목', content='게시글 1'}}, Comment{id=2, content='댓글 1 내용', post=Post{id=1, title='게시글 1 제목', content='게시글 1'}}]

        // #5. FetchType 설정 없이, Comment 객체를 find (#2 대비)
//        Comment comment = em.find(Comment.class, 1);
        // Hibernate: select c1_0.id,c1_0.content,c1_0.post_id from Comment c1_0 where c1_0.id=?
        // Comment 1건 select

//        System.out.println(comment.getPost());
        // Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
        // Post{id=1, title='게시글 1 제목', content='게시글 1'}

        // #6. Post 객체 find(), Comment 객체 생성, 연결 Comment 객체 persist
        Post p = em.find(Post.class, 1);

        Comment c3 = new Comment();
        c3.setContent("댓글 3 내용");

        c3.setPost(p);

        em.persist(c3);
        // Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
        // Hibernate: insert into Comment (content,post_id) values (?,?)

        em.getTransaction().commit();  // 이 시점에 테이블에 반영한다.

        em.close();
    }

}