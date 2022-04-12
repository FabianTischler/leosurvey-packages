import { Survey } from './survey';
export class S_Transactioncode{
  constructor(
    public tr_id: number,
    public t_transactioncode: String,
    public t_is_used: boolean,
    public t_survey: Survey
  ){}
}
