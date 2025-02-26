import { City } from "./city.model";

export class Hotel{
    constructor(
        public id:number,
        public name: string,
        public phone: string,
        public address: string,
        public imageUrl: string,
        public numberOfStar: number,
        public totalRoomOfAvailable: number,
        public price: number,
        public city: City
    ){}

}