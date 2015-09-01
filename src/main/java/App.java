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

    // get("/students", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   List<Student> students = Student.all();
    //   model.put("students", students);
    //   model.put("template", "templates/students.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/courses", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   List<Course> courses = Course.all();
    //   model.put("courses", courses);
    //   List<Student> students = Student.all();
    //   model.put("students", students);
    //   model.put("template", "templates/courses.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/students/:id", (request,response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int id = Integer.parseInt(request.params("id"));
    //   Student task = Student.find(id);
    //   model.put("task", task);
    //   model.put("allCategories", Course.all());
    //   model.put("template", "templates/task.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/categories/:id", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int id = Integer.parseInt(request.params("id"));
    //   Category category = Category.find(id);
    //   model.put("category", category);
    //   model.put("allTasks", Student.all());
    //   model.put("template", "templates/category.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // // post("/students/:id/edit", (request, response) -> {
    // //   HashMap<String, Object> model = new HashMap<String, Object>();
    // //   int taskId = Integer.parseInt(request.queryParams("task_id"));
    // //   Student task = Student.find(ta
    // //   int categoryId = Integer.parseInt(request.queryParams("category_id"));
    // //   Category category = Category.find(categoryId);
    // //   String description = request.queryParams("name");
    // //   task.update(description);
    // //   model.put("template", "templates/edit-task.vtl");
    // // return new ModelAndView(model, layout);
    // // }, new VelocityTemplateEngine());
    // //
    // // post("/category/:id/edit", (request, response) -> {
    // //   int taskId = Integer.parseInt(request.queryParams("task_id"));
    // //   int categoryId = Integer.parseInt(request.queryParams("category_id"));
    // //   Category category = Category.find(categoryId);
    // //   Student task = Student.find(taskId);
    // //   String name = request.queryParams("name");
    // //   category.edit(name);
    // //   model.put("template", "templates/edit-category.vtl");
    // //   return new ModelAndView(model, layout);
    // //   }, new VelocityTemplateEngine());
    //
    post("/students", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object> ();
      model.put("template", "templates/home.vtl");
      String name = request.queryParams("name");
      String enrollment_date = request.queryParams("enrollment_date");
      Student newStudent = new Student(name, enrollment_date);
      newStudent.save();

      List<Student> students = Student.all();
      model.put("students", students);
      List<Course> courses = Course.all();
      model.put("courses", courses);

      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/courses", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      String title = request.queryParams("title");
      int course_number = Integer.parseInt(request.queryParams("course_number")); //must convert integer to a string.
      Course newCourse = new Course(title, course_number);
      newCourse.save();

      List<Student> students = Student.all();
      model.put("students", students);
      List<Course> courses = Course.all();
      model.put("courses", courses);

      // response.redirect("/courses");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    //
    // post("/add_tasks", (request, response) -> {
    //   int taskId = Integer.parseInt(request.queryParams("task_id"));
    //   int categoryId = Integer.parseInt(request.queryParams("category_id"));
    //   Course category = Category.find(categoryId);
    //   Student task = Student.find(taskId);
    //   category.addTask(task);
    //   response.redirect("/courses/" + categoryId);
    //   return null;
    // });
    //
    // post("/add_categories", (request, response) -> {
    //   int taskId = Integer.parseInt(request.queryParams("task_id"));
    //   int categoryId = Integer.parseInt(request.queryParams("category_id"));
    //   Category category = Category.find(categoryId);
    //   Student task = Student.find(taskId);
    //   task.addCategory(category);
    //   response.redirect("/students/" + taskId);
    //   return null;
    // });
  }
}
