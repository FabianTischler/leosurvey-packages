import { Question } from 'src/model/question';
export class Answer{
  constructor(
    public a_id: number,
    public a_answer_text: String,
    public q_question: Question
  ){}
}
