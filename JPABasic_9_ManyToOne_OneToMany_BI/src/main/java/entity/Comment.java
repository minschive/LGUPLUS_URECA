package entity;

import jakarta.persistence.*;

// 게시글에 달린 comment
// Owing Entity
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

//    @ManyToOne
//    private Post post;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", post=" + post +
                '}';
    }
}
