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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site1 = (Site) o;

        if (groupId != site1.groupId) return false;
        return site.equals(site1.site);

    }

    @Override
    public int hashCode() {
        int result = site.hashCode();
        result = 31 * result + (int) (groupId ^ (groupId >>> 32));
        return result;
    }
}
