import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Component/home/home.component';
import { RecherchCoursComponent } from './Component/recherch-cours/recherch-cours.component';
import { SinginStudentComponent } from './Component/singin-student/singin-student.component';
import { SinginTeacherComponent } from './Component/singin-teacher/singin-teacher.component';
import { ListConsultStudentComponent } from './Component/list-consult-student/list-consult-student.component';
import { StudentsInfoComponent } from './Component/students-info/students-info.component';
import { ListConsultTeacherComponent } from './Component/list-consult-teacher/list-consult-teacher.component';
import { TeachersInfoComponent } from './Component/teachers-info/teachers-info.component';
import { ElementComponent } from './Component/element/element.component';
import { ListConsultToolsComponent } from './Component/list-consult-tools/list-consult-tools.component';
import { ElementInfoComponent } from './Component/element-info/element-info.component';
import { ListConsultModuleComponent } from './Component/list-consult-module/list-consult-module.component';
import { ModuleInfoComponent } from './Component/module-info/module-info.component';
import { ListConsultClassComponent } from './Component/list-consult-class/list-consult-class.component';
import { ListConsultFilierComponent } from './Component/list-consult-filier/list-consult-filier.component';
import { ModuleComponent } from './Component/module/module.component';
import { ClassComponent } from './Component/class/class.component';
import { FilierComponent } from './Component/filier/filier.component';
import { ClassInfoComponent } from './Component/class-info/class-info.component';
import { FilierInfoComponent } from './Component/filier-info/filier-info.component';
import { DownloadsComponent } from './Component/downloads/downloads.component';
import { NotesModuleComponent } from './Component/notes-module/notes-module.component';


const routes: Routes = [
  {path:"",component:HomeComponent,children:[
    {path:"rechercheClass",component:RecherchCoursComponent},
    {path:"SinginStudent",component:SinginStudentComponent},
    {path:"SinginTeacher",component:SinginTeacherComponent},
    {path:"Consult_Student",component:ListConsultStudentComponent},
    {path:"Consult_Teacher",component:ListConsultTeacherComponent},
    {path:"Element",component:ElementComponent},
    {path:"Consult_Tools",component:ListConsultToolsComponent},
    {path:"Consult_Module",component:ListConsultModuleComponent},
    {path:"Consult_Class",component:ListConsultClassComponent},
    {path:"Consult_Filier",component:ListConsultFilierComponent},
    {path:"Module",component:ModuleComponent},
    {path:"Classes",component:ClassComponent},
    {path:"Filier",component:FilierComponent},
    {path:"Downloads",component:DownloadsComponent},
    {path:"Notes",component:NotesModuleComponent}
  ]},
  {path:"StudentDetail",component:StudentsInfoComponent},
  {path:"TeacherDetail",component:TeachersInfoComponent},
  {path:"ElementDetail",component:ElementInfoComponent},
  {path:"ModuleDetail",component:ModuleInfoComponent},
  {path:"ClassDetail",component:ClassInfoComponent},
  {path:"FilierDetail",component:FilierInfoComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
