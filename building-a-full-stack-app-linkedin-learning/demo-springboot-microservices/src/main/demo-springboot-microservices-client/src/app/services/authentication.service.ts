import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {Customer} from "../model/customer";
import {HttpClient} from "@angular/common/http";
import {UrlSettings} from "../utils/urlSettings";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentCustomerSubject: BehaviorSubject<Customer>;
  public currentCustomer: Observable<Customer>;

  constructor(private httpClient: HttpClient) {
    this.currentCustomerSubject=new BehaviorSubject<Customer>(JSON.parse(localStorage.getItem('currentCustomer')));
    this.currentCustomer=this.currentCustomerSubject.asObservable();
  }

  public get currentCustomerValue():Customer{
    return this.currentCustomerSubject.value;
  }

  login(username, password) {
    return this.httpClient.post<any>(`${UrlSettings.BASE_URL}/auth/login`, { username, password })
      .pipe(map(customer => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentCustomer', JSON.stringify(customer));
        this.currentCustomerSubject.next(customer);
        return customer;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentCustomer');
    this.currentCustomerSubject.next(null);
  }
}
