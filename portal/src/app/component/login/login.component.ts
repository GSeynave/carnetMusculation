import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/class/user';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  loginFormGroup: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(
    private router: Router,
    private musclationService: MusculationService
  ) { }

  ngOnInit(): void { }

  onSubmit() {
    var  user: User = new User;
    user.username = this.loginFormGroup.value.username;
    user.password = this.loginFormGroup.value.password;

    this.musclationService.login(user).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(user.username + ':' + user.password)
        );
        this.router.navigate(['/programmes']);
      } else {
        alert("Authentication failed.")
      }
    });
  }

  isLogin(): boolean {
    if (sessionStorage.getItem('token') != null && sessionStorage.getItem('token') != '') {
      console.log(sessionStorage.getItem('token'));
      return true;
    }
    console.log(sessionStorage.getItem('token'));
    return false;
  }
}
