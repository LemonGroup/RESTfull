package org.lemongroup.lemonstat.rest.controller;

import org.lemongroup.lemonstat.rest.datamodel.DailyStat;
import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class StatController {

    @RequestMapping(value = "/stat/over_stat", method = RequestMethod.GET)
    public ResponseEntity<List<OverMentionStatItem>> getOverStat(@RequestParam(value = "site") String site) {

        //Fake data
        List<OverMentionStatItem> list = new ArrayList<>();
        if (site.equals("lenta.ru")) {
            list.add(new OverMentionStatItem("Путин", 100000));
            list.add(new OverMentionStatItem("Медведев", 50000));
            list.add(new OverMentionStatItem("Навальный", 25000));
        } else if (site.equals("rbc.ru")) {
            list.add(new OverMentionStatItem("Путин", 100005));
            list.add(new OverMentionStatItem("Медведев", 45000));
            list.add(new OverMentionStatItem("Навальный", 34000));
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<OverMentionStatItem>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/stat/daily_stat", method = RequestMethod.GET)
    public ResponseEntity<List<DailyStat>> getDailyStat(@RequestParam Map<String, Object> requestParams) {
        String site = (String) requestParams.get("site");
        String person = (String) requestParams.get("person");
        Date startDate = (Date) requestParams.get("start_date");
        Date endDate = (Date) requestParams.get("end_date");

        List<DailyStat> list = new ArrayList<>();
        GregorianCalendar calendar = new GregorianCalendar(2016, Calendar.NOVEMBER, 10);
        GregorianCalendar calendar2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 11);
        GregorianCalendar calendar3 = new GregorianCalendar(2016, Calendar.NOVEMBER, 12);
        GregorianCalendar calendar4 = new GregorianCalendar(2016, Calendar.NOVEMBER, 13);

        if (site.equals("lenta.ru") && person.equalsIgnoreCase("Путин")) {
            list.add(new DailyStat(calendar.getTime(), 50));
            list.add(new DailyStat(calendar2.getTime(), 100));
            list.add(new DailyStat(calendar3.getTime(), 200));
            list.add(new DailyStat(calendar4.getTime(), 300));

        } else if (site.equals("rbc.ru") && person.equalsIgnoreCase("Путин")) {
            list.add(new DailyStat(calendar.getTime(), 70));
            list.add(new DailyStat(calendar2.getTime(), 90));
            list.add(new DailyStat(calendar3.getTime(), 75));
            list.add(new DailyStat(calendar4.getTime(), 100));
        } else if (site.equals("lenta.ru") && person.equalsIgnoreCase("Медведев")) {
            list.add(new DailyStat(calendar.getTime(), 30));
            list.add(new DailyStat(calendar2.getTime(), 2));
            list.add(new DailyStat(calendar3.getTime(), 300));
            list.add(new DailyStat(calendar4.getTime(), 400));
        } else if (site.equals("rbc.ru") && person.equalsIgnoreCase("Медведев")) {
            list.add(new DailyStat(calendar.getTime(), 100));
            list.add(new DailyStat(calendar2.getTime(), 300));
            list.add(new DailyStat(calendar3.getTime(), 100));
            list.add(new DailyStat(calendar4.getTime(), 500));
        } else if (site.equals("lenta.ru") && person.equalsIgnoreCase("Навальный")) {
            list.add(new DailyStat(calendar.getTime(), 30));
            list.add(new DailyStat(calendar2.getTime(), 2));
            list.add(new DailyStat(calendar3.getTime(), 300));
            list.add(new DailyStat(calendar4.getTime(), 400));
        } else if (site.equals("rbc.ru") && person.equalsIgnoreCase("Навальный")) {
            list.add(new DailyStat(calendar.getTime(), 10));
            list.add(new DailyStat(calendar2.getTime(), 20));
            list.add(new DailyStat(calendar3.getTime(), 30));
            list.add(new DailyStat(calendar4.getTime(), 40));
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
