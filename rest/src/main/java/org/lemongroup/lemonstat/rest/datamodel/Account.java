package org.lemongroup.lemonstat.rest.datamodel;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Accounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"username","groupid"})})
@JsonIgnoreProperties({"groupid"})
public class Account {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "groupid", nullable = false)
    private long groupId;
    @Column(name = "privilege", nullable = false)
    private byte privilege;

    public Account() {
    }

    public Account(String username, String password, String email, byte privilege) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.privilege = privilege;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getPrivilege() {
        return privilege;
    }

    public void setPrivilege(byte privilege) {
        this.privilege = privilege;
    }
}
