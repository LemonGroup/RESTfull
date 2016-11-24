package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Represents an instance of a person.
 */

public class Person {

    private long id;
    private String personName;

    public Person() {

    }

    public Person(long id, String personName) {
        this.id = id;
        this.personName = personName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

}
