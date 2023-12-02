package entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;


public class Message {
    private final String content;
    private final LocalDateTime msgDate;
    private final User user;
    private long timeStamp;

    public Message(String msg) {
        this.user = null;
        this.msgDate = LocalDateTime.now();
        this.content = msg;
    }

    public Message(User user, String msg, LocalDateTime time) {
        this.user = user;
        this.msgDate = time;
        this.content = msg;
    }

    public Message(User user, String msg, long rawTimeStamp) {
        this.user = user;
        this.timeStamp = rawTimeStamp;
        this.msgDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(rawTimeStamp / 10000000L),
                TimeZone.getDefault().toZoneId());
        this.content = msg;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getContent() {
        return this.content;
    }

    public LocalDateTime getTime() {
        return this.msgDate;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public String toString() {
        return msgDate + "     " + content;
    }
}