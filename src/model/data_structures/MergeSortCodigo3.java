package model.data_structures;

import model.Comparendo;

public class MergeSortCodigo3 {
	private static Comparendo[] aux;

	public static void sort(Comparendo[] a)
	{
		aux = new Comparendo[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static void sort(Comparendo[] a,Comparendo aux[], int lo, int hi)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a,aux, lo, mid);
		sort(a,aux, mid+1, hi);
		merge(a,aux, lo, mid, hi); 
	}
	
	public static void merge(Comparendo[] a,Comparendo[] aux, int lo, int mid, int hi)
	{
		int i = lo;
		int j = mid+1;
		for(int x = lo;x <= hi; x++)
		{
			aux[x] = a[x];
		}
		
		for(int x = lo;x <= hi; x++)
		{
			if(i > mid)
			{
				a[x] = aux[j++];
			}
			else if(j > hi)
			{
				a[x] = aux[i++];
			}
			else if(less(aux[j],aux[i]))
			{
				a[x] = aux[j++];
			}
			else
			{
				a[x] = aux[i++];
			}
		}
		
	}
	private static boolean less(Comparendo x, Comparendo y) 
	{	
		return x.getInfraccion().compareTo(y.getInfraccion())<0;
	}

}