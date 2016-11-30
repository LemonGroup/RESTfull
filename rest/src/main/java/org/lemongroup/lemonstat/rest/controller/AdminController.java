package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.db.*;
import org.lemongroup.lemonstat.rest.service.*;
import org.lemongroup.lemonstat.rest.datamodel.Account;
import org.lemongroup.lemonstat.rest.datamodel.Token;
import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;
import org.lemongroup.lemonstat.rest.datamodel.DailyStat;

import java.util.Map;
import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    RandomStringService randomStringService;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/user/auth", method = RequestMethod.GET)
    public ResponseEntity<?> auth(
            @RequestParam Map<String, String> authParams) {
        String username = authParams.get("user");
        //Compare passwords
        boolean isAuthenticated;
        try {
            isAuthenticated = authParams.get("pass").equals(accountService.getPasswordByUserName(username));
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
    public ResponseEntity checkUsernameExists(
            @RequestParam(value = "username") String username) {
        boolean userIsBusy = accountService.checkUsernameExists(username);
        if (userIsBusy) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //Check email is busy
    @RequestMapping(value = "/user/reguser/check_email", method = RequestMethod.POST)
    public ResponseEntity checkEmailExists(
            @RequestParam(value = "email") String email) {
        boolean emailIsBusy = accountService.checkEmailExists(email);
        if (emailIsBusy) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //Registration of new user
    @RequestMapping(value = "/user/reguser", method = RequestMethod.POST)
    public ResponseEntity<?> checkEmailExists(
            @RequestBody Account account) {
        String username = account.getUsername();
        //Create new group which named as Username
        String groupname = username;
        //Set default privilege as admin (magic number 2)
        account.setPrivilege((byte) 2);
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
        long groupId = accountService.getGroupIdByToken(token);
        System.out.println("groupId" + groupId);
        List<?> list = (List) accountService.getAllAccountsByGroup(groupId);
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
        try {
            long groupId = accountService.getGroupIdByToken(token);
            long accountId = accountService.createNewAccountByGroup(account, groupId);
            account.setId(accountId);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    //Update account privilege
    @RequestMapping(value = "/catalog/accounts/privilege", method = RequestMethod.PUT)
    public ResponseEntity updateAccountPrivilege(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Account newAccount) {
        long groupId = accountService.getGroupIdByToken(token);
        boolean updated = accountService.updateAccountPrivilegeByGroup(newAccount.getId(), newAccount.getPrivilege(), groupId);
        if (updated) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //Update account email
    @RequestMapping(value = "/catalog/accounts/email", method = RequestMethod.PUT)
    public ResponseEntity updateAccountMail(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Account newAccount) {
        long groupId = accountService.getGroupIdByToken(token);
        boolean updated = accountService.updateAccountMailByGroup(newAccount.getId(), newAccount.getEmail(), groupId);
        if (updated) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //Update account password
    @RequestMapping(value = "/catalog/accounts/password", method = RequestMethod.PUT)
    public ResponseEntity updateAccountPassword(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Account newAccount) {
        long groupId = accountService.getGroupIdByToken(token);
        boolean updated = accountService.updateAccountPasswordByGroup(newAccount.getId(), newAccount.getPassword(), groupId);
        if (updated) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // get account data by token
    @RequestMapping(value = "/catalog/accounts/myaccount", method = RequestMethod.GET)
    public ResponseEntity getMyAccount(
            @RequestHeader(value = "Auth-Token") String token){
        Account myAccount = null;
        try {
             myAccount = accountService.getAccountByToken(token);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Account>(myAccount, HttpStatus.OK);
    }

    //If user forgot account password
    @RequestMapping(value = "/catalog/accounts/reset_password", method = RequestMethod.POST)
    public ResponseEntity resetAccountPassword(
            @RequestBody Account newAccount) {
        Account account = accountService.getAccountByEmail(newAccount.getEmail());
        String randomPassword = randomStringService.randomString(7);
        boolean updated = accountService.updateAccountPassword(account.getId(), randomPassword);
        if (updated) {
            account.setPassword(randomPassword);
            try {
                notificationService.sendNotification(account);
            }
            catch (MailException e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //Delete account
    @RequestMapping(value = "/catalog/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable long id) {
        long groupId = accountService.getGroupIdByToken(token);
        boolean isDeleted = accountService.deleteAccountByGroup(id, groupId);
        if (isDeleted) {
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
        long groupId = accountService.getGroupIdByToken(token);
        List<?> list = (List) personService.getAllPersonsByGroup(groupId);
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
        try {
            long groupId = accountService.getGroupIdByToken(token);
            long personId = personService.createNewPersonByGroup(person.getPersonName(), groupId);
            System.out.println(personId);
            person.setId(personId);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<Person>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    //Update person name
    @RequestMapping(value = "/catalog/persons", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Person newPerson) {
        try {
            long groupId = accountService.getGroupIdByToken(token);
            boolean updated = personService.updatePersonByGroup(newPerson.getId(), newPerson.getPersonName(), groupId);
            if (updated) {
                return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<Person>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

    //Delete person
    @RequestMapping(value = "/catalog/persons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerson(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable long id) {
        long groupId = accountService.getGroupIdByToken(token);
        boolean updated = personService.deletePersonByGroup(id, groupId);

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
        long groupId = accountService.getGroupIdByToken(token);
        List<?> list = (List) keywordService.getAllKeywords(groupId);
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
        try {
            long groupId = accountService.getGroupIdByToken(token);
            long keywordId = keywordService.createNewKeywordByGroup(keyword.getPersonId(), keyword.getKeyword(), groupId);
            System.out.println(keyword.getKeyword());
            keyword.setId(keywordId);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(keyword, HttpStatus.OK);
    }

    //Update keyword by person
    @RequestMapping(value = "/catalog/keywords", method = RequestMethod.PUT)
    public ResponseEntity<Keyword> updateKeywordByPerson(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Keyword newKeyword) {
        try {
            long groupId = accountService.getGroupIdByToken(token);
            boolean updated = keywordService.updateKeywordByGroup(newKeyword.getId(), newKeyword.getKeyword(), groupId);
            if (updated) {
                return new ResponseEntity<>(newKeyword, HttpStatus.OK);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete keyword by person
    @RequestMapping(value = "/catalog/keywords/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteKeyword(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable long id) {
        long groupId = accountService.getGroupIdByToken(token);
        boolean deleted = keywordService.deleteKeywordByGroup(id, groupId);
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
        long groupId = accountService.getGroupIdByToken(token);
        List<?> list = (List) siteService.getAllSites(groupId);
        if (list.size() == 0) {
            System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<?>>(list, HttpStatus.OK);
    }

    //Create new site
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.POST)
    public ResponseEntity<Site> postNewSite(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Site site) {
        try {
            long groupId = accountService.getGroupIdByToken(token);
            long siteId = siteService.createNewSiteByGroup(site.getSite(), groupId);
            site.setId(siteId);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Site>(site, HttpStatus.OK);
    }

    //Update site name
    @RequestMapping(value = "/catalog/sites", method = RequestMethod.PUT)
    public ResponseEntity<Site> updateSite(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody Site newSite) {
        try {
            long groupId = accountService.getGroupIdByToken(token);
            boolean updated = siteService.updateSiteByGroup(newSite.getId(), newSite.getSite(), groupId);
            if (updated) {
                return new ResponseEntity<Site>(newSite, HttpStatus.OK);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete site
    @RequestMapping(value = "/catalog/sites/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSite(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable long id) {
        long groupId = accountService.getGroupIdByToken(token);
        boolean deleted = siteService.deleteSiteByGroup(id, groupId);
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

        long groupId = accountService.getGroupIdByToken(token);
        List<OverMentionStatItem> list = new StatRepository().getOverStatBySiteIdByGroup(siteId, groupId);
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

        long groupId = accountService.getGroupIdByToken(token);
        List<DailyStat> list = new StatRepository().getDaylyStatByParamsByGroup(requestParams, groupId);
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
