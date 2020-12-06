import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {HomeService} from "../../services/home.service";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit{
 /* @Input() book;*/

  books: Book[]=[];
  showTools:boolean;

  constructor(private homeService: HomeService) {
    this.showTools=false;
  }

  ngOnInit(): void {
    /*if(this.book.isbn==9780140120905){
      this.path='/assets/the-predators-ball.jpg';
    }else if (this.book.isbn==35618906){
      this.path='/assets/annapurna.jpg';
    }else if (this.book.isbn==9781324002642){
      this.path='/assets/the-fifth-risk.jpg';
    }else if (this.book.isbn==9780393351590){
      this.path='/assets/flash-boys.jpg';
    }else if (this.book.isbn==9780140446036){
      this.path='/assets/arthashastra.jpg';
    }*/
  }

  reset(){
    this.loadBooks();
  }

  loadBooks() {
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
  }
}
