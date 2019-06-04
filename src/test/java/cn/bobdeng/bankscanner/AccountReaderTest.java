package cn.bobdeng.bankscanner;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class AccountReaderTest {
    AccountRepositoryImpl accountRepository;

    @Before
    public void setup() {
        accountRepository = new AccountRepositoryImpl();
        Repositories.accountRepository = accountRepository;
    }

    @Test
    public void test() {
        accountRepository.lines= Arrays.asList(getLines());
        Accounts accounts = Accounts.read();
        assertNotNull(accounts);
        assertEquals(accounts.size(),2);
        assertEquals(accounts.get(0),new Account("123456789"));
        assertEquals(accounts.get(1),new Account("183456789"));
    }

    private String[] getLines() {
        return new String[]{
                "    _  _     _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|",
                "  ||_  _|  | _||_|  ||_| _|",
                "",
                "    _  _     _  _  _  _  _ ",
                "  ||_| _||_||_ |_   ||_||_|",
                "  ||_| _|  | _||_|  ||_| _|",
                ""
        };
    }
}
