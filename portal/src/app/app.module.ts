import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationBarComponent } from './component/navigation-bar/navigation-bar.component';
import { ProgrammeFormComponent } from './component/programme-form/programme-form.component';
import { ProgrammeListComponent } from './component/programme-list/programme-list.component';
import { ExerciceComponent } from './page/exercice/exercice.component';
import { ProgrammeComponent } from './page/programme/programme.component';
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
import { MatExpansionModule } from '@angular/material/expansion';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FormsModule } from '@angular/forms';
import { EntrainementListeComponent } from './component/entrainement-liste/entrainement-liste.component';
import { EntrainementStartComponent } from './component/entrainement-start/entrainement-start.component';
import { CountPipe } from './pipe/count.pipe';
import { SerieFormComponent } from './component/serie-form/serie-form.component';
import { EntrainementFormComponent } from './component/entrainement-form/entrainement-form.component';
import { SeanceComponent } from './page/seance/seance.component';
import { EntrainementComponent } from './page/entrainement/entrainement.component';
import { SeanceStartComponent } from './component/seance-start/seance-start.component';
import { SeanceHistoriqueComponent } from './component/seance-historique/seance-historique.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { SeanceDetailComponent } from './component/seance-detail/seance-detail.component';
import { LoggerComponent } from './component/logger/logger.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    ProgrammeFormComponent,
    ProgrammeListComponent,
    ExerciceComponent,
    ProgrammeComponent,
    HomeComponent,
    GeneralComponent,
    EntrainementListeComponent,
    EntrainementStartComponent,
    CountPipe,
    SerieFormComponent,
    EntrainementFormComponent,
    SeanceComponent,
    EntrainementComponent,
    SeanceStartComponent,
    SeanceHistoriqueComponent,
    SeanceDetailComponent,
    LoggerComponent
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
    MatExpansionModule,
    MatCheckboxModule,
    MatPaginatorModule,
    FormsModule,
    MatAutocompleteModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
