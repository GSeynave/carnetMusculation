import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  ngOnInit(): void {
    if(!sessionStorage.getItem('token')){
      sessionStorage.setItem('token', '');
    }
  }

  isLogin(): boolean {
    if (sessionStorage.getItem('token') != null && sessionStorage.getItem('token') != '') {
      return true;
    }
    return false;
  }
}
