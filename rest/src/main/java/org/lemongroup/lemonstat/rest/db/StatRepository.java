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


    public List<OverMentionStatItem> getOverStatBySiteIdByGroup(long siteId, long groupId) {
        //Fake data
        List<OverMentionStatItem> list = new ArrayList<>();
        if (siteId == 1 && groupId == 3) {
            list.add(new OverMentionStatItem("Путин", 100000));
            list.add(new OverMentionStatItem("Медведев", 50000));
            list.add(new OverMentionStatItem("Навальный", 25000));
        } else if (siteId == 2 && groupId == 3) {
            list.add(new OverMentionStatItem("Путин", 100005));
            list.add(new OverMentionStatItem("Медведев", 45000));
            list.add(new OverMentionStatItem("Навальный", 34000));
	}
	return list;
    }
    public List<DailyStat> getDaylyStatByParamsByGroup(Map<String,String> requestParams, long groupId) {

	String site = requestParams.get("siteId");
	String person = requestParams.get("personId");
	String startDate = requestParams.get("start_date");
	String endDate = requestParams.get("end_date");

	List<DailyStat> list = new ArrayList<>();
	if (site.equals("1") && person.equalsIgnoreCase("1")) {
	    list.add(new DailyStat(startDate, 50));
	    list.add(new DailyStat(endDate, 100));
	} else if (site.equals("2") && person.equalsIgnoreCase("1")) {
	    list.add(new DailyStat(startDate, 45));
	    list.add(new DailyStat(endDate, 88));
	} else if (site.equals("1") && person.equalsIgnoreCase("2")) {
	    list.add(new DailyStat(startDate, 57));
	    list.add(new DailyStat(endDate, 94));
	} else if (site.equals("2") && person.equalsIgnoreCase("2")) {
	    list.add(new DailyStat(startDate, 66));
	    list.add(new DailyStat(endDate, 91));
	} else if (site.equals("1") && person.equalsIgnoreCase("3")) {
	    list.add(new DailyStat(startDate, 36));
	    list.add(new DailyStat(endDate, 79));
	} else if (site.equals("2") && person.equalsIgnoreCase("3")) {
	    list.add(new DailyStat(startDate, 45));
	    list.add(new DailyStat(endDate, 110));
	}
	return list;
    }
}
