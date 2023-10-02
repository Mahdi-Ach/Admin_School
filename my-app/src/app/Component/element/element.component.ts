import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ElementService } from 'src/app/services/School_tools/element.service';
import { ModulesService } from 'src/app/services/School_tools/modules.service';

@Component({
  selector: 'app-element',
  templateUrl: './element.component.html',
  styleUrls: ['./element.component.scss']
})
export class ElementComponent implements OnInit {
  modules_nom:any = []
  isFormHidden = false
  constructor(private http: HttpClient,private element_service:ElementService,private module_service:ModulesService){}
  ngOnInit(): void {
    this.module_service.getAllModule_Names("http://localhost:8080/school_tools/module/list_nom_module").subscribe({
      next:(res)=>{
        console.log(res)
        this.modules_nom = res
        this.isFormHidden = true
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }
  element = new FormGroup({
    elementNom:new FormControl('',{validators:[Validators.required],updateOn:"submit"}),
    module_name:new FormControl('',{validators:[Validators.required],updateOn:"submit"})
  })
  submit_element(){
    if(this.element.valid){
      let data = {elementNom:this.element.controls["elementNom"].value}
      this.element_service.create_element("http://localhost:8080/school_tools/element/Elements?nomModule="+this.element.controls["module_name"].value,data).subscribe({
        next:(res)=>{
        },
        error:(err)=>{
          this.element.controls["elementNom"].setErrors({required:err.error["elementNom_NotEmpty"]})
          console.log(this.element.controls)
          console.log(err)
        }
      })
    }
  }
}
