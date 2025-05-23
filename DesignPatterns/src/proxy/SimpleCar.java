package proxy;

public class SimpleCar implements Car {

    @Override
    public void engineStart() {
        System.out.println("Simple Car Engine Start.");
    }

    @Override
    public void drive() {
        System.out.println("Simple Car Drive.");
    }
}
