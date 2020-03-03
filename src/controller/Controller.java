package controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import model.Comparendo;
import model.data_structures.ILinkedList;
import model.data_structures.LinkedList;
import model.data_structures.Node;
import model.data_structures.IArregloDinamico;
import model.data_structures.ArregloDinamico;
import model.logic.Modelo;
import view.View;
import java.text.SimpleDateFormat;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	private ArregloDinamico<Comparendo> consulta;
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();


	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				modelo = new Modelo();

				long start = System.currentTimeMillis();
				consulta= modelo.cargarlinkedList();
				long end = System.currentTimeMillis();


				view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0);
				view.printMessage("Datos cargados: " + consulta.tamaño() + "\n");
				view.printMessage("Primer dato: " + consulta.get(0) + "\n");
				break;

			case 2: 
				System.out.println("2. primer comparendo con tal localidad");
				System.out.println("Digite la localidad:");
				String plocalidad1 = lector.next();
				view.printMessage("rta es: " + modelo.primeroA(plocalidad1,consulta ));

				break;

			case 3: 
				System.out.println("3. comparendos por fecha");
				System.out.println("Digite la fecha:");
				String pfecha = lector.next();
				Comparendo[] rta = modelo.segundoA(pfecha, consulta);

				System.out.println("estos son:");

				for(int i = 0; i < modelo.contador123 ;i++)
				{
					System.out.println("------------------------------------------------------------------------------------------------------------");
					System.out.println(rta[i].toString());
					System.out.println("------------------------------------------------------------------------------------------------------------");
				}
				break;
			case 4: 
				System.out.println("4. comparendos comparados por fecha");
				System.out.println("Digite la fecha1:");
				String pfecha11111 = lector.next();
				System.out.println("Digite la fecha2:");
				String pfecha22222 = lector.next();
				String[] rta33 = modelo.TerceroA(consulta, pfecha11111, pfecha22222);
				System.out.println("estos son:");

				for(int i = 0; i < modelo.jcon3a;i++)
				{

					System.out.println(rta33[i]);
				}

				break;	
				
			case 5: 
				System.out.println("5. primer comparendo con tal infraccion");
				System.out.println("Digite la infraccion:");
				String pinfraccion1 = lector.next();
				view.printMessage("rta es: " + modelo.primeroB(pinfraccion1,consulta ));
				break;
			case 6: 
				System.out.println("6. comparendos por codigo");
				System.out.println("Digite el codigo:");
				String pcodigo = lector.next();
				Comparendo[] rta2 = modelo.segundoB(pcodigo, consulta);

				System.out.println("estos son:");

				for(int i = 0; i < modelo.contador1234 ;i++)
				{
					System.out.println("------------------------------------------------------------------------------------------------------------");
					System.out.println(rta2[i].toString());
					System.out.println("------------------------------------------------------------------------------------------------------------");

				}
				break;
			case 7: 
				System.out.println("4. comparendos comparados por fecha");

				String[] rta3333 = modelo.TerceroB(consulta);
				System.out.println("estos son:");

				for(int i = 0; i < modelo.jcon3a;i++)
				{

					System.out.println(rta3333[i]);
				}

				break;
			case 8: 
				System.out.println("8. comparendos por localidad y fecha con tabla de infraccion");
				System.out.println("Digite la localidad:");
				String plocalidad = lector.next();
				System.out.println("Digite la fecha1:");
				String pfecha1 = lector.next();
				System.out.println("Digite la fecha2:");
				String pfecha2 = lector.next();
				String[] rta3 = modelo.Cprimero(consulta, pfecha1, pfecha2, plocalidad);
				System.out.println("estos son:");

				for(int i = 0; i < 200000 ;i++)
				{

					System.out.println(rta3[i]);
				}

				break;
			case 9: 
				
				System.out.println("9. los comparendos con mas infracciones en un tiempo dado");
				System.out.println("Digite el numero:");
				int pnumero = lector.nextInt();
				System.out.println("Digite la fecha1:");
				String pfecha11 = lector.next();
				System.out.println("Digite la fecha2:");
				String pfecha22 = lector.next();
				String[] rta4 = modelo.Csegundo(consulta, pfecha11, pfecha22);
				System.out.println("estos son:");

				for(int i = 0; i < pnumero ;i++)
				{
					System.out.println(rta4[i]);
				}
				break;
             case 10: 
				
				System.out.println("10. localidalidades y comparendos");
				String[] rta44 = modelo.Ctercero(consulta);
				System.out.println("estos son:");

				for(int i = 0; i < 21 ;i++)
				{
					System.out.println(rta44[i]);
				}
				break;

			}
		}
	}
}