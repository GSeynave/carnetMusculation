import { Component, OnInit } from '@angular/core';


export interface PeriodicElement {
  date: string;
  serie: number[];
  rep: string[];
  poids: number[];
  recup: string[];
}

const ELEMENT_DATA: PeriodicElement[] = [
  {date: "2021-12-04", 
  serie: [1,2,3,4,5],
  rep: ['10','10','10','10','10'],
  poids: [50, 50, 50, 50, 50],
  recup: ['1"30', '1"30', '1"30', '1"30', '1"30']},
  {date: "2021-12-15", 
  serie: [1,2,3,4,5],
  rep: ['8','8','8','8','8'],
  poids: [55, 55, 55, 55, 55],
  recup: ['1"30', '1"30', '1"30', '1"30', '1"30']}
];

@Component({
  selector: 'app-exercice-panel',
  templateUrl: './exercice-panel.component.html',
  styleUrls: ['./exercice-panel.component.css']
})

export class ExercicePanelComponent implements OnInit {
  
  panelOpenState = false;
  
  
  displayedColumns: string[] = ['date', 'serie', 'rep', 'poids', 'recup'];
  dataSource = ELEMENT_DATA;
   constructor() { }

  ngOnInit(): void {

  }

}
