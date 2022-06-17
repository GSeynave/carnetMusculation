import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormArray, FormBuilder, FormControl } from '@angular/forms';
import { EntrainementCreer } from 'src/app/class/entrainement-creer';
import { EntrainementType } from 'src/app/class/entrainement-type';
import { Exercice } from 'src/app/class/exercice';
import { Programme } from 'src/app/class/programme';
import { SeanceInformationInit } from 'src/app/class/seance-information-init';
import { MusculationService } from 'src/app/service/musculation.service';
import { DetailExercice } from "src/app/class/detail-exercice";

@Component({
  selector: 'app-entrainement-form',
  templateUrl: './entrainement-form.component.html',
  styleUrls: ['./entrainement-form.component.css']
})
export class EntrainementFormComponent implements OnInit {

  @Input() programmeListe: Programme[] = [];
  @Input() exerciceListe: Exercice[] = [];
  @Input() entrainementId: number = -1;
  @Input() entrainementToUpdate: EntrainementCreer = new EntrainementCreer();

  @Output('annulerModification') annulerModificationEntrainementEvent: EventEmitter<number> = new EventEmitter<number>();
  entrainementType = EntrainementType;
  enumsKeys: String[] = [];

  entrainementForm = this.formBuilder.group({
    'entrainementId': [],
    'programmeId': [],
    'nom': [''],
    'type': [EntrainementType],
    'creationDate': [],
    'modificationDate': [],
    'details': this.formBuilder.array([])
  });

  get details() {
    return this.entrainementForm.controls["details"] as FormArray;
  }

  constructor(private musculationService: MusculationService, private formBuilder: FormBuilder) {
    this.enumsKeys = Object.keys(this.entrainementType);
  }

  ngOnInit(): void { }

  onSubmit(): void {
    if(this.entrainementForm.value.creationDate === null) {
      this.entrainementForm.value.creationDate = new Date().toISOString().substring(0, 10);
    }
    this.entrainementForm.value.modificationDate = new Date().toISOString().substring(0, 10);
    let entrainementCreer: EntrainementCreer = this.entrainementForm.value;
    if (entrainementCreer.entrainementId != null) {
      this.musculationService.updateEntrainement(entrainementCreer).subscribe();
    } else {
      this.musculationService.setEntrainement(entrainementCreer).subscribe();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['entrainementToUpdate']) {
      this.entrainementToUpdate = changes['entrainementToUpdate'].currentValue;
      this.fillFormWithEntrainementToUpdate(this.entrainementToUpdate);
    }
  }

  public addDetailFormGroupe(): void {
    const detailForm = this.formBuilder.group({
      'exerciceId': new FormControl(),
      'nbSerie': new FormControl(),
      'nbRep': new FormControl(),
      'recup': new FormControl()
    });
    this.details.push(detailForm);
  }

  public addDetailToUpdateFormGroupe(detailExercices: DetailExercice[]): void {
    if(detailExercices){
      detailExercices.forEach(details => {
        let exercice: Exercice = new Exercice();
        const detailForm = this.formBuilder.group({
          'exerciceId': details.exerciceId,
          'nbSerie': details.nbSerie,
          'nbRep': details.nbRep,
          'recup': details.recup
        });
        this.details.push(detailForm);
      });
    }
  }

  public removeOrClearDetails(id: number) {
    this.details.removeAt(id);
  }

  public annulerModification(): void {
    this.annulerModificationEntrainementEvent.emit();
  }

  public fillFormWithEntrainementToUpdate(entrainementToUpdate: EntrainementCreer): void {
    console.log("ent : ", entrainementToUpdate);
    this.entrainementForm.patchValue({
      entrainementId: entrainementToUpdate.entrainementId,
      programmeId: entrainementToUpdate.programmeId,
      nom: entrainementToUpdate.nom,
      type: entrainementToUpdate.type,
      dateCreation: entrainementToUpdate.creationDate,
      details: this.addDetailToUpdateFormGroupe(entrainementToUpdate.details)
    });
  }
}
