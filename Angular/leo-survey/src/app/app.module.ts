import { MatTableModule } from '@angular/material/table';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { CommonModule } from "@angular/common";
import {MatRadioModule} from '@angular/material/radio';


import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FillOutSurveyComponent } from './fill-out-survey/fill-out-survey.component';
import { AppRoutingModule } from './app-routing.module';
import { GetResultsComponent } from './get-results/get-results.component';
import { MatSelectModule } from '@angular/material/select';
import { CongratulationComponent } from './congratulation/congratulation.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { HomepageComponent } from './homepage/homepage.component';
import { NewSurveyComponent } from './new-survey/new-survey.component';
import {MatSliderModule} from '@angular/material/slider';

@NgModule({
  declarations: [
    AppComponent,
    FillOutSurveyComponent,
    GetResultsComponent,
    CongratulationComponent,
    HomepageComponent,
    NewSurveyComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatTableModule,
    MatCheckboxModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatRadioModule,
    MatToolbarModule,
    MatSliderModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
