package com.example.dashboardrest.GlobalController.School_toolsController;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.DTOs.School_tools.ElementsDto;
import com.example.dashboardrest.DTOs.School_tools.ElementsListDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
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
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/school_tools/element")
@Validated
public class ElementsController {
    @Autowired
    private ElementService elementService;
    @Autowired
    private ExportToCsv exportToCsv;

    @PostMapping("/Elements")
    public ResponseEntity<HashMap<String,String>> Create_Elements(
            @Valid @RequestBody Elements elements,
            @RequestParam(required = false)
            @NotBlank(message = "Nom du Module is required")
            @NotEmpty(message = "Nom du is required")
            String nomModule){
        return ResponseEntity.ok(elementService.Create_Element(elements,nomModule));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HashMap<String,String>> Delete_Elements(@PathVariable List<Long> id) throws NoUserFound {
        return ResponseEntity.ok(elementService.Delete_Element(id));
    }
    @GetMapping("/list_elements")
    public ResponseEntity<List<ElementsListDto>> Findall_ELements(){
        return ResponseEntity.ok(elementService.FindAllElement());
    }
    @GetMapping("/DetailInfo/{id}")
    public ResponseEntity<ElementsDto> DetailInfo_Element(@PathVariable Long id) throws NoUserFound {
        return ResponseEntity.ok(elementService.FindElementwithId(id));
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<HashMap<String,String>> Update_Element_Nom(
            @PathVariable Long id,
            @RequestParam(required = false)
            String elementNom,
            @RequestParam(required = false)
            String moduleNom
    ){
        return ResponseEntity.ok(elementService.Update_ElementNom(id,elementNom,moduleNom));
    }
    @GetMapping("/csv/export")
    public void exportCSV(HttpServletResponse response) throws IOException, IOException {

        exportToCsv.exportElementToCSV(response);
    }
}
