package entity;

import java.time.LocalDateTime;


public class Message {
    private final String content;
    private final LocalDateTime msgDate;
    private final User user;

    public Message(String msg) {
        this.msgDate = LocalDateTime.now();
        this.content = msg;
    }

    public Message(User user, String msg, LocalDateTime time) {
        this.user = user;
        this.msgDate = time;
        this.content = msg;
    }

    public String getContent() {
        return this.content;
    }

    public LocalDateTime getTime() {
        return this.msgDate;
    }
}