package factory;

public class SportsCar implements Transportation {

    @Override
    public void move() {
        System.out.println("스포츠카가 움직입니다.");
    }
}
