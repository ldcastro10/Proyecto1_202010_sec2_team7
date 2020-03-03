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
				view.printMessage("Ultimo dato: " + consulta.darCapacidad() + "\n");

	

		//		view.printMessage("Ultimo dato: " + f.toString() + "\n");
				view.printMessage("Ultimo dato: " + consulta.get(1).getFechaDate() + "\n");

				Date date1 = new Date(2008,3,2);
				Date date2 = new Date(2009,01,15);
				
				System.out.println(date2.before(date1));

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
			case 5: 
				System.out.println("5. primer comparendo con tal infraccion");
				System.out.println("Digite la infraccion:");
				String pinfraccion1 = lector.next();
				view.printMessage("rta es: " + modelo.primeroB(pinfraccion1,consulta ));
				break;
			case 6: 
				System.out.println("5. comparendos por codigo");
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

			}
		}

	}	
}
