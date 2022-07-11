import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Entrainement } from 'src/app/class/entrainement';
import { Programme } from 'src/app/class/programme';
import { SeanceInformationInit } from 'src/app/class/seance-information-init';
import { SeanceInformationSubmit } from 'src/app/class/seance-information-submit';
import { MusculationService } from 'src/app/service/musculation.service';
import { State } from 'src/app/class/state';
import { Exercice } from 'src/app/class/exercice';
import { Seance } from 'src/app/class/seance';

@Component({
  selector: 'app-seance-start',
  templateUrl: './seance-start.component.html',
  styleUrls: ['./seance-start.component.css']
})
export class SeanceStartComponent implements OnInit {


  isSeanceSelected: boolean = false;
  exerciceList: Exercice[] = [];
  @Input() seanceId: number = -1;
  @Input() programmeId: number = -1;
  @Input() entrainementId: number = -1;

  @Input() programmeListe: Programme[] = [];
  @Output() selectedProgrammeIdEvent = new EventEmitter<number>();

  @Input() entrainementListe: Entrainement[] = [];
  @Output() selectedEntrainementIdEvent = new EventEmitter<number>();

  @Input() seanceInformationInit: SeanceInformationInit = new SeanceInformationInit();
  @Output() seanceInformationSubmitEvent = new EventEmitter<SeanceInformationSubmit>();
  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    if(this.programmeId > 0 && this.entrainementId > 0){
      this.isSeanceSelected = true;
    }
  }

  onProgrammeSelect(programmeId: number): void {
    this.selectedProgrammeIdEvent.emit(programmeId);
    this.entrainementListe = [];
    this.seanceInformationInit = new SeanceInformationInit();
    this.entrainementId = -1;
  }

  onEntrainementSelect(entrainementId: number): void {
    this.musculationService.getDetailExercice(entrainementId, State.INIT).subscribe( (data) => {
      this.seanceInformationInit = data;
    });
    this.isSeanceSelected = true;
  }


  onSeanceOver() : void {
    this.musculationService.setSeanceStatus(this.seanceId, State.FINISHED).subscribe();
  }
}
