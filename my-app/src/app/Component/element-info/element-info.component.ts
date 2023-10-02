import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { error } from 'jquery';
import { ElementService } from 'src/app/services/School_tools/element.service';
import { ModulesService } from 'src/app/services/School_tools/modules.service';

@Component({
  selector: 'app-element-info',
  templateUrl: './element-info.component.html',
  styleUrls: ['./element-info.component.scss']
})
export class ElementInfoComponent implements OnInit{
  isFormHidden = false
  element:FormGroup;
  list_Module:any ;
  editIcon=faEdit
  default_data = {}
  constructor(private http:HttpClient,private element_service:ElementService,private activatedRouter:ActivatedRoute){}
  ngOnInit(): void {
    this.element_service.findall_element("http://localhost:8080/school_tools/element/DetailInfo/"+this.activatedRouter.snapshot.queryParamMap.get("id")).subscribe({
      next:(res)=>{
        this.isFormHidden = true
        console.log(res)
        this.element = new FormGroup({
          elementNom:new FormControl({value:res["elementNom"],disabled:true},{validators:[Validators.required],updateOn:'blur'}),
          moduleNom:new FormControl({value:res['moduleNom'],disabled:true},{validators:[Validators.required]})
        })
        console.log(res['moduleNom'])
        this.list_Module = res['listModuleNoms']
        this.default_data["elementNom"] = this.element.controls["elementNom"]?.value
        this.default_data["moduleNom"] = this.element.controls["moduleNom"]?.value
        console.log(this.element)
      },
      error:(err)=>{

      }
    })
  }
  keyByValue(appObj, value) {
    const [key] = Object.entries(appObj).find(([key, val]) => val === value) || [];
    return key || null;
 }
  update_data(formcontroller:any,fieldform:any){
    let fieldname =this.keyByValue(this.element.controls,fieldform)
    console.log(fieldname)
    if(this.element.valid){
      console.log("http://localhost:8080/school_tools/element/"+this.activatedRouter.snapshot.queryParamMap.get("id")+"/update?"+fieldname+"="+fieldform.value)
      this.element_service.update_element("http://localhost:8080/school_tools/element/"+this.activatedRouter.snapshot.queryParamMap.get("id")+"/update?"+fieldname+"="+fieldform.value).subscribe({
        next:(res)=>{
          console.log(res)
        },
        error:(err)=>{
          this.element.controls[fieldname].setValue(this.default_data[fieldname])
          this.element.controls[fieldname].setErrors(err.error["element_unicity"]?{element_unicity:err.error["element_unicity"]}:null)
          this.element.controls[fieldname].setErrors(err.error["required"]?{required:err.error["required"]}:null)
        }
      })
    }
  }
  enable_editing(formcntrl:any){
    this.element.disable()
    formcntrl.enable()
  }
}
