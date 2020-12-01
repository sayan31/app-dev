package com.sayan.microservices.demospringbootmicroservices.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    * Hashcode method is made to return constant due to the following scenario:
    * A transient object has a null value for ID, and after it becomes managed it has a valid ID
    * This implies that the same object can fail the equality test if hashcode is implemented using
    * the DB generated id field.
    * To take care of this, hashcode returns a constant value.
    */
    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public boolean equals(Object obj) {
        //First compares if reference this and reference obj point to the SAME object
        //If yes, return true
        if (this==obj){ return true;}
        //Checks if reference points to null object. If yes, return false
        if (obj==null){return false;}
        if(getClass()!=obj.getClass()){return false;}
        return id!=null && id.equals(((Customer)obj).id);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", username=" + username
                + ", email=" + email + '}';
    }
}
