/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.zaxxer.hikari.HikariDataSource;
import java.net.URI;
import java.net.URISyntaxException;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 *
 * @author phmci
 */
public class JDBIDaoFactory {

    private static final String DB_USERNAME = "uqsryfvaaceixx";
    private static final String DB_PASSWORD = "cce399ae04e314be65d4cb2efadc947c4327d2892ed1d31c50a945c898e48890";

    private static String jdbcUri = "jdbc:postgresql://ec2-34-194-40-194.compute-1.amazonaws.com:5432/d63fugsvdfe5fn?sslmode=require";

//    private static final String DB_USERNAME = "postgres";
//    private static final String DB_PASSWORD = "postgres";
//    private static String jdbcUri = "jdbc:postgresql://localhost:5432/info301";
    private static HikariDataSource HIKARI_DATA_SOURCE;
    private static Jdbi JDBI;

    public static void setJdbcUri(String uri) {
        if (HIKARI_DATA_SOURCE != null) {
            throw new IllegalStateException("Connection pool as already been initialised.  It is too late to change the JDBC URI.");
        }

        jdbcUri = uri;
    }

    private static void initialisePool() {

        HIKARI_DATA_SOURCE = new HikariDataSource();
        HIKARI_DATA_SOURCE.setJdbcUrl(jdbcUri);
        HIKARI_DATA_SOURCE.setUsername(DB_USERNAME);
        HIKARI_DATA_SOURCE.setPassword(DB_PASSWORD);

        JDBI = Jdbi.create(HIKARI_DATA_SOURCE);
        JDBI.installPlugin(new SqlObjectPlugin());
    }

    public static PersonDAO getPersonDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(PersonJdbiDAO.class);
    }

    public static MentorDAO getMentorDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(MentorJdbiDAO.class);
    }

    public static MenteeDAO getMenteeDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(MenteeJdbiDAO.class);
    }

    public static PostgradCourseDAO getPostgradCourseDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(PostgradCourseJdbiDAO.class);
    }

    public static UndergradCourseDAO getUndergradCourseDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(UndergradCourseJdbiDAO.class);
    }

    public static MatchDAO getMatchDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(MatchJdbiDAO.class);
    }

    public static UndergraduateExperienceDAO getUndergraduateExperienceDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(UndergraduateExperienceJdbiDAO.class);
    }

    public static PostgraduateExperienceDAO getPostgraduateExperienceDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(PostgraduateExperienceJdbiDAO.class);
    }

    public static WorkExperienceDAO getWorkExperienceDAO() {
        if (HIKARI_DATA_SOURCE == null) {
            initialisePool();
        }
        return JDBI.onDemand(WorkExperienceJdbiDAO.class);
    }

}
