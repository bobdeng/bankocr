package cn.bobdeng.bankscanner;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class AccountsOutputTest {
    AccountRepositoryImpl accountRepository;

    @Before
    public void setup() {
        accountRepository = new AccountRepositoryImpl();
        Repositories.accountRepository = accountRepository;
    }

    @Test
    public void test_right() {
        List<String> inputLines=new ArrayList<>();
        inputLines.addAll(DigitalChars.getCharsLines("345882865"));
        accountRepository.lines=inputLines;
        Accounts accounts=Accounts.read();

        List<String> lines = accounts.toLines();
        assertTrue(lines.contains("345882865"));
    }

    @Test
    public void test_wrong_with_one_option() {
        List<String> inputLines=new ArrayList<>();
        inputLines.addAll(DigitalChars.getCharsLines("111111111"));
        accountRepository.lines=inputLines;
        Accounts accounts=Accounts.read();
        List<String> lines = accounts.toLines();
        assertTrue(lines.contains("711111111"));
    }
    @Test
    public void test_wrong_with_more_option() {
        List<String> inputLines=new ArrayList<>();
        inputLines.addAll(DigitalChars.getCharsLines("345682865"));
        accountRepository.lines=inputLines;
        Accounts accounts=Accounts.read();
        List<String> lines = accounts.toLines();
        assertTrue(lines.contains("345682865 AMB"));
    }

    @Test
    public void test_wrong_with_no_option() {
        List<String> inputLines=new ArrayList<>();
        inputLines.addAll(DigitalChars.getCharsLines("991111118"));
        accountRepository.lines=inputLines;
        Accounts accounts=Accounts.read();
        List<String> lines = accounts.toLines();
        assertTrue(lines.contains("991111118 ILL"));
    }

    @Test
    public void test_wrong_with_error() {
        List<String> inputLines=new ArrayList<>();
        inputLines.add("                           ");
        inputLines.add("     |  |  |  |  |  |  |  |");
        inputLines.add("     |  |  |  |  |  |  |  |");
        inputLines.add("");
        accountRepository.lines=inputLines;
        Accounts accounts=Accounts.read();
        List<String> lines = accounts.toLines();
        assertTrue(lines.contains("?11111111 ERR"));
    }
}
