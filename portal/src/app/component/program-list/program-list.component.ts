import { Component, OnInit } from '@angular/core';
import { MusculationService } from 'src/app/service/musculation.service';
import { MatTableDataSource } from '@angular/material/table';
import { Program } from 'src/app/class/program';

@Component({
  selector: 'app-program-list',
  templateUrl: './program-list.component.html',
  styleUrls: ['./program-list.component.css']
})
export class ProgramListComponent implements OnInit {
  displayedColumns: string[] = ['dateCreation', 'nom', 'deletion'];
  programs: Program[] = [];
  dataSource: MatTableDataSource<Program> = new MatTableDataSource();

  constructor(
    private musculationService: MusculationService
    ){ }

  ngOnInit(): void {
    this.musculationService.getPrograms()
      .subscribe(response => {
        this.programs = response
        this.dataSource = new MatTableDataSource(this.programs);
        console.log(this.dataSource)
      });
      console.log("programs", this.programs);
  }

  onDelete(idProgram: number) {
    console.log("delete: ", idProgram)
    this.musculationService.deleteProgram(idProgram).subscribe(() =>  location.reload());
  }
}
