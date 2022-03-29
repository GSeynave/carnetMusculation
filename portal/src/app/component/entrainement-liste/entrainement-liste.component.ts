import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Entrainement } from 'src/app/class/entrainement';
import { Programme } from 'src/app/class/programme';

@Component({
  selector: 'app-entrainement-liste',
  templateUrl: './entrainement-liste.component.html',
  styleUrls: ['./entrainement-liste.component.css']
})
export class EntrainementListeComponent implements OnInit {

  displayedColumns: string[] = ['dateCreation', 'nom', "type", "start"];
  dataSource: MatTableDataSource<Entrainement> = new MatTableDataSource();

  @Output() selectedProgrammeIdEvent = new EventEmitter<number>();
  @Input() entrainementListe: Entrainement[] = [];
  @Input() programmeListe: Programme[] = [];

  @Output('onEntrainementSelect') entrainementSelected: EventEmitter<number> =
    new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource(this.entrainementListe);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['entrainementListe']) {
      this.dataSource = new MatTableDataSource(changes['entrainementListe'].currentValue);
    }
  }

  onEntrainementSelect(entrainementId: number): void {
    // FIXME: route vers seanceStart.
    this.entrainementSelected.emit(entrainementId);
  }

  onProgrammeSelect(programmeId: number): void{
    console.log("emit : ", programmeId);
    this.selectedProgrammeIdEvent.emit(programmeId);
  }

}
