package at.htl.leosurvey.dtos;

import at.htl.leosurvey.entities.AnswerOption;
import at.htl.leosurvey.entities.Question;

import java.util.Map;

public class DisplayResultDTO {
    public Question question;
    public Map<AnswerOption, Integer> answerOptionMap;

    public DisplayResultDTO(Question question, Map<AnswerOption, Integer> answerOptionMap) {
        this.question = question;
        this.answerOptionMap = answerOptionMap;
    }
}
