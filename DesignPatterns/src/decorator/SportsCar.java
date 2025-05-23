package decorator;

public class SportsCar extends CarDecorator {

    public SportsCar(Car car) {
        super(car);
    }

    // 생성자에 전달된 Car 인터페이스를 구현한 객체의 메소드를 그대로 쓰려면 재정의 안하면 된다.

    // 아래처럼 재정의하면 새로운 기능을 수행할 수 있다.
    @Override
    public String getDesc() {
        return super.getDesc() + " changed to sports car";
    }

    @Override
    public int getSpeed() {
        return super.getSpeed() + 100;
    }
}
