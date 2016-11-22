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
        if (siteId == 323 && groupId == 3) {
            list.add(new OverMentionStatItem("Путин", 100000));
            list.add(new OverMentionStatItem("Медведев", 50000));
            list.add(new OverMentionStatItem("Навальный", 25000));
        } else if (siteId == 324 && groupId == 3) {
            list.add(new OverMentionStatItem("Путин", 100005));
            list.add(new OverMentionStatItem("Медведев", 45000));
            list.add(new OverMentionStatItem("Навальный", 34000));
	}
	return list;
    }
    public List<DailyStat> getDaylyStatByParamsByGroup(Map<String,String> requestParams, long groupId) {

	//Fake datas, return just given dates
        long siteId = Long.parseLong(requestParams.get("siteId"));
        long personId = Long.parseLong(requestParams.get("personId"));
        String startDate = requestParams.get("start_date");
        String endDate = requestParams.get("end_date");

        List<DailyStat> list = new ArrayList<>();
        if (siteId == 344 && personId == 2342 && groupId == 3) {
            list.add(new DailyStat(startDate, 50));
            list.add(new DailyStat(endDate, 100));
	}
	return list;
    }
}
