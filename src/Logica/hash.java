/*
 para esta clase se utilizo como apoyo la clase HashMap que nos provee el lenguaje
 */
package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author Mateo
 */
public class hash {
    HashMap<String, inmigrante> inmigrantes = new HashMap<String, inmigrante>(); //creamos la tabla hash que contendra 
                                                                                 //los elemetnos, y le indicamos la clave

    public hash() {
    }
    
    
    public void llenar()
    { 
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("prueba.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);


            String linea = br.readLine();

                int i = 0;
               while(linea!=null)
               {
                  String[] campos = linea.split("-");
                  inmigrante in = new inmigrante();
                  
                  
                  in.setId(campos[0]);
                  in.setNombre(campos[1]);
                  in.setApellidos(campos[2]+" "+campos[3]);
                  in.setProcedencia(campos[4]);
                  in.setNacionalidad(campos[5]);

                  inmigrantes.put(in.getId(),in); // insertamos los inmigrantes en la tabla hsah
                  linea = br.readLine();
                  i++;
                }
            }catch(Exception e){
         e.printStackTrace();   
        }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
    
    public inmigrante buscar(String id)
    {
        inmigrante in = new inmigrante();
        try {
            in = inmigrantes.get(id); // obtenemos el elemento qe se busca
        } catch (Exception e) {
            e.getMessage();
        }
        return in;
    }
    
    public void eliminar(String id)
    {
        inmigrantes.remove(id); // eliminamos elemento
    }
    /*para editar se recrea el inmigrante, se elimina, y se reinserta con los datos corregidos*/
    public void editar (String id, String nombre, String apellido, String nacio, String procede)
    {
        inmigrante in = buscar(id);
        inmigrantes.remove(id);
        in.setId(id);
        in.setApellidos(apellido);
        in.setNacionalidad(nacio);
        in.setNombre(nombre);
        in.setProcedencia(procede);
        
        inmigrantes.put(in.getId(),in);
        
    }
    
    public void agregar (String id, String nombre, String apellido, String nacio, String procede)
    {
        inmigrante in = new inmigrante();
        in.setId(id);
        in.setApellidos(apellido);
        in.setNacionalidad(nacio);
        in.setNombre(nombre);
        in.setProcedencia(procede);
        
        inmigrantes.put(id,in);
        
    }
    
    
}
