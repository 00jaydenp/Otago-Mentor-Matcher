/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Person;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindBeanList;

import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author David
 */
public interface PersonJdbiDAO extends PersonDAO {

    @Override
    @SqlUpdate("DELETE FROM PERSON WHERE PERSON_ID = :personID")
    public void deletePerson(@Bind("personID") Integer id);

    @Override
    @SqlQuery("SELECT * FROM PERSON WHERE PERSON_ID = :personID")
    @RegisterBeanMapper(Person.class)
    public Person getPerson(@Bind("personID") Integer id);

    @Override
    @SqlQuery("SELECT *  FROM PERSON ORDER BY EMAIL")
    @RegisterBeanMapper(Person.class)
    public Collection<Person> getPersons();

    @Override
    @SqlUpdate("INSERT INTO PERSON (PERSON_ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, PHONE, "
            + "MOBILE, INTERESTS, SKILLS, BIO, ACTIVE, CITY, FIND_OUT, ROLE, NOTES, PROFILE_PHOTO) VALUES"
            + "(:personID, :email, crypt(:password, gen_salt('md5')), :firstName, :lastName, :phone, :mobile, :interests, "
            + ":skills, :bio, :active, :city, :findOut, :role, :notes, :profilePhoto)")
    public void savePerson(@BindBean Person person);

    @Override
    @SqlUpdate("UPDATE PERSON SET PASSWORD =:password, FIRST_NAME=:firstName, "
            + "LAST_NAME=:lastName, PHONE=:phone, MOBILE=:mobile, INTERESTS=:interests, "
            + "SKILLS=:skills, BIO=:bio, ACTIVE=:active, CITY=:city, FIND_OUT=:findOut, ROLE=:role, "
            + "NOTES=:notes WHERE EMAIL = :email")
    public void updatePerson(@Bind("personID") Integer id, @BindBean Person person);

    @Override
    @SqlQuery("SELECT * FROM PERSON WHERE EMAIL = :email AND PASSWORD = crypt(:password, password)")
    @RegisterBeanMapper(Person.class)
    public Person authenticate(@Bind("email") String email, @Bind("password") String password);
}
