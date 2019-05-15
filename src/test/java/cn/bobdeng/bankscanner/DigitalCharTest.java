package cn.bobdeng.bankscanner;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DigitalCharTest {
    @Before
    public void setup() {

    }

    @Test
    public void test() {
        assertEquals(new DigitalChar("   ","  |","  |"),new DigitalChar('1'));
        assertEquals(new DigitalChar(" _ "," _|","|_ "),new DigitalChar('2'));
        assertEquals(new DigitalChar(" _ "," _|"," _|"),new DigitalChar('3'));
        assertEquals(new DigitalChar("   ","|_|","  |"),new DigitalChar('4'));
        assertEquals(new DigitalChar(" _ ","|_ "," _|"),new DigitalChar('5'));
        assertEquals(new DigitalChar(" _ ","|_ ","|_|"),new DigitalChar('6'));
        assertEquals(new DigitalChar(" _ ","  |","  |"),new DigitalChar('7'));
        assertEquals(new DigitalChar(" _ ","|_|","|_|"),new DigitalChar('8'));
        assertEquals(new DigitalChar(" _ ","|_|"," _|"),new DigitalChar('9'));
        assertEquals(new DigitalChar(" _ ","| |","|_|"),new DigitalChar('0'));

    }

    @Test
    public void test_invalid_digital(){
        assertEquals(new DigitalChar("_  ","  |","  |"),new DigitalChar('?'));
    }

}
