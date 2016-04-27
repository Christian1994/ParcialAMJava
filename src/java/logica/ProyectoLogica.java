/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jxl.Sheet;
import jxl.Workbook;
import modelo.Departamento;
import modelo.Proyecto;
import persistencia.DepartamentoFacadeLocal;
import persistencia.ProyectoFacadeLocal;

/**
 *
 * @author Proyecto Univalle
 */
@Stateless
public class ProyectoLogica implements ProyectoLogicaLocal {

    @EJB
    ProyectoFacadeLocal proyectoDAO;

    @EJB
    DepartamentoFacadeLocal departamentoDAO;
    
    @Override
    public List<Proyecto> consultarTodos() throws Exception {
        return proyectoDAO.findAll();
    }

    @Override
    public String importarProyectos(String archivo) throws Exception {
        Workbook archivoExcel = Workbook.getWorkbook(new File(archivo));
        Sheet hoja = archivoExcel.getSheet(0);
        int numFilas = hoja.getRows();
        
        Integer proyectosRegistrados = 0;
        Integer proyectosExistentes = 0;
        
        for(int fila = 1; fila < numFilas; fila++){
            Proyecto proyecto = new Proyecto();
            
            proyecto.setNombrep(hoja.getCell(0, fila).getContents());
            
            Departamento departamento = departamentoDAO.find((Object) hoja.getCell(1, fila).getContents());
            proyecto.setNumd(departamento);
            
            if(proyectoDAO.find(proyecto.getNumerop()) == null){
                proyectoDAO.create(proyecto);
                proyectosRegistrados++;
            }
            else{
                proyectosExistentes++;
            }
        }
        return "Se importaron " + proyectosRegistrados + " proyectos, ya existían " + proyectosExistentes + " proyectos.";
    }

}
