import { AnswerOption } from './answer-option';
export class HowOften{
  constructor(
    public q_text: String,
    public q_options: AnswerOption[],
  ){}
}
