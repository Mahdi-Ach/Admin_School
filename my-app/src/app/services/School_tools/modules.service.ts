import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModulesService {

  constructor(private http:HttpClient) { }
  getAllModule_Names(Api_Url:string){
    return this.http.get(Api_Url);
  }
}
