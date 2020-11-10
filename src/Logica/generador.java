/*
 LA clase generador tiene la funcion principal de crear los 5000 registros iniciales
lo hace a partir de un banco de nombres, apellidos y paises, genera aleatoriamente
los ids con las especificaciones que se nos asignaron en el proyecto
 */
package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class generador {
    
    String[] arrNombres = new String[480];
    String[] arrApellidos = new String[600];
    String[] arrPaises = new String[194];
    String[] arrIds = new String [5000];

    public String[] getArrIds() {
        return arrIds;
    }
    
    
    
    public generador() {
    }
    /*La funcion leer es la encargada de leer los txts de nombres, paises y apellidos y guardarlos
    en los arreglos respectivos*/
    public void leer(String arc)
    {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (arc+".txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         if(arc == "Nombres")  /*lectura del archivo de nombres*/
         {
            int i = 0; 
            while((linea=br.readLine())!=null)
            {
               arrNombres[i]=linea;
               i++;
            }
         }
         if(arc == "Apellidos") /*lectura del archivo de apellidos*/
         {
            int i = 0; 
            while((linea=br.readLine())!=null)
            {
               arrApellidos[i]=linea;
               i++;
            }
         }
         if(arc == "Paises")/*lectura del archivo de paises*/
         {
            int i = 0; 
            while((linea=br.readLine())!=null)
            {
               arrPaises[i]=linea;
               i++;
            }
         }
       }
      catch(Exception e){
         e.printStackTrace();   
        }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
   public void generaIds(){ /*generador de identificadores primero genera las letras y luego los numeros*/
       
       for(int i = 0;i<5000;i++)
       {
           int pri = (int) (Math.random()*26+65);
           int seg = (int) (Math.random()*26+65);
           int rest = (int) (Math.random()*90000000+10000000);
           
           char a = (char)pri;
           char b = (char)seg;
           String ini = Character.toString(a)+Character.toString(b);
           String c = Integer.toString(rest);
           String id = ini+c;
           System.out.println(id);
           arrIds[i]=id;        
       }
   } 
   public void generar(){ /*la funcion generar es la encargada de concatenar los identificadores
       que se generan aleatoriamente y la seleccion aleatoria de nombres apellidos y paises*/
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("prueba.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 5000; i++)
            {
                int pos = (int) (Math.random()*480);
                String nombre = arrNombres[pos];
                pos = (int) (Math.random()*600);
                String ape = arrApellidos[pos];
                pos = (int) (Math.random()*600);
                String ape2 = arrApellidos[pos];
                pos = (int) (Math.random()*194);
                String proce = arrPaises[pos];
                pos = (int) (Math.random()*194);
                String nacio = arrPaises[pos];
                
                pw.println(arrIds[i]+"-"+nombre+"-"+ape+"-"+ape2+"-"+proce+"-"+nacio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
   } 

}
      
   
     


}
