import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Codigo {

    public Scanner leer = new Scanner(System.in);
    private ArrayList<Electrodomesticos> aparatos= new ArrayList<>();

    public Codigo(){
        archivopasaarraylist();
    } // este constructor nos permite q cuando se inicie el programa se pasen todos la lista de archivos al arraylist, pq al salir del programa
    //el arraylist se borra pero la lista no entonces, al entrar los valores q agregamos al archivo ps pasarlos al arraylist

    
     public void agggprodarray() throws IOException{
        String name;
        int usodario=0;
        double volteos=0.0;
        double consumodiario=0.0;
        double consumomensual=0.0;
        
                System.out.println("________________________________________________________________");
                System.out.println("Ingrese el nombre del objeto que desea registrar");
                  name = leer.nextLine();
                  
        while (true) {
            try {
                System.out.println("Cuantas horas diarias suele usar su "+name+"?");
                usodario= leer.nextInt();
                break;

            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
        }

       
                 
          while (true) {
            try {
                System.out.println("Revise su producto y digite la potencia de consumo que tiene");
                System.out.println("La cantidad de consumo tiene que ser en Watts (W)");
                volteos=leer.nextDouble();
                 break;
            } catch (Exception e) {
                System.out.println(e);
                leer.nextLine();
            }
          }
                  consumodiario = (volteos / 1000.0) * usodario;
                  consumomensual = consumodiario * 30;// * 6.446;

                  aparatos.add(new Electrodomesticos(name, usodario, volteos, consumomensual));
                  escribirarrayenarchivo(); //Guardado automatico
                  
                  System.out.println("________________________________________________________________\n");
    }

     public void escribirarrayenarchivo() throws IOException{

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("aparatos.txt"))) {
            for (Electrodomesticos aparatoselectricos2 : aparatos) {
                bw.write(aparatoselectricos2.toString());
                bw.newLine(); //peque;o buffer
            }
            System.out.println("Archivo guardado correctamente");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

     public void leerarraydearchivo(){
       
        try (BufferedReader br = new BufferedReader(new FileReader("aparatos.txt"))) {
            String line;
            while ((line=br.readLine())!=null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();// corregir leer
        }
    }

     public void eliminararrayyarchivo() throws IOException{
        int opcion=0;
       if (aparatos.isEmpty()) {
        System.out.println("No hay dispositivos electricos disponibles para eliminar");
        return;
       }
         

         while (opcion<1||opcion>aparatos.size()) {
            do {
                System.out.println("Ingrese el objeto electrico que desea eliminar");
            for(int i=0; i<aparatos.size();i++){
            System.out.println("---------------------------------------------------------------------------");
            System.out.println((i+1)+"-"+aparatos.get(i).getName());
            System.out.println("---------------------------------------------------------------------------");
         }
         //pedir el producto a eliminar aqui solo vamos a eliminar el del arraylist luego q eliminamos el del arraylist volvemos a sobreescribir la lista desde 0 con los valores actu
         
            try {
                System.out.print("Elija una opcion: ");
                 opcion = leer.nextInt();
                 break;
           
            } catch (Exception e) {
   
               System.out.println("Opcion Incorrecta !");
               leer.nextLine();
               
            }
         } while (true);
         }
            
                Electrodomesticos apa = aparatos.remove(opcion-1);
                System.out.println("Producto "+apa.getName()+" eliminado correctamente.");
                escribirarrayenarchivo();
                
             }
         
     public void archivopasaarraylist(){
       
        try (BufferedReader br = new BufferedReader(new FileReader("aparatos.txt"))) {
            String line;

            while ((line=br.readLine())!= null) {
                String []dividir = line.split("---");

                if (dividir.length==4) {
                    String nombre= dividir[0].trim();
                    int usodiario = Integer.parseInt(dividir[1].trim());
                    double volteos = Double.parseDouble(dividir[2].trim());
                    double consumomensual= Double.parseDouble(dividir[3].trim());

                    aparatos.add(new Electrodomesticos(nombre, usodiario, volteos, consumomensual));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

     }  

     public void Interfaz (){
        boolean salir = false;
        
        do {
            try {
                System.out.println("-----BIENVENIDO A ELECTROCOST-----");
                   System.out.println("1- Agregar Electrodomestico");             
                   System.out.println("2- Ver Consumo"); 
                   System.out.println("3- Modificar Electrodomestico");              
                   System.out.println("4- Eliminar Electrodomestico");  
                   System.out.println("5- Mostrar lista");
                   System.out.println("6- Generar factura");                 
                   System.out.println("7- Salir");
                   System.out.print("> Elija una opcion: ");
            
                   int opcion = leer.nextInt();
                   
                   System.out.println("________________________________________________________________\n");
                   leer.nextLine();//buffer

                   switch (opcion) {
                        case 1:
                        agggprodarray();
                        break;
                        case 2:
                        consumototaldetodoslosaparatos();
                        break;
                        case 3:
                        modificaraparatos();
                        break;
                        case 4:
                        eliminararrayyarchivo();
                        break;
                        case 5:
                        mostraraparatos();
                        break;
                       
                        case 6:
                        MostrarFact();
                        break;
                        case 7:
                        System.out.println("Gracias por usar ELECTROCOST");
                        salir = true;
                        break;
                   
                    default:
                        break;
                   }
                
            } catch (Exception e) {
               System.out.println(e);
               leer.nextLine();
            }
           } while (!salir);
     }

     public  double calcularconsumodetodoslosaparatos(){

        double consumototal=0.0;

       for (Electrodomesticos aparatoselectricos2 : aparatos) {

          consumototal+=aparatoselectricos2.getConsumomensual();
       }

        return consumototal;
     }
        
     public void consumototaldetodoslosaparatos(){
        if (aparatos.isEmpty()) {
            System.out.println("No hay objetos en la lista");
            System.out.println("!Agrega un nuevo dispositivo electrico para poder ver su consumo !");
            System.out.println("________________________________________________________________\n");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("OBJETOS ELECTRICOS - CONSUMO MENSUAL ");
        System.out.println("----------------------------------------------------------------------------------------------------");
       for (Electrodomesticos aparatoselectricos2 : aparatos) {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(aparatoselectricos2.getName()+" <--Su consumo mensual es de --> C$"+ aparatoselectricos2.getConsumomensual());
        System.out.println("----------------------------------------------------------------------------------------------------");
       }
       System.out.println("----------------------------------------------------------------------------------------------------");
       System.out.println("-CONSUMO MENSUAL-");
       System.out.println("----------------------------------------------------------------------------------------------------");
       double consumototaldetodoslosaparatos= calcularconsumodetodoslosaparatos();
       System.out.println("El consumo mensual de todos los dispositivos de la lista es de C$"+ consumototaldetodoslosaparatos);
       
       System.out.println("----------------------------------------------------------------------------------------------------\n");
    }

     public void modificaraparatos() throws IOException{


        if (aparatos.isEmpty()) {
            System.out.println("________________________________________________________________");
            System.out.println("No hay aparatos en tu lista");
            System.out.println("!Agrega aparatos electricos para poder modificarlos !");
            System.out.println("________________________________________________________________\n");
            return;
        }


        int opcion=0;
        int op=0;
        String namen;


        while (opcion<1||opcion>aparatos.size()) {//ingresar el aparato q quiere modificar
            do {
                try {
                    
                    System.out.println("________________________________________________________________");
                    System.out.println("Ingrese el indice del objeto que desea modificar");
                    for (int i=0; i<aparatos.size();i++){
                        System.out.println("------------------------------------------------");
                        System.out.println((i+1)+"-"+aparatos.get(i).getName());
                        System.out.println("------------------------------------------------");
                    }
                    System.out.print("Elija una opcion -->");
                    opcion = leer.nextInt();
                    System.out.println("________________________________________________________________");
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                    leer.nextLine();
            }
            } while (true);
    }
    //ya leimos el aparato que quiere modificar

       while (op<1||op>3) {//leemos el atributo del aparato que desea modificar
           try {
            System.out.println("Ingrese los atributos del objeto que desea modificar");
            System.out.println("1- Nombre");
            System.out.println("2- Uso diario");
            System.out.println("3- Volteos del objeto");
            System.out.print("Elija una opcion -->");
            op=leer.nextInt();
            System.out.println("________________________________________________________________");
             } catch (Exception e) {
             System.out.println(e);
             leer.nextLine();
               }
             }
        leer.nextLine();
        if (op==1) {//leer el nuevo nombre
            System.out.println("Ingrese el nuevo nombre del objeto "+ aparatos.get(opcion-1).getName());
            namen =leer.nextLine();
            aparatos.get(opcion-1).setName(namen);
        }

        if (op==2) {
            while (true) {
                try {
                    System.out.println("Ingrese el uso diario modificado de "+aparatos.get(opcion-1).getName());
            int usodiario= leer.nextInt();
            aparatos.get(opcion - 1).setUsodiario(usodiario);
            break;
                } catch (Exception e) {
                    System.out.println(e);
                    leer.nextLine();
                }
            }
        }

        if (op==3) {//leer el nuevo volteo
            while (true) {
                try {
                    System.out.println("Ingrese el nuevo valor de volteos que tendra el aparato "+ aparatos.get(opcion-1).getName());
                    double volti = leer.nextDouble();
                    aparatos.get(opcion-1).setVolteos(volti);
                    System.out.println("________________________________________________________________\n");
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                    leer.nextLine();
                }
            }
        }
    escribirarrayenarchivo();
    }
    
     public void mostraraparatos(){
        if (aparatos.isEmpty()) {
            System.out.println("No hay objetos en la lista");
            System.out.println("!Agrega objetos primero, para poder verlos!");
            System.out.println("________________________________________________________________\n");
            return;
        }

        

        //Elegir un objeto y ver mas detalles del mismo
        boolean Continuar = true;
        while(Continuar){
            try {
                System.out.println("___________________OBJETOS DE LA LISTA_______________________");
                for(int i=0 ;i<aparatos.size();i++){
                    System.out.println((i+1)+"--"+aparatos.get(i).getName());
                }
                System.out.println("_____________________________________________________________\n");
                System.out.println("-- Seleccione un objeto para ver mas detalles --");
                System.out.println("----- O PRECIONE (E) PARA SALIR -----");
                String opcion = leer.nextLine();

                /*Primer se lee la condicio de string, luego si esta no coincide con (E) pasa a la siguiente
                converion de string a int para manejar esas condiciones*/

            if(opcion.equalsIgnoreCase("E")){ //SI LA OPCION ES E SALE DEL CICLO

                    System.out.println("SALIENDO....");
                    Continuar = false;
                    System.out.println("________________________________________________________________");
            }
            else if(Integer.parseInt(opcion) > 0 && Integer.parseInt(opcion) <= aparatos.size()){//VER DETALLES DE UN ELECTROMESTICO ESPECIFICO
                System.out.println("------------------------------------------------------------");
                System.out.println("Nombre: "+aparatos.get(Integer.parseInt(opcion) - 1).getName());
                System.out.println("Potencia: "+aparatos.get(Integer.parseInt(opcion) - 1).getVolteos()+"kW");//Watts
                System.out.println("Horas de uso: "+aparatos.get(Integer.parseInt(opcion) - 1).getUsodiario()+"hrs");
                System.out.println("Uso mensual: "+aparatos.get(Integer.parseInt(opcion) - 1).getConsumomensual());
                System.out.println("------------------------------------------------------------\n");

                Continuar = false;//Sale del ciclo

            }else{

                System.out.println("Ese objeto no existe en la lista...");
                System.out.println("------------------------------------------------------------\n");
            }

            } catch (Exception NumberFormatException) {//Excepcion para cuando no ingrese la letra correcta a la hora de salir
                System.out.println("Ingrese una opcion valida!");
                System.out.println("_____________________________________________________________\n");
            }  
        }  
    }

    public void FacturaAparato(){
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter("facturaAparatos.txt"))) {

            if (aparatos.isEmpty()) {
                System.out.println("Agrega aparatos electricos para poder generar tu factura");
                return;
            }

            int contador = 1;
            double Total = calcularconsumodetodoslosaparatos();
            System.out.println("Escriba el mes actual: ");
            String mes = leer.nextLine();

            escribir.write("------------------FACTURA ELECTROCOST-----------------");
            escribir.newLine();
            escribir.write("________________________________________________________________\n");
            
            for (Electrodomesticos j : aparatos) {
                escribir.newLine(); 
                escribir.write(contador+". "+"Nombre: "+j.getName()+" ---------------------"+"Potencia: "+j.getVolteos()+" kW");
                escribir.newLine();
                escribir.write("Uso diario: "+j.getUsodiario()+" hrs "+" ------------------- "+"Consumo mensual: "+j.getConsumomensual()+" kW\n");
                escribir.newLine();

                contador += 1;
            }
            escribir.write("CONSUMO TOTAL DEL MES DE "+mes+": "+Total+" C$");
            escribir.newLine();
            escribir.write("________________________________________________________________\n");
            escribir.newLine();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MostrarFact(){
        FacturaAparato();
        try (BufferedReader  leer = new BufferedReader(new FileReader("facturaAparatos.txt"))) {
            String line;
            while ((line= leer.readLine())!= null) {
                System.out.println(line);
            }  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    



    //get y set del array
    public ArrayList<Electrodomesticos> getAparatos() {
        return aparatos;
    }

    public void setAparatos(ArrayList<Electrodomesticos> aparatos) {
        this.aparatos = aparatos;
    }
    
}
