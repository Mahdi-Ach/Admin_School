package com.example.dashboardrest.GlobalController.School_toolsController;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Filier;
import com.example.dashboardrest.DTOs.School_tools.ElementsDto;
import com.example.dashboardrest.DTOs.School_tools.FilierDto;
import com.example.dashboardrest.DTOs.School_tools.FilierListDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.Projection.List_filier_nom;
import com.example.dashboardrest.Projection.List_module_nom;
import com.example.dashboardrest.Services.ServiceSchool_tools.ElementService;
import com.example.dashboardrest.Services.ServiceSchool_tools.FilierService;
import com.example.dashboardrest.Utils.ExportToCsv;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/school_tools/filier")
@Validated
public class FilierController {
    @Autowired
    private FilierService filierService;
    @Autowired
    private ExportToCsv exportToCsv;
    @PostMapping("/Filier")
    public ResponseEntity<HashMap<String,String>> Create_Filier(@Valid @RequestBody Filier filier){
        return ResponseEntity.ok(filierService.Create_Filier(filier));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HashMap<String,String>> Delete_Filiers(@PathVariable List<Long> id) throws NoUserFound {
        return ResponseEntity.ok(filierService.Delete_Filier(id));
    }
    @GetMapping("/list_filiers")
    public ResponseEntity<List<FilierListDto>> Findall_Filiers(){
        return ResponseEntity.ok(filierService.FindAllFilier());
    }
    @GetMapping("/DetailInfo/{id}")
    public ResponseEntity<FilierListDto> DetailInfo_Element(@PathVariable Long id) throws NoUserFound {
        return ResponseEntity.ok(filierService.findFilierbyId(id));
    }
    @GetMapping("/list_nom_filier")
    public ResponseEntity<List<List_filier_nom>> findall_nomModule(){
        return ResponseEntity.ok(filierService.findall_nomFilier());
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<HashMap<String,String>> Update_Filier(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotBlank(message = "Nom filier is required")
            @NotEmpty(message = "Nom du Filier is Required")
            String filierNom
    ){
        return ResponseEntity.ok(filierService.Update_Filier(id,filierNom));
    }

    @GetMapping("/csv/export")
    public void exportCSV(HttpServletResponse response) throws IOException, IOException {

        exportToCsv.exportFilierToCSV(response);
    }
}
