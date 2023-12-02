package app;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


class MainTest {

    @BeforeEach
    void setUp() {
        String[] start = new String[1];
        start[0]= start[0]+"test";
        Main.main(start);
    }

    @Test
    void main() {

    }
}