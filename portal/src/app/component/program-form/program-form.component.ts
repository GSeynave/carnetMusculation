import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Exercice } from 'src/app/class/exerice';
import { Program } from 'src/app/class/program';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-program-form',
  templateUrl: './program-form.component.html',
  styleUrls: ['./program-form.component.css']
})
export class ProgramFormComponent implements OnInit {

  exercicesList: Exercice [] = [];

  programFormGroup: FormGroup = new FormGroup({
    name: new FormControl(''),
    exercices: new FormControl([])
  });

  constructor(
    private musculationService: MusculationService
    ){ }

  ngOnInit(): void {
    this.musculationService.getExercices().subscribe(response => {
      this.exercicesList = response;
    });
  }

  onSubmit(){
    console.log(this.programFormGroup.value);
    var program: Program = new Program();
    program.name = this.programFormGroup.value.name;
    program.creationDate = new Date().toISOString().substring(0,10);
    program.exercices = this.programFormGroup.value.exercices;
    console.log(program.exercices);

    this.musculationService.setProgram(program).subscribe();
      
    location.reload();
  }
}
