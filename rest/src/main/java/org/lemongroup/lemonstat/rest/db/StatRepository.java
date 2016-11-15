package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.DailyStat;
import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
/**
 *  * Stat data repository
 *   */
public class StatRepository {


    public List<OverMentionStatItem> getOverStatBySite(String site) {
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
	}
	return list;
    }
    public List<DailyStat> getDaylyStatByParams(Map<String,String> requestParams) {

	//Fake datas, return just given dates
        String site = requestParams.get("site");
        String person = requestParams.get("person");
        String startDate = requestParams.get("start_date");
        String endDate = requestParams.get("end_date");

        List<DailyStat> list = new ArrayList<>();
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
	}
	return list;
    }
}

