package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Questionnaire;
import at.htl.leosurvey.entities.Survey;
import at.htl.leosurvey.entities.Teacher;
import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherRepositoryTest {

    @Inject
    TeacherRepository teacherRepository;

    Table teacher = new Table(DataSource.getDataSource(), "teacher");

    @Test
    @Order(10)
    void createTeacherTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey s = new Survey(dt, q);
        teacherRepository.save(new Teacher("Teach", s));
        assertThat(teacher).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Teach")
                .value().isEqualTo(1);
    }
}
