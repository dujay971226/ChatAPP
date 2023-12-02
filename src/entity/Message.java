package entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;


public class Message {
    private final String content;
    private final LocalDateTime msgDate;
    private final User user;
    private long startTimeStamp;
    private long endTimeStamp;

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
        this.startTimeStamp = rawTimeStamp - 1;
        this.endTimeStamp = startTimeStamp;
        this.msgDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(rawTimeStamp / 10000000L),
                TimeZone.getDefault().toZoneId());
        this.content = msg;
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }
    public long getEndTimeStamp() {
        return endTimeStamp;
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