import { Questionnaire } from './questionnaire';
export class Survey{
  constructor(
    public s_id: number,
    public s_date: Date,
    public s_questionnaire: Questionnaire
  ){}
}
