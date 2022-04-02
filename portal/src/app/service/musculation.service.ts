import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Programme } from '../class/programme';
import { catchError, } from 'rxjs/operators';
import { Exercice } from '../class/exercice';
import { Seance } from '../class/seance';
import { Entrainement } from '../class/entrainement';
import { detailExercice } from '../class/detail-exercice';
import { EntrainementCreer } from '../class/entrainement-creer';
import { SeanceInformationInit } from '../class/seance-information-init';
import { State } from '../class/state';

@Injectable({
  providedIn: 'root'
})
export class MusculationService implements ErrorHandler {

  private url: string = "http://localhost:8080/";



  constructor(private http: HttpClient) { }

  handleError(error: any): void {
    throw new Error('Method not implemented.');
  }

  /**
   *
   * @returns Exercice's
   */
  getExercices(): Observable<Exercice[]> {
    return this.http.get<Exercice[]>(this.url + "exercices").pipe(
      catchError(err => {
        console.error('Error while retrieving the list of exercices');
        return throwError(err);
      })
    )
  }
  getExercicesByEntrainementId(entrainementId: number): Observable<detailExercice[]> {
    return this.http.get<detailExercice[]>(this.url + `exercices/entrainement/${entrainementId}`).pipe(
      catchError(err => {
        console.error('Error while retrieving the list of exercices');
        return throwError(err);
      })
    )
  }

  /**
   *
   * @returns Seance's
   */
  getSeances(): Observable<Seance[]> {
    console.log("getSeance")
    return this.http.get<Seance[]>(this.url + "seances")
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of seances');
          return throwError(err);
        })
      )
  }
  getSeancesByProgramme(programmeId: number): Observable<Seance[]> {
    return this.http.get<Seance[]>(this.url + `seances/programme/${programmeId}`).pipe(
      catchError(err => {
        console.error('Error while retrieving the list of seances');
        return throwError(err);
      })
    )
  }

  /**
   * Program's
   * @returns
   */
  getProgrammesPage(page: number, pageSize: number, sort: string): Observable<Programme[]> {
    return this.http.get<Programme[]>(this.url + `programmes?page=${page}&size=${pageSize}&sort=${sort}`,)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }
  getProgrammes(): Observable<Programme[]> {
    return this.http.get<Programme[]>(this.url + "programmes")
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }
  getProgrammeById(programmeId: number): Observable<Programme> {
    return this.http.get<Programme>(this.url + `programmes/${programmeId}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }

  setProgramme(program: Programme): Observable<Programme> {
    return this.http.post<Programme>(this.url + "programmes", program)
      .pipe(
        catchError(err => {
          console.error('Error while saving program', err);
          return throwError(err);
        })
      );
  }

  getProgrammeCount(): Observable<number> {
    return this.http.get<number>(this.url + "programmes/count")
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }

  deleteProgramme(idProgram: number): Observable<number> {
    const headers = new HttpHeaders({
      'content-type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    });
    const options = {
      headers: headers,
      responseType: 'number'
    };
    return this.http.delete<number>(this.url + "programmes/" + idProgram,)
      .pipe(
        catchError(err => {
          return throwError(err);
        })
      );
  }

  /**
   * Entirnaments
   * @returns
   */
  getEntrainements(programmeId: number): Observable<Entrainement[]> {
    return this.http.get<Entrainement[]>(this.url + `entrainements/programme/${programmeId}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }

  getEntrainementById(entrainementId: number): Observable<Entrainement> {
    return this.http.get<Entrainement>(this.url + `entrainements/${entrainementId}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }

  getEntrainementByProgrammeId(programmeId: number): Observable<Entrainement[]> {
    return this.http.get<Entrainement[]>(this.url + `entrainements/programme/${programmeId}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }
  setEntrainement(entrainementCreer: EntrainementCreer): Observable<EntrainementCreer> {
    return this.http.post<EntrainementCreer>(this.url + "entrainements", entrainementCreer)
    .pipe(
      catchError(err => {
        console.error('Error while saving entrainement', err);
        return throwError(err);
      })
    );
  }


  getDetailExercice(entrainementId: number, state: State): Observable<SeanceInformationInit> {
    return this.http.get<SeanceInformationInit>(this.url + `seances/entrainement/${entrainementId}/${state}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }

}
