import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExerciceFormComponent } from './component/exercice-form/exercice-form.component';
import { ExerciceListComponent } from './component/exercice-list/exercice-list.component';
import { NavigationBarComponent } from './component/navigation-bar/navigation-bar.component';
import { ProgramFormComponent } from './component/program-form/program-form.component';
import { ProgramListComponent } from './component/program-list/program-list.component';
import { SessionFormComponent } from './component/session-form/session-form.component';
import { SessionListComponent } from './component/session-list/session-list.component';
import { SessionComponent } from './page/session/session.component';
import { ExerciceComponent } from './page/exercice/exercice.component';
import { ProgramComponent } from './page/program/program.component';
import { HomeComponent } from './page/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input'
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { GeneralComponent } from './page/general/general.component';
import { HeaderComponent } from './component/header/header.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { ExercicePanelComponent } from './component/exercice-panel/exercice-panel.component';
import { StringListComponent } from './component/string-list/string-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ExerciceFormComponent,
    ExerciceListComponent,
    NavigationBarComponent,
    ProgramFormComponent,
    ProgramListComponent,
    SessionFormComponent,
    SessionListComponent,
    SessionComponent,
    ExerciceComponent,
    ProgramComponent,
    HomeComponent,
    GeneralComponent,
    HeaderComponent,
    ExercicePanelComponent,
    StringListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatButtonModule,
    MatTableModule,
    MatListModule,
    MatSelectModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
