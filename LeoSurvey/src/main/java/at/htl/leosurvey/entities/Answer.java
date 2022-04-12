package at.htl.leosurvey.entities;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long a_id;
    private String a_answer_text;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private Question q_question;

    public Answer() {
    }

    public Answer(String a_answer_text, Question q_question) {
        this.a_answer_text = a_answer_text;
        this.q_question = q_question;
    }

    public Long getA_id() {
        return a_id;
    }

    public String getA_answer_text() {
        return a_answer_text;
    }

    public void setA_answer_text(String a_answer_text) {
        this.a_answer_text = a_answer_text;
    }

    public Question getQ_question() {
        return q_question;
    }

    public void setQ_question(Question q_question) {
        this.q_question = q_question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "a_id=" + a_id +
                ", a_answer_text='" + a_answer_text + '\'' +
                ", q_question=" + q_question +
                '}';
    }
}
