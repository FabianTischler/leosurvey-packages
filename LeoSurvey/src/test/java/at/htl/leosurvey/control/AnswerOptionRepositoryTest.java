package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.Question;
import at.htl.leosurvey.entities.QuestionType;
import at.htl.leosurvey.entities.Questionnaire;
import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerOptionRepositoryTest {

    @Inject
    AnswerOptionRepository answerOptionRepository;

    Table ao = new Table(DataSource.getDataSource(), "answeroption");

    @Test
    @Order(10)
    void createAnswerOptionTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q);

        answerOptionRepository.save(new AnswerOption("Yes", 1, 1, qn, 0));
        answerOptionRepository.save(new AnswerOption("no", 2, 2, qn, 0));

        assertThat(ao).hasNumberOfRows(2);
        assertThat(ao).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes")
                .value().isEqualTo(1);
    }
}
