package com.fitnessclub.fragments;

public class IndeksHesaplama {

	public float bkiHesapla(float kilo,float boy){
		float bki=kilo/(boy*boy);
		float yuvarla=(int)Math.ceil(bki);
		return yuvarla;
	}
	public float idealkilo(float boy){
		float idealkilo=(float) (45.8+2.3*((boy/0.0254)-60));
		return idealkilo;
	}
	public float vucutYuzeyAlani(float kilo,float boy){
		float vucutYuzeyAlani=(float) (0.20247*Math.pow(boy, 0.725)*Math.pow(kilo, 0.425));
		return vucutYuzeyAlani;
	}
	public float yagsizvucut(float kilo,float boy){
		float yagsizvucut=(float) ((1.09*kilo)-128*(Math.pow(kilo, 2)/Math.pow((100*boy), 2)));	
		return yagsizvucut;
	}
	public float toplamsu(float kilo,float boy){
		float toplamsu=(float) (3+0.3*kilo+0.1*(boy*100));
		return toplamsu;
	}
	
	public float fazlakiloHesaplama(float kilo,float idealkilo){
		float fazlakilo=(float)(kilo - idealkilo);
		return fazlakilo;
	}
	
	
}
