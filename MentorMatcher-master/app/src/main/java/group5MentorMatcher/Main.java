/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package group5MentorMatcher;

import dao.JDBIDaoFactory;
import dao.MatchDAO;
import dao.MenteeDAO;
import dao.MentorDAO;
import dao.PersonDAO;
import dao.PostgradCourseDAO;
import dao.PostgraduateExperienceDAO;
import dao.UndergradCourseDAO;
import dao.UndergraduateExperienceDAO;
import dao.WorkExperienceDAO;
import io.jooby.Jooby;
import io.jooby.OpenAPIModule;
import io.jooby.ServerOptions;
import io.jooby.json.GsonModule;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.*;
import module.MatchModule;
import module.MenteeModule;
import module.MentorModule;
import module.PersonModule;
import module.PostgradCourseModule;
import module.PostgraduateExperienceModule;
import module.UndergradCourseModule;
import module.UndergraduateExperienceModule;
import module.WorkModule;

public class Main extends Jooby {

    private final PersonDAO personDAO = JDBIDaoFactory.getPersonDAO();
    private final MentorDAO mentorDAO = JDBIDaoFactory.getMentorDAO();
    private final MenteeDAO menteeDAO = JDBIDaoFactory.getMenteeDAO();
    private final MatchDAO matchDAO = JDBIDaoFactory.getMatchDAO();
    private final WorkExperienceDAO workDAO = JDBIDaoFactory.getWorkExperienceDAO();
    private final UndergraduateExperienceDAO undergraduateDAO = JDBIDaoFactory.getUndergraduateExperienceDAO();
    private final PostgraduateExperienceDAO postgraduateDAO = JDBIDaoFactory.getPostgraduateExperienceDAO();
    private final PostgradCourseDAO pgCourseDAO = JDBIDaoFactory.getPostgradCourseDAO();
    private final UndergradCourseDAO ugCourseDAO = JDBIDaoFactory.getUndergradCourseDAO();

    public Main() {
//        setServerOptions(new ServerOptions().setPort(8072));

        install(new GsonModule());
        install(new OpenAPIModule());

        assets("/openapi.json", "mentor-matcher.json");
        assets("/openapi.yaml", "mentor-matcher.yaml");
        assets("/*", Paths.get("static"));
        //get("/", ctx -> ctx.sendRedirect("/swagger"));

        mount(new PersonModule(personDAO));
        mount(new MentorModule(mentorDAO));
        mount(new MenteeModule(menteeDAO));
        mount(new MatchModule(matchDAO));
        mount(new WorkModule(workDAO));
        mount(new UndergraduateExperienceModule(undergraduateDAO));
        mount(new PostgraduateExperienceModule(postgraduateDAO));
        mount(new PostgradCourseModule(pgCourseDAO));
        mount(new UndergradCourseModule(ugCourseDAO));

    }

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws URISyntaxException, SQLException {
        System.out.println(new Main().getGreeting());
//        try ( Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/info301", "postgres", "postgres")) {
        try {
            getConnection();
            Class.forName("org.postgresql.Driver");
            System.out.println("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Main().start();
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = "uqsryfvaaceixx";
        String password = "cce399ae04e314be65d4cb2efadc947c4327d2892ed1d31c50a945c898e48890";
        String dbUrl = "jdbc:postgresql://ec2-34-194-40-194.compute-1.amazonaws.com:5432/d63fugsvdfe5fn?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}

