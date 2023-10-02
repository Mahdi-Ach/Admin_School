import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { ElementService } from 'src/app/services/School_tools/element.service';

@Component({
  selector: 'app-class-info',
  templateUrl: './class-info.component.html',
  styleUrls: ['./class-info.component.scss']
})

export class ClassInfoComponent {
  isFormHidden = false
  class:FormGroup;
  list_Filier:any ;
  editIcon=faEdit
  default_data = {}
  constructor(private http:HttpClient,private element_service:ElementService,private activatedRouter:ActivatedRoute){
  }
  ngOnInit(): void {
    this.element_service.findall_element("http://localhost:8080/school_tools/class/DetailInfo/"+this.activatedRouter.snapshot.queryParamMap.get("id")).subscribe({
      next:(res)=>{
        this.isFormHidden = true
        console.log(res)
        this.class = new FormGroup({
          classNom:new FormControl({value:res["classNom"],disabled:true},{validators:[Validators.required],updateOn:'blur'}),
          nomfilier:new FormControl({value:res['nomfilier'],disabled:true},{validators:[Validators.required]})
        })
        this.list_Filier = res['listFilierNoms']
        this.default_data["classNom"] = this.class.controls["classNom"]?.value
        this.default_data["nomfilier"] = this.class.controls["nomfilier"]?.value
        console.log(this.class)
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
    let fieldname =this.keyByValue(this.class.controls,fieldform)
    console.log("http://localhost:8080/school_tools/class/"+this.activatedRouter.snapshot.queryParamMap.get("id")+"/update?"+fieldname+"="+fieldform.value)
    console.log(this.class.valid)
    if(this.class.valid){
      console.log("eaz  ")
      this.element_service.update_element("http://localhost:8080/school_tools/class/"+this.activatedRouter.snapshot.queryParamMap.get("id")+"/update?"+fieldname+"="+fieldform.value).subscribe({
        next:(res)=>{
          console.log(res)
        },
        error:(err)=>{

          console.log(err)
          this.class.controls[fieldname].setValue(this.default_data[fieldname])
          this.class.controls[fieldname].setErrors(err.error["class_unicity"]?{class_unicity:err.error["class_unicity"]}:null)
          this.class.controls[fieldname].setErrors(err.error["required"]?{required:err.error["required"]}:null)
        }
      })
    }
  }
  enable_editing(formcntrl:any){
    this.class.disable()
    formcntrl.enable()
  }
}
