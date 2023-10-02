package com.example.dashboardrest.GlobalController.School_toolsController;

import com.example.dashboardrest.BO.School_tools.Class_room;
import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.DTOs.School_tools.ClassListDto;
import com.example.dashboardrest.DTOs.School_tools.ClassesListDto;
import com.example.dashboardrest.DTOs.School_tools.ElementsDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.Projection.List_class_nom;
import com.example.dashboardrest.Projection.List_module_nom;
import com.example.dashboardrest.Services.ServiceSchool_tools.Class_RoomService;
import com.example.dashboardrest.Services.ServiceSchool_tools.ElementService;
import com.example.dashboardrest.Utils.ExportToCsv;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/school_tools/class")
@Validated
public class Class_RoomController {
    @Autowired
    private Class_RoomService classRoomService;
    @Autowired
    private ExportToCsv exportToCsv;

    @PostMapping("/Class")
    public ResponseEntity<HashMap<String,String>> Create_Class(@Valid @RequestBody Class_room classRoom,
                                                               @RequestParam(required = false)
                                                               @NotBlank(message = "Nom du Module is required")
                                                               @NotEmpty(message = "Nom du is required")
                                                               String nomfilier){
        return ResponseEntity.ok(classRoomService.Create_Class(classRoom,nomfilier));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HashMap<String,String>> Delete_Class(@PathVariable List<Long> id) throws NoUserFound {
        return ResponseEntity.ok(classRoomService.Delete_Class(id));
    }
    @GetMapping("/list_classes")
    public ResponseEntity<List<ClassListDto>> Findall_Classes(){
        return ResponseEntity.ok(classRoomService.FindAllClass());
    }
    @GetMapping("/list_nom_class")
    public ResponseEntity<List<List_class_nom>> findall_nomModule(){
        return ResponseEntity.ok(classRoomService.findall_nomClass());
    }
    @GetMapping("/DetailInfo/{id}")
    public ResponseEntity<ClassesListDto> DetailInfo_Element(@PathVariable Long id) throws NoUserFound {
        return ResponseEntity.ok(classRoomService.FindElementwithId(id));
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<HashMap<String,String>> Update_Class(
            @PathVariable Long id,
            @RequestParam(required = false)
            String classNom,
            @RequestParam(required = false)
            String nomfilier
    ){
        return ResponseEntity.ok(classRoomService.Update_Class(id,classNom,nomfilier));
    }
    @GetMapping("/csv/export")
    public void exportCSV(HttpServletResponse response) throws IOException, IOException {

        exportToCsv.exportClassToCSV(response);
    }
}
