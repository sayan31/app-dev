import {Component, OnInit, ViewChild} from '@angular/core';
import {HomeComponent} from "../home/home.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
/*  templateUrl:'./navbar.component.actual.html',*/
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private collapsed = true;

  constructor() { }

  ngOnInit() {
  }

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

}
