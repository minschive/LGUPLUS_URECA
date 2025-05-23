package observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements Subject {

    // 메세지 구독을 하는 Observer 객체 관리하는 자료 구조
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyUpdate(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
