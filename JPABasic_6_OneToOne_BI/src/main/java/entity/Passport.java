package entity;

import jakarta.persistence.*;

// Passport <-> Person (양방향)
@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    // Ownership 을 가진 Entity 안 Person 의 passport 와 연계
    // passport table person 관련 컬럼이 만들어 지지 않는다.
    @OneToOne(mappedBy = "passport", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }  // toString() 에 Person 포함 X
}
