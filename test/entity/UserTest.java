package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setup(){
        user = new User("user1","password1");
    }

    @Test
    public void getName() {
        assertEquals("user1",user.getName());
    }

    @Test
    public void getPassword() {
        assertEquals("password1",user.getPassword());
    }
}