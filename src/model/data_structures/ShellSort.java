package model.data_structures;

import model.Comparendo;


public class ShellSort
{

	ArregloDinamico<Comparendo> arreglo;
public ShellSort(ArregloDinamico<Comparendo> pArreglo)
{
	this.arreglo = pArreglo;
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
}