package cn.bobdeng.bankscanner;

import java.util.*;
import java.util.stream.Collectors;

public class DigitalChar {
    private char character;
    String lines;

    public DigitalChar(String line1, String line2, String line3) {
        this.lines=line1+line2+line3;
        this.character=DigitalChars.getChar(lines,'?');
    }


    public DigitalChar(char c) {
        this.character=c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalChar that = (DigitalChar) o;
        return character == that.character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(String.valueOf(character));
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }

    public int getNumber() {
        return new Integer(String.valueOf(character));
    }

    public boolean isUncorgnized() {
        return character=='?';
    }


}
