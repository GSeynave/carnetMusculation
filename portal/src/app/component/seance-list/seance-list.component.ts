import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Seance } from 'src/app/class/seance';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-seance-list',
  templateUrl: './seance-list.component.html',
  styleUrls: ['./seance-list.component.css']
})
export class SeanceListComponent implements OnInit {
  displayedColumns: string[] = ['dateCreation', 'nom', 'muscleCible', 'deletion'];
  seances: Seance[] = [];
  dataSource: MatTableDataSource<Seance> = new MatTableDataSource();
  panelOpenState: boolean = false;

  constructor(
    private musculationService: MusculationService
    ){ }

  ngOnInit(): void {
    this.musculationService.getSeances()
      .subscribe(response => {
        this.seances = response
        this.dataSource = new MatTableDataSource(this.seances);
        console.log(this.seances)
      });
  }

  onDelete(idSeance: number) {
    this.musculationService.deleteSeance(idSeance).subscribe(() =>  location.reload());
  }
}
