package com.example.assess.model;

import com.example.assess.utils.Assess;

/**
 * The type Assess patient.
 */
public class AssessPatient {
    private String firstName;
    private String lastName;
    private int age;
    private Assess assessement;

    /**
     * Instantiates a new Assess patient.
     */
    public AssessPatient() {}

    /**
     * Instantiates a new Assess patient.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param age         the age
     * @param assessement the assessement
     */
    public AssessPatient(String firstName, String lastName, int age, Assess assessement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.assessement = assessement;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets assessement.
     *
     * @return the assessement
     */
    public Assess getAssessement() {
        return assessement;
    }

    /**
     * Sets assessement.
     *
     * @param assessement the assessement
     */
    public void setAssessement(Assess assessement) {
        this.assessement = assessement;
    }
}
