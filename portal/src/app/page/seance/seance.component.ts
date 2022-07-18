import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Entrainement } from 'src/app/class/entrainement';
import { Programme } from 'src/app/class/programme';
import { SeanceInformationInit } from 'src/app/class/seance-information-init';

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
  seanceId: number = -1;

  constructor(private route: ActivatedRoute) {
    this.route.paramMap.subscribe(paramMap => {
      this.programmeId = Number(paramMap.get('programmeId'));
      this.entrainementId = Number(paramMap.get('entrainementId'));
      this.seanceId = Number(paramMap.get('seanceId'));
    });
  }

  ngOnInit(): void {
  }
}
