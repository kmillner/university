import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Course {
  private int id;
  private String title;
  private int course_number;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getCourseNumber() {
    return course_number;
  }

  public Course(String title, int course_number) {
    this.title = title;
    this.course_number = course_number;
  }

  public static List<Course> all() {
    String sql = "SELECT id, title, course_number FROM Courses";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Course.class);
    }
  }

  @Override
  public boolean equals(Object otherCourse){
    if (!(otherCourse instanceof Course)) {
      return false;
    } else {
      Course newCourse = (Course) otherCourse;
      return this.getTitle().equals(newCourse.getTitle()) &&
            this.getCourseNumber() == (newCourse.getCourseNumber());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO courses (title, course_number) VALUES (:title, :course_number)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("course_number", this.course_number)
        .executeUpdate()
        .getKey();
    }
  }

  public static Course find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Courses where id=:id";
      Course Course = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Course.class);
      return Course;
    }
  }

  public void addStudent(Student student) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO student_courses (course_id, student_id) VALUES (:course_id, :student_id)";
        con.createQuery(sql)
          .addParameter("course_id", this.getId())
          .addParameter("student_id", student.getId())
          .executeUpdate();
    }
  }

  public ArrayList<Student> getStudents() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="SELECT student_id FROM student_courses WHERE course_id = :course_id";
      List<Integer> studentIds = con.createQuery(sql)
        .addParameter("course_id", this.getId())
        .executeAndFetch(Integer.class);

      ArrayList<Student> students = new ArrayList<Student>();

      for (Integer studentId : studentIds) {
        String studentQuery = "Select * From students WHERE id = :studentId";
        Student student = con.createQuery(studentQuery)
          .addParameter("studentId", studentId)
          .executeAndFetchFirst(Student.class);
          students.add(student);
      }
      return students;
    }
  }
  //
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String deleteQuery = "DELETE FROM courses WHERE id = :id;";
  //       con.createQuery(deleteQuery)
  //         .addParameter("id", id)
  //         .executeUpdate();
  //
  //     String joinDeleteQuery = "DELETE FROM student_courses WHERE Course_id = :CourseId";
  //       con.createQuery(joinDeleteQuery)
  //       .addParameter("CourseId", this.getId())
  //       .executeUpdate();
  //   }
  // }
  //
  // public void update(String title) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE courses SET title = :title WHERE id= :id";
  //     con.createQuery(sql)
  //     .addParameter("title", title)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
}
