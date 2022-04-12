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
class TeacherTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;

    @Test
    void createTeacherTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey s = new Survey(dt, q);
        tm.begin();
        em.persist(q);
        em.persist(s);
        em.persist(new Teacher("Teach", s));
        tm.commit();
        Table teacher = new Table(DataSource.getDataSource(), "teacher");
        assertThat(teacher).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Teach")
                .value().isEqualTo(1);
    }
}