import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Demo';
  greeting = {};

  constructor(
    private musculationService: MusculationService,
    private http: HttpClient,
    private router: Router
  ) {
    http.get('http://localhost:8080').subscribe(data => this.greeting = data);
  }

  ngOnInit(): void {
  }

  login() {
    this.router.navigate(['/login']);
  }

  isLogin(): boolean {
    if (sessionStorage.getItem('token') != null && sessionStorage.getItem('token') != '') {
      return true;
    }
    return false;
  }
}
