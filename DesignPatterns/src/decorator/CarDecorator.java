package decorator;

// Car 인터페이스를 구현함과 동시에 Car 타입의 필드를 가진다.
// Car 인터페이스의 추상 메소드를 구현하되, 필드로 가진 Car 의 메소드를 기본 호출하도록
public abstract class CarDecorator implements Car {

    private final Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public String getDesc() {
        return car.getDesc();
    }

    @Override
    public int getSpeed() {
        return car.getSpeed();
    }
}
