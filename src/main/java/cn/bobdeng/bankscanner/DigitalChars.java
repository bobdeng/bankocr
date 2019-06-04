package cn.bobdeng.bankscanner;

import java.util.*;
import java.util.stream.Collectors;

public class DigitalChars {
    static Map<String, Character> digitals = new HashMap<>();
    static Map<Character, String> digitalsLines;

    static {
        digitals.put("     |  |", '1');
        digitals.put(" _  _||_ ", '2');
        digitals.put(" _  _| _|", '3');
        digitals.put("   |_|  |", '4');
        digitals.put(" _ |_  _|", '5');
        digitals.put(" _ |_ |_|", '6');
        digitals.put(" _   |  |", '7');
        digitals.put(" _ |_||_|", '8');
        digitals.put(" _ |_| _|", '9');
        digitals.put(" _ | ||_|", '0');
        digitalsLines = digitals.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static char getChar(String lines, char defaultValue) {
        return digitals.getOrDefault(lines, defaultValue);
    }

    public static List<DigitalChar> getPossibleChar(String lines) {
        return digitals.entrySet().stream()
                .filter(entry -> isSimilar(lines, entry.getKey()))
                .map(entry -> new DigitalChar(entry.getValue()))
                .collect(Collectors.toList());
    }

    private static boolean isSimilar(String oldLine, String lines) {
        int differentTimes = 0;
        for (int i = 0; i < lines.length(); i++) {
            if (oldLine.charAt(i) != lines.charAt(i)) {
                differentTimes++;
            }
            if (differentTimes > 1) {
                break;
            }
        }
        return differentTimes == 1;
    }

    public static List<String> getCharsLines(String account) {
        List<String> lines=new ArrayList<>();
        for(int i=0;i<account.length();i++){
            lines.add(digitalsLines.get(account.charAt(i)));
        }
        List<String> result=new ArrayList<>();
        result.add(getLine(lines,0));
        result.add(getLine(lines,1));
        result.add(getLine(lines,2));
        result.add("");
        return result;
    }

    private static String getLine(List<String> lines, int lineIndex) {
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<lines.size();i++){
            int beginIndex = lineIndex * 3;
            stringBuffer.append(lines.get(i), beginIndex, beginIndex +3);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(getCharsLines("111111111"));
    }
}
