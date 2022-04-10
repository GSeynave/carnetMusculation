import { Component, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Entrainement } from 'src/app/class/entrainement';
import { EntrainementCreer } from 'src/app/class/entrainement-creer';
import { Exercice } from 'src/app/class/exercice';
import { Programme } from 'src/app/class/programme';
import { SeanceInformationInit } from 'src/app/class/seance-information-init';
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
  public isModification: boolean = false;
  public entrainementToUpdate: EntrainementCreer = new EntrainementCreer();
  private programmeId: number = -1;

  constructor(private musculationService: MusculationService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(paramMap => {
      this.programmeId = Number(paramMap.get('programmeId'));

      if (this.programmeId > 0) {
        this.musculationService.getProgrammeById(this.programmeId).subscribe((data) => {
          this.programmeSelected = data;
          this.getEntrainements(this.programmeSelected.id);
        })
      }
    });


    this.musculationService.getProgrammes().subscribe((data) => {
      this.programmeListe = data;
    })
    this.musculationService.getExercices().subscribe((data) => {
      this.exerciceListe = data;
    })


  }

  getEntrainements(programmeId: number) {
    this.musculationService.getEntrainements(programmeId).subscribe((data) => {
      this.entrainementListe = data;
    });
  }

  onEntrainementSelect(entrainementId: number): void {
    if (entrainementId && entrainementId != -1) {
      this.musculationService.getEntrainementById(entrainementId).subscribe((data) => {
        this.entrainementSelected = data;
      });

      this.router.navigate([`/seances/programme/${this.programmeId}/entrainement/${entrainementId}`,]);
    }
  }

  onEntrainementDelete(entrainementId: number): void {
    console.log("delete ", entrainementId);
    this.musculationService.deleteEntrainement(entrainementId).subscribe();
  }

  retour(): void {
    this.entrainementSelected.id = -1;
  }

  displayListe(): void {
    this.isListDisplay = true;
    this.isCreerDisplay = false;
  }

  displayCreer(): void {
    this.isListDisplay = false;
    this.isCreerDisplay = true;
  }

  onProgrammeSelect(programmeId: number): void {
    this.musculationService.getEntrainementByProgrammeId(programmeId).subscribe((data) => {
      this.entrainementListe = data;
    })
  }

  onModificationEntrainement(entrainementId: number): void {
    this.musculationService.getEntrainementDetailsById(entrainementId).subscribe((data) =>  {
      this.entrainementToUpdate = data;
      this.isModification = true;
    });
  }
  annulerModification(): void {
    this.isModification = false;
    this.entrainementToUpdate = new EntrainementCreer();
  }
}
