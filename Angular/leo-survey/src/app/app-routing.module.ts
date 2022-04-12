import { NewSurveyComponent } from './new-survey/new-survey.component';
import { HomepageComponent } from './homepage/homepage.component';
import { CongratulationComponent } from './congratulation/congratulation.component';
import { AppComponent } from './app.component';
import { GetResultsComponent } from './get-results/get-results.component';
import { FillOutSurveyComponent } from './fill-out-survey/fill-out-survey.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  { path: 'fillout', component: FillOutSurveyComponent },
  { path: 'getresults', component: GetResultsComponent },
  { path: 'home', component: HomepageComponent },
  { path: '', component: HomepageComponent },
  { path: 'congratulation', component: CongratulationComponent },
  { path: 'new', component: NewSurveyComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
