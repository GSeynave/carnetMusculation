import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Programme } from 'src/app/class/programme';
import { Seance } from 'src/app/class/seance';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-seances-list-from-programme',
  templateUrl: './seances-list-from-programme.component.html',
  styleUrls: ['./seances-list-from-programme.component.css'],
})
export class SeancesListFromProgrammeComponent implements OnInit, OnChanges {
  previousProgrammeId: number = 0;
  seances: Seance[] = [];
  @Input() programme: Programme = new Programme();
  @Output() selectedSeanceId = new EventEmitter<number>();

  constructor(private musculationService: MusculationService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['programme'] && this.programme.id != this.previousProgrammeId) {
      this.fillSeances(this.programme.id);
    }
  }

  ngOnInit(): void { }
  fillSeances(programmeId: number) {
    this.musculationService
      .getSeancesByProgramme(programmeId)
      .subscribe((response) => {
        this.seances = response;
      });
  }

  onSeanceSelect(seanceId: number) {
    console.log('alimentation id seance');
    this.selectedSeanceId.emit(seanceId);
  }
}
