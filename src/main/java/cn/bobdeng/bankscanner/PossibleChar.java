package cn.bobdeng.bankscanner;

import java.util.List;
import java.util.function.Consumer;

public class PossibleChar {
    List<DigitalChar> chars;
    DigitalChar source;

    public PossibleChar(List<DigitalChar> chars, DigitalChar source) {
        this.chars = chars;
        this.source = source;
    }

    public void forEach(Consumer<DigitalChar> charConsumer) {
        chars.forEach(charConsumer);
        charConsumer.accept(source);
    }

    @Override
    public String toString() {
        return chars.toString();
    }

    public boolean isSource(DigitalChar digitalChar) {
        return source.equals(digitalChar);
    }

    public boolean hasPossible() {
        return chars.size()>=1;
    }
}
