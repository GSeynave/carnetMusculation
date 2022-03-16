import { Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';


@Component({
  selector: 'app-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent {
  @Output() programmeId: number = -1;
  constructor() { }

  ngOnInit(): void {
  }

  onProgrammeSelect(programmeId: number) {
    this.programmeId = programmeId;
  }
}
