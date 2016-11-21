package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Website
 */
public class Site {
    private long id;
    private String siteName;

    public Site() {
    }

    public Site(long id, String siteName) {
        this.id = id;
        this.siteName = siteName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
