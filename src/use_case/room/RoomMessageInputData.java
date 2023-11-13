package use_case.room;
import entity.Message;
import java.io.File;
import java.time.LocalDateTime;

public class RoomMessageInputData {
    private LocalDateTime timeSent = null;
    private String messageContent = null;
    private Channel channel = null;
    private User user = null;

    public RoomMessageInputData (User user, Channel channel, String msg) {
        this.messageContent = msg;
    }

    public String getContent() {
        return messageContent;
    }

    public Channel getChannel() {
        return channel;
    }

    public User getUser() {
        return user;
    }

}
