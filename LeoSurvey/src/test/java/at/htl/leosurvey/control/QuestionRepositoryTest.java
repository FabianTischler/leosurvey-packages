package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Question;
import at.htl.leosurvey.entities.QuestionType;
import at.htl.leosurvey.entities.Questionnaire;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import at.htl.leosurvey.misc.DataSource;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Inject
    QuestionRepository questionRepository;

    Table t = new Table(DataSource.getDataSource(), "question");
    Table questionnaire = new Table(DataSource.getDataSource(), "questionnaire");

    @Test
    @Order(10)
    void createQuestionTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qu = new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q);

        questionRepository.save(qu);
        assertThat(t).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes or No")
                .value().isEqualTo("SINGLECHOICE")
                .value().isEqualTo(1);
    }

    @Test
    @Order(20)
    void deleteQuestionTest(){
        questionRepository.delete(1);
        assertThat(t).hasNumberOfRows(0);
    }

    @Test
    @Order(30)
    void createMultipleChoiceQuestionTest(){
        Questionnaire q = new Questionnaire(2L, "Test", "Test of the Questionnaire");
        Question qu = new Question("MultipleChoice Question", 1, QuestionType.MULTIPLECHOICE.name(), q);

        questionRepository.save(qu);
        assertThat(t).row(0).column(3).value().equals("MULTIPLECHOICE");
    }

    @Test
    @Order(40)
    void createYESORNOQuestionTest(){

        Question qu = new Question("YESORNO Question", 1, QuestionType.YESORNO.name(), (Questionnaire) em.createQuery("select q from Questionnaire q where q.qn_id = 2").getSingleResult());

        questionRepository.save(qu);
        assertThat(t).row(1).column(3).value().equals("YESORNO");
    }

    @Test
    @Order(66)
    void createFreetextQuestionTest(){

        Question qu = new Question("Freetext Question", 1, QuestionType.FREETEXT.name(), (Questionnaire) em.createQuery("select q from Questionnaire q where q.qn_id = 2").getSingleResult());

        questionRepository.save(qu);
        assertThat(t).row(2).column(3).value().equals("FREETEXT");
    }
}
