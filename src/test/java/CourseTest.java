import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class CourseTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Course.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Course firstCourse = new Course("Intro to Bio", 1);
    Course secondCourse = new Course("Intro to Bio", 1);
    assertTrue(firstCourse.equals(secondCourse));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Course myCourse = new Course("Intro to Bio", 1);
    myCourse.save();
    assertTrue(Course.all().get(0).equals(myCourse));
  }

  @Test
  public void find_findCourseInDatabase_true() {
    Course myCourse = new Course("Intro to Bio", 1);
    myCourse.save();
    Course savedCourse = Course.find(myCourse.getId());
    assertTrue(myCourse.equals(savedCourse));
  }

 //  @Test
 //  public void addStudent_addsStudentToCourse() {
 //    Course myCourse = new Course("Intro to Bio");
 //    myCourse.save();
 //
 //    Student myStudent = new Student("Jake");
 //    myStudent.save();
 //
 //    myCourse.addStudent(myStudent);
 //    Student savedStudent = myCourse.getStudents().get(0);
 //    assertTrue(myStudent.equals(savedStudent));
 // }
 //
 //   @Test
 //   public void getStudents_returnsAllStudents_ArrayList() {
 //     Course myCourse = new Course("Intro to Bio");
 //     myCourse.save();
 //
 //     Student myStudent = new Student("Jake");
 //     myStudent.save();
 //
 //     myCourse.addStudent(myStudent);
 //     List savedStudents = myCourse.getStudents();
 //     assertEquals(savedStudents.size(), 1);
 //   }
 //
 //   @Test
 //   public void delete_deletesAllStudentsAndListsAssociations() {
 //     Course myCategory = new Course("Intro to Bio");
 //     myCourse.save();
 //
 //     Student myStudent = new Student("Jake");
 //     myStudent.save();
 //
 //     myCourse.addStudent(myStudent);
 //     myCourse.delete();
 //     assertEquals(myStudent.getCourses().size(), 0);
 //   }
 //
 //   @Test
 //   public void edit_editCourseInStudentInDatabase() {
 //     Course myCourse = new Course("Intro to Bio");
 //     myCourse.save();
 //
 //     Student myStudent = new Student("Jake");
 //     myStudent.save();
 //
 //     myCourse.update("Chemistry");
 //     assertEquals("Chemistry", Course.all().get(0).getTitle());
 //   }
}
