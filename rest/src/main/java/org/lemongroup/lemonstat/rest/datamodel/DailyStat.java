package org.lemongroup.lemonstat.rest.datamodel;

import java.util.Date;

/**
 * Экземпляр ежедневной статистики
 */
public class DailyStat {
    private Date date;
    private Integer numberOfNewPages;

    public DailyStat(){
    }

    public DailyStat(Date date, Integer numberOfNewPages) {
        this.date = date;
        this.numberOfNewPages = numberOfNewPages;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfNewPages() {
        return numberOfNewPages;
    }

    public void setNumberOfNewPages(Integer numberOfNewPages) {
        this.numberOfNewPages = numberOfNewPages;
    }
}
