package observer;

// Observer 등록, 삭제, 메세지 전송 ...역할
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyUpdate(Message message);
}
