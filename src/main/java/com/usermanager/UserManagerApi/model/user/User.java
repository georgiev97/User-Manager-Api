package com.usermanager.UserManagerApi.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.regexp.internal.RE;
import com.usermanager.UserManagerApi.utils.SortedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Document
public class User {

        @Id
        @Field("id")
        private String  id;
        private String firstName;
        private String lastName;
        @Indexed(unique = true)
        private String email;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateOfBirth;

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate  getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate  dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
