package org.lemongroup.lemonstat.rest.datamodel;

import java.util.List;
/**
 * Список всех справочников
 */
public class CatalogList {

    List<Site> sites;
    List<Person> persons;
    List<Keyword> keywords;

    public CatalogList(){
    }

    public CatalogList(List<Site> sites, List<Person> persons, List<Keyword> keywords) {
        this.sites = sites;
        this.persons = persons;
        this.keywords = keywords;
    }

    public List getSites() {
        return sites;
    }

    public void setSites(List sites) {
        this.sites = sites;
    }

    public List getPersons() {
        return persons;
    }

    public void setPersons(List perons) {
        this.persons = persons;
    }

    public List getKeywords() {
        return keywords;
    }

    public void setKeywords(List keywords) {
        this.keywords = keywords;
    }
}
