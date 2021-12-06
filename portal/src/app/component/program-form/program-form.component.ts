import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Exercice } from 'src/app/class/exercice';
import { Program } from 'src/app/class/program';
import { Seance } from 'src/app/class/seance';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-program-form',
  templateUrl: './program-form.component.html',
  styleUrls: ['./program-form.component.css']
})
export class ProgramFormComponent implements OnInit {

  seancesList: Seance [] = [];

  programFormGroup: FormGroup = new FormGroup({
    name: new FormControl(''),
    seances: new FormControl([])
  });

  constructor(private musculationService: MusculationService){ }

  ngOnInit(): void {
    this.musculationService.getSeances().subscribe(response => {
      this.seancesList = response;
    });
  }

  onSubmit(){
    console.log(this.programFormGroup.value);
    var program: Program = new Program();
    program.nom = this.programFormGroup.value.name;
    program.dateCreation = new Date().toISOString().substring(0,10);
    console.log(program);

    this.musculationService.setProgram(program).subscribe();
      
    location.reload();
  }
}
