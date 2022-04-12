import { Question } from 'src/model/question';
export class AnswerOption{
  constructor(
    public ao_id: number,
    public ao_text: String,
    public ao_value: number,
    public ao_sequenceNumber: Number,
    public ao_question: Question,
    public ao_how_often: number
  ){}
}
