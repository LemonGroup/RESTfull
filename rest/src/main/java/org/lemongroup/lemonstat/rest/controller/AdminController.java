package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.db.*;
import org.lemongroup.lemonstat.rest.service.KeywordService;
import org.lemongroup.lemonstat.rest.service.PersonService;
import org.lemongroup.lemonstat.rest.utils.AccountHandler;
import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.lemongroup.lemonstat.rest.datamodel.CatalogList;
import org.lemongroup.lemonstat.rest.datamodel.AuthResponse;
import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;
import org.lemongroup.lemonstat.rest.datamodel.DailyStat;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    KeywordService keywordService;

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/user/auth", method = RequestMethod.GET)
    public ResponseEntity<AuthResponse> auth(
            @RequestParam Map<String, String> authParams) {

        AccountHandler ah = AccountHandler.getInstance();
        AuthResponse authResp;
        //if auth success
        if (ah.auth(authParams)) {
            authResp = ah.getAuthResponse(authParams.get("user"));
            return new ResponseEntity<AuthResponse>(authResp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //Get all catalogs
    @RequestMapping(value = "/catalog/catalogs", method = RequestMethod.GET)
    public ResponseEntity<CatalogList> getAllCatalogs(
            @RequestHeader(value = "Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
        CatalogList list = new CatalogRepository().getAllCatalogsByGroupId(groupId);
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<CatalogList>(list, HttpStatus.OK);
    }

    /**
     * Person CRUD methods
     */
    //GET persons
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.GET)
    //public ResponseEntity<List<Person>> getAllPersons(
    public ResponseEntity<?> getAllPersons(
            @RequestHeader(value = "Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
	Collection list = personService.getAllPersonsByGroup(groupId);
        //List<Person> list = new CatalogRepository().getAllPersons(groupId);
	System.out.println(personService.getAllPersonsByGroup(groupId));
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Create new person
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.POST)
    public ResponseEntity<Person> postNewPerson(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Person person) {
        AccountHandler ah = AccountHandler.getInstance();
        long personId = personService.createNewPersonByGroup(person.getPersonName(), ah.getGroupIdByToken(token));
        person.setId(personId);
        //Do something with repository
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    //Update person name
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Person newPerson) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean updated = personService.updatePersonByGroup(newPerson.getId(), newPerson.getPersonName(), ah.getGroupIdByToken(token));

	if(updated) { 
	    return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete person
    @RequestMapping(value = "/catalog/persons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerson(
            @RequestHeader(value = "Auth-Token") String token,
	    @PathVariable long id ) {
        AccountHandler ah = AccountHandler.getInstance();
        //boolean updated = pr.deletePersonByGroup(Long.parseLong(id), ah.getGroupIdByToken(token));
        boolean updated = personService.deletePersonByGroup(id, ah.getGroupIdByToken(token));

	if(updated) { 
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Keywords CRUD methods
     */
    //Get all keywords 
    @RequestMapping(value = "/catalog/keywords", method = RequestMethod.GET)
    public ResponseEntity<List<Keyword>> getAllKeywords(
            @RequestHeader(value = "Auth-Token") String token) {
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
    @RequestMapping(value = "/catalog/keywords", method = RequestMethod.POST)
    public ResponseEntity<Keyword> postNewKeywordToPerson(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Keyword keyword) {
        AccountHandler ah = AccountHandler.getInstance();
        long keywordId = keywordService.createNewKeywordByGroup(keyword.getPersonId(), keyword.getKeyword(), ah.getGroupIdByToken(token));
	System.out.println(keyword.getKeyword());
        keyword.setId(keywordId);
        //Do something with repository
        return new ResponseEntity<Keyword>(keyword, HttpStatus.OK);
    }

    //Update keyword by person
    @RequestMapping(value = "/catalog/keywords", method = RequestMethod.PUT)
    public ResponseEntity<Keyword> updateKeywordByPerson(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Keyword newKeyword) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean updated = keywordService.updateKeywordByGroup(newKeyword.getId(), newKeyword.getKeyword(), ah.getGroupIdByToken(token));
	if(updated) { 
	    return new ResponseEntity<Keyword>(newKeyword, HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete keyword by person
    @RequestMapping(value = "/catalog/keywords/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteKeyword(
            @RequestHeader(value = "Auth-Token") String token,
	    @PathVariable long id ) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean deleted = keywordService.deleteKeywordByGroup(id, ah.getGroupIdByToken(token));

	if(deleted) { 
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Sites CRUD methods
     */
    //Get all sites
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.GET)
    public ResponseEntity<List<Site>> getAllSites(
            @RequestHeader(value = "Auth-Token") String token) {
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
    public ResponseEntity<Site> postNewSite(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Site site) {
        AccountHandler ah = AccountHandler.getInstance();
        ISiteRepository siteRepository = FakeSiteRepository.getInstance();
        long siteId = siteRepository.createNewSiteByGroup(site.getSite(), ah.getGroupIdByToken(token));
        site.setId(siteId);
        //Do something with repository
        return new ResponseEntity<Site>(site, HttpStatus.OK);
    }

    //Update site name
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.PUT)
    public ResponseEntity<Site> updateSite(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Site newSite) {
        AccountHandler ah = AccountHandler.getInstance();
        ISiteRepository siteRepository = FakeSiteRepository.getInstance();
        boolean updated = siteRepository.updateSiteByGroup(newSite.getId(), newSite.getSite(), ah.getGroupIdByToken(token));
	if(updated) { 
	    return new ResponseEntity<Site>(newSite, HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete site
    @RequestMapping(value = "/catalog/sites/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSite(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable long id) {
        AccountHandler ah = AccountHandler.getInstance();
        ISiteRepository pr = FakeSiteRepository.getInstance();
        boolean deleted = pr.deleteSiteByGroup(id, ah.getGroupIdByToken(token));

	if(deleted) { 
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /**
     *Get statistic methods
     */
    @RequestMapping(value = "/stat/over_stat", method = RequestMethod.GET)
    public ResponseEntity<List<OverMentionStatItem>> getOverStat(
            @RequestHeader(value = "Auth-Token") String token,
	    @RequestParam(value = "siteId") long siteId) {

        AccountHandler ah = AccountHandler.getInstance();
	List<OverMentionStatItem> list = new StatRepository().getOverStatBySiteIdByGroup(siteId, ah.getGroupIdByToken(token));

        if (list.size() == 0) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OverMentionStatItem>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/stat/daily_stat", method = RequestMethod.GET)
    public ResponseEntity<List<DailyStat>> getDailyStat(
            @RequestHeader(value = "Auth-Token") String token,
	    @RequestParam Map<String, String> requestParams) {

        AccountHandler ah = AccountHandler.getInstance();
	List<DailyStat> list = new StatRepository().getDaylyStatByParamsByGroup(requestParams, ah.getGroupIdByToken(token));

        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
