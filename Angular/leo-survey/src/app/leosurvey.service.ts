import { S_Transactioncode } from './../model/transactioncode';
import { FullQuestion } from './../model/full-question';
import { AnswerOption } from './../model/answer-option';
import { Question } from './../model/question';
import { Injectable } from '@angular/core';
import { HowOften } from 'src/model/how-often';
import { Answer } from 'src/model/answer';

@Injectable({
  providedIn: 'root'
})
export class LeosurveyService {
  private questions: Question[];
  private options: AnswerOption[];
  private fullquestions: FullQuestion[];
  private howoften: HowOften[];
  private answers: Answer[];
  private trcodes: S_Transactioncode[];

  constructor() {
    this.questions = [] ;
    this.options = [];
    this.fullquestions = [];
    this.howoften = [];
    this.answers = [];
  }
  setQuestions(data: Question[]): void{
    this.questions = data;
  }
  getQuestions(): Question[]{
    return this.questions;
  }

  setOptions(data: AnswerOption[]): void{
    this.options = data;
  }
  getOptions(): AnswerOption[]{
    return this.options;
  }

  setFullQuestions(data: FullQuestion[]): void{
    this.fullquestions = data;
  }
  getFullQuestions1(): FullQuestion[]{
    return this.fullquestions.slice(0, 9);
  }
  getFullQuestions2(): FullQuestion[]{
    return this.fullquestions.slice(9, 39);
  }
  getFullQuestions3(): FullQuestion[]{
    return this.fullquestions.slice(39, 40);
  }
  getFullQuestions4(): FullQuestion[]{
    return this.fullquestions.slice(40);
  }

  setOneQuestion(data: FullQuestion): void{
    this.fullquestions.push(data);
  }

  setOneHowoften(data: HowOften): void{
    this.howoften.push(data);
  }
  getHowOften1(): HowOften[]{
    return this.howoften.slice(0,40);
  }

  setTrCodes(data: S_Transactioncode[]): void{
    this.trcodes = data;
  }
  getTrCodes(): S_Transactioncode[]{
    return this.trcodes;
  }
  getTrCodeStrings(): String[]{
    let codes = [];
    this.trcodes.forEach(element => {
      codes.push(element.t_transactioncode)
    });
    return codes;
  }
  setCodeToUsed(code: String) {
    this.trcodes.forEach(element => {
      if(element.t_transactioncode == code){
        element.t_is_used = true;
        console.log(element.t_is_used)
      }
    });
  }
  isCodeUsed(code: String): boolean {
    var r = true;
    this.trcodes.forEach(element => {
      if(element.t_transactioncode == code && element.t_is_used == false){
        r = false;
      }
    });
    return r;
  }
}
