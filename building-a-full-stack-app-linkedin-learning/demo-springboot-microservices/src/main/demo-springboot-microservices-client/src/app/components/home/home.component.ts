import {AfterViewInit, Component, Input, OnInit, ViewChild} from "@angular/core";
import {HomeService} from "../../services/home.service";
import {BookComponent} from "../book/book.component";
import {Book} from "../../model/book";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  books: Book[]=[];
  /*booksC:BookComponent=new BookComponent();*/

  constructor(private homeService:HomeService){}

  ngOnInit() {
    this.reset();
  }

  reset(){
    this.loadBooks();
  }

/*  ngAfterViewInit(): void {
    /!*console.log(this.booksC.books);*!/
    this.booksC.reset();
    //this.products=this.booksC.books;
  }*/

  loadBooks() {
    this.homeService.showBooks()
      .subscribe(
        response => {
          this.books=response.body["_embedded"].bookDtoes;
          /*this.books.forEach(book=>this.loadImage());*/
        });
  }
}
