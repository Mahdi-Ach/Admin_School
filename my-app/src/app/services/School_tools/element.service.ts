import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ElementService {

  constructor(private http:HttpClient) { }
  create_element(Api_Url:string,element:{}){
    return this.http.post(Api_Url,element);
  }
  findall_element(Api_Url:string){
    return this.http.get(Api_Url)
  }
  delete_element(Api_Url:string){
    return this.http.delete(Api_Url)
  }
  update_element(Api_Url:string){
    return this.http.put(Api_Url,{})
  }
}
