import { Component, OnInit, ViewChild } from '@angular/core';
import { Pagination } from 'src/app/class/pagination';
import { Programme } from 'src/app/class/programme';
import { ProgrammeFormComponent } from 'src/app/component/programme-form/programme-form.component';
import { MusculationService } from 'src/app/service/musculation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-programme',
  templateUrl: './programme.component.html',
  styleUrls: ['./programme.component.css'],
})
export class ProgrammeComponent implements OnInit {
  public programmes: Programme[] = [];
  public pagination: Pagination = new Pagination(0, 10, [5, 10, 20], 0, 'nom');
  public isEntrainementDisplay: boolean = false;
  public programmeSelected: Programme = new Programme();

  @ViewChild(ProgrammeFormComponent) programmeForm: ProgrammeFormComponent =
    new ProgrammeFormComponent();

  constructor(private musculationService: MusculationService,
    private router: Router) {
    this.pagination.sort = 'nom';
    this.musculationService.getProgrammeCount().subscribe((response) => {
      this.pagination.itemCount = response;
    });

    this.musculationService
      .getProgrammesPage(0, 10, this.pagination.sort)
      .subscribe((response) => {
        this.programmes = response;
        //to detect change of immutable list
        this.programmes = Object.assign([], this.programmes);
      });
  }

  ngOnInit(): void { }


  onProgrammeSelect(programmeId: number): void {
    this.musculationService.getProgrammeById(programmeId).subscribe( data => {
      this.programmeSelected = data;
      this.isEntrainementDisplay = true;
    })
    // this.router.navigate(['/entrainements/programme/', programmeId ]);
  }

  onSubmit(programme: Programme) {
    this.musculationService.setProgramme(programme).subscribe(() => {
      this.musculationService
        .getProgrammesPage(0, 10, this.pagination.sort)
        .subscribe((response) => {
          this.programmes = response;
          this.programmes = Object.assign([], this.programmes);
        });
    });
  }

  onUpdate(programme: Programme) {
    this.programmeForm.onUpdate(programme);
  }

  getPage(pagination: Pagination) {
    this.musculationService
      .getProgrammesPage(
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
