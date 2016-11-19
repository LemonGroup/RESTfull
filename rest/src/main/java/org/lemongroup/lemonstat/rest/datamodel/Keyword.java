package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Ключевое слово персоны
 */
public class Keyword {
    private long id;
    private long personId;
    private String keyword;

    public Keyword(){
    }

    public Keyword(long id, long personId, String keyword) {
	this.id = id;
	this.personId = personId;
        this.keyword = keyword;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
