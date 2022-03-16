import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Programme } from 'src/app/class/programme';
import { Seance } from 'src/app/class/seance';

@Component({
  selector: 'app-programme-form',
  templateUrl: './programme-form.component.html',
  styleUrls: ['./programme-form.component.css']
})
export class ProgrammeFormComponent implements OnInit {

  @Output("programmeOnSubmit") programmeOnSubmit: EventEmitter<Programme> = new EventEmitter();
  @Output("hideCreateForm") hideCreateForm: EventEmitter<any> = new EventEmitter();

  seancesList: Seance[] = [];
  programme: Programme = new Programme();

  programmeFormGroup: FormGroup = new FormGroup({
    id: new FormControl(this.programme.id),
    nom: new FormControl(this.programme.nom),
    dateCreation: new FormControl(this.programme.dateCreation),
    dateModification: new FormControl(this.programme.dateModification)
  });

  constructor() { }

  ngOnInit(): void { }

  onSubmit(): void {
    var programme: Programme = new Programme();
    if (this.programmeFormGroup.value.id) {
      programme.id = this.programmeFormGroup.value.id;
    }
    programme.nom = this.programmeFormGroup.value.nom;
    if (this.programmeFormGroup.value.dateCreation) {
      programme.dateCreation = this.programmeFormGroup.value.dateCreation;
    }
    else {
      programme.dateCreation = new Date().toISOString().substring(0, 10);
    }
    programme.dateModification = new Date().toISOString().substring(0, 10);
    this.programmeOnSubmit.emit(programme);
  }

  onUpdate(programme: Programme): void {
    this.programme.id = programme.id;
    this.programme.nom = programme.nom;
    this.programme.dateCreation = programme.dateCreation;
    this.programme.dateModification = new Date().toISOString().substring(0, 10);
  }

  onHideCreateForm(): void{
    this.hideCreateForm.emit();
  }
}
