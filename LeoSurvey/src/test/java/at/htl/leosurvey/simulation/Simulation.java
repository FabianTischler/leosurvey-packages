package at.htl.leosurvey.simulation;

import at.htl.leosurvey.control.*;
import at.htl.leosurvey.entities.*;
import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Simulation {
    @PersistenceContext
    EntityManager em;

    @Inject
    AnswerOptionRepository answerOptionRepository;
    @Inject
    QuestionnaireRepository questionnaireRepository;
    @Inject
    QuestionRepository questionRepository;
    @Inject
    S_TransactionRepository s_transactionRepository;
    @Inject
    SurveyRepository surveyRepository;
    @Inject
    TeacherRepository teacherRepository;

    Table ao_table = new Table(DataSource.getDataSource(), "answeroption");
    Table q_table = new Table(DataSource.getDataSource(), "questionnaire");
    Table qn_table = new Table(DataSource.getDataSource(), "question");
    Table st_table = new Table(DataSource.getDataSource(), "s_transaction");
    Table s_table = new Table(DataSource.getDataSource(), "survey");
    Table t_table = new Table(DataSource.getDataSource(), "teacher");


    @Test
    @Order(10)
    void createFullSurvey(){
        Questionnaire q = new Questionnaire(1L, "Questionnaire", "Survey about the current Situation");
        questionnaireRepository.save(q);

        LocalDate d = LocalDate.now();
        Survey s = new Survey(d, (Questionnaire) em.createQuery("select q from Questionnaire q where q.qn_id = 1").getSingleResult());
        surveyRepository.save(s);

        S_Transaction s_t1 = new S_Transaction(false,
                (Survey) em.createQuery("select s from Survey s where s.s_id = 1").getSingleResult(), "abc");
        S_Transaction s_t2 = new S_Transaction(false,
                (Survey) em.createQuery("select s from Survey s where s.s_id = 1").getSingleResult(), "abc");
        S_Transaction s_t3 = new S_Transaction(false,
                (Survey) em.createQuery("select s from Survey s where s.s_id = 1").getSingleResult(), "abc");
        s_transactionRepository.save(s_t1);
        s_transactionRepository.save(s_t2);
        s_transactionRepository.save(s_t3);

        Teacher t = new Teacher("Teach",
                (Survey) em.createQuery("select s from Survey s where s.s_id = 1").getSingleResult());
        teacherRepository.save(t);

        Question q1 = new Question("Are you satisfied?", 1, QuestionType.YESORNO.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.qn_id = 1").getSingleResult());
        Question q2 = new Question("What are your Thoughts?", 2, QuestionType.FREETEXT.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.qn_id = 1").getSingleResult());
        Question q3 = new Question("Choose one or more", 3, QuestionType.MULTIPLECHOICE.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.qn_id = 1").getSingleResult());
        questionRepository.save(q1);
        questionRepository.save(q2);
        questionRepository.save(q3);

        AnswerOption a1q1 = new AnswerOption("Yes", 1, 1,
                (Question) em.createQuery("select q from Question q where q.q_id = 1").getSingleResult(), 0);
        AnswerOption a1q3 = new AnswerOption("Answer 1", 3, 3,
                (Question) em.createQuery("select q from Question q where q.q_id = 3").getSingleResult(), 0);
        AnswerOption a2q1 = new AnswerOption("No", 2, 2,
                (Question) em.createQuery("select q from Question q where q.q_id = 1").getSingleResult(), 0);
        AnswerOption a2q3 = new AnswerOption("Answer 2", 4, 4,
                (Question) em.createQuery("select q from Question q where q.q_id = 3").getSingleResult(), 0);
        AnswerOption a3q3 = new AnswerOption("Answer 3", 5, 5,
                (Question) em.createQuery("select q from Question q where q.q_id = 3").getSingleResult(), 0);
        AnswerOption a4q3 = new AnswerOption("Answer 4", 6, 6,
                (Question) em.createQuery("select q from Question q where q.q_id = 3").getSingleResult(), 0);
        answerOptionRepository.save(a1q1);
        answerOptionRepository.save(a2q1);
        answerOptionRepository.save(a1q3);
        answerOptionRepository.save(a2q3);
        answerOptionRepository.save(a3q3);
        answerOptionRepository.save(a4q3);

        List<Object[]> results = em.createQuery("select s, q from Survey as s, Questionnaire as q where s.s_id = 1 " +
                "and s.s_questionnaire.qn_id = q.qn_id").getResultList();

        for(Object[] o : results){
            Survey survey = (Survey) o[0];
            Questionnaire questionnaire = (Questionnaire) o[1];
            System.out.println("Survey: " + survey.getS_id() + " Questionnaire: " + questionnaire.getQn_name());
        }

        assertThat(ao_table).hasNumberOfRows(6);
        assertThat(q_table).hasNumberOfRows(1);
        assertThat(qn_table).hasNumberOfRows(3);
        assertThat(st_table).hasNumberOfRows(3);
        assertThat(s_table).hasNumberOfRows(1);
        assertThat(t_table).hasNumberOfRows(1);
    }
}
