import { State } from "./state";

export class Seance {

    id: number = 0;
    dateEntrainement: string = "";
    state!: State;
    entrainement: number = 0;
    series: number[] = [];
}
