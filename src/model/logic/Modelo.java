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
import model.data_structures.MergeSortCodigo2;
import model.data_structures.MergeSortCodigo3;
import model.data_structures.MergeSortLocalidad;
import model.data_structures.LinkedList;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	public static final String SHELLSORT = "ShellSort";
	public static final String QUICKSORT = "QuickSort";
	public static final String MERGESORT = "MergeSort";
	public static String PATH = "./data/comparendos_dei_2018.geojson";
	public int contador123 = 0;
	public int con3a = 0;
	public int con3a2 = 0;
	public int con4a = 0;
	public int con4a2 = 0;
	public int contador1234 = 0;
	public int tamaño = 0;
	public int tamaño2 = 0;
	public int jcon = 0;
	public int jcon2 = 0;
	public int jcon3a = 0;
	public int jcon3a2 = 0;
	public int jcon4a = 0;
	public int jcon4a2 = 0;
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






	public String[] TerceroA( ArregloDinamico<Comparendo> parreglo, String pFecha, String pFecha2)
	{
		ArregloDinamico<Comparendo> rta = new ArregloDinamico<Comparendo>(550000);
		ArregloDinamico<Comparendo> rta2 = new ArregloDinamico<Comparendo>(550000);

		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
			if(parreglo.get(i).getFechaHora().equals(pFecha))
			{
				rta.asignar(con3a, parreglo.get(i));
				con3a++;
			}
		}
		
		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
		if(parreglo.get(i).getFechaHora().equals(pFecha2))
		{
			rta2.asignar(con3a2, parreglo.get(i));
			con3a2++;
		}
		}
		Comparendo[] comparendos = new Comparendo[con3a];
		Comparendo[] comparendos2 = new Comparendo[con3a2];

		for(int i =0 ;i< con3a ; i++ )
		{
			comparendos[i] = rta.get(i);
		}
		for(int i =0 ;i< con3a2 ; i++ )
		{
			comparendos2[i] = rta2.get(i);
		}

		MergeSortCodigo3.sort(comparendos);
		MergeSortCodigo3.sort(comparendos2);
		
		String[] comparendosindividuales = new String[con3a-1];	
		String[] comparendosindividuales2 = new String[con3a2-1];	
		

		// 1
		for(int i =0 ;i< con3a-1; i++ )
		{
			comparendosindividuales[0] = comparendos[0].getInfraccion().toString();
			boolean palabra1 = (comparendos[i].getInfraccion().toString()).equals(comparendos[i+1].getInfraccion().toString());	
			if(palabra1 == false)
			{

				comparendosindividuales[jcon3a+1] = comparendos[i+1].getInfraccion().toString();
				jcon3a ++;
			}
		}	

		Integer[] contadores1 =new Integer[jcon3a];
		int contar1 = 0;
		for(int i =0 ;i< jcon3a; i++ )
		{
			contar1 =0;
			for(int j =0 ;j< con3a; j++ )
			{
				if((comparendos[j].getInfraccion().toString()).equals(comparendosindividuales[i]))
				{
					contar1 ++;
				}
			}
			contadores1[i] = contar1;
		}
		
		String[] a = new String[jcon3a];
		for(int i = 0; i < jcon3a  ;i++)
		{

			String str = comparendosindividuales[i]+" \t |"+ contadores1[i];
			a[i] = str;
	}
		// 2
		for(int i =0 ;i< con3a2-1; i++ )
		{
			comparendosindividuales2[0] = comparendos2[0].getInfraccion().toString();
			boolean palabra2 = (comparendos2[i].getInfraccion().toString()).equals(comparendos2[i+1].getInfraccion().toString());	
			if(palabra2 == false)
			{

				comparendosindividuales2[jcon3a2+1] = comparendos2[i+1].getInfraccion().toString();
				jcon3a2 ++;
			}
		}	

		Integer[] contadores2 =new Integer[jcon3a2];
		int contar2 = 0;
		for(int i =0 ;i< jcon3a2; i++ )
		{
			contar2 =0;
			for(int j =0 ;j< con3a2; j++ )
			{
				if((comparendos2[j].getInfraccion().toString()).equals(comparendosindividuales2[i]))
				{
					contar2 ++;
				}
			}
			contadores2[i] = contar2;
		}
		
		String[] a2 = new String[jcon3a2];
		for(int i = 0; i < jcon3a2  ;i++)
		{

			String str1 = comparendosindividuales2[i]+" \t |"+ contadores2[i];
			a2[i] = str1;
	}

		String[] abc = new String[250000];
		int contador1 = 0;
		int contador2 = 0;
		int indi1 = 0;
		int indi2 = 0;
		
		for(int i = 0; i < (jcon3a) ;i++)
		{
			if(comparendosindividuales[indi1].equals(comparendosindividuales2[indi2]))
			{
				String str12 = comparendosindividuales[indi1]+" \t |"+ contadores1[contador1]+" \t |"+ contadores2[contador2];
				abc[i] = str12;
				indi1++;
				indi2++;
				contador1++;
				contador2++;
			}
			
			else
			{
				if(comparendosindividuales[indi1].compareTo(comparendosindividuales2[indi2])<0)
				{
					String str13 = comparendosindividuales[indi1]+" \t |"+ contadores1[contador1]+" \t |"+ "0";
					abc[i] = str13;
					indi1++;
					contador1++;
				}
				else
				{
					String str14 = comparendosindividuales2[indi2]+" \t |"+ "0"+" \t |"+ contadores2[contador2];
					abc[i] = str14;
					indi2++;
					contador2++;
				}
			}

		}
		return abc;
	}

	public String[] TerceroB( ArregloDinamico<Comparendo> parreglo)
	{
		ArregloDinamico<Comparendo> rta = new ArregloDinamico<Comparendo>(550000);
		ArregloDinamico<Comparendo> rta2 = new ArregloDinamico<Comparendo>(550000);

		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
			if(parreglo.get(i).getTipoServi().equals(parreglo.get(1).getTipoServi()))
			{
				rta.asignar(con3a, parreglo.get(i));
				con3a++;
			}
		}
		
		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
		if(parreglo.get(i).getTipoServi().equals(parreglo.get(0).getTipoServi()))
		{
			rta2.asignar(con3a2, parreglo.get(i));
			con3a2++;
		}
		}
		Comparendo[] comparendos = new Comparendo[con3a];
		Comparendo[] comparendos2 = new Comparendo[con3a2];

		for(int i =0 ;i< con3a ; i++ )
		{
			comparendos[i] = rta.get(i);
		}
		for(int i =0 ;i< con3a2 ; i++ )
		{
			comparendos2[i] = rta2.get(i);
		}

		MergeSortCodigo3.sort(comparendos);
		MergeSortCodigo3.sort(comparendos2);
		
		String[] comparendosindividuales = new String[con3a-1];	
		String[] comparendosindividuales2 = new String[con3a2-1];	
		

		// 1
		for(int i =0 ;i< con3a-1; i++ )
		{
			comparendosindividuales[0] = comparendos[0].getInfraccion().toString();
			boolean palabra1 = (comparendos[i].getInfraccion().toString()).equals(comparendos[i+1].getInfraccion().toString());	
			if(palabra1 == false)
			{

				comparendosindividuales[jcon3a+1] = comparendos[i+1].getInfraccion().toString();
				jcon3a ++;
			}
		}	

		Integer[] contadores1 =new Integer[jcon3a];
		int contar1 = 0;
		for(int i =0 ;i< jcon3a; i++ )
		{
			contar1 =0;
			for(int j =0 ;j< con3a; j++ )
			{
				if((comparendos[j].getInfraccion().toString()).equals(comparendosindividuales[i]))
				{
					contar1 ++;
				}
			}
			contadores1[i] = contar1;
		}
		
		String[] a = new String[jcon3a];
		for(int i = 0; i < jcon3a  ;i++)
		{

			String str = comparendosindividuales[i]+" \t |"+ contadores1[i];
			a[i] = str;
	}
		// 2
		for(int i =0 ;i< con3a2-1; i++ )
		{
			comparendosindividuales2[0] = comparendos2[0].getInfraccion().toString();
			boolean palabra2 = (comparendos2[i].getInfraccion().toString()).equals(comparendos2[i+1].getInfraccion().toString());	
			if(palabra2 == false)
			{

				comparendosindividuales2[jcon3a2+1] = comparendos2[i+1].getInfraccion().toString();
				jcon3a2 ++;
			}
		}	

		Integer[] contadores2 =new Integer[jcon3a2];
		int contar2 = 0;
		for(int i =0 ;i< jcon3a2; i++ )
		{
			contar2 =0;
			for(int j =0 ;j< con3a2; j++ )
			{
				if((comparendos2[j].getInfraccion().toString()).equals(comparendosindividuales2[i]))
				{
					contar2 ++;
				}
			}
			contadores2[i] = contar2;
		}
		
		String[] a2 = new String[jcon3a2];
		for(int i = 0; i < jcon3a2  ;i++)
		{

			String str1 = comparendosindividuales2[i]+" \t |"+ contadores2[i];
			a2[i] = str1;
	}

		String[] abc = new String[250000];
		int contador1 = 0;
		int contador2 = 0;
		int indi1 = 0;
		int indi2 = 0;
		
		for(int i = 0; i < (jcon3a) ;i++)
		{
			if(comparendosindividuales[indi1].equals(comparendosindividuales2[indi2]))
			{
				String str12 = comparendosindividuales[indi1]+" \t |"+ contadores1[contador1]+" \t |"+ contadores2[contador2];
				abc[i] = str12;
				indi1++;
				indi2++;
				contador1++;
				contador2++;
			}
			
			else
			{
				if(comparendosindividuales[indi1].compareTo(comparendosindividuales2[indi2])<0)
				{
					String str13 = comparendosindividuales[indi1]+" \t |"+ contadores1[contador1]+" \t |"+ "0";
					abc[i] = str13;
					indi1++;
					contador1++;
				}
				else
				{
					String str14 = comparendosindividuales2[indi2]+" \t |"+ "0"+" \t |"+ contadores2[contador2];
					abc[i] = str14;
					indi2++;
					contador2++;
				}
			}

		}
		return abc;
	}
	
	
	public String[] Cprimero( ArregloDinamico<Comparendo> parreglo, String fecha1, String fecha2, String localidad)
	{
		String[] dates1 = fecha1.split("/");
		String[] dates2 = fecha2.split("/");

		Date date1 = new Date(Integer.parseInt(dates1[0]),Integer.parseInt(dates1[1]),Integer.parseInt(dates1[2]));
		Date date2 = new Date(Integer.parseInt(dates2[0]),Integer.parseInt(dates2[1]),Integer.parseInt(dates2[2]));
		ArregloDinamico<Comparendo> rta2 = new ArregloDinamico<Comparendo>(550000);


		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
			if((parreglo.get(i).getFechaDate().after(date1) || parreglo.get(i).getFechaDate().equals(date1)) &&
					(parreglo.get(i).getFechaDate().before(date2) || parreglo.get(i).getFechaDate().equals(date2)) &&
					parreglo.get(i).getLocalidad().equals(localidad)	)
			{

				rta2.asignar(tamaño, parreglo.get(i));
				tamaño++;
			}
		}
		String[] comparendosindividuales = new String[tamaño-1];	
		Comparendo[] comparendos2 = new Comparendo[tamaño];
		for(int i =0 ;i< tamaño ; i++ )
		{
			comparendos2[i] = rta2.get(i);
		}

		MergeSortCodigo.sort(comparendos2);

		for(int i =0 ;i< tamaño-1; i++ )
		{
			comparendosindividuales[0] = comparendos2[0].getInfraccion().toString();
			boolean palabra = (comparendos2[i].getInfraccion().toString()).equals(comparendos2[i+1].getInfraccion().toString());	
			if(palabra == false)
			{

				comparendosindividuales[jcon+1] = comparendos2[i+1].getInfraccion().toString();
				jcon ++;
			}
		}	

		Integer[] contadores =new Integer[jcon];
		int contar = 0;
		for(int i =0 ;i< jcon; i++ )
		{
			contar =0;
			for(int j =0 ;j< tamaño; j++ )
			{
				if((comparendos2[j].getInfraccion().toString()).equals(comparendosindividuales[i]))
				{
					contar ++;
				}
			}
			contadores[i] = contar;
		}
		String[] a = new String[jcon];
		for(int i = 0; i < jcon  ;i++)
		{

			String str = comparendosindividuales[i]+" \t |"+ contadores[i];
			a[i] = str;
		}
		return a;
	}



	public String[] Csegundo( ArregloDinamico<Comparendo> parreglo, String fecha1, String fecha2)

	{
		String[] dates1 = fecha1.split("/");
		String[] dates2 = fecha2.split("/");

		Date date1 = new Date(Integer.parseInt(dates1[0]),Integer.parseInt(dates1[1]),Integer.parseInt(dates1[2]));
		Date date2 = new Date(Integer.parseInt(dates2[0]),Integer.parseInt(dates2[1]),Integer.parseInt(dates2[2]));
		ArregloDinamico<Comparendo> rta5 = new ArregloDinamico<Comparendo>(550000);


		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{

			if((parreglo.get(i).getFechaDate().after(date1) || parreglo.get(i).getFechaDate().equals(date1)) &&
					(parreglo.get(i).getFechaDate().before(date2) || parreglo.get(i).getFechaDate().equals(date2)) 	)
			{

				rta5.asignar(tamaño2, parreglo.get(i));
				tamaño2++;
			}
		}
		String[] comparendosindividuales2 = new String[tamaño2-1];	
		Comparendo[] comparendos3 = new Comparendo[tamaño2];
		for(int i =0 ;i< tamaño2 ; i++ )
		{
			comparendos3[i] = rta5.get(i);
		}
		MergeSortCodigo.sort(comparendos3);
		for(int i =0 ;i< tamaño2-1; i++ )
		{
			comparendosindividuales2[0] = comparendos3[0].getInfraccion().toString();
			boolean palabra = (comparendos3[i].getInfraccion().toString()).equals(comparendos3[i+1].getInfraccion().toString());	
			if(palabra == false)
			{

				comparendosindividuales2[jcon2+1] = comparendos3[i+1].getInfraccion().toString();
				jcon2 ++;
			}
		}	
		Integer[] contadores2 =new Integer[jcon2];
		int contar = 0;
		for(int i =0 ;i< jcon2; i++ )
		{
			contar =0;
			for(int j =0 ;j< tamaño2; j++ )
			{
				if((comparendos3[j].getInfraccion().toString()).equals(comparendosindividuales2[i]))
				{
					contar ++;
				}
			}
			contadores2[i] = contar;
		}

		String[] a = new String[jcon2];
		Comparendo[] nuevo=new Comparendo[jcon2];
		for(int i = 0; i < jcon2  ;i++)
		{

			Comparendo x1 = new Comparendo(0 , comparendosindividuales2[i], "", "", "", "", (contadores2[i]).toString(), "", 0, 0);
			nuevo[i] = x1;
		}
		MergeSortCodigo2.sort(nuevo);

		String[] a8 = new String[jcon2];
		for(int i = 0; i < jcon2  ;i++)
		{

			String str2 = nuevo[i].getFechaHora()   +" \t |"+ nuevo[i].getInfraccion();
			a8[i] = str2;
		}
		return a8;
	}
	
	
	
	public String[] Ctercero( ArregloDinamico<Comparendo> parreglo)

	{
		
		String[] comparendosindividuales2 = new String[550000];	
		Comparendo[] comparendos3 = new Comparendo[parreglo.darCapacidad()];
		for(int i =0 ;i< parreglo.darCapacidad() ; i++ )
		{
			comparendos3[i] = parreglo.get(i);
		}
		MergeSortLocalidad.sort(comparendos3);
		for(int i =0 ;i< parreglo.darCapacidad()-1; i++ )
		{
			comparendosindividuales2[0] = comparendos3[0].getLocalidad().toString();
			boolean palabra = (comparendos3[i].getLocalidad().toString()).equals(comparendos3[i+1].getLocalidad().toString());	
			if(palabra == false)
			{

				comparendosindividuales2[jcon2+1] = comparendos3[i+1].getLocalidad().toString();
				jcon2 ++;
			}
		}	
		Integer[] contadores2 =new Integer[jcon2];
		int contar = 0;
		for(int i =0 ;i< jcon2; i++ )
		{
			contar =0;
			for(int j =0 ;j< parreglo.darCapacidad(); j++ )
			{
				if((comparendos3[j].getLocalidad().toString()).equals(comparendosindividuales2[i]))
				{
					contar ++;
				}
			}
			contadores2[i] = contar;
		}


		String[] a8 = new String[jcon2];
		for(int i = 0; i < jcon2  ;i++)
		{

			String str2 = comparendosindividuales2[i]   +" \t |"+ (contadores2[i]).toString();
			
			a8[i] = str2;
		}
		return a8;
	}
}
	