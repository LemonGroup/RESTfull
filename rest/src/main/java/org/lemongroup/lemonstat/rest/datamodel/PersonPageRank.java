package org.lemongroup.lemonstat.rest.datamodel;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;                                                                                                                                                                                                                  import java.io.Serializable;
@Entity
@Table(name = "person_page_rank")                                                                                                                                                                                                            public class PersonPageRank implements Serializable {
    //@IdClass(PersonPageRankPK.class)
    @Column(name = "rank")
    private long rank;
    @Column(name = "personid", nullable = false, insertable = false, updatable = false)
    //@JoinColumn(name = "personid", nullable = false, insertable = false, updatable = false)
    private long personId;
    @Column(name = "pageid",nullable = false, insertable = false, updatable = false)
    //@JoinColumn(name = "pageid", nullable = false, insertable = false, updatable = false)
    private long  pageId;
    @EmbeddedId
    PersonPageRankPK id;


    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

}

