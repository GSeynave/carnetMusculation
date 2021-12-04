import { Component, OnInit } from '@angular/core';
import { Program } from 'src/app/class/program';
import { Session } from 'src/app/class/session';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent implements OnInit {
  programs: Program[] = [];
  sessions: Session[] = [];
  programSelected: Program = new Program();
  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    this.musculationService.getPrograms()
      .subscribe(response => {
        this.programs = response
      });
    this.musculationService.getSessions()
      .subscribe(response => {
        this.sessions = response
      });
  }

}
