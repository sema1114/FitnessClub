package com.fitnessclub.fragments;
import com.gelecegiyazanlar.navigationdrawer.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

public class FragmentArkadaslar extends Fragment {
	ListView list; // ListView referansý
	ListViewAdapter adapter; // listview türünden veri tutulyor.
	private String[] adi;// kisi adlarýný tutacak array
	private String[] adimsayisi;// adým sayýlarýný tutacak
	private String[] kalori;// kalori degerlerini tutacak array
	private String[] tarih;// tarih degerlerini tutacak array
	private TypedArray kisi_icon;// iconlarý tutacak array
	private int[] kisi_icon_int;// iconlarýn id lerini tutacak array

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.layout_arkadaslarfragment,
				container, false);
		adi = getResources().getStringArray(R.array.adi_array); 
		adimsayisi = getResources().getStringArray(R.array.adimsayisi_array);
		kalori = getResources().getStringArray(R.array.kalori_array);
		tarih = getResources().getStringArray(R.array.tarih_array);
		kisi_icon = getResources().obtainTypedArray(R.array.icon_arrayy);
		kisi_icon_int = new int[kisi_icon.length()];
		for (int i = 0; i < kisi_icon.length(); i++) {
			kisi_icon_int[i] = kisi_icon.getResourceId(i, -1);
		}
		kisi_icon.recycle();
		list = (ListView) view.findViewById(R.id.kisiList);
		adapter = new ListViewAdapter(getActivity(), adi, adimsayisi, kalori,
				tarih, kisi_icon_int);
		
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				/*
				 * Intent i = new Intent(getActivity(), KisiDetay.class);
				 * 
				 * i.putExtra("Kullanýcý Adý:", adi[position]);
				 * startActivity(i);
				 */
				Toast.makeText(getActivity(), "Henüz Aktif Deðildir",
						Toast.LENGTH_SHORT).show();
			}

		});
		/*
		 * list.setOnItemLongClickListener(new OnItemLongClickListener() {
		 * 
		 * public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int
		 * pos, long id) { // TODO Auto-generated method stub
		 * 
		 * AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 * builder.setCancelable(true); builder.setTitle("Uyarý!");
		 * builder.setInverseBackgroundForced(true);
		 * builder.setPositiveButton("Tamam", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } });
		 * 
		 * AlertDialog alert = builder.create(); alert.show(); return true; }
		 * });
		 * 
		 * }
		 */
		return view;
	}
}
