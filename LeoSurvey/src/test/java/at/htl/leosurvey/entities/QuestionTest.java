package at.htl.leosurvey.entities;

import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.derby.jdbc.ClientDataSource;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
class QuestionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;

    @Test
    void createQuestionTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        tm.begin();
        em.persist(q);
        em.persist(new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q));
        em.persist(new Question("No or Yes", 2, QuestionType.SINGLECHOICE.name(), q));
        tm.commit();
        Table questions = new Table(DataSource.getDataSource(), "question");
        assertThat(questions).hasNumberOfRows(2);
        assertThat(questions).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes or No")
                .value().isEqualTo("SINGLECHOICE")
                .value().isEqualTo(1);
    }
}