import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-string-list',
  templateUrl: './string-list.component.html',
  styleUrls: ['./string-list.component.css']
})
export class StringListComponent implements OnInit {
  @Input() elements: string[] = [];
  constructor() { }

  ngOnInit(): void {
  }

}
