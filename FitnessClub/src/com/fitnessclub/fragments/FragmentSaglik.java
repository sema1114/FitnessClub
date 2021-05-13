package com.fitnessclub.fragments;

import java.text.DecimalFormat;

import com.fitnessclub.navigationdrawer.AsyncGetUserInformation;
import com.fitnessclub.navigationdrawer.AsyncSetUserInformation;
import com.fitnessclub.navigationdrawer.AsyncUpdateUserInformation;
import com.fitnessclub.navigationdrawer.MainActivity;
//import com.fitnessclub.navigationdrawer.KaydolActivity;
import com.gelecegiyazanlar.navigationdrawer.R;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
//import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSaglik extends Fragment {
	TextView sonuc, durum, iksonuc, vyaSonuc, yagsizsonuc, susonuc, fazlakilo,
			txtKilo, txtBoy;
	EditText editBoy, editKilo;
	Button btnHesapla, btnGuncelle;
	Float boy, kilo, vke, ik, vya, yva, tvs, fKilo;
	Integer yuvarla;
	String metin;
	IndeksHesaplama ih;
	AsyncSetUserInformation asui;
	AsyncUpdateUserInformation auui;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.layout_saglikfragment,
				container, false);
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		SharedPreferences.Editor editor = preferences.edit();
		final String userID = preferences.getString("userID", "0");
		editKilo = (EditText) view.findViewById(R.id.editKilo);
		editBoy = (EditText) view.findViewById(R.id.editBoy);
		txtBoy = (TextView) view.findViewById(R.id.txtBoy);
		txtKilo = (TextView) view.findViewById(R.id.txtKilo);
		ih = new IndeksHesaplama();
		sonuc = (TextView) view.findViewById(R.id.txt1);
		iksonuc = (TextView) view.findViewById(R.id.txt2);
		susonuc = (TextView) view.findViewById(R.id.txt3);
		vyaSonuc = (TextView) view.findViewById(R.id.txt4);
		yagsizsonuc = (TextView) view.findViewById(R.id.txt5);
		durum = (TextView) view.findViewById(R.id.txt6);
		fazlakilo = (TextView) view.findViewById(R.id.txt7);
		btnHesapla = (Button) view.findViewById(R.id.btnHesapla);
		btnGuncelle = (Button) view.findViewById(R.id.btnGuncelle);
		// Sayfa yüklendiðinde eðer veri varsa Hesapla ve Kaydet Butonu aktif
		// olmasýn sadece güncelle aktif olsun
		AsyncGetUserInformation agui = new AsyncGetUserInformation(
				getActivity(), editBoy, editKilo, sonuc, iksonuc, susonuc,
				vyaSonuc, yagsizsonuc, durum, btnHesapla, txtKilo, txtBoy);
		agui.execute(userID);
		// Guncelle iþlemleri
		btnGuncelle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (editBoy.getText().toString().equals("")
						|| editKilo.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "Boþ geçilmez",
							Toast.LENGTH_SHORT).show();
				} else {
					kilo = Float.parseFloat(editKilo.getText().toString());
					boy = Float.parseFloat(editBoy.getText().toString());
					if (boy == 0) {
						// Toast.makeText(getActivity(), "Boy 0 girilemez!",
						// Toast.LENGTH_LONG).show();
						editBoy.requestFocus();
						editBoy.setError("Boy 0 girilemez!");
						editBoy.setText("");
						editKilo.setText("");
					}

					if (kilo == 0) {
						// Toast.makeText(getActivity(), "Boy 0 girilemez!",
						// Toast.LENGTH_LONG).show();
						editKilo.requestFocus();
						editKilo.setError("Kilo 0 girilemez!");
						editKilo.setText("");
						editBoy.setText("");
					}

					else if (boy > 2.60) {
						Toast.makeText(getActivity(),
								"Boyunuzu metre cinsinde giriniz.",
								Toast.LENGTH_LONG).show();
						editBoy.setText("");
						editKilo.setText("");
					} else {

						vke = ih.bkiHesapla(kilo, boy);
						sonuc.setText("Vücut Kitle Endeksiniz:"
								+ String.valueOf(vke));
						ik = ih.idealkilo(boy);
						iksonuc.setText("Ýdeal Kilonuz: "
								+ new DecimalFormat("##.##").format(ik) + "kg");
						vya = ih.vucutYuzeyAlani(kilo, boy);
						vyaSonuc.setText("Vücut Yüzey Alanýnýz: "
								+ new DecimalFormat("##.##").format(vya) + "m2");
						yva = ih.yagsizvucut(kilo, boy);
						yagsizsonuc.setText("Yaðsýz vücut aðýrlýðýnýz: "
								+ new DecimalFormat("##.##").format(yva) + "kg");
						tvs = ih.toplamsu(kilo, boy);
						susonuc.setText("Toplam vücut suyu: "
								+ new DecimalFormat("##.##").format(tvs) + "kg");

						float fKilo = ih.fazlakiloHesaplama(kilo, ik);

						if (vke <= 18.5) {
							durum.setText("Zayýf");
							metin = durum.getText().toString();
						} else if (18.5 <= vke && vke <= 24.9) {

							durum.setText("Kilonuz normal");
							metin = durum.getText().toString();

						} else if (25 <= vke && vke <= 29.9) {
							durum.setText("Fazla kilolulusunuz fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						} else if (30 <= vke && vke <= 34.9) {
							durum.setText("1. derece Obez fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						} else if (35 <= vke && vke <= 40) {
							durum.setText("2. derece Obez fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						} else {
							durum.setText("3. derece Obez fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						}
					}
					txtBoy.setText("Boyunuz: " + boy.toString() + " m");
					txtKilo.setText("Kilonuz:" + kilo.toString() + " kg");
					auui = new AsyncUpdateUserInformation(getActivity());
					auui.execute(kilo.toString(), boy.toString(),
							vke.toString(), ik.toString(), tvs.toString(),
							vya.toString(), yva.toString(), userID.toString(),
							metin);
				}
			}
		});
		// Hesapla ve kaydet
		btnHesapla.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editBoy.getText().toString().equals("")
						|| editKilo.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "Boþ geçilmez",
							Toast.LENGTH_SHORT).show();
				} else {
					kilo = Float.parseFloat(editKilo.getText().toString());
					boy = Float.parseFloat(editBoy.getText().toString());
					if (boy == 0) {
						// Toast.makeText(getActivity(), "Boy 0 girilemez!",
						// Toast.LENGTH_LONG).show();
						editBoy.requestFocus();
						editBoy.setError("Boy 0 girilemez!");
						editBoy.setText("");
						editKilo.setText("");
					}

					if (kilo == 0) {
						// Toast.makeText(getActivity(), "Boy 0 girilemez!",
						// Toast.LENGTH_LONG).show();
						editKilo.requestFocus();
						editKilo.setError("Kilo 0 girilemez!");
						editKilo.setText("");
						editBoy.setText("");
					}

					else if (boy > 2.60) {
						Toast.makeText(getActivity(),
								"Boyunuzu metre cinsinde giriniz.",
								Toast.LENGTH_LONG).show();
						editBoy.setText("");
						editKilo.setText("");
					} else {

						vke = ih.bkiHesapla(kilo, boy);
						sonuc.setText("Vücut Kitle Endeksiniz:"
								+ String.valueOf(vke));
						ik = ih.idealkilo(boy);
						iksonuc.setText("Ýdeal Kilonuz: "
								+ new DecimalFormat("##.##").format(ik) + "kg");
						vya = ih.vucutYuzeyAlani(kilo, boy);
						vyaSonuc.setText("Vücut Yüzey Alanýnýz: "
								+ new DecimalFormat("##.##").format(vya) + "m2");
						yva = ih.yagsizvucut(kilo, boy);
						yagsizsonuc.setText("Yaðsýz vücut aðýrlýðýnýz: "
								+ new DecimalFormat("##.##").format(yva) + "kg");
						tvs = ih.toplamsu(kilo, boy);
						susonuc.setText("Toplam vücut suyu: "
								+ new DecimalFormat("##.##").format(tvs) + "kg");

						float fKilo = ih.fazlakiloHesaplama(kilo, ik);

						if (vke <= 18.5) {
							durum.setText("Zayýf");
							metin = durum.getText().toString();
						} else if (18.5 <= vke && vke <= 24.9) {

							durum.setText("Kilonuz normal");
							metin = durum.getText().toString();

						} else if (25 <= vke && vke <= 29.9) {
							durum.setText("Fazla kilolulusunuz fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						} else if (30 <= vke && vke <= 34.9) {
							durum.setText("1. derece Obez fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						} else if (35 <= vke && vke <= 40) {
							durum.setText("2. derece Obez fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						} else {
							durum.setText("3. derece Obez fazla olan kilonuz:"
									+ new DecimalFormat("##.##").format(fKilo)
									+ "kg");
							metin = durum.getText().toString();
						}
					}
					txtBoy.setText("Boyunuz: " + boy.toString() + " m");
					txtKilo.setText("Kilonuz:" + kilo.toString() + " kg");
					asui = new AsyncSetUserInformation(getActivity());
					asui.execute(kilo.toString(), boy.toString(),
							vke.toString(), ik.toString(), tvs.toString(),
							vya.toString(), yva.toString(), userID.toString(),
							metin);
				}
			}
		});
		return view;
	}


}