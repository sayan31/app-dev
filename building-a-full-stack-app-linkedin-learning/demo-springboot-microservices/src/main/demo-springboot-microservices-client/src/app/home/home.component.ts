import {Component, Input, OnInit} from "@angular/core";
import {BOOKS} from '../model/mock-books';
import {HomeService} from "../services/home.service";
import {Observable} from "rxjs";
import {Book} from "../model/book";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  //books$: Observable<Book[]>;
  books: Book[];
  constructor(private homeService: HomeService){}

  ngOnInit() {
    //console.log(this.homeService.showBooks());
   //this.homeService.showBooks().subscribe(books => console.log(books));
    this.homeService.showBooks().subscribe(_embedded => {console.log(_embedded['bookDtoes']);this.books=_embedded['bookDtoes']; });

  }
}
