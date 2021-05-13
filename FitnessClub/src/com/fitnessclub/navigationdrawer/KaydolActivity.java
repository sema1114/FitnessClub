package com.fitnessclub.navigationdrawer;

import com.gelecegiyazanlar.navigationdrawer.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class KaydolActivity extends Activity {
	Spinner spinner;
	EditText kullanýcý_adi, ad, soyad, sifre, sifreTekrar, dogumtarihi;
	String cinsiyet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kaydol);
		// ActionBarï¿½ kapatï¿½r.
		android.app.ActionBar act = getActionBar();
		act.hide();	
		kullanýcý_adi = (EditText) findViewById(R.id.edtKullaniciAd);
		ad = (EditText) findViewById(R.id.edtAd);
		soyad = (EditText) findViewById(R.id.edtSoyad);
		dogumtarihi = (EditText) findViewById(R.id.edtDogumTarihi);
		sifre = (EditText) findViewById(R.id.edtSifre);
		sifreTekrar = (EditText) findViewById(R.id.edtSifreTekrar);
		spinner = (Spinner) findViewById(R.id.spinnerCinsiyet);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.spinner_cinsiyet, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		}

	public void btnKaydol(View v) {
		cinsiyet = spinner.getSelectedItem().toString();
		if (kullanýcý_adi.getText().toString().equals("")
				|| ad.getText().toString().equals("")
				|| soyad.getText().toString().equals("")
				|| dogumtarihi.getText().toString().equals("")
				|| sifre.getText().toString().equals("")) {
			Toast.makeText(getApplication(), "Boþ geçilemez",
					Toast.LENGTH_SHORT).show();
		} else {
			if (sifre.getText().toString()
					.equalsIgnoreCase(sifreTekrar.getText().toString())) {
				AsyncRegister ar = new AsyncRegister(this);
				ar.execute(kullanýcý_adi.getText().toString(), ad.getText()
						.toString(), soyad.getText().toString(), cinsiyet,
						sifre.getText().toString(), dogumtarihi.getText()
								.toString());
			} else
				Toast.makeText(getApplicationContext(),
						"Þifreler Uyuþmuyor...!", Toast.LENGTH_SHORT).show();
		}
	}
}