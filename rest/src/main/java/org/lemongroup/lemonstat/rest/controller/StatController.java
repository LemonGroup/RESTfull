package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.datamodel.DailyStat;
import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;
import org.lemongroup.lemonstat.rest.db.StatRepository;

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
public class StatController {

    private StatRepository repository;

    @RequestMapping(value = "/stat/over_stat", method = RequestMethod.GET)
    public ResponseEntity<List<OverMentionStatItem>> getOverStat(@RequestParam(value = "site") String site) {

	List<OverMentionStatItem> list = new StatRepository().getOverStatBySite(site);

        if (list.size() == 0) {
	    System.out.println("NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OverMentionStatItem>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/stat/daily_stat", method = RequestMethod.GET)
    public ResponseEntity<List<DailyStat>> getDailyStat(@RequestParam Map<String, String> requestParams) {

	List<DailyStat> list = new StatRepository().getDaylyStatByParams(requestParams);

        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
