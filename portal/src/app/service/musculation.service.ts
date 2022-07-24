import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Programme } from '../class/programme';
import { catchError, } from 'rxjs/operators';
import { Exercice } from '../class/exercice';
import { Seance } from '../class/seance';
import { Entrainement } from '../class/entrainement';
import { DetailExercice } from '../class/detail-exercice';
import { SeanceInformationInit } from '../class/seance-information-init';
import { State } from '../class/state';
import { EntrainementCreer } from '../class/entrainement-creer';
import { Serie } from '../class/serie';
import { ExercicePerformance } from '../class/exercice-performance';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class MusculationService implements ErrorHandler {

  private url: string = "http://localhost:8080/";



  constructor(private http: HttpClient) { }

  handleError(error: any): void {
    throw new Error('Method not implemented.');
  }


  login(user: User) : Observable<boolean>{
    return this.http.post<boolean>(this.url + "login", user).pipe(
      catchError(err => {
        console.error('Error while retrieving the list of exercices');
        return throwError(err);
      })
    )
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
  getExercicesByEntrainementId(entrainementId: number): Observable<DetailExercice[]> {
    return this.http.get<DetailExercice[]>(this.url + `exercices/entrainement/${entrainementId}`).pipe(
      catchError(err => {
        console.error('Error while retrieving the list of exercices');
        return throwError(err);
      })
    )
  }

  getDetailExercicesBySeanceId(seanceId: number): Observable<ExercicePerformance[]> {
    return this.http.get<ExercicePerformance[]>(this.url + `exercices/seance/${seanceId}`).pipe(
      catchError(err => {
        console.error('Error while retrieving the list of exercices details');
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
   * Entrainements
   * @returns
   */
  getEntrainementById(entrainementId: number): Observable<Entrainement> {
    return this.http.get<Entrainement>(this.url + `entrainements/${entrainementId}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }

  getEntrainementsByProgrammeId(programmeId: number): Observable<Entrainement[]> {
    return this.http.get<Entrainement[]>(this.url + `entrainements/programme/${programmeId}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of entrainements');
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

  updateEntrainement(entrainementCreer: EntrainementCreer): Observable<EntrainementCreer> {
    return this.http.patch<EntrainementCreer>(this.url + "entrainements", entrainementCreer)
      .pipe(
        catchError(err => {
          console.error('Error while saving entrainement', err);
          return throwError(err);
        })
      );
  }

  deleteEntrainement(idEntrainement: number): Observable<number> {
    const headers = new HttpHeaders({
      'content-type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    });
    const options = {
      headers: headers,
      responseType: 'number'
    };
    return this.http.delete<number>(this.url + "entrainements/" + idEntrainement,)
      .pipe(
        catchError(err => {
          return throwError(err);
        })
      );
  }

  getDetailExercice(entrainementId: number, state: State): Observable<SeanceInformationInit> {
    return this.http.get<SeanceInformationInit>(this.url + `seances/entrainement/${entrainementId}/state/${state}`)
      .pipe(
        catchError(err => {
          console.error('Error while retrieving the list of seance');
          return throwError(err);
        })
      )
  }

  getSeanceByProgrammeIdAndEntrainementIdAndState(programmeId: number, entrainementId: number, state:State): Observable<Seance[]> {
    return this.http.get<Seance[]>(this.url + `seances/programme/${programmeId}/entrainement/${entrainementId}/state/${state}`)
    .pipe(
      catchError(err => {
        console.error('Error while retrieving the list of seance');
        return throwError(err);
      })
    )
  }
  createSeance(entrainementId: number, status: State) : Observable<Seance> {
    console.log("createSeance", status);
    return this.http.post<Seance>(this.url + `seances/entrainement/${entrainementId}/status/${status}`, null)
      .pipe(
        catchError(err => {
          console.error('Error while saving seance', err);
          return throwError(err);
        })
      );
  }

  setSeanceStatus(seanceId: number, status: State) : Observable<Seance> {
    return this.http.post<Seance>(this.url + `seances/${seanceId}`, status)
    .pipe(
      catchError(err => {
        console.error('Error while saving seance status', err);
        return throwError(err);
      })
    );
  }
  setSerie(serie: Serie, seanceId: number) : Observable<Serie> {
    return this.http.post<Serie>(this.url + `series/seance/${seanceId}`, serie)
    .pipe(
      catchError(err => {
        console.error('Error while saving serie', err);
        return throwError(err);
      })
    );
  }

}
