import { SelectionModel } from '@angular/cdk/collections';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CrudStudentService } from '../../services/services/crud-student.service';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { data } from 'jquery';

@Component({
  selector: 'app-list-consult-student',
  templateUrl: './list-consult-student.component.html',
  styleUrls: ['./list-consult-student.component.scss']
})

export class ListConsultStudentComponent implements OnInit {
  TrashIcon = faTrash
  @ViewChild(MatPaginator) paginator: MatPaginator;
  student_data : StudentProjection[] = [];
  pageSizeOptions = [5, 10, 20]; // Define your page size options
  pageSize = 5; // Initial page size
  displayedColumns: string[] = ['select', 'id', 'cne', 'nom', 'prenom',"Constultation"];
  dataSource = new MatTableDataSource<StudentProjection>();
  selection = new SelectionModel<StudentProjection>(true, []);
  constructor(private crudStudent: CrudStudentService,private cdr: ChangeDetectorRef){}
  ngOnInit(): void {
    this.crudStudent.findallStudent("http://localhost:8080/Student/list_student").subscribe(res=>{
      this.student_data = res as StudentProjection[]
      this.dataSource.data = this.student_data
      console.log(this.student_data)
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
    this.crudStudent.deleteStudent("http://localhost:8080/Person/deletePerson?List_id="+row.id).subscribe(
      {
        next:Response=>console.log(Response),
        error:err=>console.log(err)
      }
    )
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
    this.crudStudent.deleteStudent("http://localhost:8080/Person/deletePerson?List_id="+idList).subscribe(
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
export interface StudentProjection {
  cne: string;
  id: number;
  nom: number;
  prenom: string;
}
