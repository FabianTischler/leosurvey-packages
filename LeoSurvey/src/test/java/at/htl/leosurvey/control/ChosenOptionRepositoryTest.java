package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.*;
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
public class ChosenOptionRepositoryTest {

    @Inject
    ChosenOptionRepository chosenOptionRepository;

    Table chosenOption = new Table(DataSource.getDataSource(), "chosenoption");

    @Test
    @Order(10)
    void createChosenOptionTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q);
        Answer a = new Answer("Yes", qn);
        AnswerOption a1 = new AnswerOption("Yes", 1, 1, qn, 0);
        chosenOptionRepository.save(new ChosenOption(a1, a, qn, "abc"));
        assertThat(chosenOption).hasNumberOfRows(1);
        assertThat(chosenOption).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("abc")
                .value().isEqualTo(1)
                .value().isEqualTo(1);
    }
}
