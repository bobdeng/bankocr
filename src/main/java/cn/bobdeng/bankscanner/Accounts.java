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

    public Accounts() {
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
        return new Accounts();
    }

    public int size() {
        return 1;
    }

    public Account get(int index) {
        return accounts.get(index);
    }

    public List<String> toLines() {
        return accounts.stream()
                .map(account -> {
                    account.tryCorrect();
                    String result = account.toString();
                    if (!account.isValid()) {
                        account.tryCorrect();
                        if(account.isHasMoreThanOnePossible()){
                            return result+" AMB";
                        }
                        if (account.hasUnrecognized()) {
                            return result + " ERR";
                        }
                        return result + " ILL";
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    public void add(Account account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        return accounts.toString();
    }
}
