import { SelectionModel } from '@angular/cdk/collections';
import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { ElementService } from 'src/app/services/School_tools/element.service';
import { error } from 'jquery';

@Component({
  selector: 'app-notes-module',
  templateUrl: './notes-module.component.html',
  styleUrls: ['./notes-module.component.scss']
})
export class NotesModuleComponent {
  selected = 'Geni Info';
  filier_list:any = []
  list_module={}
  TrashIcon = faTrash
  @ViewChild(MatPaginator) paginator: MatPaginator;
  module_data : ClassProjection[] = [];
  pageSizeOptions = [5, 10, 20]; // Define your page size options
  pageSize = 5; // Initial page size
  displayedColumns: string[] = ['select', "nom d'etudiant",...Object.keys(this.list_module)];
  dataSource = new MatTableDataSource<ClassProjection>();
  selection = new SelectionModel<ClassProjection>(true, []);
  constructor(private class_service: ElementService,private cdr: ChangeDetectorRef){}
  ngOnInit(): void {
    this.class_service.findall_element("http://localhost:8080/school_tools/filier/list_nom_filier?filiernom="+this.selected).subscribe({
      next:(res)=>{
        this.filier_list = res
        console.log(this.filier_list)
      },
      error:(err)=>{
        console.log(err)
      }
    })
    this.class_service.findall_element("http://localhost:8080/Student/list_notes").subscribe(res=>{
      this.module_data = res as ClassProjection[]
      this.dataSource.data = this.module_data
      this.displayedColumns = ['select', "nom d'etudiant",...Object.keys(this.list_module)];

      console.log()
    })
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.paginator.pageSize = this.pageSize;
  }
  select_filier(){
    this.class_service.findall_element("http://localhost:8080/Student/list_notes?filiernom="+this.selected).subscribe(res=>{
      this.module_data = res as ClassProjection[]
      this.dataSource.data = this.module_data
      this.list_module = this.module_data[0]?.module_note
      this.displayedColumns = ['select', "nom d'etudiant",...Object.keys(this.list_module)];

      console.log(this.module_data)
    })
  }
  deleteRow(row: any) {
    const index = this.dataSource.data.indexOf(row);
    if (index >= 0) {
      this.dataSource.data.splice(index, 1);
      this.dataSource._updateChangeSubscription();
    }
    this.class_service.delete_element("http://localhost:8080/school_tools/class/"+row.id+"/delete?List_id=").subscribe(
      {
        next:Response=>console.log(Response),
        error:err=>console.log(err)
      }
    )
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
  }

  deleteSelectedRow() {
    const idList = this.selection.selected.map(item => item.id);
    this.class_service.delete_element("http://localhost:8080/school_tools/class/"+idList+"/delete?List_id=").subscribe(
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
export interface ClassProjection {
  classNom: string;
  id: number;
  filier:{filierNom:string};
  studentnom:string;
  module_note:{}
}