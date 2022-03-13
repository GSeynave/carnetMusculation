import { Component, OnInit, ViewChild } from '@angular/core';
import { Pagination } from 'src/app/class/pagination';
import { Programme } from 'src/app/class/programme';
import { ProgramFormComponent } from 'src/app/component/program-form/program-form.component';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrls: ['./program.component.css']
})
export class ProgramComponent implements OnInit {

  programmes: Programme [] = [];
  pagination: Pagination = new Pagination(0, 10, [5, 10, 20], 0, "nom");

  @ViewChild(ProgramFormComponent) programmeForm: ProgramFormComponent = new ProgramFormComponent();
 
  constructor(private musculationService: MusculationService) {
    this.pagination.sort = "nom";
    this.musculationService.getProgrammeCount()
      .subscribe(response => {
        this.pagination.itemCount = response;
      });

    this.musculationService.getProgrammes(0, 10, this.pagination.sort)
      .subscribe(response => {
        this.programmes = response;
        //to detect change of immutable list
        this.programmes =  Object.assign([],this.programmes);
      });
   }
   

  ngOnInit(): void {  }

  onSubmit(programme: Programme) {
    // this.musculationService.setProgramme(programme).subscribe();
    this.musculationService.getProgrammes(0, 10, this.pagination.sort)
      .subscribe(response => {
        this.programmes = response;
      });
  }

  onUpdate(programme: Programme) {
    this.programmeForm.onUpdate(programme);
  }

  getPage() {
    this.musculationService.getProgrammes(this.pagination.currentPage, this.pagination.pageSize, this.pagination.sort)
      .subscribe(response => {
        this.programmes = response
      });
  }

}
