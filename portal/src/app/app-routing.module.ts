import { APP_BASE_HREF } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExerciceComponent } from './page/exercice/exercice.component';
import { HomeComponent } from './page/home/home.component';
import { ProgramComponent } from './page/program/program.component';
import { SessionComponent } from './page/session/session.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'program', component: ProgramComponent },
  { path: 'exercice', component: ExerciceComponent },
  { path: 'session', component: SessionComponent },
  { path: '**', component: HomeComponent },  // Wildcard route for a 404 page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{provide: APP_BASE_HREF, useValue : '/'}]
})
export class AppRoutingModule { }
