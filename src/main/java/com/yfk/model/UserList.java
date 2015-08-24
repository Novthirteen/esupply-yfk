package com.yfk.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "users"
})
@XmlRootElement(name = "UserList")
public class UserList {
 
    @XmlElement(name = "User")
    protected List<User> users;
    public List<User> getUers() {
        if (users == null) {
        	users = new ArrayList<User>();
        }
        return this.users;
    }
    
    public void setUsers(List<User> users) {
        this.users=users;
    }
}