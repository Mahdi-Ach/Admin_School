import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './Component/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {MatIconModule} from '@angular/material/icon';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'; 
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIf } from '@angular/common';
import {MatRadioModule} from '@angular/material/radio';
import { HttpClientModule } from '@angular/common/http';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatCheckboxModule } from '@angular/material/checkbox';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDatepickerModule} from '@angular/material/datepicker';

import { SinginTeacherComponent } from './Component/singin-teacher/singin-teacher.component';
import { RecherchCoursComponent } from './Component/recherch-cours/recherch-cours.component';
import { SinginStudentComponent } from './Component/singin-student/singin-student.component';
import { StudentsInfoComponent } from './Component/students-info/students-info.component';
import { ListConsultStudentComponent } from './Component/list-consult-student/list-consult-student.component';
import { ListConsultTeacherComponent } from './Component/list-consult-teacher/list-consult-teacher.component';
import { TeachersInfoComponent } from './Component/teachers-info/teachers-info.component';
import { MatButtonModule } from '@angular/material/button';
import { ElementComponent } from './Component/element/element.component';
import { MatSelectModule } from '@angular/material/select';
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
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RecherchCoursComponent,
    SinginStudentComponent,
    StudentsInfoComponent,
    SinginTeacherComponent,
    ListConsultStudentComponent,
    ListConsultTeacherComponent,
    TeachersInfoComponent,
    ElementComponent,
    ListConsultToolsComponent,
    ElementInfoComponent,
    ListConsultModuleComponent,
    ModuleInfoComponent,
    ListConsultClassComponent,
    ListConsultFilierComponent,
    ModuleComponent,
    ClassComponent,
    FilierComponent,
    ClassInfoComponent,
    FilierInfoComponent,
    DownloadsComponent,
    NotesModuleComponent
  ],
  imports: [  
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FontAwesomeModule,
    MatIconModule,
    NgbModule,
    MdbCollapseModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatRadioModule,
    HttpClientModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatButtonModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
