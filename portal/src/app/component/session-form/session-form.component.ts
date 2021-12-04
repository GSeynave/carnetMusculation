import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Program } from 'src/app/class/program';
import { Session } from 'src/app/class/session';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-session-form',
  templateUrl: './session-form.component.html',
  styleUrls: ['./session-form.component.css']
})
export class SessionFormComponent implements OnInit {

  programsList: Program [] = [];
  bodyPartList: String [] = ["Full body", "Upper body", "Lower body"];
  sessionFormGroup: FormGroup = new FormGroup({
    name: new FormControl(''),
    bodyPart: new FormControl(''),
    program: new FormControl()
  });
  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    this.musculationService.getPrograms().subscribe(response => {
      this.programsList = response;
    });
  }

  onSubmit(){
    console.log(this.sessionFormGroup.value);
    var session: Session = new Session();
    session.name = this.sessionFormGroup.value.name;
    session.bodyPart = this.sessionFormGroup.value.bodyPart;
    session.creationDate = new Date().toISOString().substring(0,10);
    let programForm: Program = this.sessionFormGroup.value.program;
    session.programId = programForm.id;
    console.log(session);

    this.musculationService.setSession(session).subscribe();
      
    // location.reload();
  }

}
