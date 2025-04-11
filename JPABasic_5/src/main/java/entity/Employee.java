package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    // Key 생성 방법
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO) // hibernate 에 위임. (hibernate 가 DB 에 맞게 알아서 처리 - mysql : SEQUENCE )
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Entity table 의 id 컬럼을 이용 (Auto Increment)

    // GenerationType.SEQUENCE, Generation.TABLE 은 별도의 TABLE 또는 SEQUENCE 를 생성한다.

//    private int id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String address;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
