import { Session } from "./session";

export class Exercice {

    id: number = 0;
    name: string = "";
    insertDate: Date = new Date;
    sessions: Session[] = [];
}
