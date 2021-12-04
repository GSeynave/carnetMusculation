import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Exercice } from 'src/app/class/exerice';
import { Program } from 'src/app/class/program';
import { Session } from 'src/app/class/session';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-program-form',
  templateUrl: './program-form.component.html',
  styleUrls: ['./program-form.component.css']
})
export class ProgramFormComponent implements OnInit {

  sessionsList: Session [] = [];

  programFormGroup: FormGroup = new FormGroup({
    name: new FormControl(''),
    sessions: new FormControl([])
  });

  constructor(private musculationService: MusculationService){ }

  ngOnInit(): void {
    this.musculationService.getSessions().subscribe(response => {
      this.sessionsList = response;
    });
  }

  onSubmit(){
    console.log(this.programFormGroup.value);
    var program: Program = new Program();
    program.name = this.programFormGroup.value.name;
    program.creationDate = new Date().toISOString().substring(0,10);
    console.log(program);

    this.musculationService.setProgram(program).subscribe();
      
    location.reload();
  }
}
