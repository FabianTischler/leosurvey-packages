package at.htl.leosurvey.entities;

import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.derby.jdbc.ClientDataSource;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AnswerTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    
    @Test
    void createAnswerTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q);
        tm.begin();
        em.persist(q);
        em.persist(qn);
        em.persist(new Answer("Yes", qn));
        tm.commit();
        Table a = new Table(DataSource.getDataSource(), "answer");
        assertThat(a).hasNumberOfRows(1);
        assertThat(a).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes")
                .value().isEqualTo(1);
    }
}