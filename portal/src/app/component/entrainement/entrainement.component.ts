import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
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

  displayedColumns: string[] = ['dateCreation', 'nom', "type", "start"];
  dataSource: MatTableDataSource<Entrainement> = new MatTableDataSource();

  constructor(private musculationService: MusculationService) { }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['programmeId']) {
      this.getEntrainements(changes['programmeId'].currentValue);
    }
  }

  ngOnInit(): void {
  }

  getEntrainements(programmeId: number) {
    this.musculationService.getEntrainements(programmeId).subscribe((data) => {
      this.entrainementListe = data;
      this.dataSource = new MatTableDataSource(this.entrainementListe);
    });
  }
}
