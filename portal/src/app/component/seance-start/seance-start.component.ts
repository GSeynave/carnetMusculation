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


  exerciceList: Exercice[] = [];

  @Input() seanceId: number = -1;
  @Input() programmeId: number = -1;
  @Input() entrainementId: number = -1;

  @Input() seanceInformationInit: SeanceInformationInit = new SeanceInformationInit();
  @Output() seanceInformationSubmitEvent = new EventEmitter<SeanceInformationSubmit>();
  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    console.log("seanceInformationInit", this.seanceInformationInit);
    this.musculationService.getDetailExercice(this.entrainementId, State.INIT).subscribe( (data) => {
      this.seanceInformationInit = data;
    });
  }



  onSeanceOver() : void {
    this.musculationService.setSeanceStatus(this.seanceId, State.FINISHED).subscribe();
  }
}
