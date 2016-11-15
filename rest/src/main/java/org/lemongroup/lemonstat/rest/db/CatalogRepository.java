package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.Person;

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
            list.add(new Person("Мариарти"));
            list.add(new Person("Ватсон"));
            list.add(new Person("Путин"));
	return list;
    }
}


