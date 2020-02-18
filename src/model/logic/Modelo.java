package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;
import model.data_structures.ILinkedList;
import model.data_structures.Node;
import model.data_structures.LinkedList;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	
	public static String PATH = "./data/comparendos_dei_2018_small.geojson";
//	public static String PATH = "./data/comparendos_dei_2018.geojson";
	private ILinkedList<Comparendo> linkedList;
	
	public String cargarlinkedList() {
		
		
		double latMax = -99999999;
		double latMin = 99999999;
		double longMax = -99999999;
		double longMin = 99999999;
		int idmax = 0;
		Comparendo rta = null; 
		int tamaño = 0;
		linkedList = new LinkedList<Comparendo>();
		//TODO Cambiar la clase del contenedor de linkedList por la Estructura de linkedList propia adecuada para resolver el requerimiento 
	

		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			
			
			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				
				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 
				
				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();
				
				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);
				Node<Comparendo> node = new Node<Comparendo>(c);
				if(c.getid() > idmax )
	            {
					idmax = c.getid();
	            	rta = c;
	            
	            }
				if(c.getLatitud() > latMax )
	            {
	            	latMax = c.getLatitud();
	            	
	            }
				if(c.getLatitud() < latMin )
	            {
					latMin = c.getLatitud();
	            
	            }
				if(c.getLongitud() > longMax )
	            {
					longMax = c.getLongitud();
	            	
	            }
				if(c.getLongitud() < longMin )
	            {
					longMin = c.getLongitud();
	            
	            }
//			
				linkedList.append(node);
				
				
				
			}
		tamaño = linkedList.getSize();

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return   "" + tamaño + ";" + rta + ";" + latMin + ";" + longMin + ";" + latMax + ";" + longMax;

	}
	
}
