import { APP_BASE_HREF } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SeanceComponent } from './component/seance/seance.component';
import { EntrainementComponent } from './page/entrainement/entrainement.component';
import { ExerciceComponent } from './page/exercice/exercice.component';
import { HomeComponent } from './page/home/home.component';
import { ProgrammeComponent } from './page/programme/programme.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'programme', component: ProgrammeComponent },
  { path: 'entrainement', component: EntrainementComponent },
  { path: 'seance', component: SeanceComponent },
  { path: 'seance/entrainement/:entrainementId', component: SeanceComponent },
  { path: '**', component: HomeComponent },  // Wildcard route for a 404 page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{provide: APP_BASE_HREF, useValue : '/'}]
})
export class AppRoutingModule { }
