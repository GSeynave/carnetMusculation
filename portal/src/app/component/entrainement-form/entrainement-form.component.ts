import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Entrainement } from 'src/app/class/entrainement';
import { EntrainementCreer } from 'src/app/class/entrainement-creer';
import { EntrainementType } from 'src/app/class/entrainement-type';
import { Exercice } from 'src/app/class/exercice';
import { Programme } from 'src/app/class/programme';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-entrainement-form',
  templateUrl: './entrainement-form.component.html',
  styleUrls: ['./entrainement-form.component.css']
})
export class EntrainementFormComponent implements OnInit {

  @Input() programmeListe: Programme[] = [];
  @Input() exerciceListe: Exercice[] = [];
  @Input() entrainementId: number = -1;
  @Input() entrainementToUpdate: Entrainement = new Entrainement();
  entrainementType = EntrainementType;
  enumsKeys: String[] = [];

  entrainementForm = this.formBuilder.group({
    'programmeId': [],
    'nom': [''],
    'type': [EntrainementType],
    'dateCreation': [],
    'details': this.formBuilder.array([])
  });

  get details() {
    return this.entrainementForm.controls["details"] as FormArray;
  }

  constructor(private musculationService: MusculationService, private formBuilder: FormBuilder) {
    this.enumsKeys = Object.keys(this.entrainementType)
    this.musculationService.getExercices().subscribe((data) => {
      this.exerciceListe = data;
    })
  }

  ngOnInit(): void {  }

  onSubmit(): void {
    let entrainementCreer: EntrainementCreer = this.entrainementForm.value;
    this.entrainementForm.value.dateCreation = new Date().toISOString().substring(0, 10);
    this.musculationService.setEntrainement(entrainementCreer).subscribe();
    console.log("entrainement créer", entrainementCreer);
  }

  public addDetailFormGroupe(): void {
    const detailForm = this.formBuilder.group({
      'exercice': new FormControl(),
      'nbSerie': new FormControl(),
      'nbRep': new FormControl(),
      'recup': new FormControl()
    });
    this.details.push(detailForm);
  }

  public removeOrClearDetails(id: number) {
    this.details.removeAt(id);
  }

}
