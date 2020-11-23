import {Component} from "@angular/core";
import {BOOKS} from '../model/mock-books';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{
  books=BOOKS;
}
