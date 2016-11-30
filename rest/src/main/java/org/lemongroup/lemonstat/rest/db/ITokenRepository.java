package org.lemongroup.lemonstat.rest.db;


public interface ITokenRepository {
    long getAccountIdByToken (String token);
}
