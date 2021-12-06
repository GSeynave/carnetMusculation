import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Exercice } from 'src/app/class/exercice';
import { MusculationService } from 'src/app/service/musculation.service';



@Component({
  selector: 'app-exercice-panel',
  templateUrl: './exercice-panel.component.html',
  styleUrls: ['./exercice-panel.component.css']
})

export class ExercicePanelComponent implements OnInit, OnChanges {
  
  panelOpenState = false;
  exercices: Exercice[] = [];
  @Input() seanceId: number = 0;
  

   constructor(private musculationService: MusculationService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["seanceId"]){
      console.log("seanceId", this.seanceId)
      if(this.seanceId != 0){
        console.log("init exercice", this.seanceId)
        this.updateExercicesValues(this.seanceId);
      }
    }
  }
  
  ngOnInit(): void {
  }

  updateExercicesValues(seanceId: number){
    this.musculationService.getExercicesBySeanceId(seanceId)
    .subscribe(response => {
      this.exercices = response
      console.log("exercices !! : ", this.exercices);
    });

  }

  resetSeanceId(){
    this.seanceId = 0;
  }
}
