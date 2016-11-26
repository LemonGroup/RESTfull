package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.lemongroup.lemonstat.rest.datamodel.CatalogList;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * * Catalog data repository
 */
public class CatalogRepository {

    public CatalogList getAllCatalogsByGroupId(long groupId) {
        //Fake data
/*        if (groupId == 3) {
            List<Site> slist = new ArrayList<>();
            slist.add(new Site(1, "lenta.ru"));
            slist.add(new Site(2, "rbc.ru"));
            List<Person> plist = new ArrayList<>();
            plist.add(new Person(1, "Путин"));
            plist.add(new Person(2, "Медведев"));
            plist.add(new Person(3, "Навальный"));
            List<Keyword> klist = new ArrayList<>();
            klist.add(new Keyword(1, 1, "Путину"));
            klist.add(new Keyword(2, 1, "Путина"));
            klist.add(new Keyword(3, 2, "Медведева"));
            klist.add(new Keyword(4, 2, "Медведеву"));
            klist.add(new Keyword(5, 3, "Навальному"));
            klist.add(new Keyword(6, 3, "Навального"));
            return new CatalogList(slist, plist, klist);
        }
*/        return null;
    }

    public List<Person> getAllPersons(long groupId) {
        //Fake data
 /*       if (groupId == 3) {
            List<Person> list = new ArrayList<>();
            list.add(new Person(1, "Путин"));
            list.add(new Person(2, "Медведев"));
            list.add(new Person(3, "Навальный"));
            return list;
        }
*/        return null;
    }

    public List<Keyword> getAllKeywordsByPerson(String person) {
        //Fake data
        List<Keyword> list = new ArrayList<>();
        if (person.equalsIgnoreCase("Путин")) {
            list.add(new Keyword(1, 1, "Путину"));
            list.add(new Keyword(2, 1, "Путина"));
        } else if (person.equalsIgnoreCase("Медведев")) {
            list.add(new Keyword(3, 2, "Медведеву"));
            list.add(new Keyword(4, 2, "Медведева"));
        } else if (person.equalsIgnoreCase("Навальный")) {
            list.add(new Keyword(5, 3, "Навального"));
            list.add(new Keyword(6, 3, "Навальному"));
        }
        return list;
    }

    public List<Keyword> getAllKeywords(long groupId) {
        //Fake data
        if (groupId == 3) {
            List<Keyword> list = new ArrayList<>();
            list.add(new Keyword(1, 1, "Путину"));
            list.add(new Keyword(2, 1, "Путина"));
            list.add(new Keyword(3, 2, "Медведеву"));
            list.add(new Keyword(4, 2, "Медведева"));
            list.add(new Keyword(5, 3, "Навального"));
            list.add(new Keyword(6, 3, "Навальному"));
            return list;
        }
        return null;
    }

    public List<Site> getAllSites(long groupId) {
        //Fake data
        if (groupId == 3) {
            List<Site> list = new ArrayList<>();
            list.add(new Site(1, "lenta.ru"));
            list.add(new Site(2, "rbc.ru"));
            return list;
        }
        return null;
    }
}


