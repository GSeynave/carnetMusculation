import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Programme } from '../class/programme';
import { catchError,  } from 'rxjs/operators';
import { Exercice } from '../class/exercice';
import { Seance } from '../class/seance';
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
  getSeancesByProgramme(programmeId: number): Observable<Seance[]> {
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
  getProgrammes(page: number, pageSize: number, sort: string): Observable<Programme[]> {
    return this.http.get<Programme[]>(this.url+`programmes?page=${page}&size=${pageSize}&sort=${sort}`,)
      .pipe(
        catchError(err => {
          console.log('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
    }

  setProgramme(program: Programme): Observable<Programme>{
    return this.http.post<Programme>(this.url+"programmes", program)
      .pipe(
        catchError(err => {
          console.log('Error while saving program', err);
          return throwError(err);
        })
      );
  }
  
  getProgrammeCount(): Observable<number> {
    return this.http.get<number>(this.url+"programmes/count")
      .pipe(
        catchError(err => {
          console.log('Error while retrieving the list of programmes');
          return throwError(err);
        })
      )
  }
  
  deleteProgramme(idProgram: number): Observable<number> {
    const headers = new HttpHeaders({
      'content-type' : 'application/json',
      'Access-Control-Allow-Origin' : '*'
    });
    const options = {
      headers: headers,
      responseType:'number'
    };
    return this.http.delete<number>(this.url +"programmes/" +idProgram, )
    .pipe(
      catchError(err => {
        return throwError(err);
      })
    );
  }

   /**
   * Serie's
   * @returns 
   */
    getSeriesByExerciceId(exerciceId: number): Observable<SerieLine[]> {
      return this.http.get<SerieLine[]>(this.url+`series/exercice/${exerciceId}`)
        .pipe(
          catchError(err => {
            console.log('Error while retrieving the list of programmes');
            return throwError(err);
          })
        )
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
