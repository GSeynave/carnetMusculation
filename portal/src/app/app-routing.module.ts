import { APP_BASE_HREF } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SeanceComponent } from './page/seance/seance.component';
import { EntrainementComponent } from './page/entrainement/entrainement.component';
import { HomeComponent } from './page/home/home.component';
import { ProgrammeComponent } from './page/programme/programme.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'programmes', component: ProgrammeComponent },
  { path: 'entrainements', component: EntrainementComponent },
  { path: 'entrainements/programme/:programmeId', component: EntrainementComponent },
  { path: 'seances', component: SeanceComponent },
  { path: 'seances/entrainement/:entrainementId', component: SeanceComponent },
  { path: '**', component: HomeComponent },  // Wildcard route for a 404 page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{provide: APP_BASE_HREF, useValue : '/'}]
})
export class AppRoutingModule { }
