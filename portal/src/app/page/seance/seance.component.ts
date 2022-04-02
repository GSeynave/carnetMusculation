import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { detailExercice } from 'src/app/class/detail-exercice';
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
  entrainementId: number = -1;
  programmeId: number = -1;

  constructor(private musculationService: MusculationService, private route: ActivatedRoute) {
    this.route.paramMap.subscribe(paramMap => {
      this.programmeId = Number(paramMap.get('programmeId'));
      this.entrainementId = Number(paramMap.get('entrainementId'));

      if(this.programmeId > 0) {
        this.musculationService.getEntrainementByProgrammeId(this.programmeId).subscribe( (data) => {
          this.entrainementListe = data;
          if(this.entrainementId > 0 && this.entrainementListe.length > 0){
            this.onEntrainementSelect(this.entrainementId);
          }
        })
      }

    });
  }

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
      this.seanceInformationInit = data;
    });
  }
}
