import { formatDate } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormControlName, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { CrudStudentService } from '../../services/services/crud-student.service';
import * as $ from 'jquery'

@Component({
  selector: 'app-singin-teacher',
  templateUrl: './singin-teacher.component.html',
  styleUrls: ['./singin-teacher.component.scss']
})
export class SinginTeacherComponent {
  constructor(private crudStudent: CrudStudentService,private cdr: ChangeDetectorRef){}
  success_message :any = ""
  hide : any = false
  file:File = null
  singinstudentsection : FormGroup = new FormGroup({
    cne : new FormControl('',{validators:[Validators.required],updateOn:'blur'}),
    nom : new FormControl('',{validators:[Validators.required],updateOn:'blur'}),
    prenom : new FormControl('',{validators:[Validators.required],updateOn:'blur'}),
    email : new FormControl('',{validators:[Validators.required,Validators.email],updateOn: 'blur'}),
    birthday : new FormControl('', {validators:[Validators.required,Validators.pattern(/\d{1,2}-\d{1,2}-\d{2,4}/)],updateOn: 'blur'}),
    phone : new FormControl('', {validators:[Validators.required,Validators.pattern(/^\d{2}-\d{2}-\d{2}-\d{2}-\d{2}$/)],updateOn: 'blur'}),
    gender : new FormControl('Male',{validators:[Validators.required],updateOn: 'blur'}),
    address : new FormControl('',{validators:[Validators.required],updateOn: 'blur'}),
    file : new FormControl('')
  })
  errors(){
    
  }
  submitStudentSection(e:any){
    e.preventDefault()
    let a = document.querySelector(".filename") as HTMLInputElement 
    if(this.file?.name == undefined){
      this.singinstudentsection.controls["file"].setErrors({required:true})
    }
    if(this.singinstudentsection.valid){
      let form = new FormData()
      form.append("cne",this.singinstudentsection.controls['cne'].value)
      form.append("nom",this.singinstudentsection.controls['nom'].value)
      form.append("prenom",this.singinstudentsection.controls['prenom'].value)
      form.append("email",this.singinstudentsection.controls['email'].value)
      form.append("birthday",this.singinstudentsection.controls['birthday'].value)
      form.append("gender",this.singinstudentsection.controls['gender'].value)
      form.append("phone",this.singinstudentsection.controls['phone'].value)
      form.append("address",this.singinstudentsection.controls['address'].value)
      form.append("role","Student")
      form.append("file",this.file)
      console.log(form)
      this.crudStudent.create(form,'http://localhost:8080/Teacher/create_teacher').subscribe(res=>{
        this.success_message = res.success
        this.hide = true
        $("form").css("transform","translateY(0%)")
        this.cdr.detectChanges();
      },(error)=>{
        console.log(error.error)
        this.hide = false
        $("form").css("transform","")
        this.singinstudentsection.controls['cne_NotEmpty']?.setErrors(error.error["cne"]?{required:true}:null)
        this.singinstudentsection.controls['nom_NotEmpty']?.setErrors(error.error["nom"]?{required:true}:null)
        this.singinstudentsection.controls['prenom_NotEmpty']?.setErrors(error.error["prenom"]?{required:true}:null)
        let email_errors = {...error.error["email_NotEmpty"]?{required:true}:null,...error.error["email_Email"]?{email:true}:null,...error.error["email_unicity"]?{email_unicity:error.error["email_unicity"]}:null}
        this.singinstudentsection.controls['email']?.setErrors(Object.keys(email_errors).length == 0?null:email_errors)
        this.singinstudentsection.controls['address']?.setErrors(error.error["address_NotEmpty"]?{required:true}:null)
        let phone_errors = {...error.error["phone_NotEmpty"]?{required:true}:null,...error.error["phone_Pattern"]?{pattern:true}:null,...error.error["phone_unicity"]?{phone_unicity:error.error["phone_unicity"]}:null}
        this.singinstudentsection.controls['phone']?.setErrors(Object.keys(phone_errors).length == 0?null:phone_errors)
        this.singinstudentsection.controls['gender_NotEmpty']?.setErrors(error.error["gender"]?{required:true}:null)
        let file_errors = {...(error.error["filename_NotEmpty"])?{required:true}:null,...error.error["file_size"]?{file_size:error.error["file_size"]}:null}
        this.singinstudentsection.controls['file']?.setErrors(Object.keys(file_errors).length == 0?null:file_errors)
        this.singinstudentsection.controls['birthday_NotEmpty']?.setErrors(error.error["birthday_NotEmpty"]?{required:true}:null)
        this.cdr.detectChanges();
      })
    }
  }

  fileName = '';

    onFileSelected(event) {
      this.file = event.target.files[0];
        if (this.file) {
          this.fileName = this.file.name;
          let input_file = document.querySelector(".file-input") as HTMLInputElement
          let input_show = document.querySelector(".filename") as HTMLInputElement
          input_show.value = input_file.files[0].name
          this.singinstudentsection.controls["file"].setValue(input_file.files[0].name)
        }
    }
}
