import { EntrainementType } from "./entrainement-type";
import { Exercice } from "./exercice";

export class Details{
  exercice: Exercice = new Exercice();
  nbrep: number = -1;
  nbSerie: number = -1;
  recup: string = "";
}

export class EntrainementCreer {

  programmeId: number = -1;
  nom: String = "";
  type: EntrainementType = EntrainementType.NON_DEFINI;
  creationDate: string = "";
  exercicesDetails: Details[] = [];
}
