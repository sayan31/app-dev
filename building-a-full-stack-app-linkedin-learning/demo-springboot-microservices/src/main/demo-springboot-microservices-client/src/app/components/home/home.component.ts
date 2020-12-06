import {AfterViewInit, Component, Input, OnInit, ViewChild} from "@angular/core";
import {HomeService} from "../../services/home.service";
import {BookComponent} from "../book/book.component";
import {Book} from "../../model/book";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit{

  //products: Book[]=[];
  @ViewChild(BookComponent)
  booksC:BookComponent;

  constructor(){}

  /*ngOnInit() {
    this.reset();
  }

  reset(){
    this.booksC.reset();
  }*/

  ngAfterViewInit(): void {
    /*console.log(this.booksC.books);*/
    this.booksC.reset();
    //this.products=this.booksC.books;
  }

  /*loadBooks() {
    this.homeService.showBooks()
      .subscribe(
        response => {
          this.books=response.body["_embedded"].bookDtoes;
          this.books.forEach(book=>this.loadImage(book));
        });
  }

  loadImage(book:Book) {
    if(book.isbn==9780140120905){
      book.imagePath='assets/the-predators-ball.jpg';
    }else if (book.isbn==35618906){
      book.imagePath='/assets/annapurna.jpg';
    }else if (book.isbn==9781324002642){
      book.imagePath='/assets/the-fifth-risk.jpg';
    }else if (book.isbn==9780393351590){
      book.imagePath='/assets/flash-boys.jpg';
    }else if (book.isbn==9780140446036){
      book.imagePath='/assets/arthashastra.jpg';
    }
  }*/
}
