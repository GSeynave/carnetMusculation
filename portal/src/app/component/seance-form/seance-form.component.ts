import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Exercice } from 'src/app/class/exercice';
import { Program } from 'src/app/class/program';
import { Seance } from 'src/app/class/seance';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-seance-form',
  templateUrl: './seance-form.component.html',
  styleUrls: ['./seance-form.component.css']
})
export class SeanceFormComponent implements OnInit {

  programsList: Program [] = [];
  exercicesList: Exercice [] = [];
  bodyPartList: String [] = ["Full body", "Upper body", "Lower body"];
  seanceFormGroup: FormGroup = new FormGroup({
    name: new FormControl(''),
    bodyPart: new FormControl(''),
    program: new FormControl(),
    exercices: new FormControl([])
  });
  displayedColumns: string[] = ['nom', 'muscle', 'check'];
  dataSource: Exercice[] = [];
  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    this.musculationService.getPrograms().subscribe(response => {
      this.programsList = response;
    });
    this.musculationService.getExercices().subscribe(response => {
      this.exercicesList = response;
    });
  }

  onSubmit(){
    console.log("form value", this.seanceFormGroup.value);
    var seance: Seance = new Seance();
    seance.nom = this.seanceFormGroup.value.name;
    seance.muscleCible = this.seanceFormGroup.value.bodyPart;
    seance.dateCreation = new Date().toISOString().substring(0,10);
    let programForm: Program = this.seanceFormGroup.value.program;
    seance.programId = programForm.id;
    seance.exerciceIds = this.seanceFormGroup.value.exercices;
    console.log("seance send to back", seance);

    this.musculationService.setSeance(seance).subscribe(() => location.reload());
      
    
  }

}
