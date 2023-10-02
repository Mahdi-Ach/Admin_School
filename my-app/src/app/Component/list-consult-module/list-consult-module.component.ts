import { SelectionModel } from '@angular/cdk/collections';
import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { ElementService } from 'src/app/services/School_tools/element.service';

@Component({
  selector: 'app-list-consult-module',
  templateUrl: './list-consult-module.component.html',
  styleUrls: ['./list-consult-module.component.scss']
})
export class ListConsultModuleComponent {
  TrashIcon = faTrash
  @ViewChild(MatPaginator) paginator: MatPaginator;
  module_data : ModuleProjection[] = [];
  pageSizeOptions = [5, 10, 20]; // Define your page size options
  pageSize = 5; // Initial page size
  displayedColumns: string[] = ['select', 'id', 'nomModule', 'codeModule', 'classNom',"Constultation"];
  dataSource = new MatTableDataSource<ModuleProjection>();
  selection = new SelectionModel<ModuleProjection>(true, []);
  constructor(private module_service: ElementService,private cdr: ChangeDetectorRef){}
  ngOnInit(): void {
    this.module_service.findall_element("http://localhost:8080/school_tools/module/list_modules").subscribe(res=>{
      this.module_data = res as ModuleProjection[]
      this.dataSource.data = this.module_data
      console.log(this.module_data)
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
    this.module_service.delete_element("http://localhost:8080/school_tools/module/"+row.id+"/delete?List_id=").subscribe(
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
    this.module_service.delete_element("http://localhost:8080/school_tools/module/"+idList+"/delete?List_id=").subscribe(
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
export interface ModuleProjection {
  nomModule: string;
  id: number;
  codeModule:string
  classRoom:{classNom:string}
}
