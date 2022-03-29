import { Component, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Entrainement } from 'src/app/class/entrainement';
import { Exercice } from 'src/app/class/exercice';
import { Programme } from 'src/app/class/programme';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-entrainement',
  templateUrl: './entrainement.component.html',
  styleUrls: ['./entrainement.component.css']
})
export class EntrainementComponent implements OnInit {

  public programmeListe: Programme[] = [];
  public exerciceListe: Exercice[] = [];
  public entrainementSelected: Entrainement = new Entrainement();
  public entrainementListe: Entrainement[] = [];
  public isCreerDisplay: boolean = false;
  public isListDisplay: boolean = true;

  constructor(private musculationService: MusculationService, private route: ActivatedRoute) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['programmeId']) {
      this.getEntrainements(changes['programmeId'].currentValue);
    }
  }

  ngOnInit(): void {
    this.musculationService.getProgrammes().subscribe( (data) => {
      this.programmeListe = data;
    })
    this.musculationService.getExercices().subscribe( (data) => {
      this.exerciceListe = data;
    })
  }

  getEntrainements(programmeId: number) {
    this.musculationService.getEntrainements(programmeId).subscribe((data) => {
      this.entrainementListe = data;
    });
  }

  onEntrainementSelect(entrainementId: number): void {
    if(entrainementId && entrainementId != -1){
      this.musculationService.getEntrainementById(entrainementId).subscribe((data) => {
        this.entrainementSelected = data;
      });
    }
  }

  retour(): void {
    this.entrainementSelected.id = -1;
  }

  displayListe(): void{
    this.isListDisplay =true;
    this.isCreerDisplay = false;
  }

  displayCreer(): void{
    this.isListDisplay =false;
    this.isCreerDisplay = true;
  }

  onProgrammeSelect(programmeId: number): void{
    this.musculationService.getEntrainementByProgrammeId(programmeId).subscribe( (data) => {
      this.entrainementListe = data;
    })
  }
}
