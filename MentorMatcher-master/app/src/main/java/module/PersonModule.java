/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.PersonDAO;
import domain.Person;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author David
 */
public class PersonModule extends Jooby {

    public PersonModule(PersonDAO dao) {

        path("/api/login/{email}/{password}", () -> {
            get("", ctx -> {
                String email = ctx.path("email").value();
                String password = ctx.path("password").value();
                Person person = dao.authenticate(email, password);
                if (person == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return person;
                }
            });

        });

        path("/api/user", () -> {
            get("", ctx -> {
                return dao.getPersons();
            });

            post("", ctx -> {
                Person person = ctx.body().to(Person.class);
                if (dao.getPerson(person.getPersonID()) == null) {
                    dao.savePerson(person);
                    return ctx.send(StatusCode.CREATED);
                } else {
                    return ctx
                            .setResponseCode(StatusCode.UNPROCESSABLE_ENTITY)
                            .render(new ErrorMessage("That email already exists in the system"));
                }
            });
        });

        path("/api/login/{person_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("person_ID").value());
                Person person = dao.getPerson(id);
                if (person == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return person;
                }
            });

            put("", ctx -> {
                int id = Integer.parseInt(ctx.path("person_ID").value());
                if (dao.getPerson(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    Person person = ctx.body().to(Person.class);
                    if (id != person.getPersonID()) {
                        return ctx
                                .setResponseCode(StatusCode.CONFLICT)
                                .render(new ErrorMessage("Modifying the product's ID via this operation is not allowed.  Create a new product instead."));
                    } else {
                        dao.updatePerson(id, person);
                        return ctx.send(StatusCode.NO_CONTENT);
                    }
                }
            });

            delete("", ctx -> {
                int id = Integer.parseInt(ctx.path("person_ID").value());
                if (dao.getPerson(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    dao.deletePerson(id);
                    return ctx.send(StatusCode.NO_CONTENT);
                }
            });
        });

    }

}
