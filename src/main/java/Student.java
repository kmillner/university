import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Student {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Student(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherStudent){
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getName().equals(newStudent.getName()) &&
             this.getId() == newStudent.getId();
    }
  }

  public static List<Student> all() {
    String sql = "SELECT id, name FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Student find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students where id=:id";
      Student student = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Student.class);
      return student;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE students SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM students WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM student_courses WHERE student_id = :studentId";
      con.createQuery(joinDeleteQuery)
        .addParameter("StudentId", this.getId())
        .executeUpdate();
    }
  }

  public void addCourse(Course course) {
    try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO student_courses (course_id, student_id) VALUES (:course_id, :student_id)";
        con.createQuery(sql)
        .addParameter("course_id", course.getId())
        .addParameter("Student_id", this.getId())
        .executeUpdate();
      }
    }
  //
  // public ArrayList<Course> getCourses() {
  //   try(Connection con = DB.sql2o.open()){
  //     String sql = "SELECT course_id FROM student_courses WHERE Student_id = :Student_id";
  //     List<Integer> courseIds = con.createQuery(sql)
  //     .addParameter("Student_id", this.getId())
  //     .executeAndFetch(Integer.class);
  //
  //     ArrayList<Course> courses = new ArrayList<Course>();
  //
  //     for (Integer courseId : courseIds) {
  //         String StudentQuery = "Select * From courses WHERE id = :courseId";
  //         course course = con.createQuery(StudentQuery)
  //           .addParameter("courseId", courseId)
  //           .executeAndFetchFirst(course.class);
  //         courses.add(course);
  //     }
  //     return courses;
  //   }
  // }
  //
  // public void completed() {
  //   if true
  // }
}
