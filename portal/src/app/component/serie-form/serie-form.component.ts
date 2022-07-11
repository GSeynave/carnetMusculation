import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Serie } from 'src/app/class/serie';
import { State } from 'src/app/class/state';
import { MusculationService } from 'src/app/service/musculation.service';

@Component({
  selector: 'app-serie-form',
  templateUrl: './serie-form.component.html',
  styleUrls: ['./serie-form.component.css']
})
export class SerieFormComponent implements OnInit {

  @Input() numeroSerie: number = 0;
  @Input() seanceId: number = 0;
  @Input() exerciceId: number = 0;
  @Input() entrainementId: number = 0;
  isValidate: boolean = false;
  nbRep: number = 0;
  poids: number = 0;
  recup: string = '0.00';

  serieFormGroup: FormGroup = new FormGroup({
    numero: new FormControl(),
    exerciceId: new FormControl(),
    entrainementId: new FormControl(),
    nbRep: new FormControl(this.nbRep),
    poids: new FormControl(this.poids),
    recup: new FormControl(this.recup)
  });
  constructor(private musculationService: MusculationService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log("serieform seanceId:", this.seanceId);
  }

  onSubmit() :void {
    let serie: Serie = new Serie();
    serie.numeroSerie = this.numeroSerie;
    serie.poids = this.serieFormGroup.value.poids;
    serie.rep = this.serieFormGroup.value.nbRep;
    serie.recup = this.serieFormGroup.value.recup;
    serie.exerciceId= this.exerciceId;
    serie.entrainementId = this.entrainementId;
    console.log("serie form serie : ", serie);
    console.log("serie form seance id: ", this.seanceId);

    this.musculationService.setSerie(serie, this.seanceId).subscribe();
    this.isValidate = true;
  }

}
