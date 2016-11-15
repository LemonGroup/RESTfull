package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.datamodel.KeyWord;
import org.lemongroup.lemonstat.rest.datamodel.Site;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
/**
 *  * Catalog data repository
 *   */
public class CatalogRepository {

    public List<Person> getAllPersons() {
        //Fake data
        List<Person> list = new ArrayList<>();
            list.add(new Person("Путин"));
            list.add(new Person("Медведев"));
            list.add(new Person("Навальный"));
	return list;
    }
    public List<KeyWord> getAllKeyWordsByPerson(String person) {
        //Fake data
        List<KeyWord> list = new ArrayList<>();
	if(person.equalsIgnoreCase("Путин")){
            list.add(new KeyWord("Путину"));
            list.add(new KeyWord("Путина"));
	} else if(person.equalsIgnoreCase("Медведев")){
            list.add(new KeyWord("Медведеву"));
            list.add(new KeyWord("Медведева"));
	} else if(person.equalsIgnoreCase("Навальный")){
            list.add(new KeyWord("Навального"));
            list.add(new KeyWord("Навальному"));
	}
	return list;
    }
    public List<Site> getAllSites() {
        //Fake data
        List<Site> list = new ArrayList<>();
            list.add(new Site("lenta.ru"));
            list.add(new Site("rbc.ru"));
	return list;
    }
}


