package proxy;

// Hard Coding
public class ProxyCar implements Car {

    // 대상이 되는 객체
//    private Car car = new SimpleCar();
//
//    @Override
//    public void engineStart() {
//        System.out.println("Proxy Car Engine Start");
//        car.engineStart();
//    }
//
//    @Override
//    public void drive() {
//        System.out.println("Proxy Car Drive");
//        car.drive();
//    }

    // Lazy Loading
    // 대상이 되는 객체
    private Car car;

    @Override
    public void engineStart() {
        System.out.println("Proxy Car Engine Start");
//        car.engineStart();
    }

    @Override
    public void drive() {
        System.out.println("Proxy Car Drive");

        if(car == null) {
            car = new SimpleCar();
            car.engineStart();
        }
        car.drive();
    }
}
