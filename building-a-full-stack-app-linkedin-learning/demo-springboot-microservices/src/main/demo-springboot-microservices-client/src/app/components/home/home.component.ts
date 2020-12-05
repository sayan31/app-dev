import {Component, Input, OnInit, ViewChild} from "@angular/core";
import {HomeService} from "../../services/home.service";
import {BookComponent} from "../book/book.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  @ViewChild('booksC')
  booksC: BookComponent;

  books: Array<any>;
  constructor(private homeService: HomeService){}

  ngOnInit() {
  }

  reset(){
    this.loadBooks();
  }

  loadBooks() {
    this.homeService.showBooks().subscribe(response => {this.books=response.body["_embedded"].bookDtoes});
  }
}
