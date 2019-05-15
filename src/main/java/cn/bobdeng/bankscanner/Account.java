package cn.bobdeng.bankscanner;

import java.util.*;
import java.util.stream.Collectors;

public class Account {
    private List<DigitalChar> charList;
    private boolean hasMoreThanOnePossible = false;

    public boolean isHasMoreThanOnePossible() {
        return hasMoreThanOnePossible;
    }

    public Account(List<DigitalChar> charList) {
        this.charList = charList;
    }

    public Account(String str) {
        char[] chars = str.toCharArray();
        charList = new ArrayList<>();
        for (char c : chars) {
            charList.add(new DigitalChar(c));
        }
    }

    public Account(String line1, String line2, String line3) {
        int charCounts = line1.length() / 3;
        List<DigitalChar> chars = new ArrayList<>();
        for (int i = 0; i < charCounts; i++) {
            int beginIndex = i * 3;
            chars.add(new DigitalChar(
                    line1.substring(beginIndex, beginIndex + 3),
                    line2.substring(beginIndex, beginIndex + 3),
                    line3.substring(beginIndex, beginIndex + 3)
            ));
        }
        this.charList = chars;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return account.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(charList);
    }

    @Override
    public String toString() {
        return charList.stream()
                .map(DigitalChar::toString)
                .collect(Collectors.joining());
    }

    public boolean isValid() {
        if (hasUnrecognized()) {
            return false;
        }
        return getCheckSum() % 11 == 0;
    }

    private int getCheckSum() {
        int checkSum = 0;
        for (int i = 0; i < charList.size(); i++) {
            checkSum += (i + 1) * charList.get(charList.size() - i - 1).getNumber();
        }
        return checkSum;
    }

    public boolean hasUnrecognized() {
        return charList.stream()
                .filter(digitalChar -> digitalChar.isUncorgnized())
                .findAny()
                .isPresent();
    }

    public void tryCorrect() {
        if (isValid()) {
            return;
        }
        List<Account> accounts = getValidPossibleAccounts();
        correctAccount(accounts);

    }

    private void correctAccount(List<Account> accounts) {
        if (accounts.size() == 1) {
            this.charList = accounts.get(0).charList;
            return;
        }
        if (accounts.size() > 1) {
            hasMoreThanOnePossible = true;
        }
    }

    private List<Account> getValidPossibleAccounts() {
        return getAllPossibleAccounts()
                .stream()
                .filter(Account::isValid)
                .collect(Collectors.toList());
    }

    public List<Account> getAllPossibleAccounts() {
        List<PossibleChar> possibleChars = charList.stream()
                .map(dc -> new PossibleChar(DigitalChars.getPossibleChar(dc.lines), dc))
                .collect(Collectors.toList());
        Accounts accounts = new Accounts(new ArrayList<>());
        getAllPossibleAccounts(accounts, possibleChars);
        return accounts.accounts;
    }

    private void getAllPossibleAccounts(Accounts accounts, List<PossibleChar> possibleChars) {
        for (int i = 0; i < possibleChars.size(); i++) {
            getPossibleAccount(accounts, possibleChars, i);
        }

    }

    private void getPossibleAccount(Accounts accounts, List<PossibleChar> possibleChars, int cursor) {
        PossibleChar possibleChar = possibleChars.get(cursor);
        if (possibleChar.hasPossible()) {
            getEveryPossible(accounts, possibleChars, cursor, possibleChar);
        }
    }

    private void getEveryPossible(Accounts accounts, List<PossibleChar> possibleChars, int cursor, PossibleChar possibleChar) {
        possibleChar.forEach(digitalChar -> {
            accounts.add(new Account(getOnePossibleAccount(possibleChars, cursor, digitalChar)));
        });
    }

    private List<DigitalChar> getOnePossibleAccount(List<PossibleChar> possibleChars, int cursor, DigitalChar digitalChar) {
        List<DigitalChar> chars = new ArrayList<>();
        chars.addAll(getDigialChars(possibleChars, 0, cursor ));
        chars.add(digitalChar);
        chars.addAll(getDigialChars(possibleChars, cursor+1, possibleChars.size()));
        return chars;
    }

    private List<DigitalChar> getDigialChars(List<PossibleChar> possibleChars, int from, int to) {
        return possibleChars
                .stream()
                .map(possibleChar -> possibleChar.source)
                .skip(from)
                .limit(to - from)
                .collect(Collectors.toList());
    }


}
