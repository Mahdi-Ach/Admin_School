import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, ElementRef,ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { error } from 'jquery';
import { CrudStudentService } from '../../services/services/crud-student.service';
import { FormBuilder, FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';
import { faCamera, faEdit } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-students-info',
  templateUrl: './students-info.component.html',
  styleUrls: ['./students-info.component.scss']
})
export class StudentsInfoComponent {
  default_data={}
  image:any
  hiddenCancel = false
  id_disabled = true
  student_data:FormGroup;
  image_previou: File
  file:File = null
  @ViewChild('matButton', { static: true }) matButton: ElementRef;
  Profile: FormGroup;
  load_profile = false
  editIcon = faEdit
  cameraicon = faCamera
  isiconcamera_disabled = true
  constructor(private cdr: ChangeDetectorRef,private crudService:CrudStudentService,private activatedRouter:ActivatedRoute,private http: HttpClient){}
  
  ngOnInit(): void {
    this.crudService.getStudentDetail('http://localhost:8080/Student/Detail_student?Student_id='+this.activatedRouter.snapshot.queryParamMap.get("Student_id")).subscribe(res=>{
      this.image = `data:image/jpg;base64,${res["imagebyte"]}`
      this.load_profile = true
      const binaryString = atob(this.image.split(',')[1]); // Binary data string
      const blob = new Blob([binaryString], { type: 'image/jpeg' }); // Create a BLOB object
      this.file = new File([blob], "profile_img.jpg", { type: 'image/jpeg' });
      this.Profile = new FormGroup({
        nom: new FormControl({ value: res["nom"], disabled: true }),
        prenom: new FormControl({ value: res["prenom"], disabled: true }),
        cne: new FormControl({ value: res["cne"], disabled: true }),
        email: new FormControl({ value: res["email"], disabled: true }),
        phone: new FormControl({ value: res["phone"], disabled: true }),
        gender: new FormControl({ value: res["gender"], disabled: true }),
        id: new FormControl({ value: res["id"], disabled: true}),
        birthday: new FormControl({ value: res["birthday"], disabled: true }),
        address: new FormControl({ value: res["address"], disabled: true }),
        file: new FormControl('')
      }); 
      this.default_data["cne"] = this.Profile.controls["cne"].value
      this.default_data["email"] = res["email"]
      this.default_data["phone"] = res["phone"]
      this.default_data["address"] = res["address"]
      this.default_data["nom"] = res["nom"]
      this.default_data["prenom"] = res["prenom"]
      this.default_data["gender"] = res["gender"]
      this.default_data["file"] = this.image
    },err=>{
      })
  }

  Edit_Save(eve:any){
    this.isiconcamera_disabled = !this.isiconcamera_disabled
    if(eve.innerHTML == "Edit"){
      eve.innerHTML = "Save"
      this.hiddenCancel = true
      this.Profile.enable()
      this.Profile.controls["id"].disable()
    }else{
      eve.innerHTML = "Edit"
      this.hiddenCancel = false
      this.Profile.disable()
      let form = new FormData()
      form.append("cne",this.Profile.controls['cne'].value)
      form.append("nom",this.Profile.controls['nom'].value)
      form.append("prenom",this.Profile.controls['prenom'].value)
      form.append("email",this.Profile.controls['email'].value)
      form.append("birthday",this.Profile.controls['birthday'].value)
      form.append("gender",this.Profile.controls['gender'].value)
      form.append("phone",this.Profile.controls['phone'].value)
      form.append("address",this.Profile.controls['address'].value)
      form.append("file",this.file)
      this.crudService.updateStudent("http://localhost:8080/Student/Update_Student/"+this.Profile.controls["id"].value,form).subscribe({
        next : (res)=>{console.log(res)},
        error : (err)=>{console.log(err)}
      })
    }
  }

  onFileSelected(event) {
    this.file = event.target.files[0];
    let Filetoupload = new FormData()
    Filetoupload.append("file",this.file)
    this.crudService.updateStudentdata("http://localhost:8080/Student/update_Student/"+event.target.dataset["value"]+"/"+
    this.Profile.controls["id"].value,Filetoupload).subscribe({
      next:(res)=>{
          let reader = new FileReader()
          reader.readAsDataURL(this.file)
          reader.onload = (e)=>{
            this.image = reader.result
          }
      },
      error:(err)=>{
        this.Profile.controls["file"].setErrors(err.error)
      }
    })
  }

  Cancelbuttonclick(){
    this.Profile.disable();
    this.isiconcamera_disabled=true
    this.hiddenCancel = true
  }

  Update_input(e:any){
    this.crudService.updateStudentdata("http://localhost:8080/Student/update_Student/"+e.target.dataset["value"]+"/"+
    this.Profile.controls["id"].value+"?"+e.target.dataset["value"]+"="+e.target.value).subscribe(
      {
        next:(res)=>{
          this.Profile.controls[e.target.dataset["value"]].setErrors(null)
        },
        error:(err)=>{
          this.Profile.controls[e.target.dataset["value"]].setValue(this.default_data[e.target.dataset["value"]])
          this.Profile.controls[e.target.dataset["value"]].setErrors(err.error)
        }
      }
    )
  }
  start_updating(){
  }
  enable_editing(event:any,input:any){
    this.Profile.disable()
    this.Profile.controls[input.dataset["value"]].enable()
  }
}


