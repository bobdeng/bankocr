package cn.bobdeng.bankscanner;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Accounts {
    List<Account> accounts;

    public Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    private Accounts() {
        readAccounts();
    }

    private void readAccounts() {
        accounts = new ArrayList<>();
        List<String> lines = Repositories.accountRepository.readLines();
        ListIterator<String> listIterator = lines.listIterator();
        while (listIterator.hasNext()) {
            Account account = new Account(listIterator.next(), listIterator.next(), listIterator.next());
            listIterator.next();
            accounts.add(account);

        }
    }


    public static Accounts read() {
        Accounts accounts= new Accounts();
        accounts.readAccounts();
        return accounts;
    }

    public int size() {
        return accounts.size();
    }

    public Account get(int index) {
        return accounts.get(index);
    }

    public List<String> toLines() {
        return accounts.stream()
                .map(account -> {
                    account.tryCorrect();
                    return getAccountOutput(account);
                })
                .collect(Collectors.toList());
    }

    private String getAccountOutput(Account account) {
        StringBuffer  result= new StringBuffer(account.toString());
        if (!account.isValid()) {
            return addFailTail(account, result);
        }
        return result.toString();
    }

    private String addFailTail(Account account, StringBuffer result) {
        if(account.isHasMoreThanOnePossible()){
            return result+" AMB";
        }
        if (account.hasUnrecognized()) {
            return result + " ERR";
        }
        return result + " ILL";
    }

    public void add(Account account) {
        accounts.add(account);
    }

}
