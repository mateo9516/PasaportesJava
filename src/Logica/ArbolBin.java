/*
 LA clase arbolBin es la encargada de gestionar los registros en forma de arbol, se basa en 
el principio de que un arbol binario es un arreglo ordenado de manera ascendente
 */
package Logica;

import Vista.GUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateo
 */
public class ArbolBin {
    
    int datos;
    inmigrante[] arbol = new inmigrante[5000];
    Vector<inmigrante> arbolAux = new Vector<inmigrante>(5000 , 1); /*usamos un vector como estructura auxiliar para hacer dinamico el proceso*/
    inmigrante[] arbolSec;
    
    
    public void llenar() ////Se lee el archivo en el cual estan los registros creados aleatoriamente para llenar el vector de inmigrantes
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
                  inmigrante in = new inmigrante();  //se usa la clase inmigrante para separar atributos
                  
                  
                  in.setId(campos[0]);
                  in.setNombre(campos[1]);
                  in.setApellidos(campos[2]+" "+campos[3]);
                  in.setProcedencia(campos[4]);
                  in.setNacionalidad(campos[5]);

                  arbol[i]=in; //se guarda en el arreglo
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
    
    public void mostrar()
    {
        for(int i=0;i<arbol.length;i++){
            System.out.println(arbol[i].id);    
        }
    }
    
    public void representar(int izq,int der) // toma todo el arreglo y lo ordena a partir del metodo quicksort
    {
       inmigrante pivote=arbol[izq]; // tomamos primer elemento como pivote
       int i=izq; // i realiza la búsqueda de izquierda a derecha
       int j=der; // j realiza la búsqueda de derecha a izquierda
       inmigrante aux;
 
     while(i<j){            // mientras no se crucen las búsquedas
         while(arbol[i].id.compareTo(pivote.id)<= 0 && i<j) i++; // busca elemento mayor que pivote
         while(arbol[j].id.compareTo(pivote.id)> 0) j--;         // busca elemento menor que pivote
         if (i<j) {                      // si no se han cruzado                      
            aux= arbol[i];                  // los intercambia
            arbol[i]=arbol[j];
            arbol[j]=aux;
     }
   }
       arbol[izq]=arbol[j]; // se coloca el pivote en su lugar de forma que tendremos
       arbol[j]=pivote; // los menores a su izquierda y los mayores a su derecha
       if(izq<j-1)
        representar(izq,j-1); // ordenamos subarray izquierdo
       if(j+1 <der)
        representar(j+1,der); // ordenamos subarray derecho
       
    }
    
    public void rellenar() // metodo usado para pasar todos losdatos del arreglo al vector
    {
        arbolAux.clear();
        for(int i=0;i<arbol.length;i++)
        {
            arbolAux.addElement(arbol[i]);
        }
    }
    
    public int buscar(String identificador) // apoyados en la busqueda binaria cumplimos con la complejidad temporal de buscar en un arbol binario
   {
        int n = arbol.length;
        int centro,inf=0,sup=n-1;
         while(inf<=sup){
           centro=(sup+inf)/2;
           if(arbol[centro].id.compareTo(identificador) == 0){ 
               return centro;
           }
           else if(arbol[centro].id.compareTo(identificador)> 0 ){
               sup=centro-1;
           }
           else {
             inf=centro+1;
           }
         }
         return -1;    
   }
    
    public inmigrante buscarInmi(String identificador)
    {
        
        if(buscar(identificador)!=-1){
            
            inmigrante in = arbol[buscar(identificador)];
            return in;
        }else
            return null; 
    }
    
    
    public void editarInmi(String identificador,String nombre, String apellidos, String proc, String nacio)
    {
        inmigrante in = arbol[buscar(identificador)];
        
        in.setNombre(nombre);
        in.setApellidos(apellidos);
        in.setProcedencia(proc);
        in.setNacionalidad(nacio);   
    }
    
    public void eliminarInmi(String identificador)
    {
       rellenar();
       arbolAux.remove(buscar(identificador));
       System.out.println(arbolAux.size());
       
       arbol = new inmigrante[arbolAux.size()];
       for(int i=0;i<arbolAux.size();i++)
       {
           arbol[i]=arbolAux.elementAt(i);
       }
    }
    /*para agregar un inmigrante realizamos un proceso de insercion de complejidad logn apoyamos en el principio de busqueda binaria*/
    public void agregarInmi(String identificador,String nombre, String apellidos, String proc, String nacio)        
    {
        rellenar(); // para insertarlo de una manera mas sencilla usamos el vector mediante el metodo add()
                    //el cual como parametros recibe el elemento y la posicion, corriendo los demas a la derecha
        inmigrante in = new inmigrante(identificador, nombre, apellidos, proc, nacio);
        
        
        int n = arbolAux.size();
        int centro,inf=0,sup=n-1;
         while(inf<=sup){
           centro=(sup+inf)/2;
           if(arbolAux.elementAt(centro).id.compareTo(identificador)>0 && arbolAux.elementAt(centro-1).id.compareTo(identificador)<=0){ 
               arbolAux.add(centro, in);
               System.out.println("Elemento insertado");
               break;
           }
           else if(arbol[centro].id.compareTo(identificador)> 0 && arbolAux.elementAt(centro-1).id.compareTo(identificador)> 0){
               sup=centro-1;
           }
           else {
             inf=centro+1;
           }
         }
         arbol = new inmigrante[arbolAux.size()]; //se redimensiona el arreglo para insertar los elementos nuevamente
         for(int i=0;i<arbolAux.size();i++)
          {
           arbol[i]=arbolAux.elementAt(i); //se pasan los elementos del vector al arreglo
          }
         
         mostrar();
  
    }
}
