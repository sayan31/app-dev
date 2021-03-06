import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {GetResponse} from "../model/getallbooks.response";
import {UrlSettings} from "../utils/urlSettings";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private httpClient: HttpClient) { }

  showBooks(){
    let API_URL=`${UrlSettings.BASE_URL}/books/v1`;
    return this.httpClient.get<GetResponse[]>(API_URL,{ headers: this.headers,observe: 'response' });
  }
}
