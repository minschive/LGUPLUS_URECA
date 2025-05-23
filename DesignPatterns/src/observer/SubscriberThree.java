package observer;

// 메세지 구독
public class SubscriberThree implements Observer {

    @Override
    public void update(Message message) {
        System.out.println("SubscriberThree Received : " + message.getMessageContent());
    }
}
