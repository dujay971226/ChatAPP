package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChannelTest {
    private Channel channel;
    User user;
    @Before
    public void setup(){
        user = new User("user1","password1");
        channel = new Channel("channel1",user);
    }

    @Test
    public void getName() {
        assertEquals("channel1",channel.getName());
    }

    @Test
    public void getUser() {
        assertEquals(user,channel.getUser());
    }

    @Test
    public void setCurrUser() {
        User curruser = new User("user2","password2");
        channel.setCurrUser(curruser);
        assertEquals(curruser,channel.getUser());
    }
}