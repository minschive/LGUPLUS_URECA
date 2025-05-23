package factory;

public class Helicopter implements Transportation{

    @Override
    public void move() {
        System.out.println("헬리콥터가 날라갑니다.");
    }
}
