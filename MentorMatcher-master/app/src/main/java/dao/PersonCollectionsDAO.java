/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Person;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class PersonCollectionsDAO implements PersonDAO {

    //private final Collection<Person> persons = new HashSet<>();
    private static final Map<Integer, Person> personID = new HashMap<>();
    private static final Map<String, Person> personEmail = new HashMap<>();

    /**
     * Gets a list of all persons.
     *
     * @return Collection of persons
     */
    @Override
    public Collection<Person> getPersons() {
        return personID.values();
    }

    /**
     * Adds a Person.
     *
     * @param person to add
     */
    @Override
    public void savePerson(Person person) {
        //persons.add(person);
        personID.put(person.getPersonID(), person);
        personEmail.put(person.getEmail(), person);
    }

    /**
     * Gets a specific person.
     *
     * @param id of the person to return
     * @return Person who's id was specified.
     */
    @Override
    public Person getPerson(Integer id) {
        return personID.get(id);
    }

    /**
     * Updates an existing person
     *
     * @param id of person to update
     * @param updatedPerson updated details of person
     */
    @Override
    public void updatePerson(Integer id, Person person) {
        // persons.remove(getPerson(email));
        // persons.add(person);
        personID.put(id, person);
        personEmail.put(person.getEmail(), person);
    }

    /**
     * Delete a person
     *
     * @param person to delete
     */
    @Override
    public void deletePerson(Integer id) {
        // persons.remove(getPerson(email));
        personID.remove(id);
    }

    /**
     * Authenticates a person with email and password.
     *
     * @param email of the person being authenticated.
     * @param password of the person being authenticated.
     * @return authenticated person
     */
    @Override
    public Person authenticate(String email, String password) {
        Person p1 = personEmail.get(email);
        if (p1.getPassword().equals(password)) {
            return p1;
        }
        return null;
    }

}
