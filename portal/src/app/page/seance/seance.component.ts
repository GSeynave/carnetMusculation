import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Entrainement } from 'src/app/class/entrainement';
import { Programme } from 'src/app/class/programme';
import { SeanceInformationInit } from 'src/app/class/seance-information-init';
import { State } from 'src/app/class/state';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-seance',
  templateUrl: './seance.component.html',
  styleUrls: ['./seance.component.css']
})
export class SeanceComponent implements OnInit {
  isSeanceDisplay: boolean = true;
  isHistoriqueDisplay: boolean = false;
  programmeListe: Programme[] = [];
  entrainementListe: Entrainement[] = [];
  seanceInformationInit: SeanceInformationInit = new SeanceInformationInit();

  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    this.musculationService.getProgrammes().subscribe( (data) => {
      this.programmeListe = data;
    })
  }

  displaySeance(): void {
    this.isSeanceDisplay = true;
    this.isHistoriqueDisplay = false;
  }

  displayHistorique(): void {
    this.isSeanceDisplay = false;
    this.isHistoriqueDisplay = true;
  }

  onProgrammeSelect(programmeId: number): void{
    this.musculationService.getEntrainementByProgrammeId(programmeId).subscribe( (data) => {
      this.entrainementListe = data;
    })
  }

  onEntrainementSelect(entrainementId: number): void {
    this.musculationService.getDetailExercice(entrainementId, State.INIT).subscribe( (data) => {
      console.log(data);
      this.seanceInformationInit = data;
    });
  }
}
