package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Сайт
 */
public class Site {
    private String site;

    public Site(){
    }

    public Site(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
