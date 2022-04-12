package at.htl.leosurvey.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long s_id;
    private LocalDate s_date;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private Questionnaire s_questionnaire;

    public Survey() {
    }

    public Survey(LocalDate s_date, Questionnaire s_questionnaire) {
        this.s_date = s_date;
        this.s_questionnaire = s_questionnaire;
    }

    public Long getS_id() {
        return s_id;
    }

    public LocalDate getS_date() {
        return s_date;
    }

    public void setS_date(LocalDate s_date) {
        this.s_date = s_date;
    }

    public Questionnaire getS_questionnaire() {
        return s_questionnaire;
    }

    public void setS_questionnaire(Questionnaire s_questionnaire) {
        this.s_questionnaire = s_questionnaire;
    }
}
