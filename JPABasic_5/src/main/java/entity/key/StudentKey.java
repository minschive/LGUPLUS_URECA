package entity.key;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/*
The composite primary key class must be public.
It must have a no-args constructor.
It must define the equals() and hashCode() methods.
It must be Serializable.
 */
@Embeddable // 다른 Entity class 의 필드로 사용
public class StudentKey implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private int number;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentKey that = (StudentKey) o;
        return number == that.number && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, number);
    }

    @Override
    public String toString() {
        return "StudentKey{" +
                "code='" + code + '\'' +
                ", number=" + number +
                '}';
    }
}
