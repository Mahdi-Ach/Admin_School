import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { error } from 'jquery';
import { ElementService } from 'src/app/services/School_tools/element.service';

@Component({
  selector: 'app-filier-info',
  templateUrl: './filier-info.component.html',
  styleUrls: ['./filier-info.component.scss']
})
export class FilierInfoComponent implements OnInit {
  isFormHidden = false
  filier:FormGroup
  editIcon = faEdit
  constructor(private http: HttpClient,private element_service:ElementService,private activatedRouter:ActivatedRoute){}
  ngOnInit(): void {
    this.element_service.findall_element("http://localhost:8080/school_tools/filier/DetailInfo/"+this.activatedRouter.snapshot.queryParamMap.get("id")).subscribe({
      next:(res)=>{
        this.isFormHidden = true

        console.log(res)
        this.filier = new FormGroup({
          filierNom:new FormControl({value:res['filierNom'],disabled:true},{validators:[Validators.required],updateOn:"blur"})
        })
      },
      error:(err)=>{

      }
  })
  }
  enable_editing(input:any){
    this.filier.disable()
    input.enable()
  }
  keyByValue(appObj, value) {
    const [key] = Object.entries(appObj).find(([key, val]) => val === value) || [];
    return key || null;
  }
  disable_input(input:any){
    if(this.filier.valid){
      let fieldname =this.keyByValue(this.filier.controls,input)
      this.element_service.update_element("http://localhost:8080/school_tools/filier/"+this.activatedRouter.snapshot.queryParamMap.get("id")+"/update?"+fieldname+"="+input.value).subscribe({
        next:(res)=>{
          console.log(res)
        },
        error:(err)=>{
          this.filier.controls["filierNom"].setErrors(err.error["filierNom"] ? {required:true}:null)
          this.filier.controls["filierNom"].setErrors(err.error["msg_filier"] ? {filier_unicity:err.error["msg_filier"]}:null)
          console.log(err.error)
        }
      })
    }
    //input.disable()
  }
}
