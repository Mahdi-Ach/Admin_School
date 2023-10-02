import { SelectionModel } from '@angular/cdk/collections';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { data } from 'jquery';
import { CrudStudentService } from '../../services/services/crud-student.service';

@Component({
  selector: 'app-list-consult-teacher',
  templateUrl: './list-consult-teacher.component.html',
  styleUrls: ['./list-consult-teacher.component.scss']
})
export class ListConsultTeacherComponent {
  TrashIcon = faTrash
  @ViewChild(MatPaginator) paginator: MatPaginator;
  teacher_data : TeacherProjection[] = [];
  pageSizeOptions = [5, 10, 20]; // Define your page size options
  pageSize = 5; // Initial page size
  displayedColumns: string[] = ['select', 'id', 'cin', 'nom', 'prenom',"Constultation"];
  dataSource = new MatTableDataSource<TeacherProjection>();
  selection = new SelectionModel<TeacherProjection>(true, []);
  constructor(private crudteacher: CrudStudentService,private cdr: ChangeDetectorRef){}
  ngOnInit(): void {
    this.crudteacher.findallStudent("http://localhost:8080/Teacher/list_teacher").subscribe(res=>{
      this.teacher_data = res as TeacherProjection[]
      this.dataSource.data = this.teacher_data
      console.log(this.teacher_data)
    })
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.paginator.pageSize = this.pageSize; // Set the initial page size
  }

  deleteRow(row: any) {
    const index = this.dataSource.data.indexOf(row);
    if (index >= 0) {
      this.dataSource.data.splice(index, 1);
      this.dataSource._updateChangeSubscription(); // Notify MatTable that the data has changed
    }
    this.crudteacher.deleteStudent("http://localhost:8080/Person/deletePerson?List_id="+row.id).subscribe(
      {
        next:Response=>console.log(Response),
        error:err=>console.log(err)
      }
    )
    console.log(row)
    console.log(index)
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
  }

  deleteSelectedRow() {
    const idList = this.selection.selected.map(item => item.id);
    this.crudteacher.deleteStudent("http://localhost:8080/Person/deletePerson?List_id="+idList).subscribe(
      {
        next:(Response)=>{
          console.log(Response)
          this.dataSource.data = this.dataSource.data.filter(item1 => !this.selection.selected.some(item2 => item1.id === item2.id));
        },
        error:err=>console.log(err)
      }
    )
  }
}
export interface TeacherProjection {
  cin: string;
  id: number;
  nom: number;
  prenom: string;
}