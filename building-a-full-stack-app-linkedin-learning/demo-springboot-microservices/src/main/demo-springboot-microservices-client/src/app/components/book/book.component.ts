import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit{
  @Input() book;
  imagePath:string="";
  showTools:boolean;

  constructor() {
  }

  ngOnInit(): void {
    this.loadImage()
  }

  loadImage() {
    if(this.book.isbn==9780140120905){
      this.imagePath='assets/images/the-predators-ball.jpg';
    }else if (this.book.isbn==35618906){
      this.imagePath='assets/images/annapurna.jpg';
    }else if (this.book.isbn==9781324002642){
      this.imagePath='assets/images/the-fifth-risk.jpg';
    }else if (this.book.isbn==9780393351590){
      this.imagePath='assets/images/flash-boys.jpg';
    }else if (this.book.isbn==9780140446036){
      this.imagePath='assets/images/arthashastra.jpg';
    }
  }
}
