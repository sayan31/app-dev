import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Customer} from "../model/customer";
import {UrlSettings} from "../utils/urlSettings";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private httpClient: HttpClient) { }

  register(customer:Customer){
    return this.httpClient.post<any>(`${UrlSettings.BASE_URL}/auth/register`,customer)
  }
}
