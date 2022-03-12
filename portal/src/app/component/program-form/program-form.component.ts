import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Exercice } from 'src/app/class/exercice';
import { Programme } from 'src/app/class/programme';
import { Seance } from 'src/app/class/seance';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-program-form',
  templateUrl: './program-form.component.html',
  styleUrls: ['./program-form.component.css']
})
export class ProgramFormComponent implements OnInit {

  seancesList: Seance [] = [];

  programmeFormGroup: FormGroup = new FormGroup({
    name: new FormControl('')
  });

  constructor(private musculationService: MusculationService){ }

  ngOnInit(): void {
    this.musculationService.getSeances().subscribe(response => {
      this.seancesList = response;
    });
  }

  onSubmit(){
    console.log(this.programmeFormGroup.value);
    var programme: Programme = new Programme();
    programme.nom = this.programmeFormGroup.value.name;
    programme.dateCreation = new Date().toISOString().substring(0,10);
    console.log(programme);

    this.musculationService.setProgramme(programme).subscribe();
      
    location.reload();
  }
}
