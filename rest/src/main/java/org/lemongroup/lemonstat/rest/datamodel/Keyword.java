package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Ключевое слово персоны
 */
public class Keyword {
    private long id;
    private long personId;
    private String keywordName;

    public Keyword() {
    }

    public Keyword(long id, long personId, String keywordName) {
        this.id = id;
        this.personId = personId;
        this.keywordName = keywordName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }
}
