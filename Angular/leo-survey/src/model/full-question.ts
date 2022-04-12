import { AnswerOption } from './answer-option';
export class FullQuestion{
  constructor(
    public q_id: number,
    public q_text: String,
    public q_sequenceNumber: Number,
    public q_type: String,
    public q_questionnaire: String,
    public q_options: AnswerOption[]
  ){}
}
