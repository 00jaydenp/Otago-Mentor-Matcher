/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Person;
import java.util.Collection;

/**
 *
 * @author David
 */
public interface PersonDAO {

    /**
     * Delete a person
     *
     * @param person to remove.
     */
    void deletePerson(Integer id);

    Person getPerson(Integer id);

    /**
     * Get a list of all Persons.
     *
     * @return list of persons
     */
    Collection<Person> getPersons();

    /**
     * Add a person
     *
     * @param person to add
     */
    void savePerson(Person person);

    /**
     * Update details of a person
     *
     * @param id of person to update.
     * @param updatedPerson updated details for person.
     */
    void updatePerson(Integer id, Person person);

    /**
     * Authenticates a person with email and password.
     *
     * @param email of the person being authenticated.
     * @param password of the person being authenticated.
     */
    Person authenticate(String email, String password);

}
