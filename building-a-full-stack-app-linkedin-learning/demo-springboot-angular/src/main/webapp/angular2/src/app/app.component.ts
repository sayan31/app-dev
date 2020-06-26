import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

	public submitted: boolean;
	roomsearch: FormGroup;
	rooms: Room[];

	ngOnInit() {
		this.roomsearch = new FormGroup({
			checkin: new FormControl(''),
			checkout: new FormControl('')
		});
		
		this.rooms=ROOMS;
	}

	onSubmit({ value, valid}: { value: Roomsearch, valid: boolean }) {
		console.log(value);
	}
	
	reserveRoom(value:string){
		console.log("Room id:"+value);
	}
}
export interface Roomsearch {
	checkin: string;
	checkout: string;
}

export interface Room {
	id: string;
	roomNumber: string;
	price: string;
	links:string;
}

/**The array below simulates a response from REST API */
var ROOMS:Room[]=[
	{
		"id": "35446565",
		roomNumber:"101",
		price:"2000",
		links:""		
	},
	{
		"id": "45446565",
		roomNumber:"102",
		price:"2000",
		links:""		
	},
	{
		"id": "55446565",
		roomNumber:"103",
		price:"2100",
		links:""		
	}
]
