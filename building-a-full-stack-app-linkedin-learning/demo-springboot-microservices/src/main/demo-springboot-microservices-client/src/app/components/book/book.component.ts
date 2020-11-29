import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit{
  @Input() book;
  path:string;
  showTools:boolean;

  constructor() {
    this.path="";
    this.showTools=false;
  }

  ngOnInit(): void {
    if(this.book.isbn==9780140120905){
      this.path='/assets/the-predators-ball.jpg';
    }else if (this.book.isbn==35618906){
      this.path='/assets/annapurna.jpg';
    }else if (this.book.isbn==9781324002642){
      this.path='/assets/the-fifth-risk.jpg';
    }else if (this.book.isbn==9780393351590){
      this.path='/assets/flash-boys.jpg';
    }else if (this.book.isbn==9780140446036){
      this.path='/assets/arthashastra.jpg';
    }
  }

}
