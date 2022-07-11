import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Programme } from 'src/app/class/programme';

@Component({
  selector: 'app-programme-form',
  templateUrl: './programme-form.component.html',
  styleUrls: ['./programme-form.component.css']
})
export class ProgrammeFormComponent implements OnInit {

  @Output("programmeOnSubmit") programmeOnSubmit: EventEmitter<Programme> = new EventEmitter();
  @Output("hideCreateForm") hideCreateForm: EventEmitter<any> = new EventEmitter();

  programme: Programme = new Programme();

  programmeFormGroup: FormGroup = new FormGroup({
    id: new FormControl(this.programme.id),
    nom: new FormControl(this.programme.nom),
    dateCreation: new FormControl(this.programme.dateCreation),
    dateModification: new FormControl(this.programme.dateModification)
  });

  constructor() { }

  ngOnInit(): void { }
  onSubmit() {
    var programme: Programme = new Programme();

    programme.nom = this.programmeFormGroup.value.nom;
    programme.dateCreation = new Date().toISOString().substring(0, 10);
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
