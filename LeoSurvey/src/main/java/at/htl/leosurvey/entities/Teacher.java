package at.htl.leosurvey.entities;

import javax.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int t_id;
    private String t_name;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private Survey t_survey;

    public Teacher() {
    }

    public Teacher(String t_name, Survey t_survey) {
        this.t_name = t_name;
        this.t_survey = t_survey;
    }

    public int getT_id() {
        return t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public Survey getT_survey() {
        return t_survey;
    }

    public void setT_survey(Survey t_survey) {
        this.t_survey = t_survey;
    }
}
