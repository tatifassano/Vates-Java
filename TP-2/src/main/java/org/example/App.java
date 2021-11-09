package org.example;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Gestor gestor = new Gestor();

        //para usar JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personasPU");
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        System.out.println("MENU");

        while(!salir) {

            System.out.println("Elija opcion: ");
            System.out.println("Opcion 1: Alta recital.");
            System.out.println("Opcion 2: Registrar venta de entradas");
            System.out.println("Opcion 3: Cancelar recital.");
            System.out.println("REPORTES");
            System.out.println("Opcion 4: Listado completo de recitales.");
            System.out.println("Opcion 5: Listado de recitales de mayor convocatoria.");
            System.out.println("Opcion 6: Total de recaudación por mes");
            System.out.println("Opcion 7: Mejor comprador");
            System.out.println("Opcion 8: Cartelera");
            System.out.println("Opcion 9: Salir");
            System.out.println("-------------------------");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    //ALTA RECITAL
                    System.out.println("Ingrese nombre de artista");
                    sc.nextLine();
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese precio de entradas");
                    int precio = sc.nextInt();
                    System.out.println("Ingrese capacidad de local");
                    int capacidad = sc.nextInt();
                    gestor.altaRecital(nombre, precio, capacidad);
                    break;
                case 2:
                    //ALTA VENTA
                    int nroRecital;
                    String comprador;
                    int cantidadEntradas;
                    int cuotas;
                    int totalVenta = 0;
                    System.out.println("Ingrese comprador");
                    sc.nextLine();
                    comprador = sc.nextLine();
                    System.out.println("Elige Recital");
                    gestor.listarRecitalesSinCancelar();
                    System.out.println("Ingrese recital:");
                    nroRecital = sc.nextInt();
                    Recital recital = gestor.buscarRecital(nroRecital);
                    if(recital != null && !recital.getCancelada()){

                        System.out.println("Ingrese cantidad entradas:");
                        cantidadEntradas = sc.nextInt();
                        if(cantidadEntradas <= (recital.getCapacidadLocal() - recital.getOcupacion()))
                        {
                            System.out.println("Ingrese cantidad de cuotas - 1, 3 o 6");
                            cuotas = sc.nextInt();
                            while(cuotas != 1 && cuotas != 3 && cuotas != 6){
                                System.out.println("Ingrese cantidad de cuotas - 1, 3 o 6");
                                cuotas = sc.nextInt();
                                totalVenta = gestor.calcularTotal(recital.getPrecioEntrada(), cantidadEntradas, cuotas);
                            }
                            System.out.println("El importe total de la venta es: " + totalVenta);
                            System.out.println("------------------");
                            System.out.println("Confirma la venta: \n" +
                                    "1- SI\n" +
                                    "2- NO\n" +
                                    "Seleccione una opción:");
                            int eleccion = sc.nextInt();

                            if(eleccion == 1){
                                gestor.altaVenta(nroRecital, comprador, cantidadEntradas, cuotas, totalVenta);
                                gestor.sumarEntradas(cantidadEntradas, recital);
                            }
                            else
                            {
                                System.out.println("Venta Cancelada");
                            }
                        }
                        else
                        {
                            if(recital.getOcupacion() == recital.getCapacidadLocal())
                                System.out.println("RECITAL COMPLETO, NO HAY MAS ENTRADAS");
                            if(recital.getOcupacion() < recital.getCapacidadLocal()){
                                System.out.println("ENTRADAS INSUFICIENTES");
                                System.out.println("QUEDAN: " + (recital.getCapacidadLocal() - recital.getOcupacion()) + " ENTRADAS DISPONIBLES");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("RECITAL CANCELADO, NO HAY VENTA DE ENTRADAS");
                    }
                    break;
                case 3:
                    //CANCELAR RECITAL
                    System.out.println("Ingrese recital a cancelar:");
                    int recitalCancelar = sc.nextInt();
                    Recital buscado = gestor.buscarRecital(recitalCancelar);
                    int totalDevolver;
                    totalDevolver = gestor.calcularTotalDevolver(buscado.getPrecioEntrada(), buscado.getOcupacion());
                    System.out.println("El total a devolver es: " + " $" + totalDevolver);
                    gestor.cancelarRecital(buscado);
                    break;
                case 4:
                    //LISTADO COMPLETO DE RECITALES
                    System.out.println("Listado de Recitales");
                    gestor.listarRecitales();
                    break;
                case 5:
                    //LISTADO DE RECITALES DE MAYOR CONVOCATORIA
                    System.out.println("Listado de recitales de mayor convocatoria");
                    gestor.mayorConvocatoria();
                    break;
                case 6:
                    //TOTAL DE RECAUDACION POR MES
                    System.out.println("Recaudación por mes");
                    System.out.println(gestor.recaudacionPorMes());
                    break;
                case 7:
                    //MEJOR COMPRADOR
                    System.out.println("Mejor Venta");
                    System.out.println(gestor.mejorVenta() + gestor.recitalMayorVenta());
                    break;
                case 8:
                    //CARTELERA. Sin los recitales cancelados
                    System.out.println("CARTELERA");
                    System.out.println(gestor.recitalesCompletos());
                    break;
                case 9:
                    salir = true;
            }
        }
        em.close();
        emf.close();
    }
}
