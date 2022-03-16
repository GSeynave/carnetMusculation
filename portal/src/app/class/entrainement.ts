import { EntrainementType } from "./entrainement-type";

export class Entrainement {
  id: number = 0;
  creationDate: string = "";
  type: EntrainementType = 1;
  nom: String = "";
  seanceIds: number = 0;
  exerciceIds: number[] = [];
}
