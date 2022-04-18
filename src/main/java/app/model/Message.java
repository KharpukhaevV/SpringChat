package app.model;

import javax.validation.constraints.NotNull;

public class Message {
    private String content;
    private String sender;
    private String age;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getAge() {
        return age;
    }
}