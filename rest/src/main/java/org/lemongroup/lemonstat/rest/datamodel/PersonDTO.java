package org.lemongroup.lemonstat.rest.datamodel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "persons", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class PersonDTO {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String personName;

    @Column(name = "groupid")
    private long groupId;

    public PersonDTO(String personName, long groupId) {
        this.personName = personName;
        this.groupId = groupId;
    }

    public PersonDTO(){

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

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
