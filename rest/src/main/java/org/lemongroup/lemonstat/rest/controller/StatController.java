package oterg.lemongroup.lemonstat.rest.controller;

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
    public ResponseEntity<List<DailyStat>> getDailyStat(@RequestParam Map<String, String> requestParams) {
        String site = requestParams.get("site");
        String person = requestParams.get("person");
        String startDate = requestParams.get("start_date");
        String endDate = requestParams.get("end_date");

	//Fake datas, return just given dates
        if (site.equals("lenta.ru") && person.equalsIgnoreCase("Путин")) {
            list.add(new DailyStat(startDate, 50));
            list.add(new DailyStat(endDate, 100));
        } else if (site.equals("rbc.ru") && person.equalsIgnoreCase("Путин")) {
            list.add(new DailyStat(startDate, 45));
            list.add(new DailyStat(endDate, 88));
        } else if (site.equals("lenta.ru") && person.equalsIgnoreCase("Медведев")) {
            list.add(new DailyStat(startDate, 57));
            list.add(new DailyStat(endDate, 94));
        } else if (site.equals("rbc.ru") && person.equalsIgnoreCase("Медведев")) {
            list.add(new DailyStat(startDate, 66));
            list.add(new DailyStat(endDate, 91));
        } else if (site.equals("lenta.ru") && person.equalsIgnoreCase("Навальный")) {
            list.add(new DailyStat(startDate, 36));
            list.add(new DailyStat(endDate, 79));
        } else if (site.equals("rbc.ru") && person.equalsIgnoreCase("Навальный")) {
            list.add(new DailyStat(startDate, 45));
            list.add(new DailyStat(endDate, 110));
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
