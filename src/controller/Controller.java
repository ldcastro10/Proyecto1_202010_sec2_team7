package controller;


import java.util.Scanner;

import model.Comparendo;
import model.data_structures.ILinkedList;
import model.data_structures.LinkedList;
import model.data_structures.Node;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;
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
		
		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				modelo = new Modelo();
				
				long start = System.currentTimeMillis();
				String respuestaModelo = modelo.cargarlinkedList();
			    long end = System.currentTimeMillis();
			    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0);
				view.printMessage("tamaño: " + respuestaModelo.split(";")[0] + "\n");
				view.printMessage("el de mayor id es " + respuestaModelo.split(";")[1]);
				view.printMessage("El punto minimo es "  + respuestaModelo.split(";")[2] +"," + respuestaModelo.split(";")[3] + " y el punto máximo es " + respuestaModelo.split(";")[4]+ "," + respuestaModelo.split(";")[5] + "\n");

				break;

			case 2:
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
