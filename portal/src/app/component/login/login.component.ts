import { HttpClient } from '@angular/common/http';
import { asLiteral } from '@angular/compiler/src/render3/view/util';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from 'src/app/class/user';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private musclationService: MusculationService
  ) { }

  ngOnInit(): void {
    sessionStorage.setItem('token', '');
  }

  login() {
    console.log('loggin in...');
    this.user.username = "user";
    this.user.password = "password";

    this.musclationService.login(this.user).subscribe(isValid => {
        if (isValid) {
            sessionStorage.setItem(
              'token',
              btoa(this.user.username + ':' + this.user.password)
            );
      this.router.navigate(['']);
        } else {
            alert("Authentication failed.")
        }
    });
  }

}
