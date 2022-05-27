import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
} from '@angular/core';
import { MusculationService } from 'src/app/service/musculation.service';
import { MatTableDataSource } from '@angular/material/table';
import { Programme } from 'src/app/class/programme';
import { PageEvent } from '@angular/material/paginator';
import { Pagination } from 'src/app/class/pagination';

@Component({
  selector: 'app-programme-list',
  templateUrl: './programme-list.component.html',
  styleUrls: ['./programme-list.component.css'],
})
export class ProgrammeListComponent implements OnChanges {
  displayedColumns: string[] = ['dateCreation', 'nom', 'update', 'deletion'];
  dataSource: MatTableDataSource<Programme> = new MatTableDataSource();
  indexToUpdate: number = 0;
  programmeToUpdate: Programme = new Programme();
  nomUpdated: string = '';

  // MatPaginator Inputs
  @Input() pagination: Pagination = new Pagination(0, 0, [], 0, '');
  @Input() programmes: Programme[] = [];

  @Output() paginationChange = new EventEmitter<Pagination>();

  @Output('programmeGetPage') programmeGetPage: EventEmitter<Pagination> =
    new EventEmitter();
  @Output('programmeOnUpdate') programmeOnUpdate: EventEmitter<Programme> =
    new EventEmitter();
  @Output('onProgrammeSelect') programmeSelect: EventEmitter<number> =
    new EventEmitter();

  // MatPaginator Output
  pageEvent: PageEvent = new PageEvent();

  constructor(private musculationService: MusculationService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['programmeCount']) {
      this.pagination.itemCount = changes['programmeCount'].currentValue;
    }
    if (changes['programmes']) {
      this.programmes = changes['programmes'].currentValue;
      this.dataSource = new MatTableDataSource(this.programmes);
    }
    if (changes['pageSize']) {
      this.pagination.pageSize = changes['pageSize'].currentValue;
    }
    if (changes['pageSizeOptions']) {
      this.pagination.pageSizeOptions = changes['pageSizeOptions'].currentValue;
    }
    if (changes['currentPage']) {
      this.pagination.currentPage = changes['currentPage'].currentValue;
    }
    if (changes['programmeSort']) {
      this.pagination.sort = changes['programmeSort'].currentValue;
    }
  }

  ngOnInit() {
    this.dataSource = new MatTableDataSource(this.programmes);
    this.pageEvent.pageIndex = this.pagination.currentPage;
    this.pageEvent.pageSize = this.pagination.pageSize;
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pagination.pageSizeOptions = setPageSizeOptionsInput
        .split(',')
        .map((str) => +str);
      this.paginationChange.emit(this.pagination);
    }
  }

  setToUpdate(programme: Programme) {
    this.programmeToUpdate = programme;
    this.nomUpdated = programme.nom;
  }

  onUpdate() {
    this.programmeToUpdate.nom = this.nomUpdated;
    this.programmeToUpdate.dateModification = new Date().toISOString().substring(0, 10);
    this.programmeOnUpdate.emit(this.programmeToUpdate);
    console.log('child update event', this.programmeToUpdate);
    this.programmeToUpdate = new Programme();
    this.nomUpdated = '';
  }

  cancelUpdate() {
    this.programmeToUpdate = new Programme();
    this.nomUpdated = '';
  }

  onDelete(programmeId: number) {
    this.musculationService.deleteProgramme(programmeId).subscribe(() => {
      this.musculationService.getProgrammesPage(
        this.pageEvent.pageIndex,
        this.pageEvent.pageSize,
        this.pagination.sort
      )
      .subscribe((response) => {
        this.dataSource = new MatTableDataSource(response);
        this.pagination.itemCount = response.length;
      });
    });
  }

  getPage(event: PageEvent) {
    // Creer une class page pour emit les infos plus
    let pagination: Pagination = new Pagination(
      event.length,
      event.pageSize,
      this.pagination.pageSizeOptions,
      event.pageIndex,
      this.pagination.sort
    );
    this.pagination = pagination;
    this.programmeGetPage.emit(pagination);
  }

  onProgrammeSelect(programmeId: number): void {
    this.programmeSelect.emit(programmeId);
  }
}
