package org.lemongroup.lemonstat.rest.datamodel;

/**
 * Экземпляр общей статистики
 */
public class OverMentionStatItem {

    private String person;
    private Integer numberOfMentions;

    public OverMentionStatItem(){}
    public OverMentionStatItem(String person, int numberOfMentions) {
	this.person = person;
	this.numberOfMentions = numberOfMentions;
    }

    public String getPerson() {
	return person;
    }
    public void setPerson(String person) {
	this.person = person;
    }
    public Integer getNumberOfMentions() {
	return numberOfMentions;
    }
    public void setNumberOfMentions(Integer numberOfMentions) {
	this.numberOfMentions = numberOfMentions;
    }
}

