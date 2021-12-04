import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Session } from 'src/app/class/session';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-session-list',
  templateUrl: './session-list.component.html',
  styleUrls: ['./session-list.component.css']
})
export class SessionListComponent implements OnInit {
  displayedColumns: string[] = ['creationDate', 'name', 'deletion'];
  sessions: Session[] = [];
  dataSource: MatTableDataSource<Session> = new MatTableDataSource();

  constructor(
    private musculationService: MusculationService
    ){ }

  ngOnInit(): void {
    this.musculationService.getSessions()
      .subscribe(response => {
        this.sessions = response
        this.dataSource = new MatTableDataSource(this.sessions);
        console.log(this.sessions)
      });
  }

  onDelete(idSession: number) {
    this.musculationService.deleteSession(idSession).subscribe(() =>  location.reload());
  }

}
