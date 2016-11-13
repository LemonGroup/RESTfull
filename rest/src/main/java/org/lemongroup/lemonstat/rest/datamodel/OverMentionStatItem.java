package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Экземпляр общей статистики
 */

public class OverMentionStatItem {

    private String person;
    private Integer mentions;

    public OverMentionStatItem(){}
    public OverMentionStatItem(String person, int mentions) {
	this.person = person;
	this.mentions = mentions;
    }

    public String getPerson() {
	return person;
    }
    public void setPerson(String person) {
	this.person = person;
    }
    public Integer getMentions() {
	return mentions;
    }
    public void setMentions(Integer mentions) {
	this.mentions = mentions;
    }
}

