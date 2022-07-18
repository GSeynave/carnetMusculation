import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { map, Observable, startWith } from 'rxjs';
import { Entrainement } from 'src/app/class/entrainement';
import { Programme } from 'src/app/class/programme';
import { SeanceInformationInit } from 'src/app/class/seance-information-init';
import { State } from 'src/app/class/state';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-seance-historique',
  templateUrl: './seance-historique.component.html',
  styleUrls: ['./seance-historique.component.css']
})
export class SeanceHistoriqueComponent implements OnInit {
  programmeSelectedOk: boolean = false;
  entrainementSelectedOk: boolean = false;

  programmes: Programme[] = [];
  programmeForm = new FormControl('');
  programmeOptions: string[] = [];
  programmeFilteredOptions!: Observable<string[]>;
  programmeSelectedId: number = -1;


  entrainements: Entrainement[] = [];
  entrainementForm = new FormControl('');
  entrainementOptions: string[] = [];
  entrainementFilteredOptions!: Observable<string[]>;
  entrainementSelectedId: number = -1;

  seancesInformationInit: SeanceInformationInit[] = [];

  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {

    this.musculationService.getProgrammes().subscribe( (data) => {
      this.programmes = data;
      this.programmes.forEach( programme => {
        this.programmeOptions.push(programme.nom);
      })
    });

    this.programmeFilteredOptions = this.programmeForm.valueChanges.pipe(
      startWith(''),
      map(value => this.programmeFilter(value || '')),
    );
  }

  private programmeFilter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.programmeOptions.filter(option => option.toLowerCase().includes(filterValue));
  }

  isProgrammeOk() : void {
    this.programmeSelectedOk = false;
    this.programmes.forEach((programme) => {
      if (programme.nom === this.programmeForm.value){
        if(this.entrainementSelectedId !== programme.id){
          this.programmeSelectedId = programme.id;
          this.getEntrainements();
        }
      }
    });
  }

  getEntrainements(): void {
    this.musculationService.getEntrainementsByProgrammeId(this.programmeSelectedId).subscribe( data => {
      this.entrainements = data;
      this.programmeSelectedOk = true;
      this.getEntrainementOptions(this.entrainements);
      this.getEntrainementFilteredOptions(this.entrainements);
      console.log(this.entrainements);
    });
  }

  getEntrainementOptions(entrainements: Entrainement[]) : void {
    entrainements.forEach( entrainement => {
      this.entrainementOptions.push(entrainement.nom);
    })
    console.log(this.entrainementOptions);
  }

  getEntrainementFilteredOptions(entrainements: Entrainement[]): void {
    this.entrainementFilteredOptions = this.entrainementForm.valueChanges.pipe(
      startWith(''),
      map(value => this.entrainementFilter(value || '')),
    );
  }

  private entrainementFilter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.entrainementOptions.filter(option => option.toLowerCase().includes(filterValue));
  }


  isEntrainementOk() : void {
    this.entrainementSelectedOk = false;
    this.entrainements.forEach((entrainement) => {
      if (entrainement.nom === this.entrainementForm.value){
        if(this.entrainementSelectedId !== entrainement.id){
          this.entrainementSelectedId = entrainement.id;
          this.getSeances();
        }
      }
    });
  }

  getSeances(): void {
    this.musculationService.getDetailExerciceByProgrammeIdAndEntrainementIdAndState(this.programmeSelectedId, this.entrainementSelectedId, State.FINISHED).subscribe(data => {
      this.seancesInformationInit = data;
      this.entrainementSelectedOk = true;
    })
  }
}
