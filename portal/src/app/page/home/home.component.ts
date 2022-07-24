import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title ='Demo';
  greeting = {};

  constructor(private musculationService: MusculationService, private http: HttpClient) {
    http.get('http://localhost:8080').subscribe(data => this.greeting = data);
   }

  ngOnInit(): void {
  }

}
