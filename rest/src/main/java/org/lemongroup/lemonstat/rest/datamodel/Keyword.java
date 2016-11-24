package org.lemongroup.lemonstat.rest.datamodel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Ключевое слово персоны
 */
@Entity
@Table(name = "keywords", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "personid"})})
public class Keyword {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "personid", nullable = false)
    private long personId;
    @Column(name = "name", nullable = false)
    private String keyword;

    public Keyword() {
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
