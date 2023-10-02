import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { faEdit } from '@fortawesome/free-regular-svg-icons';
import { ElementService } from 'src/app/services/School_tools/element.service';

@Component({
  selector: 'app-module-info',
  templateUrl: './module-info.component.html',
  styleUrls: ['./module-info.component.scss']
})
export class ModuleInfoComponent {
  isFormHidden = false
  module:FormGroup;
  list_class:any ;
  editIcon=faEdit
  default_data = {}
  constructor(private http:HttpClient,private element_service:ElementService,private activatedRouter:ActivatedRoute){}
  ngOnInit(): void {
    this.element_service.findall_element("http://localhost:8080/school_tools/module/DetailInfo/"+this.activatedRouter.snapshot.queryParamMap.get("id")).subscribe({
      next:(res)=>{
        this.isFormHidden = true
        console.log(res)
        this.module = new FormGroup({
          nomModule:new FormControl({value:res["nomModule"],disabled:true},{validators:[Validators.required],updateOn:'blur'}),
          codeModule:new FormControl({value:res['codeModule'],disabled:true},{validators:[Validators.required]}),
          classRoom:new FormControl({value:res['classRoom'],disabled:true},{validators:[Validators.required]})
        })
        this.list_class = res['listClassNoms']
        this.default_data["nomModule"] = this.module.controls["nomModule"]?.value
        this.default_data["codeModule"] = this.module.controls["codeModule"]?.value
        this.default_data['classRoom'] = this.module.controls['classRoom']?.value
        console.log(this.module)
      },
      error:(err)=>{

      }
    })
  }
  keyByValue(appObj, value) {
    const [key] = Object.entries(appObj).find(([key, val]) => val === value) || [];
    return key || null;
 }
  update_data(fieldform:any){
    let fieldname =this.keyByValue(this.module.controls,fieldform)
    console.log("http://localhost:8080/school_tools/class/"+this.activatedRouter.snapshot.queryParamMap.get("id")+"/update?"+fieldname+"="+fieldform.value)
    console.log(this.module.valid)
    if(this.module.valid){
      console.log("eaz  ")
      this.element_service.update_element("http://localhost:8080/school_tools/module/"+this.activatedRouter.snapshot.queryParamMap.get("id")+"/update?"+fieldname+"="+fieldform.value).subscribe({
        next:(res)=>{
          console.log(res)
        },
        error:(err)=>{

          console.log(err)
          /*this.module.controls[fieldname].setErrors(err.error["class_unicity"]?{class_unicity:err.error["class_unicity"]}:null)
          this.module.controls[fieldname].setErrors(err.error["required"]?{required:err.error["required"]}:null)
        */}
      })
    }
  }
  enable_editing(formcntrl:any){
    this.module.disable()
    formcntrl.enable()
  }
}


