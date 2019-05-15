package cn.bobdeng.bankscanner;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import org.junit.Before;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AccountTest {
    @Before
    public void setup() {

    }

    @Test
    public void test_illigal_correct() {
        Account account=new Account(
                " _     _  _  _  _  _  _  _ ",
                " _||_||_ |_||_| _||_||_||_ ",
                " _|  | _||_||_||_ |_||_| _|");
        assertFalse(account.isValid());
        assertEquals(account.toString(),"345882885");
        account.tryCorrect();
        assertTrue(account.isHasMoreThanOnePossible());
    }
    @Test
    public void test_illigal_correct_more_option() {
        Account account=new Account(
                " _     _  _  _  _  _  _  _ ",
                " _||_||_ |_||_| _||_||_ |_ ",
                " _   | _||_||_||_ |_||_| _|");
        assertFalse(account.isValid());
        assertEquals(account.toString(),"?45882865");
        account.tryCorrect();
        assertTrue(account.isValid());
        assertEquals(account.toString(),"345882865");
    }

    @Test
    public void test_account_valid(){
        assertTrue(new Account("345882865").isValid());
        assertTrue(new Account("966208943").isValid());
        assertFalse(new Account("145882865").isValid());
    }

    @Test
    public void test_account_find_possible(){
        List<String> charsLines = DigitalChars.getCharsLines("125");
        Account account = new Account(charsLines.get(0), charsLines.get(1), charsLines.get(2));
        List<String> collect = account.getAllPossibleAccounts()
                .stream()
                .map(Account::toString)
                .collect(Collectors.toList());
        assertTrue(collect.contains("725"));
        assertTrue(collect.contains("126"));
        assertTrue(collect.contains("125"));
        assertTrue(collect.contains("129"));
    }
}
