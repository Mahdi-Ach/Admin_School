package com.example.dashboardrest.Utils;

import com.example.dashboardrest.BO.School_tools.Class_room;
import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Filier;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.DAO.School_tools.Class_RoomRepository;
import com.example.dashboardrest.DAO.School_tools.ElementsRepository;
import com.example.dashboardrest.DAO.School_tools.FilierRepository;
import com.example.dashboardrest.DAO.School_tools.ModulesRepository;
import com.example.dashboardrest.Projection.List_Element;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
public class ExportToCsv {
    @Autowired
    private ElementsRepository elementsRepository;
    @Autowired
    private ModulesRepository modulesRepository;
    @Autowired
    private Class_RoomRepository classRoomRepository;
    @Autowired
    private FilierRepository filierRepository;
    public void exportElementToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=Element.csv");

        OutputStream outputStream = response.getOutputStream();
        List<Elements> elements = elementsRepository.findAll();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));

        String[] header = {"id", "nom d'element,nom de module(associer),code du module(associer)"};
        csvWriter.writeNext(header);

        for (Elements element : elements) {
            String[] data = {String.valueOf(element.getId()), element.getElementNom(),element.getModules().getModuleNom(),element.getModules().getCodeModule()};
            csvWriter.writeNext(data);
        }
        csvWriter.close();

        outputStream.flush();

    }
    public void exportModuleToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=Module.csv");

        OutputStream outputStream = response.getOutputStream();
        List<Modules> modules = modulesRepository.findAll();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));

        String[] header = {"id", "nom du module,code de module,nom du class(associer)"};
        csvWriter.writeNext(header);

        for (Modules module : modules) {
            String[] data = {String.valueOf(module.getId()), module.getModuleNom(),module.getCodeModule(),module.getClasses().getClassNom()};
            csvWriter.writeNext(data);
        }
        csvWriter.close();

        outputStream.flush();

    }
    public void exportClassToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=Class.csv");

        OutputStream outputStream = response.getOutputStream();
        List<Class_room> classRooms = classRoomRepository.findAll();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));

        String[] header = {"id", "nom du Class,nom du Filier(associer)"};
        csvWriter.writeNext(header);

        for (Class_room classRoom : classRooms) {
            String[] data = {String.valueOf(classRoom.getId()), classRoom.getClassNom(),classRoom.getFilier().getFilierNom()};
            csvWriter.writeNext(data);
        }
        csvWriter.close();

        outputStream.flush();

    }
    public void exportFilierToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=Filier.csv");

        OutputStream outputStream = response.getOutputStream();
        List<Filier> filiers = filierRepository.findAll();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));

        String[] header = {"id", "nom du filier"};
        csvWriter.writeNext(header);

        for (Filier filier : filiers) {
            String[] data = {String.valueOf(filier.getId()), filier.getFilierNom()};
            csvWriter.writeNext(data);
        }
        csvWriter.close();

        outputStream.flush();

    }
}
