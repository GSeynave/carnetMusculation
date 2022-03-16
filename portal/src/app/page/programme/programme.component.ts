import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Pagination } from 'src/app/class/pagination';
import { Programme } from 'src/app/class/programme';
import { ProgrammeFormComponent } from 'src/app/component/programme-form/programme-form.component';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-programme',
  templateUrl: './programme.component.html',
  styleUrls: ['./programme.component.css'],
})
export class ProgrammeComponent implements OnInit {
  programmes: Programme[] = [];
  pagination: Pagination = new Pagination(0, 10, [5, 10, 20], 0, 'nom');
  createHidden: boolean = true;
  panelOpenState: boolean = true;

  @Output('onProgrammeSelect') programmeSelect: EventEmitter<number> =
    new EventEmitter();
  @ViewChild(ProgrammeFormComponent) programmeForm: ProgrammeFormComponent =
    new ProgrammeFormComponent();

  constructor(private musculationService: MusculationService) {
    this.pagination.sort = 'nom';
    this.musculationService.getProgrammeCount().subscribe((response) => {
      this.pagination.itemCount = response;
    });

    this.musculationService
      .getProgrammes(0, 10, this.pagination.sort)
      .subscribe((response) => {
        this.programmes = response;
        //to detect change of immutable list
        this.programmes = Object.assign([], this.programmes);
      });
  }

  ngOnInit(): void { }


  hideCreateForm() {
    console.log(this.createHidden);
    if (this.createHidden) {
      this.createHidden = false;
    }
    else {
      this.createHidden = true;
    }
  }

  onProgrammeSelect(programmeId: number): void {
    this.programmeSelect.emit(programmeId);
    this.panelOpenState = false;
  }

  onSubmit(programme: Programme) {
    console.log('on submit', programme);
    this.musculationService.setProgramme(programme).subscribe(() => {
      this.musculationService
        .getProgrammes(0, 10, this.pagination.sort)
        .subscribe((response) => {
          this.programmes = response;
          this.programmes = Object.assign([], this.programmes);
        });
    });
  }

  onUpdate(programme: Programme) {
    console.log('parent update', programme);
    this.programmeForm.onUpdate(programme);
  }

  getPage(pagination: Pagination) {
    this.musculationService
      .getProgrammes(
        pagination.currentPage,
        pagination.pageSize,
        pagination.sort
      )
      .subscribe((response) => {
        this.programmes = response;
        this.programmes = Object.assign([], this.programmes);
      });
  }
}
