package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.utils.AccountHandler;
import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.lemongroup.lemonstat.rest.datamodel.CatalogList;
import org.lemongroup.lemonstat.rest.datamodel.AuthResponse;
import org.lemongroup.lemonstat.rest.db.CatalogRepository;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class AdminController {
    /**
     * Auth methods
     */
    //GET persons
    @RequestMapping(value = "/user/auth", method = RequestMethod.GET)
    public ResponseEntity<AuthResponse> auth(@RequestParam Map<String, String> authParams) {

	System.out.println("auth: " +  authParams);

	AccountHandler ah = AccountHandler.getInstance();
	AuthResponse authResp;
	//if auth success
	if(ah.auth(authParams)) {
	    authResp = ah.getAuthResponse(authParams.get("user"));
	    return new ResponseEntity<AuthResponse>(authResp, HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
    }

    //Get all catalogs
    @RequestMapping(value = "/catalog/catalogs", method = RequestMethod.GET)
    public ResponseEntity<CatalogList> getAllCatalogs(@RequestHeader(value="Auth-Token") String token) {
	AccountHandler ah = AccountHandler.getInstance();
	long groupId = ah.getGroupIdByToken(token);
        CatalogList list = new CatalogRepository().getAllCatalogsByGroupId(groupId);
        if (list == null) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<CatalogList>(list, HttpStatus.OK);
    }

    /**
     * Person CRUD methods
     */
    //GET persons
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPersons(@RequestHeader(value="Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
        List<Person> list = new CatalogRepository().getAllPersons(groupId);
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
/*
    //Delete person
    @RequestMapping(value = "/catalog/persons/{personName}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePerson(@PathVariable String personName) {
	//Do something with repository
        return new ResponseEntity<Person>(new Person(personName), HttpStatus.OK);
    }
*/
    /**
     * Keywords CRUD methods
     */
    //Get all keywords 
    @RequestMapping(value = "/catalog/keywords", method = RequestMethod.GET)
    public ResponseEntity<List<Keyword>> getAllKeywords(@RequestHeader(value="Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
        List<Keyword> list = new CatalogRepository().getAllKeywords(groupId);
        if (list.size() == 0) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Keyword>>(list, HttpStatus.OK);
    }

    //Create new keyword to person
    @RequestMapping(value = "/catalog/keywords/{person}", method = RequestMethod.POST)
    public ResponseEntity<Keyword> postNewKeywordToPerson(@PathVariable String person, @RequestBody Keyword keyword ) {
	//Do something with repository
        return new ResponseEntity<Keyword>(keyword, HttpStatus.OK);
    }

    //Update keyword by person
    @RequestMapping(value = "/catalog/keywords/{person}/{keyword}", method = RequestMethod.PUT)
    public ResponseEntity<Keyword> updateKeywordByPerson(
	    @PathVariable String person, 
	    @PathVariable String keyword, 
	    @RequestBody Keyword newKeyword ) {
	//Do something with repository
        return new ResponseEntity<Keyword>(newKeyword, HttpStatus.OK);
    }
/*
    //Delete keyword by person
    @RequestMapping(value = "/catalog/keywords/{person}/{keyword}", method = RequestMethod.DELETE)
    public ResponseEntity<Keyword> deleteKeywordForPerson(
	    @PathVariable String person, 
	    @PathVariable String keyword) {
	//Do something with repository
        return new ResponseEntity<Keyword>(new Keyword(keyword), HttpStatus.OK);
    }
*/
    /**
     * Sites CRUD methods
     */
    //Get all sites
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.GET)
    public ResponseEntity<List<Site>> getAllSites(@RequestHeader(value="Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
        List<Site> list = new CatalogRepository().getAllSites(groupId);
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
/*
    //Delete site
    @RequestMapping(value = "/catalog/sites/{site}/", method = RequestMethod.DELETE)
    public ResponseEntity<Site> deleteSite(@PathVariable String site) {
	//Do something with repository
        return new ResponseEntity<Site>(new Site(site), HttpStatus.OK);
    }
*/
}
