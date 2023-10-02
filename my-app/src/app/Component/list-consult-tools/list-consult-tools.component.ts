import { SelectionModel } from '@angular/cdk/collections';
import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { ElementService } from 'src/app/services/School_tools/element.service';

@Component({
  selector: 'app-list-consult-tools',
  templateUrl: './list-consult-tools.component.html',
  styleUrls: ['./list-consult-tools.component.scss']
})
export class ListConsultToolsComponent {
  TrashIcon = faTrash
  @ViewChild(MatPaginator) paginator: MatPaginator;
  student_data : ElementProjection[] = [];
  pageSizeOptions = [5, 10, 20]; // Define your page size options
  pageSize = 5; // Initial page size
  displayedColumns: string[] = ['select', 'id', 'cne', 'nom', 'prenom',"Constultation"];
  dataSource = new MatTableDataSource<ElementProjection>();
  selection = new SelectionModel<ElementProjection>(true, []);
  constructor(private element_service: ElementService,private cdr: ChangeDetectorRef){}
  ngOnInit(): void {
    this.element_service.findall_element("http://localhost:8080/school_tools/element/list_elements").subscribe(res=>{
      this.student_data = res as ElementProjection[]
      this.dataSource.data = this.student_data
      console.log(this.student_data)
    })
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.paginator.pageSize = this.pageSize;
  }

  deleteRow(row: any) {
    const index = this.dataSource.data.indexOf(row);
    if (index >= 0) {
      this.dataSource.data.splice(index, 1);
      this.dataSource._updateChangeSubscription();
    }
    this.element_service.delete_element("http://localhost:8080/school_tools/element/"+row.id+"/delete?List_id=").subscribe(
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
    this.element_service.delete_element("http://localhost:8080/school_tools/element/"+idList+"/delete?List_id=").subscribe(
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
export interface ElementProjection {
  elementNom: string;
  id: number;
  modul: {nomModule:string,codeModule:string};
}