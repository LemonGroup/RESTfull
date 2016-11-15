package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.lemongroup.lemonstat.rest.db.CatalogRepository;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class AdminController {

    @RequestMapping(value = "/catalog/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPersons() {

        List<Person> list = new CatalogRepository().getAllPersons();
        if (list.size() == 0) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }
}