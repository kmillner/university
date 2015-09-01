import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Student {
  private int id;
  private String name;
  private String enrollment_date;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEnrollmentDate(){
    return enrollment_date;
  }

  public Student(String name, String enrollment_date) {
    this.name = name;
    this.enrollment_date = enrollment_date;
  }

  @Override
  public boolean equals(Object otherStudent){
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getName().equals(newStudent.getName()) &&
             this.getId() == newStudent.getId() &&
             this.getEnrollmentDate().equals(newStudent.getEnrollmentDate());
    }
  }

  public static List<Student> all() {
    String sql = "SELECT id, name, enrollment_date FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students(name, enrollment_date) VALUES (:name, :enrollment_date)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("enrollment_date", enrollment_date)
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

  public void update(String name, int enrollment_date) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE students SET (name, enrollment_date) = (:name, :enrollment_date) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .addParameter("enrollment_date", enrollment_date)
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
        .addParameter("student_id", this.getId())
        .executeUpdate();
      }
    }

  public ArrayList<Course> getCourses() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT course_id FROM student_courses WHERE Student_id = :Student_id";
      List<Integer> courseIds = con.createQuery(sql)
      .addParameter("Student_id", this.getId())
      .executeAndFetch(Integer.class);

      ArrayList<Course> courses = new ArrayList<Course>();

      for (Integer courseId : courseIds) {
          String StudentQuery = "Select * From courses WHERE id = :courseId";
          Course course = con.createQuery(StudentQuery)
            .addParameter("courseId", courseId)
            .executeAndFetchFirst(Course.class);
          courses.add(course);
      }
      return courses;
    }
  }

}
