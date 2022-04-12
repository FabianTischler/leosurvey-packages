package at.htl.leosurvey.entities;

import at.htl.leosurvey.misc.DataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.derby.jdbc.ClientDataSource;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;

import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class SurveyTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;

    @Test
    void createSurveyTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        tm.begin();
        em.persist(q);
        em.persist(new Survey(dt, q));
        tm.commit();
        Table surveys = new Table(DataSource.getDataSource(), "survey");
        assertThat(surveys).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(dt)
                .value().isEqualTo(1);
    }
}