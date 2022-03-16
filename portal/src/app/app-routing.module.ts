import { APP_BASE_HREF } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExerciceComponent } from './page/exercice/exercice.component';
import { GeneralComponent } from './page/general/general.component';
import { HomeComponent } from './page/home/home.component';
import { ProgrammeComponent } from './page/programme/programme.component';
import { SeanceComponent } from './page/seance/seance.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'general', component: GeneralComponent },
  { path: 'program', component: ProgrammeComponent },
  { path: 'exercice', component: ExerciceComponent },
  { path: 'seance', component: SeanceComponent },
  { path: '**', component: HomeComponent },  // Wildcard route for a 404 page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{provide: APP_BASE_HREF, useValue : '/'}]
})
export class AppRoutingModule { }
