import org.sql2o.*;

public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/university_registrar", null, null);

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String student = "DELETE FROM students *;";
      String courses = "DELETE FROM courses *;";
      String student_courses = "DELETE FROM student_courses *;";
      con.createQuery(student).executeUpdate();
      con.createQuery(courses).executeUpdate();
      con.createQuery(student_courses).executeUpdate();
    }
  }
}
