import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ElementService } from 'src/app/services/School_tools/element.service';
import { ModulesService } from 'src/app/services/School_tools/modules.service';
import { v4 as uuidv4 } from 'uuid';
@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.scss']
})
export class ModuleComponent {
  class_nom:any = []
  isFormHidden = false
  module = new FormGroup({
    moduleNom:new FormControl('',{validators:[Validators.required],updateOn:"submit"}),
    codeModule:new FormControl({value:uuidv4(),disabled:true},{validators:[Validators.required],updateOn:"submit"}),
    class_noms:new FormControl('',{validators:[Validators.required],updateOn:"submit"})
  })
  constructor(private http: HttpClient,private element_service:ElementService,private module_service:ModulesService){}
  ngOnInit(): void {
    this.module_service.getAllModule_Names("http://localhost:8080/school_tools/class/list_nom_class").subscribe({
      next:(res)=>{
        console.log(res)
        this.class_nom = res
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
      let data = {moduleNom:this.module.controls["moduleNom"]?.value,codeModule:this.module.controls["codeModule"]?.value}
      this.element_service.create_element("http://localhost:8080/school_tools/module/Modules?nom_class="+this.module.controls["class_noms"]?.value,data).subscribe({
        next:(res)=>{
          console.log(res)
        },
        error:(err)=>{
          console.log(err.error)
          this.module.controls["moduleNom"].setErrors(err.error["moduleNom_NotEmpty"] ? {required:err.error["moduleNom_NotEmpty"]}:null)
          this.module.controls["codeModule"].setErrors(err.error["codeModule_NotEmpty"] ? {required:err.error["codeModule_NotEmpty"]}:null)
          this.module.controls["moduleNom"].setErrors(err.error["module_unicity"] ? {module_unicity:err.error["module_unicity"]}:null)
          this.module.controls["class_noms"].setErrors(err.error["nom_class"] ? {required:err.error["nom_class"]}:null)
          console.log(this.module)
        }
      })
    }
  }
}
