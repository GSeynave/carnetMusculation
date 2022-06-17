import { EntrainementType } from "./entrainement-type";

export class Entrainement {
  id: number = -1;
  dateCreation: string = "";
  dateModification: string = "";
  type: EntrainementType = EntrainementType.NON_DEFINI;
  nom: string = "";
  seanceIds: number = 0;
  exerciceIds: number[] = [];
}
