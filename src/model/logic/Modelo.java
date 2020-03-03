package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;
import model.data_structures.ILinkedList;
import model.data_structures.IArregloDinamico;
import model.data_structures.ArregloDinamico;
import model.data_structures.Node;

import model.data_structures.ShellSort;
import model.data_structures.QuickSort;
import model.data_structures.MergeSort;
import model.data_structures.MergeSortCodigo;
import model.data_structures.LinkedList;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	public static final String SHELLSORT = "ShellSort";
	public static final String QUICKSORT = "QuickSort";
	public static final String MERGESORT = "MergeSort";
	public static String PATH = "./data/comparendos_dei_2018_small.geojson";
	public int contador123 = 0;
	public int contador1234 = 0;
	//	public static String PATH = "./data/comparendos_dei_2018.geojson";
	public ILinkedList<Comparendo> linkedList;
	public IArregloDinamico<Comparendo> arreglo;
	public Comparendo[] lista=null;
	public ArregloDinamico<Comparendo> cargarlinkedList() {



		

		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			arreglo=new ArregloDinamico<Comparendo>(e2.size());
				

			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");
			int i=0;
			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				String FECHA_HORA = s; 

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
				
				arreglo.asignar(i, c);
				i++;

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return (ArregloDinamico<Comparendo>) arreglo;	

	}
	//public  Comparendo[] dearreglo()
	//{
		
//		Comparendo[] array1 = cargarlinkedList();
	//	Comparendo[] array = new Comparendo[lista.length];
	//	for(int i =0 ;i< array1.length; i++ )
	//	{
		//	array[i] =  array1[i];
			
	//	}
	//	return array;
	//}

	public Comparendo primeroA(String pLocalidad, ArregloDinamico<Comparendo> parreglo)
	{
	
		Boolean a  = false;
		Comparendo actual = null;
		for(int i =0 ;i< parreglo.darCapacidad() && a==false; i++ )
		{
			if(parreglo.get(i).getLocalidad().equals(pLocalidad))
			{
				a = true;
				actual = parreglo.get(i);
			}
		}
		return actual;
	}
	public Comparendo primeroB(String pInfraccion, ArregloDinamico<Comparendo> parreglo)
	{
	
		Boolean a  = false;
		Comparendo actual = null;
		for(int i =0 ;i< parreglo.darCapacidad() && a==false; i++ )
		{
			if(parreglo.get(i).getInfraccion().equals(pInfraccion))
			{
				a = true;
				actual = parreglo.get(i);
			}
		}
		return actual;
	}
	public void ShellSortf(ArregloDinamico<Comparendo> pArreglo)
	{
		int t = pArreglo.tamaño();
		int primero = 1;
		while (primero <t/3)
		{
			primero = 3*primero +1;
		}
		while (primero >= 1)
		{
			for(int i = primero; i <t; i++)
			{
				for(int j = i; j>=primero && pArreglo.get(j).compareTo(pArreglo.get(j-primero)) < 0; j-=primero)
				{
					
				}
			}
			primero = primero/3;
		}
	}
	
	public Comparendo[] segundoA(String pFecha, ArregloDinamico<Comparendo> parreglo)
	{

		ArregloDinamico<Comparendo> rta = new ArregloDinamico<Comparendo>(100000);
		Comparendo actual = null;
		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
			if(parreglo.get(i).getFechaHora().equals(pFecha))
			{
				rta.asignar(contador123, parreglo.get(i));
				contador123++;
			}
		}
        Comparendo[] comparendos = new Comparendo[contador123];
		
		for(int i =0 ;i< contador123 ; i++ )
		{
			comparendos[i] = rta.get(i);
		}
		
		MergeSortCodigo.sort(comparendos);
		return comparendos;
	
	}
	public Comparendo[] segundoB(String pCodigo, ArregloDinamico<Comparendo> parreglo)
	{

		ArregloDinamico<Comparendo> rta2 = new ArregloDinamico<Comparendo>(100000);
		Comparendo actual = null;
		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
			if(parreglo.get(i).getInfraccion().equals(pCodigo))
			{
				rta2.asignar(contador1234, parreglo.get(i));
				contador1234++;
			}
		}

		Comparendo[] comparendos = new Comparendo[contador1234];
		
		for(int i =0 ;i< contador1234 ; i++ )
		{
			comparendos[i] = rta2.get(i);
		}
		
		MergeSort.sort(comparendos);
		return comparendos;
	
	}

}
