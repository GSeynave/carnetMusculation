import { DetailExercice } from "./detail-exercice";
import { EntrainementType } from "./entrainement-type";

export class EntrainementCreer {

  entrainementId: number = -1;
  creationDate: string = "";
  modificationDate: string = "";
  nom: String = "";
  type: EntrainementType = EntrainementType.NON_DEFINI;
  programmeId: number = -1;
  details: DetailExercice[] = [];
}
