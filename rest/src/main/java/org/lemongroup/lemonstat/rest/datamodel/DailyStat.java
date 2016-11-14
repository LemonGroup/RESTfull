package org.lemongroup.lemonstat.rest.datamodel;

import java.util.Date;

/**
 * Экземпляр ежедневной статистики
 */
public class DailyStat {
    private String date;
    private Integer numberOfNewPages;

    public DailyStat(){
    }

    public DailyStat(String date, Integer numberOfNewPages) {
        this.date = date;
        this.numberOfNewPages = numberOfNewPages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNumberOfNewPages() {
        return numberOfNewPages;
    }

    public void setNumberOfNewPages(Integer numberOfNewPages) {
        this.numberOfNewPages = numberOfNewPages;
    }
}
