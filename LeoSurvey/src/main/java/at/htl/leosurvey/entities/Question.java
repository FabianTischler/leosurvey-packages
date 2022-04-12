package at.htl.leosurvey.entities;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long q_id;
    private String q_text;
    //private Blob q_image;
    private int q_sequenceNumber; //???
    private String q_type;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE})
    private Questionnaire q_questionnaire;

    public Question() {
    }

    public Question(String q_text, /*Blob q_image,*/ int q_sequenceNumber, String q_type, Questionnaire q_questionnaire) {
        this.q_text = q_text;
        //this.q_image = q_image;
        this.q_sequenceNumber = q_sequenceNumber;
        this.q_type = q_type;
        this.q_questionnaire = q_questionnaire;
    }

    public void setQ_id(Long q_id){
        this.q_id = q_id;
    }

    public Long getQ_id() {
        return q_id;
    }

    public String getQ_text() {
        return q_text;
    }

    public void setQ_text(String q_text) {
        this.q_text = q_text;
    }

    //public Blob getQ_image() {
    //    return q_image;
    //}

    //public void setQ_image(Blob q_image) {
    //    this.q_image = q_image;
    //}

    public int getQ_sequenceNumber() {
        return q_sequenceNumber;
    }

    public void setQ_sequenceNumber(int q_sequenceNumber) {
        this.q_sequenceNumber = q_sequenceNumber;
    }

    public String getQ_type() {
        return q_type;
    }

    public void setQ_type(String q_type) {
        this.q_type = q_type;
    }

    public Questionnaire getQ_questionnaire() {
        return q_questionnaire;
    }

    public void setQ_questionnaire(Questionnaire q_questionnaire) {
        this.q_questionnaire = q_questionnaire;
    }

    @Override
    public String toString() {
        return "Question{" +
                "q_id=" + q_id +
                ", q_text='" + q_text + '\'' +
                ", q_sequenceNumber=" + q_sequenceNumber +
                ", q_type='" + q_type + '\'' +
                ", q_questionnaire=" + q_questionnaire +
                '}';
    }
}


