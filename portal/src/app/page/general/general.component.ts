import { Component, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Programme } from 'src/app/class/programme';
import { Seance } from 'src/app/class/seance';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent implements OnInit, OnChanges {
  programs: Programme[] = [];
  seances: Seance[] = [];
  programSelected: Programme = new Programme();
  selectedSeanceId: number = 0;
  @Output() seanceId: number = 0;
  constructor(private musculationService: MusculationService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['programSelected']){
      console.log("change on programselecte")
      this.getSeanceByProgrammeId(this.programSelected.id);
    }
  }

  ngOnInit(): void {
    // this.musculationService.getProgrammes()
    //   .subscribe(response => {
    //     this.programs = response
    //     console.log("programs", this.programs);
    //   });
  }

  onSeanceSelect(seanceId: number) {
    this.selectedSeanceId = seanceId;
  }

  getSeanceByProgrammeId(programmeId: number) : Seance[]{
    this.musculationService.getSeancesByProgramme(programmeId)
    .subscribe(response => {
      this.seances = response;
    });
    return this.seances;
  }

}
