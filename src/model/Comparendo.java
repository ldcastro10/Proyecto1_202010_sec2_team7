package model;


import java.util.Date;
import java.text.SimpleDateFormat;


public class Comparendo implements Comparable<Comparendo> {
	private int objectId;
	private String fecha_hora;
	private String des_infrac;
	private String medio_dete;
	private String clase_vehi;
	private String tipo_servi;
	private String infraccion;
	private String localidad;

	private double latitud;
	private double longitud;
	
	public Comparendo(int objeId, String fecha, String descripcion, String detencion, String claseVeh, String tipoSer, String codInfraccion, String localidadP, double lonP, double latP)
	{
		objectId = objeId;
		fecha_hora = fecha;
		des_infrac = descripcion;
		medio_dete = detencion;
		clase_vehi = claseVeh;
		tipo_servi = tipoSer;
		infraccion = codInfraccion;
		localidad = localidadP;
		longitud = lonP;
		latitud = latP;
	}
	
public int compareTo(Comparendo parametro) {
		
	
		
		Date fecha1 = getFechaDate();
		Date fecha2 = parametro.getFechaDate();
		int object1 = parametro.getid();
		if(fecha1.after(fecha2)) 
			{
			return 1;
			}
		else if(fecha1.before(fecha2))
			{
			return -1;
			}
		else if(fecha2.equals(fecha1))
		{
			if(objectId > object1)
			{
				return 1;
			}
			else if(objectId < object1)
			{
				return -1;
			}
		}
		return 0;
	}

	
	
	
	@Override
	public String toString() {
		return "Comparendo [OBJECTID=" + objectId + ", FECHA_HORA=" + fecha_hora + ", DES_INFRAC=" + des_infrac
				+ ", MEDIO_DETE=" + medio_dete + ", CLASE_VEHI=" + clase_vehi + ", TIPO_SERVI=" + tipo_servi
				+ ", INFRACCION=" + infraccion + ", LOCALIDAD=" + localidad + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}
	public int getid()
	{
		return objectId;
	}
	public double getLatitud()
	{
		return latitud;
	}
	public double getLongitud()
	{
		return longitud;
	}
	public String getFechaHora()
	{
		return fecha_hora;
	}
	public String getLocalidad()
	{
		return localidad;
	}
	
	
	public String getInfraccion()
	{
		return infraccion;
	}
	public String getTipoServi()
	{
		return tipo_servi;
	}
	public String getClaseVehi()
	{
		return clase_vehi;
	}
	
	public Date getFechaDate()
	{
		
		String[] numeros = fecha_hora.split("/");
		Date fecha = new Date(Integer.parseInt(numeros[0]), Integer.parseInt(numeros[1]), Integer.parseInt(numeros[2]));
		return fecha;
	}

	
}
