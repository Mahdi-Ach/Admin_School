import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ElementService } from 'src/app/services/School_tools/element.service';

@Component({
  selector: 'app-filier',
  templateUrl: './filier.component.html',
  styleUrls: ['./filier.component.scss']
})
export class FilierComponent {
  isFormHidden = false
  filier = new FormGroup({
    filierNom:new FormControl('',{validators:[Validators.required],updateOn:"submit"}),
  })
  constructor(private http: HttpClient,private element_service:ElementService,private filier_service:ElementService){}
  submit_element(){
    console.log()
    if(this.filier.valid){
      let data = {filierNom:this.filier.controls["filierNom"]?.value}
      this.element_service.create_element("http://localhost:8080/school_tools/filier/Filier",data).subscribe({
        next:(res)=>{
          console.log(res)
        },
        error:(err)=>{
          console.log(err.error)
          this.filier.controls["filierNom"].setErrors(err.error["filierNom_NotEmpty"] ? {required:err.error["filierNom_NotEmpty"]}:null)
          console.log(this.filier)
        }
      })
    }
  }
}
