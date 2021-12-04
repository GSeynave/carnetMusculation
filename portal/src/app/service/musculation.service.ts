import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Program } from '../class/program';
import { catchError,  } from 'rxjs/operators';
import { Exercice } from '../class/exerice';
import { Session } from '../class/session';

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
    return this.http.get<Exercice[]>(this.url+"exercices").pipe(
      catchError(err => {
        console.log('Error while retrieving the list of exercices');
        return throwError(err);
      })
    )
  }

  /**
   * 
   * @returns Session's
   */
   getSessions(): Observable<Session[]> {
    return this.http.get<Session[]>(this.url+"sessions").pipe(
      catchError(err => {
        console.log('Error while retrieving the list of exercices');
        return throwError(err);
      })
    )
  }

  /**
   * Program's
   * @returns 
   */
  getPrograms(): Observable<Program[]> {
    return this.http.get<Program[]>(this.url+"programs")
      .pipe(
        catchError(err => {
          console.log('Error while retrieving the list of programs');
          return throwError(err);
        })
      )
  }

  setProgram(program: Program): Observable<Program>{
    console.log("program sessions id : ", program.sessionIds)
    return this.http.post<Program>(this.url+"programs", program)
      .pipe(
        catchError(err => {
          console.log('Error while saving program', err);
          return throwError(err);
        })
      );
  }

  
  setSession(session: Session): Observable<Session>{
    return this.http.post<Session>(this.url+"sessions", session)
      .pipe(
        catchError(err => {
          console.log('Error while saving program', err);
          return throwError(err);
        })
      );
  }

  deleteProgram(idProgram: number): Observable<Program> {
    const headers = new HttpHeaders({
      'content-type' : 'application/json',
      'Access-Control-Allow-Origin' : '*'
    });
    const options = {
      headers: headers,
      responseType:'Program'
    };
    return this.http.delete<Program>(this.url +"programs/" +idProgram, )
    .pipe(
      catchError(err => {
        console.log('Error while deleting program', err);
        return throwError(err);
      })
    );
  }
  
  deleteSession(idSession: number): Observable<Session> {
    const headers = new HttpHeaders({
      'content-type' : 'application/json',
      'Access-Control-Allow-Origin' : '*'
    });
    const options = {
      headers: headers,
      responseType:'Program'
    };
    return this.http.delete<Session>(this.url +"sessions/" +idSession, )
    .pipe(
      catchError(err => {
        console.log('Error while deleting program', err);
        return throwError(err);
      })
    );
  }
}