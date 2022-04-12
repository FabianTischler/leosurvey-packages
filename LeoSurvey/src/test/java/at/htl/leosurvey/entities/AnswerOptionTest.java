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
class AnswerOptionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;

    @Test
    void createAnswerOptionsTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q);
        tm.begin();
        em.persist(q);
        em.persist(qn);
        em.persist(new AnswerOption("Yes", 1, 1, qn, 1));
        em.persist(new AnswerOption("no", 2, 2, qn, 1));
        tm.commit();
        Table ao = new Table(DataSource.getDataSource(), "answeroption");
        assertThat(ao).hasNumberOfRows(2);
        assertThat(ao).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes")
                .value().isEqualTo(1);
    }
}