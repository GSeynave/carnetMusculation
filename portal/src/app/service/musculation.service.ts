import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Program } from '../class/program';
import { catchError,  } from 'rxjs/operators';
import { Exercice } from '../class/exercice';
import { Seance } from '../class/seance';
import { Serie } from '../class/serie';
import { SerieLine } from '../component/table-serie/table-serie.component';

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
  getExercicesBySeanceId(seanceId: number): Observable<Exercice[]> {
    return this.http.get<Exercice[]>(this.url+`exercices/seance/${seanceId}`).pipe(
      catchError(err => {
        console.log('Error while retrieving the list of exercices');
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
    return this.http.get<Seance[]>(this.url+"seances")
    .pipe(
      catchError(err => {
        console.log('Error while retrieving the list of seances');
        return throwError(err);
      })
    )
  }
  getSeancesByProgram(programmeId: number): Observable<Seance[]> {
    return this.http.get<Seance[]>(this.url+`seances/programme/${programmeId}`).pipe(
      catchError(err => {
        console.log('Error while retrieving the list of seances');
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
   /**
   * Serie's
   * @returns 
   */
    getSeriesByExerciceId(exerciceId: number): Observable<SerieLine[]> {
      return this.http.get<SerieLine[]>(this.url+`series/exercice/${exerciceId}`)
        .pipe(
          catchError(err => {
            console.log('Error while retrieving the list of programs');
            return throwError(err);
          })
        )
    }

  setProgram(program: Program): Observable<Program>{
    return this.http.post<Program>(this.url+"programs", program)
      .pipe(
        catchError(err => {
          console.log('Error while saving program', err);
          return throwError(err);
        })
      );
  }

  
  setSeance(seance: Seance): Observable<Seance>{
    return this.http.post<Seance>(this.url+"seances", seance)
      .pipe(
        catchError(err => {
          console.log('Error while saving seance', err);
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
  
  deleteSeance(idSeance: number): Observable<Seance> {
    const headers = new HttpHeaders({
      'content-type' : 'application/json',
      'Access-Control-Allow-Origin' : '*'
    });
    const options = {
      headers: headers,
      responseType:'Program'
    };
    return this.http.delete<Seance>(this.url +"seances/" +idSeance, )
    .pipe(
      catchError(err => {
        console.log('Error while deleting seance', err);
        return throwError(err);
      })
    );
  }
}
