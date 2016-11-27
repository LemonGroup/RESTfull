package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.db.*;
import org.lemongroup.lemonstat.rest.service.KeywordService;
import org.lemongroup.lemonstat.rest.service.PersonService;
import org.lemongroup.lemonstat.rest.service.SiteService;
import org.lemongroup.lemonstat.rest.service.AccountService;
import org.lemongroup.lemonstat.rest.utils.AccountHandler;
import org.lemongroup.lemonstat.rest.datamodel.Account;
import org.lemongroup.lemonstat.rest.datamodel.Token;
import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.lemongroup.lemonstat.rest.datamodel.CatalogList;
import org.lemongroup.lemonstat.rest.datamodel.AuthResponse;
import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;
import org.lemongroup.lemonstat.rest.datamodel.DailyStat;

import java.util.Map;
import java.util.List;
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
import org.springframework.dao.DataIntegrityViolationException;

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

    @Autowired
    SiteService siteService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/user/auth", method = RequestMethod.GET)
    public ResponseEntity<?> auth(
            @RequestParam Map<String, String> authParams) {

	String username = authParams.get("user");
	//Compare passwords
	boolean isAuthenticated;
	try {
	    isAuthenticated = authParams.get("pass")
		.equals(accountService
			.getPasswordByUserName(username));
	} catch (Exception e) {
	    isAuthenticated = false;
	}
	System.out.println(isAuthenticated);
        if (isAuthenticated) {
	    Token token = new Token(accountService.createNewTokenForUsername(username));
            return new ResponseEntity<Token>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    //Check username is busy
    @RequestMapping(value = "/user/reguser/check_user", method = RequestMethod.POST)
    public ResponseEntity checkUsernameExists (
            @RequestParam(value = "username") String username) {
        boolean userIsBusy = accountService.checkUsernameExists(username);
	if (userIsBusy) {
	    return new ResponseEntity(HttpStatus.CONFLICT);
	}
        return new ResponseEntity(HttpStatus.OK);
    }
    //Check email is busy
    @RequestMapping(value = "/user/reguser/check_email", method = RequestMethod.POST)
    public ResponseEntity checkEmailExists (
            @RequestParam(value = "email") String email) {
        boolean emailIsBusy = accountService.checkEmailExists(email);
	if (emailIsBusy) {
	    return new ResponseEntity(HttpStatus.CONFLICT);
	}
        return new ResponseEntity(HttpStatus.OK);
    }
    //
    //Registration of new user
    @RequestMapping(value = "/user/reguser", method = RequestMethod.POST)
    public ResponseEntity<?> checkEmailExists (
            @RequestBody Account account) {
	String username = account.getUsername();
	//Create new group which named as Username
	String groupname = username;
	//Set default privilege as admin (magic number 2)
	account.setPrivilege((byte)2);
	try {
	    long groupId = accountService.createNewGroup(groupname);
	    long accountId = accountService.createNewAccountByGroup(account, groupId);
	    account.setId(accountId);
	} catch (DataIntegrityViolationException e) {
	    return new ResponseEntity(HttpStatus.CONFLICT);
	}
        return new ResponseEntity<Account>(account, HttpStatus.OK);

    }


    /**
     * Account CRUD methods
     */
    //GET accounts
    @RequestMapping(value = "/catalog/accounts", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccounts(
            @RequestHeader(value = "Auth-Token") String token) {

        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
	System.out.println("groupId" + groupId);
	List<?> list = (List)accountService.getAllAccountsByGroup(groupId);
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }
    //Create new account
    @RequestMapping(value = "/catalog/accounts", method = RequestMethod.POST)
    public ResponseEntity<?> postNewAccount(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Account account) {
        AccountHandler ah = AccountHandler.getInstance();
        long accountId = accountService.createNewAccountByGroup(account, ah.getGroupIdByToken(token));
        account.setId(accountId);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }
    //Update account privilege
    @RequestMapping(value = "/catalog/accounts/privilege", method = RequestMethod.PUT)
    public ResponseEntity updateAccountPrivilege(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Account newAccount) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean updated = accountService.updateAccountPrivilegeByGroup(newAccount.getId(), newAccount.getPrivilege(), ah.getGroupIdByToken(token));
	if(updated) { 
	    return new ResponseEntity(HttpStatus.OK);
	}
	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    //Update account email
    @RequestMapping(value = "/catalog/accounts/email", method = RequestMethod.PUT)
    public ResponseEntity updateAccountMail(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Account newAccount) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean updated = accountService.updateAccountMailByGroup(newAccount.getId(), newAccount.getEmail(), ah.getGroupIdByToken(token));
	if(updated) { 
	    return new ResponseEntity(HttpStatus.OK);
	}
	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    //Update account password
    @RequestMapping(value = "/catalog/accounts/password", method = RequestMethod.PUT)
    public ResponseEntity updateAccountPassword(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Account newAccount) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean updated = accountService.updateAccountPasswordByGroup(newAccount.getId(), newAccount.getPassword(), ah.getGroupIdByToken(token));
	if(updated) { 
	    return new ResponseEntity(HttpStatus.OK);
	}
	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    //Delete account
    @RequestMapping(value = "/catalog/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(
            @RequestHeader(value = "Auth-Token") String token,
	    @PathVariable long id) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean isDeleted = accountService.deleteAccountByGroup(id, ah.getGroupIdByToken(token));
	if(isDeleted) { 
	    return new ResponseEntity(HttpStatus.OK);
	}
	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    /**
     * Person CRUD methods
     */
    //GET persons
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPersons(
            @RequestHeader(value = "Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
	List<?> list = (List)personService.getAllPersonsByGroup(groupId);
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    //Create new person
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.POST)
    public ResponseEntity<Person> postNewPerson(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Person person) {
        AccountHandler ah = AccountHandler.getInstance();
        long personId = personService.createNewPersonByGroup(person.getPersonName(), ah.getGroupIdByToken(token));
	System.out.println(personId);
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

        if (updated) {
            return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete person
    @RequestMapping(value = "/catalog/persons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerson(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable long id) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean updated = personService.deletePersonByGroup(id, ah.getGroupIdByToken(token));

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Keywords CRUD methods
     */
    //Get all keywords 
    @RequestMapping(value = "/catalog/keywords", method = RequestMethod.GET)
    public ResponseEntity<?> getAllKeywords(
            @RequestHeader(value = "Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
        List<?> list = (List)keywordService.getAllKeywords(groupId);
        if (list.size() == 0) {
            System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
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
        if (updated) {
            return new ResponseEntity<Keyword>(newKeyword, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete keyword by person
    @RequestMapping(value = "/catalog/keywords/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteKeyword(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable long id) {
        AccountHandler ah = AccountHandler.getInstance();
        boolean deleted = keywordService.deleteKeywordByGroup(id, ah.getGroupIdByToken(token));

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Sites CRUD methods
     */
    //Get all sites
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSites(
            @RequestHeader(value = "Auth-Token") String token) {
        AccountHandler ah = AccountHandler.getInstance();
        long groupId = ah.getGroupIdByToken(token);
        List<?> list =  (List)siteService.getAllSites(groupId);
        if (list.size() == 0) {
            System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    //
    //Create new site
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.POST)
    public ResponseEntity<Site> postNewSite(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Site site) {
        AccountHandler ah = AccountHandler.getInstance();
        long siteId = siteService.createNewSiteByGroup(site.getSite(), ah.getGroupIdByToken(token));
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
        boolean updated = siteService.updateSiteByGroup(newSite.getId(), newSite.getSite(), ah.getGroupIdByToken(token));
        if (updated) {
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
        boolean deleted = siteService.deleteSiteByGroup(id, ah.getGroupIdByToken(token));

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get statistic methods
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
