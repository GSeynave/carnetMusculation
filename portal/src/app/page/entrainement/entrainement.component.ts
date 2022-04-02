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
  public programmeSelected: Programme = new Programme();
  private programmeId: number = -1;

  constructor(private musculationService: MusculationService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(paramMap => {
      this.programmeId = Number(paramMap.get('programmeId'));

      if(this.programmeId > 0){
        this.musculationService.getProgrammeById(this.programmeId).subscribe( (data) => {
          this.programmeSelected = data;
          this.getEntrainements(this.programmeSelected.id);
        })
      }
    });


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
