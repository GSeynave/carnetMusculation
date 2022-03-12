import { Component, OnInit } from '@angular/core';
import { MusculationService } from 'src/app/service/musculation.service';
import { MatTableDataSource } from '@angular/material/table';
import { Programme } from 'src/app/class/programme';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-program-list',
  templateUrl: './program-list.component.html',
  styleUrls: ['./program-list.component.css']
})
export class ProgramListComponent {
  displayedColumns: string[] = ['dateCreation', 'nom', 'deletion'];
  dataSource: MatTableDataSource<Programme> = new MatTableDataSource();

  // MatPaginator Inputs
  programmeCount = 0;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  // MatPaginator Output
  pageEvent: PageEvent = new PageEvent();

  programmeSort: string = "nom";


  constructor(
    private musculationService: MusculationService
  ) {
    this.musculationService.getProgrammeCount()
      .subscribe(response => {
        this.programmeCount = response;
      });

    this.musculationService.getProgrammes(0, 10, this.programmeSort)
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
      });
      this.pageEvent.pageIndex=0;
      this.pageEvent.pageSize=10;
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

  onDelete(idProgramme: number) {
    this.musculationService.deleteProgramme(idProgramme).subscribe(() => {
      this.musculationService.getProgrammes(this.pageEvent.pageIndex, this.pageEvent.pageSize, this.programmeSort)
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this.programmeCount = response.length;
      });
    });
  }

  getPage(event: PageEvent) {
    this.pageEvent = event;
    this.musculationService.getProgrammes(this.pageEvent.pageIndex, this.pageEvent.pageSize, this.programmeSort)
      .subscribe(response => {
        this.dataSource.data = response
      });
  }
}
