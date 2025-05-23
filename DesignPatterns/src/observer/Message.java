package observer;

// 생성자를 통해서 message 를 받고, 변경이 불가, get 만 가능
public class Message {
    private final String messageContent;

    public Message(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return this.messageContent;
    }
}
