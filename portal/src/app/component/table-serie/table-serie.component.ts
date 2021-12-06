import { Component, Input, OnInit } from '@angular/core';
import { Exercice } from 'src/app/class/exercice';
import { Serie } from 'src/app/class/serie';
import { MusculationService } from 'src/app/service/musculation.service';

export interface SerieLine {
  date: string;
  rep: string[];
  poids: string[];
  recup: string[];
}

const ELEMENT_DATA: SerieLine[] = [
  {date: "2021-12-04", 
  rep: ['10','10','10','10','10'],
  poids: ["50", "50", "50", "50", "50"],
  recup: ['1"30', '1"30', '1"30', '1"30', '1"30']},
  {date: "2021-12-15", 
  rep: ['8','8','8','8','8'],
  poids: ["55", "55", "55", "55", "55"],
  recup: ['1"30', '1"30', '1"30', '1"30', '1"30']}
];

@Component({
  selector: 'app-table-serie',
  templateUrl: './table-serie.component.html',
  styleUrls: ['./table-serie.component.css']
})
export class TableSerieComponent implements OnInit {
  dataSource!: SerieLine[];
  series: Serie[] = [];
  displayedColumns: string[] = ['date', 'rep', 'poids', 'recup'];

  @Input() exercice: Exercice = new Exercice();
  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    this.musculationService.getSeriesByExerciceId(this.exercice.id)
    .subscribe(response => {
      this.dataSource = response
      // this.convertSeriesToPeriodicElementTable(this.series);
      console.log("series !! : ", this.dataSource);
    });
    
  }

  convertSeriesToPeriodicElementTable(series: Serie[]){
    
    
    this.dataSource = ELEMENT_DATA;
  }
}
