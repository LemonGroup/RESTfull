package org.lemongroup.lemonstat.rest.db;

import java.util.Collection;

public interface IPersonRepository {

    public Collection getAllPersonsByGroup(long groupId);
    public long createNewPersonByGroup(String personName, long groupId);
    public boolean updatePersonByGroup(long personId, String newPersonName, long groupId);
    public boolean deletePersonByGroup(long personId, long groupId);
}

