import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Entrainement } from 'src/app/class/entrainement';
import { Programme } from 'src/app/class/programme';
import { SeanceInformationInit } from 'src/app/class/seance-information-init';
import { SeanceInformationSubmit } from 'src/app/class/seance-information-submit';

@Component({
  selector: 'app-seance-start',
  templateUrl: './seance-start.component.html',
  styleUrls: ['./seance-start.component.css']
})
export class SeanceStartComponent implements OnInit {



  @Input() programmeListe: Programme[] = [];
  @Output() selectedProgrammeIdEvent = new EventEmitter<number>();

  @Input() entrainementListe: Entrainement[] = [];
  @Output() selectedEntrainementIdEvent = new EventEmitter<number>();

  @Input() seanceInformationInit: SeanceInformationInit = new SeanceInformationInit();
  @Output() seanceInformationSubmitEvent = new EventEmitter<SeanceInformationSubmit>();
  constructor() { }

  ngOnInit(): void {
  }

  onProgrammeSelect(programmeId: number): void {
    this.selectedProgrammeIdEvent.emit(programmeId);
    this.entrainementListe = [];
    this.seanceInformationInit = new SeanceInformationInit();
  }

  onEntrainementSelect(entrainementId: number): void {
    this.selectedEntrainementIdEvent.emit(entrainementId);
    this.entrainementListe = [];
    this.seanceInformationInit = new SeanceInformationInit();
  }

}
