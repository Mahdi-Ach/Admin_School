import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ElementService } from 'src/app/services/School_tools/element.service';
import { ModulesService } from 'src/app/services/School_tools/modules.service';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.scss']
})
export class ClassComponent {
  filier_noms:any = []
  isFormHidden = false
  module = new FormGroup({
    classNom:new FormControl('',{validators:[Validators.required],updateOn:"submit"}),
    filier_noms:new FormControl('',{validators:[Validators.required],updateOn:"submit"})
  })
  constructor(private http: HttpClient,private element_service:ElementService,private module_service:ModulesService){}
  ngOnInit(): void {
    this.module_service.getAllModule_Names("http://localhost:8080/school_tools/filier/list_nom_filier").subscribe({
      next:(res)=>{
        console.log(res)
        this.filier_noms = res
        this.isFormHidden = true
      },
      error:(err)=>{
        console.log(err)
      
      }
    })
  }
  submit_element(){
    console.log()
    if(this.module.valid){
      let data = {classNom:this.module.controls["classNom"]?.value}
      this.element_service.create_element("http://localhost:8080/school_tools/class/Class?nomfilier="+this.module.controls["filier_noms"]?.value,data).subscribe({
        next:(res)=>{
          console.log(res)
        },
        error:(err)=>{
          console.log(err.error)
          this.module.controls["classNom"].setErrors(err.error["classNom_NotEmpty"] ? {required:err.error["classNom_NotEmpty"]}:null)
          this.module.controls["classNom"].setErrors(err.error["class_unicity"] ? {class_unicity:err.error["class_unicity"]}:null)
          this.module.controls["filier_noms"].setErrors(err.error["nomfilier"] ? {required:err.error["nomfilier"]}:null)
          console.log(this.module.controls)
          console.log(this.module)
        }
      })
    }
  }
}


