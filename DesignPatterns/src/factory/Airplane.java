package factory;

public class Airplane implements Transportation {

    @Override
    public void move() {
        System.out.println("비행기가 날라갑니다.");
        check();
    }

    public void check() {
        System.out.println("비행기가 잘 가고 있습니다.");
    }
}
