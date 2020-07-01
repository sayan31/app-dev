import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private http:Http) {
  }

  private baseUrl:string = 'http://localhost:8080';
  private getUrl:string = this.baseUrl + '/room/reservation/v1/';
  private postUrl:string = this.baseUrl + '/room/reservation/v1';

  public submitted:boolean;
  roomsearch : FormGroup;
  rooms: Room[];
  request:ReserveRoomRequest;
  currentCheckInVal:string;
  currentCheckOutVal:string;

    ngOnInit() {
        this.roomsearch = new FormGroup({
            checkIn: new FormControl(''),
            checkOut: new FormControl('')
        });

		const roomsearchValueChanges$ =  this.roomsearch.valueChanges;
		
		roomsearchValueChanges$.subscribe(
			valChange =>{
				this.currentCheckInVal=valChange.checkIn;
				this.currentCheckOutVal=valChange.checkOut;
			}
		)

    }

  onSubmit({value, valid}: { value:Roomsearch, valid:boolean }) {

    this.getAll()
      .subscribe(
        rooms => {
			this.rooms = rooms;
			console.log(rooms);
		},
        err => {
          // Log errors if any
          console.log(err);
        });
  }

  reserveRoom(value:string) {
     //console.log("Room id for reservation:" + value);
	this.request = new ReserveRoomRequest(value,this.currentCheckInVal,this.currentCheckOutVal);
	this.createReservation(this.request);
  }

  getAll():Observable<Room[]> {

    //noinspection TypeScriptValidateTypes
    return this.http
      .get(this.getUrl+'?checkIn='+this.currentCheckInVal+'&&checkOut='+this.currentCheckOutVal)
      .map(this.mapRoom);
  }

  createReservation(body: ReserveRoomRequest){
	let bodyString = JSON.stringify(body);
	let headers = new Headers({'Content-Type': 'application/json'});
	let options = new RequestOptions({headers: headers});
	
	this.http.post(this.postUrl, body, options)
		.subscribe(res=>console.log(res));
  }

  mapRoom(response:Response):Room[] {
    return response.json().content;
  }

}

export interface Roomsearch {
  checkIn:string;
  checkOut:string;
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
}

export class ReserveRoomRequest{
	roomId:string;
	checkIn:string;
    checkOut:string;

	constructor (roomId:string,checkIn:string,checkOut:string){
		this.roomId = roomId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
}

