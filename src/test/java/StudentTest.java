import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // @Test
  // public void all_emptyAtFirst() {
  //   assertEquals(Student.all().size(), 0);
  // }
  //
  // @Test
  // public void equals_returnsTrueIfnamesAretheSame() {
  //   Student firstStudent = new Student("Jake");
  //   Student secondStudent = new Student("Jake");
  //   assertTrue(firstStudent.equals(secondStudent));
  // }
  //
  // @Test
  // public void save_returnsTrueIfnamesAretheSame() {
  //   Student myStudent = new Student("Jake");
  //   myStudent.save();
  //   assertTrue(Student.all().get(0).equals(myStudent));
  // }
  //
  // @Test
  // public void all_savesIntoDatabase_true() {
  //   Student myStudent = new Student("Jake");
  //   myStudent.save();
  //   assertEquals(Student.all().get(0).getName(), "Jake");
  // }
  //
  // @Test
  // public void find_findsStudentInDatabase_true() {
  //   Student myStudent = new Student("Jake");
  //   myStudent.save();
  //   Student savedStudent = Student.find(myStudent.getId());
  //   assertEquals(savedStudent.getname(), "Jake");
  // }
  //
  // @Test
  // public void addCourse_addsCourseToStudent() {
  //   Course myCourse = new Course ("Intro to Bio");
  //   myCourse.save();
  //
  //   Student myStudent = new Student("Jake");
  //   myStudent.save();
  //
  //   myStudent.addCourse(myCourse);
  //   Course savedCourse = myStudent.getCourses().get(0);
  //   assertTrue(myCourse.equals(savedCourse));
  // }
  //
  // @Test
  // public void getCourses_returnsAllCourses_ArrayList() {
  //   Course myCourse = new Course("Intro to Bio");
  //   myCourse.save();
  //
  //   Student myStudent = new Student("Jake");
  //   myStudent.save();
  //
  //   myStudent.addCourse(myCourse);
  //   List savedCourses = myStudent.getCourses();
  //   assertEquals(savedCourses.size(), 1);
  // }
  //
  // @Test
  // public void delete_deletesAllStudentsAndListsAssociations() {
  //   Course myCourse = new Course ("Intro to Bio");
  //   myCourse.save();
  //
  //   Student myStudent = new Student ("Jake");
  //   myStudent.save();
  //
  //   myStudent.addCourse(myCourse);
  //   myStudent.delete();
  //   assertEquals(myCourse.getStudents().size(), 0);
  // }
  //
  // @Test
  // public void edit_editStudentinCourseInDatabase() {
  //   Course myCourse = new Course ("Intro to Bio");
  //   myCourse.save();
  //
  //   Student myStudent = new Student("Jake");
  //   myStudent.save();
  //
  //   myStudent.addCourse(myCourse);
  //
  //   myStudent.update("Brian");
  //   assertEquals("Brian", Student.all().get(0).getName());
  // }
  //
  // @Test
  // public void markStudentAsDone() {
  //   Student myStudent = new Student("Jake");
  //   myStudent.save();
  //   myStudent.completed(true);
  //   assertEquals(myStudent.completed(), true);
  // }

}
