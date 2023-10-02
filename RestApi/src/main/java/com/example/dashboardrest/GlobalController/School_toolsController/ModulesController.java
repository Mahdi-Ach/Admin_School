package com.example.dashboardrest.GlobalController.School_toolsController;

import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.DAO.School_tools.ModulesRepository;
import com.example.dashboardrest.DTOs.School_tools.FilierListDto;
import com.example.dashboardrest.DTOs.School_tools.ModuleListDto;
import com.example.dashboardrest.DTOs.School_tools.ModulesDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.Projection.List_module_nom;
import com.example.dashboardrest.Services.ServiceSchool_tools.ModulesService;
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
@RequestMapping("/school_tools/module")
@Validated
public class ModulesController {
    @Autowired
    private ModulesService modulesService;
    @Autowired
    private ExportToCsv exportToCsv;

    @PostMapping("/Modules")
    public ResponseEntity<HashMap<String,String>> Create_Modules(
            @Valid @RequestBody Modules modules,
            @RequestParam(required = false)
            @NotBlank(message = "Nom du Module is required")
            @NotEmpty(message = "Nom du is required")
            String nom_class){
        return ResponseEntity.ok(modulesService.Create_Module(modules,nom_class));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HashMap<String,String>> Delete_Modules(@PathVariable List<Long> id) throws NoUserFound {
        return ResponseEntity.ok(modulesService.Delete_Module(id));
    }
    @GetMapping("/list_modules")
    public ResponseEntity<List<ModuleListDto>> Findall_Modules(){
        return ResponseEntity.ok(modulesService.FindAllModule());
    }
    @GetMapping("/list_nom_module")
    public ResponseEntity<List<List_module_nom>> findall_nomModule(){
        return ResponseEntity.ok(modulesService.findall_nomModule());
    }
    @GetMapping("/DetailInfo/{id}")
    public ResponseEntity<ModulesDto> DetailInfo_Element(@PathVariable Long id) throws NoUserFound {
        return ResponseEntity.ok(modulesService.FindmModulewithId(id));
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<HashMap<String,String>> Update_Modules_Code(
            @PathVariable Long id,
            @RequestParam(required = false) String nomModule,
            @RequestParam(required = false) String codeModule,
            @RequestParam(required = false) String classRoom) throws NoUserFound {
        return ResponseEntity.ok(modulesService.Update_Module(id,nomModule,codeModule,classRoom));
    }

    @GetMapping("/csv/export")
    public void exportCSV(HttpServletResponse response) throws IOException, IOException {

        exportToCsv.exportModuleToCSV(response);
    }
}
