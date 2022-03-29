import { EntrainementType } from "./entrainement-type";

export class Entrainement {
  id: number = -1;
  creationDate: string = "";
  type: EntrainementType = EntrainementType.NON_DEFINI;
  nom: String = "";
  seanceIds: number = 0;
  exerciceIds: number[] = [];
}
