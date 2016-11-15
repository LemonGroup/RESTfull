package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Ключевое слово персоны
 */
public class KeyWord {
    private String keyWord;

    public KeyWord(){
    }

    public KeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
