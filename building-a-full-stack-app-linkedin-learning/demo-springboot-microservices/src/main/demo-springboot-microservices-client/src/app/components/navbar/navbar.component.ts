import {Component, OnInit, ViewChild} from '@angular/core';
import {HomeComponent} from "../home/home.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  resetApp(){
    this.router.navigate(['']);
  }

}
