package cn.bobdeng.bankscanner;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    public List<String> lines;

    @Override
    public List<String> readLines() {
        return lines;
    }
}
