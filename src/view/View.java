package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Datos");
			System.out.println("2. primer comparendo con tal localidad");
			System.out.println("3. comparendos por fecha");
			System.out.println("4. 3a");
			System.out.println("5. primer comparendo con tal infraccion");
			System.out.println("6. comparendos por codigo");
			System.out.println("7. 3b");
			System.out.println("8. comparendos por localidad y fecha con tabla de infraccion");
			System.out.println("9. los comparendos con mas infracciones en un tiempo dado");
			System.out.println("10. 3c");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
