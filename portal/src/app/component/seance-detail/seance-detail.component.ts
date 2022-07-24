import { Component, Input, OnInit } from '@angular/core';
import { ExercicePerformance } from 'src/app/class/exercice-performance';
import { Performance } from 'src/app/class/performance';
import { Seance } from 'src/app/class/seance';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-seance-detail',
  templateUrl: './seance-detail.component.html',
  styleUrls: ['./seance-detail.component.css']
})
export class SeanceDetailComponent implements OnInit {

  exercicePerformances: ExercicePerformance[] = [];
  @Input() seance: Seance = new Seance();

  constructor(private musculationService: MusculationService) { }

  ngOnInit(): void {
    this.musculationService.getDetailExercicesBySeanceId(this.seance.id).subscribe( data => {
      this.exercicePerformances = data;
    })
  }

}
