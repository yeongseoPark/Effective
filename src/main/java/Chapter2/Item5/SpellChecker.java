package Chapter2.Item5;

import java.util.Objects;

public class SpellChecker {
    private final Lexicon dictionary;

    // 인스턴스 생성시 생성자에 필요한 자원(사전)을 넘김
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {return true;}
    // ..
}

class Lexicon {

}