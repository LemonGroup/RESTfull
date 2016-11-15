package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.datamodel.KeyWord;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.lemongroup.lemonstat.rest.db.CatalogRepository;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class AdminController {

    /**
     * Person CRUD methods
     */
    //GET persons
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPersons() {

        List<Person> list = new CatalogRepository().getAllPersons();
        if (list.size() == 0) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    //Create new person
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.POST)
    public ResponseEntity<Person> postNewPerson(@RequestBody Person person) {
	//Do something with repository
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    //Update person name
    @RequestMapping(value = "/catalog/persons/{personName}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable String personName, @RequestBody Person newPerson ) {
	//Do something with repository
        return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
    }

    //Delete person
    @RequestMapping(value = "/catalog/persons/{personName}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePerson(@PathVariable String personName) {
	//Do something with repository
        return new ResponseEntity<Person>(new Person(personName), HttpStatus.OK);
    }

    /**
     * Keywords CRUD methods
     */
    //Get all keywords by person
    @RequestMapping(value = "/catalog/keywords", method = RequestMethod.GET)
    public ResponseEntity<List<KeyWord>> getAllKeyWordsByPerson(@RequestParam(value = "person") String person) {

        List<KeyWord> list = new CatalogRepository().getAllKeyWordsByPerson(person);
        if (list.size() == 0) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<KeyWord>>(list, HttpStatus.OK);
    }

    //Create new keyword to person
    @RequestMapping(value = "/catalog/keywords/{person}", method = RequestMethod.POST)
    public ResponseEntity<KeyWord> postNewKeyWordToPerson(@PathVariable String person, @RequestBody KeyWord keyword ) {
	//Do something with repository
        return new ResponseEntity<KeyWord>(keyword, HttpStatus.OK);
    }

    //Update keyword by person
    @RequestMapping(value = "/catalog/keywords/{person}/{keyword}", method = RequestMethod.PUT)
    public ResponseEntity<KeyWord> updateKeyWordByPerson(
	    @PathVariable String person, 
	    @PathVariable String keyword, 
	    @RequestBody KeyWord newKeyWord ) {
	//Do something with repository
        return new ResponseEntity<KeyWord>(newKeyWord, HttpStatus.OK);
    }

    //Delete keyword by person
    @RequestMapping(value = "/catalog/keywords/{person}/{keyword}", method = RequestMethod.DELETE)
    public ResponseEntity<KeyWord> deleteKeyWordForPerson(
	    @PathVariable String person, 
	    @PathVariable String keyword) {
	//Do something with repository
        return new ResponseEntity<KeyWord>(new KeyWord(keyword), HttpStatus.OK);
    }

    /**
     * Sites CRUD methods
     */
    //Get all sites
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.GET)
    public ResponseEntity<List<Site>> getAllSites() {

        List<Site> list = new CatalogRepository().getAllSites();
        if (list.size() == 0) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Site>>(list, HttpStatus.OK);
    }
    //
    //Create new site
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.POST)
    public ResponseEntity<Site> postNewSite(@RequestBody Site site) {
	//Do something with repository
        return new ResponseEntity<Site>(site, HttpStatus.OK);
    }

    //Update site name
    @RequestMapping(value = "/catalog/sites/{site}/", method = RequestMethod.PUT)
    public ResponseEntity<Site> updateSite(@PathVariable String site, @RequestBody Site newSite ) {
	//Do something with repository
        return new ResponseEntity<Site>(newSite, HttpStatus.OK);
    }

    //Delete site
    @RequestMapping(value = "/catalog/sites/{site}/", method = RequestMethod.DELETE)
    public ResponseEntity<Site> deleteSite(@PathVariable String site) {
	//Do something with repository
        return new ResponseEntity<Site>(new Site(site), HttpStatus.OK);
    }

}
