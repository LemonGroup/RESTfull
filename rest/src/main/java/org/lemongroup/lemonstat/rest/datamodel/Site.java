package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Represents an instance of a website.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sites", uniqueConstraints = {@UniqueConstraint(columnNames = {"name","groupid"})})
@JsonIgnoreProperties({"groupId"})
public class Site {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String site;

    @Column(name = "groupid")
    private long groupId;

    public Site(){

    }

    public Site(String site, long groupId) {
        this.site = site;
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
