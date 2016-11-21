package org.lemongroup.lemonstat.rest.db;

public interface IPersonRepository {

    public long createNewPersonByGroup(String personName, long groupId);
}

