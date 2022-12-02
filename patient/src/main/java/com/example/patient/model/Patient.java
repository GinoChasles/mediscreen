package com.example.patient.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * The type Patient.
 */
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Firstname can't be empty")
    private String firstname;

    @NotEmpty(message = "lastname can't be empty")
    private String lastname;

    @NotNull(message = "birthdate can't be null")
    @Past
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthdate;

    @NotEmpty(message = "gender can't be empty")
    private String gender;

    private String address;

    private String phone;

    /**
     * Instantiates a new Patient.
     */
    public Patient() {
    }


    /**
     * Instantiates a new Patient.
     *
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param birthdate the birthdate
     * @param gender    the gender
     * @param address   the address
     * @param phone     the phone
     */
    public Patient(String firstname, String lastname, Date birthdate, String gender, String address, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Instantiates a new Patient.
     *
     * @param id        the id
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param birthdate the birthdate
     * @param gender    the gender
     * @param address   the address
     * @param phone     the phone
     */
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
     *
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param birthdate the birthdate
     * @param gender    the gender
     */
    public Patient(String firstname, String lastname, Date birthdate, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets birthdate.
     *
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Sets birthdate.
     *
     * @param birthdate the birthdate
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
