import { Question } from 'src/model/question';
import { Answer } from './answer';
import { AnswerOption } from './answer-option';
export class ChosenOption{
  constructor(
    public co_id: number,
    public co_ao: AnswerOption,
    public co_a: Answer,
    public co_q: Question,
    public transaction_code: String
  ){}
}
