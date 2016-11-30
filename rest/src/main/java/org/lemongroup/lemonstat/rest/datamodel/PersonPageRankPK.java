package org.lemongroup.lemonstat.rest.datamodel;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class PersonPageRankPK implements Serializable {

    @Column(name="personid")
    private long personId;
    @Column(name="pageid")
    private long pageId;
}


