package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class StatController {

    @RequestMapping(value = "/stat/over_stat", method = RequestMethod.GET)
    public ResponseEntity<List<OverMentionStatItem>> getOverStat(@RequestParam(value="site") String site){

	//Fake datas
	List<OverMentionStatItem> list = new ArrayList();
	if(site.equals("lenta.ru")){
	    list.add(new OverMentionStatItem("Путин", 100000));
	    list.add(new OverMentionStatItem("Медведев", 50000));
	    list.add(new OverMentionStatItem("Навальный", 25000));
	} else if(site.equals("rbc.ru")){
	    list.add(new OverMentionStatItem("Путин", 100005));
	    list.add(new OverMentionStatItem("Медведев", 45000));
	    list.add(new OverMentionStatItem("Навальный", 34000));
	 } else {
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	 }

	return new ResponseEntity<List<OverMentionStatItem>>(list, HttpStatus.OK);
    }
}
