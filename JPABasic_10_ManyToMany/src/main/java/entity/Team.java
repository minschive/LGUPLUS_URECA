package entity;

import jakarta.persistence.*;

import java.util.List;

// ManyToMamy Owing Entity, 양방향

@Entity
@Table(name="teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "teams_users",
//            joinColumns = @JoinColumn(name="team_id"), // Team Entity 와 join 될 teams_users 의 column name
//            inverseJoinColumns = @JoinColumn(name = "user_id") // User Entity 와 join 될 teams_users 의 column name
//    )
//    private List<User> users;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "teams_users",
            joinColumns = @JoinColumn(name="team_id"), // Team Entity 와 join 될 teams_users 의 column name
            inverseJoinColumns = @JoinColumn(name = "user_id") // User Entity 와 join 될 teams_users 의 column name
    )
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
