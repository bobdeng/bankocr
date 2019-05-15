package cn.bobdeng.bankscanner;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DigitalCharsTest {
    @Before
    public void setup() {

    }

    @Test
    public void test_possible() {
        assertTrue(DigitalChars.getPossibleChar("_    |  |").contains(new DigitalChar('1')));
        assertTrue(DigitalChars.getPossibleChar("     |  |").contains(new DigitalChar('7')));
        assertTrue(DigitalChars.getPossibleChar(" _ |_  _|").contains(new DigitalChar('6')));
        assertTrue(DigitalChars.getPossibleChar(" _ |_  _|").contains(new DigitalChar('9')));
    }

    @Test
    public void test_output(){
        assertArrayEquals(DigitalChars.getCharsLines("123456789").toArray(new String[0]),new String[]{
                "    _  _     _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|",
                "  ||_  _|  | _||_|  ||_| _|",
                "",
        });
        assertArrayEquals(DigitalChars.getCharsLines("133456789").toArray(new String[0]),new String[]{
                "    _  _     _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|",
                "  | _| _|  | _||_|  ||_| _|",
                "",
        });
    }


}
