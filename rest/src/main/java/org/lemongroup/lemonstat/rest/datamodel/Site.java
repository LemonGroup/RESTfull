package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Сайт
 */
public class Site {
    private long id;
    private String site;

    public Site(){
    }

    public Site(long id, String site) {
        this.id = id;
        this.site = site;
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
}
