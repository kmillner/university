import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Student> students = Student.all();
      model.put("students", students);
      List<Course> courses = Course.all();
      model.put("courses", courses);
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/newstudent", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Student> students = Student.all();
      model.put("students", students);
      List<Course> courses = Course.all();
      model.put("courses", courses);
      model.put("template", "templates/newstudent.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/profile", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // int id = Integer.parseInt(request.params("id"));
      List<Student> students = Student.all();
      // Student student = Student.find(id);
      model.put("students", students);
      model.put("template", "templates/profile.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/viewroster", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Course> courses = Course.all();
      model.put("courses", courses);
      model.put("template", "templates/viewroster.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/courses/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Course course = Course.find(id);
      model.put("course", course);

      model.put("allStudents", Student.all());
      model.put("template", "templates/courses.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/students/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Student student = Student.find(id);
      model.put("student", student);
      model.put("allCourses", Course.all());
      model.put("template", "templates/student.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/students/:id/edit-student", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Student student = Student.find(id);
      model.put("student", student);
      model.put("allCourses", Course.all());
      model.put("template", "templates/edit-student.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/students/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.queryParams("id"));
      Student student = Student.find(id);
      String name = request.queryParams("name");
      String enrollment_date = request.queryParams("enrollment_date");
      String gender = request.queryParams("gender");
      int age = Integer.parseInt(request.queryParams("age"));
      String bio = request.queryParams("bio");

      student.update(name, enrollment_date, gender, age, bio);

      response.redirect("/");
      return null;
      });

    get("/courses/:id/edit-courses", (request,response) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();
        int id = Integer.parseInt(request.params("id"));
        Course course = Course.find(id);
        model.put("course", course);
        //model.put("course", Course.all());
        model.put("template", "templates/edit-courses.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/search-results", (request,response) ->{
          HashMap<String, Object> model = new HashMap<String, Object>();
          String search = request.queryParams("search");
          List<Course> courses = Course.search(search);
          model.put("courses", courses);
          model.put("template", "templates/search-results.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

      get("/addcourse", (request,response) ->{
          HashMap<String, Object> model = new HashMap<String, Object>();
          String search = request.queryParams("search");
          List<Course> courses = Course.search(search);
          model.put("courses", courses);
          model.put("template", "templates/addcourse.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    post("/courses/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.queryParams("id"));
      Course courses = Course.find(id);
      String title = request.queryParams("title");
      courses.edit(title);

      response.redirect("/viewroster");
      return null;
      });

    post("/students", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object> ();
      String name = request.queryParams("name");
      String enrollment_date = request.queryParams("enrollment_date");
      String gender = request.queryParams("gender");
      int age = Integer.parseInt(request.queryParams("age"));
      String bio = request.queryParams("bio");
      Student newStudent = new Student(name, enrollment_date, gender, age, bio);
      newStudent.save();

      List<Student> students = Student.all();
      model.put("students", students);
      List<Course> courses = Course.all();
      model.put("courses", courses);

      response.redirect("/");
      return null;
      });

      post("/students/:id/delete", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object> ();
        int id = Integer.parseInt(request.params("id"));
        Student student = Student.find(id);
        student.delete();

        List<Student> students = Student.all();
        model.put("students", students);
        List<Course> courses = Course.all();
        model.put("courses", courses);

        response.redirect("/");
        return null;
        });

      post("/courses/:id/delete", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object> ();
        int id = Integer.parseInt(request.params("id"));
        Course course = Course.find(id);
        course.delete();

        // List<Student> students = Student.all();
        // model.put("students", students);
        // List<Course> courses = Course.all();
        // model.put("courses", courses);

        response.redirect("/");
        return null;
        });

    post("/courses", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      //model.put("template", "templates/home.vtl");
      String title = request.queryParams("title");
      int course_number = Integer.parseInt(request.queryParams("course_number")); //must convert integer to a string.
      Course newCourse = new Course(title, course_number);
      newCourse.save();

      List<Student> students = Student.all();
      model.put("students", students);
      List<Course> courses = Course.all();
      model.put("courses", courses);

      response.redirect("/");
      return null;
  });

    post("/add_student", (request, response) -> {
      int studentId = Integer.parseInt(request.queryParams("student_id"));
      int coursesId = Integer.parseInt(request.queryParams("course_id"));
      Course courses = Course.find(coursesId);
      Student student = Student.find(studentId);
      courses.addStudent(student);
      response.redirect("/courses/" + coursesId);
      return null;
    });

    post("/add_courses", (request, response) -> {
      int studentId = Integer.parseInt(request.queryParams("student_id"));
      int courseId = Integer.parseInt(request.queryParams("course_id"));
      Course course = Course.find(courseId);
      Student student = Student.find(studentId);
      student.addCourse(course);
      response.redirect("/students/" + studentId);
      return null;
    });
  }
}
