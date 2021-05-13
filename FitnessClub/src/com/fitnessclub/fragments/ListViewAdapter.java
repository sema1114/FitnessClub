package com.fitnessclub.fragments;

import com.gelecegiyazanlar.navigationdrawer.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	
	// Declare Variables
	Context context;
	String[] adi;
	String[] adimsayisi;
	String[] kalori;
	String[] tarih;
	int[] kisi_icon_int;
	LayoutInflater inflater;

	//ListviewAdapter constructor
	//Gelen degerleri set ediyor
	public ListViewAdapter(Context context, String[] adi, String[] adimsayisi, String[] kalori, String[] tarih, int[] kisi_icon_int) {
		this.context = context;
		this.adi = adi;
		this.adimsayisi = adimsayisi;
		this.kalori = kalori;
		this.tarih = tarih;
		this.kisi_icon_int = kisi_icon_int;
	}

	@Override
	public int getCount() {
		return adi.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		// Declare Variables
		TextView adi_textview;
		TextView adimsayisi_textview;
		TextView kalori_textview;
		TextView tarih_textview;
		ImageView kisi_icon_imageView;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
		View itemView = inflater.inflate(R.layout.list_item_row, parent, false);//list_item_row dan yeni bir view oluï¿½turuyoruz
		
		// oluï¿½an itemviewin iï¿½indeki alanlarï¿½ Anasayfadan gelen deï¿½erler ile set ediyoruz
		adi_textview = (TextView) itemView.findViewById(R.id.adi);
		adimsayisi_textview = (TextView) itemView.findViewById(R.id.adimsayisi);
		kalori_textview = (TextView) itemView.findViewById(R.id.kalori);
		tarih_textview = (TextView) itemView.findViewById(R.id.tarih);
		kisi_icon_imageView = (ImageView) itemView.findViewById(R.id.kisi_icon);

		adi_textview.setText(adi[position]);
		adimsayisi_textview.setText("Adým Sayýsý :"+adimsayisi[position]);
		kalori_textview.setText("Kalori :"+kalori[position]);
		tarih_textview.setText("Tarih :"+tarih[position]);
		kisi_icon_imageView.setImageResource(kisi_icon_int[position]);
		return itemView;
	}
}
