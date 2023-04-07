package com.example.assess.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * The type Patient.
 */
public class Patient {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String gender;
    private String address;
    private String phone;
    @JsonIgnore
    private int age;

    /**
     * Instantiates a new Patient.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param birthDate the birth date
     * @param gender    the gender
     */
    public Patient(String firstName, String lastName, Date birthDate, String gender) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.birthdate = birthDate;
        this.gender = gender;
    }

    public Patient(Long id, String firstname, String lastname, Date birthdate, String gender, String address, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Instantiates a new Patient.
     */
    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        LocalDate now = LocalDate.now();
        LocalDate birthDateLocal = this.getBirthdate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birthDateLocal, now).getYears();
    }


}
