import {Component, Input, OnInit} from "@angular/core";
import {HomeService} from "../../services/home.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  books: Array<any>;
  constructor(private homeService: HomeService){}

  ngOnInit() {
    this.homeService.showBooks().subscribe(response => {this.books=response.body["_embedded"].bookDtoes});
  }
}
