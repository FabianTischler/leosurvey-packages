package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Answer;
import at.htl.leosurvey.entities.Question;
import at.htl.leosurvey.entities.QuestionType;
import at.htl.leosurvey.entities.Questionnaire;
import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerRepositoryTest {

    @Inject
    AnswerRepository answerRepository;

    Table a = new Table(DataSource.getDataSource(), "answer");

    @Test
    @Order(10)
    void createAnswerTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q);
        answerRepository.save(new Answer("Yes", qn));
        assertThat(a).hasNumberOfRows(1);
        assertThat(a).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes")
                .value().isEqualTo(1);
    }
}
