import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Entrainement } from 'src/app/class/entrainement';
import { Programme } from 'src/app/class/programme';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-entrainement-liste',
  templateUrl: './entrainement-liste.component.html',
  styleUrls: ['./entrainement-liste.component.css']
})
export class EntrainementListeComponent implements OnInit {

  displayedColumns: string[] = ['nom', "type", "update", "deletion", "start"];
  dataSource: MatTableDataSource<Entrainement> = new MatTableDataSource();
  indexToUpdate: number = 0;
  entrainementToUpdate: Entrainement = new Entrainement();
  nomUpdated: string = '';

  @Output() selectedProgrammeIdEvent = new EventEmitter<number>();
  @Input() entrainementListe: Entrainement[] = [];
  @Input() programmeSelected: Programme = new Programme();

  @Output('onEntrainementSelect') entrainementSelected: EventEmitter<number> =
    new EventEmitter();

  @Output('entrainementOnUpdate') entrainementOnUpdate: EventEmitter<Entrainement> =
    new EventEmitter();

  @Output('onDeleteEntrainementIdEvent') deleteEntrainementIdEvent: EventEmitter<number> =
    new EventEmitter();

  @Output('onModificationEntrainement') entrainementIdToUpdateEvent: EventEmitter<number> = new EventEmitter<number>();

  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    this.musculationService.getEntrainementsByProgrammeId(this.programmeSelected.id).subscribe( data => {
      this.dataSource = new MatTableDataSource(data);
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['entrainementListe']) {
      this.dataSource = new MatTableDataSource(changes['entrainementListe'].currentValue);
    }
    if (changes['programmeSelected']) {
      this.programmeSelected = changes['programmeSelected'].currentValue;
    }
  }

  onEntrainementSelect(entrainementId: number): void {
    this.entrainementSelected.emit(entrainementId);
  }

  onProgrammeSelect(programmeId: number): void {
    this.selectedProgrammeIdEvent.emit(programmeId);
  }


  setToUpdate(entrainement: Entrainement) {
    this.entrainementToUpdate = entrainement;
    this.nomUpdated = entrainement.nom;
  }

  onUpdate() {
    this.entrainementToUpdate.nom = this.nomUpdated;
    this.entrainementOnUpdate.emit(this.entrainementToUpdate);
    this.entrainementToUpdate = new Entrainement();
    this.nomUpdated = '';
  }

  cancelUpdate() {
    this.entrainementToUpdate = new Entrainement();
    this.nomUpdated = '';
  }

  onDelete(idEntrainement: number) {
    this.deleteEntrainementIdEvent.emit(idEntrainement);
  }

  onModificationEntrainement(entrainementId: number) {
    this.entrainementIdToUpdateEvent.emit(entrainementId);
  }

}
