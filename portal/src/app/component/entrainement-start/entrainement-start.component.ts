import { Component, Input, OnInit, Pipe, PipeTransform } from '@angular/core';
import { Entrainement } from 'src/app/class/entrainement';
import { Exercice } from 'src/app/class/exercice';
import { detailExercice } from 'src/app/class/detail-exercice';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-entrainement-start',
  templateUrl: './entrainement-start.component.html',
  styleUrls: ['./entrainement-start.component.css']
})
export class EntrainementStartComponent implements OnInit {

  public exerciceDetailListe: detailExercice [] = [];
  @Input() entrainementSelected: Entrainement = new Entrainement();

  constructor(private musculationService: MusculationService) {

  }

  ngOnInit(): void {
    //TODO appel service getExercices avec les exercicesIds de this.entrainementSelected
    if(this.entrainementSelected.id != -1){
      this.musculationService.getExercicesByEntrainementId(this.entrainementSelected.id).subscribe( (data) => {
        this.exerciceDetailListe = data;
      })
    }
  }

}
