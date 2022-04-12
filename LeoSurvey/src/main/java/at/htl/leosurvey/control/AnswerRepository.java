package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Answer;
import at.htl.leosurvey.entities.AnswerOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AnswerRepository implements PanacheRepository<Answer> {

    @Transactional
    public Answer save(Answer answer){
        return getEntityManager().merge(answer);
    }
}
