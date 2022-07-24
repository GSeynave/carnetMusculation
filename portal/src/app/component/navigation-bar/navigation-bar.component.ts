import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  username: string = '';
  constructor() { }

  ngOnInit(): void {
  }

  logout(){
    sessionStorage.setItem('token', '');
    sessionStorage.removeItem('username');
  }

  isLogin(): boolean {
    this.username = sessionStorage.getItem('username') as string;
    if (sessionStorage.getItem('token') != null && sessionStorage.getItem('token') != '') {
      return true;
    }
    return false;
  }
}
