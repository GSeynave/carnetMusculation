import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Entrainement } from 'src/app/class/entrainement';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-entrainement',
  templateUrl: './entrainement.component.html',
  styleUrls: ['./entrainement.component.css']
})
export class EntrainementComponent implements OnInit, OnChanges {

  entrainementListe: Entrainement[] = [];
  @Input() programmeId: number = -1;

  constructor(private musculationService: MusculationService) { }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['programmeId']) {
      this.entrainementListe = this.getEntrainements(changes['programmeId'].currentValue);
    }
  }

  ngOnInit(): void {
  }

  getEntrainements(programmeId: number): Entrainement[] {
    let entrainements: Entrainement[] = [];
    this.musculationService.getEntrainements(programmeId).subscribe((data) => {
      entrainements = data;
    });
    return entrainements;
  }
}
