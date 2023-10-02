import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'jquery';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CrudStudentService {

  constructor(private http: HttpClient) { }

  create(student_data:any,API_URL:string):Observable<any>{
    return this.http.post(API_URL,student_data)
  }
  findallStudent(API_URL:string){
    return this.http.get(API_URL)
  }
  getStudentImage(API_URL:string){
    return this.http.get(API_URL, { responseType: 'blob' })
  }
  getStudentDetail(API_URL:string){
    return this.http.get(API_URL)
  }
  deleteStudent(API_URl:string){
    return this.http.delete(API_URl)
  }
  updateStudent(API_URL:string,data:any){
    return this.http.post(API_URL,data);
  }
  updateStudentdata(API_URL:string,file:any = null){
    console.log(file)
    return this.http.put(API_URL,file);
  }
}
