import {Component, OnInit, ViewChild} from '@angular/core';
import {HomeComponent} from "../home/home.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private collapsed = true;

  /*Define a route in place of this*/
  /*@ViewChild('homeC')
  homeC: HomeComponent;*/

  constructor() { }

  ngOnInit() {
  }

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  /*reset(){
    this.homeC.reset();
  }*/

}
