package at.htl.leosurvey.entities;

import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
class S_TransactionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;

    @Test
    void createS_TransactionTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey survey = new Survey(dt, q);
        tm.begin();
        em.persist(q);
        em.persist(survey);
        em.persist(new S_Transaction(false, survey, "abc"));
        tm.commit();
        Table transactions = new Table(DataSource.getDataSource(), "s_transaction");
        assertThat(transactions).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(false)
                .value().isEqualTo("abc")
                .value().isEqualTo(1);
    }
}