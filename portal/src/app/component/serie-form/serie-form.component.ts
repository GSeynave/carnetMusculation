import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-serie-form',
  templateUrl: './serie-form.component.html',
  styleUrls: ['./serie-form.component.css']
})
export class SerieFormComponent implements OnInit {

  nbRep: number = -1;
  poids: number = -1;
  recup: string = '';
  constructor() { }

  ngOnInit(): void {
  }

}
