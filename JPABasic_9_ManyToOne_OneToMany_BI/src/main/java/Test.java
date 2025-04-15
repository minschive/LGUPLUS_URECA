import config.MyPersistenceUnitInfo;
import entity.Comment;
import entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ManyToOne;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ManyToOne - OneToMany BI
// 1. ManyToOne 을 가진 테이블이 Owing Entity
// 2. Comment, Post 2개의 테이블이 생성
// 3. 연관관계를 Comment 의 post_id 컬럼으로 처리
// persist
public class Test {

    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create"); // create : drop & create,  update : 있으면 안 건드리고 없으면 만든다.

        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
                new MyPersistenceUnitInfo(), props
        );
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Post p = new Post();
        p.setTitle("게시글 1 제목");
        p.setContent("게시글 1");

        Comment c1 = new Comment();
        c1.setContent("댓글 1 내용");

        Comment c2 = new Comment();
        c2.setContent("댓글 1 내용");

        // #1. 연결 없이, Post 만 1건 persist
//        em.persist(p);
        // Hibernate: insert into Post (content,title) values (?,?)

        // #2. 연결 없이, Commit 만 2건 persist
//        em.persist(c1);
//        em.persist(c2);
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // 연관 관계 컬럼인 post_id 가 null

        // #3. 연결 없이, Post 1건, Comment 2건 persist
//        em.persist(p);
//        em.persist(c1);
//        em.persist(c2);
        // Hibernate: insert into Post (content,title) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // 3건 모두 insert
        // 연관 관계 컬럼인 post_id 가 null

        // #4. Comment 2개에만 post 연결 ( ManyToOne), c1, c2 만 persist
//        c1.setPost(p);
//        c1.setPost(p);
//
//        em.persist(c1);
//        em.persist(c2);
        // org.hibernate.TransientObjectException: persistent instance references an unsaved transient instance of 'entity.Post'
        // Comment 의 연관 관계 컬럼인 post_id 에 채워질 Post 객체가 연결되었으나, 영속화되지 못했기 때문

        // #5. Comment 2개에만 post 연결 ( ManyToOne), c1, c2, p 모두 persist
//        c1.setPost(p);
//        c1.setPost(p);
//
//        em.persist(p);
//        em.persist(c1);
//        em.persist(c2);
        // Hibernate: insert into Post (content,title) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // 3건 모두 insert
        // Comment 의 연관 관계 컬럼인 post_id 에 Post 객체의 id 값으로 사용됨

        // #6. Comment 2개에만 post 연결 ( ManyToOne), c1, c2, p 모두 persist
        // ManyToOne 에 cascade=CascadeType.PERSIST
//        c1.setPost(p);
//        c1.setPost(p);
//
//        em.persist(p);
//        em.persist(c1);
//        em.persist(c2);
        // Hibernate: insert into Post (content,title) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // 3건 모두 insert
        // Comment 의 연관 관계 컬럼인 post_id 에 Post 객체의 id 값으로 사용됨

        // #7. Post 에만 Comment 2개 연결 ( OneToMany ), p 만 persist
//        p.setComments(List.of(c1, c2));
//
//        em.persist(p);
        // Hibernate: insert into Post (content,title) values (?,?)
        // Post 1건 insert, comment insert X
        // ManyToOne, OneToMany 양방향의 Owing 연관관계는 ManyToOne
        // OneToMany 를 가진 Post 는 연관관계 관련 컬럼 X <= Comment 가 함께 영속화 되지 않아도 된다.

        // #8. Post 에만 Comment 2개 연결 ( OneToMany ), p, c1, c2 persist
        // @ManyToOne
//        p.setComments(List.of(c1, c2));
//
//        em.persist(p);
//        em.persist(c1);
//        em.persist(c2);
        // Hibernate: insert into Post (content,title) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)

        // 3개 모두 persist 가 되어서 3건 insert 가 되었지만, Comment 의 연관 관계 컬럼인 post_id 에 null
        // ManyToOne 의 연관관계를 결정짓는 comment.setPost() X

        // #9. Post 에만 Comment 2개 연결 ( ManyToOne, OneToMany ), p, c1, c2 persist
        // @ManyToOne
        p.setComments(List.of(c1, c2)); // 이 라인을 제거해도 ManyToOne에 의해 #5와 같은 처리
        c1.setPost(p);
        c2.setPost(p);

        em.persist(p);
        em.persist(c1);
        em.persist(c2);
        // Hibernate: insert into Post (content,title) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)
        // Hibernate: insert into Comment (content,post_id) values (?,?)

        // 3개 모두 persist 가 되어서 3건 insert 가 되었지만, Comment 의 연관 관계 컬럼인 post_id 에 null
        // ManyToOne 의 연관관계를 결정짓는 comment.setPost() X

        em.getTransaction().commit();  // 이 시점에 테이블에 반영한다.

        em.close();
    }

}