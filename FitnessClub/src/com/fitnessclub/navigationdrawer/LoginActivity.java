package com.fitnessclub.navigationdrawer;

import com.gelecegiyazanlar.navigationdrawer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText kullaniciAdi, password;
	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		android.app.ActionBar act = getActionBar();
		act.hide();
		if (InternetControl() == null || !InternetControl().isConnected()) {
			InternetBaglantiUyari();
		} else {
			preferences = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			editor = preferences.edit();
			kullaniciAdi = (EditText) findViewById(R.id.editKullaniciAdi);
			password = (EditText) findViewById(R.id.editSifre);
			// fregment anasayfadaki sifre sorma kapal� ise beni ana activite
			// y�nlendir
			// back tu�una bast��unda
			if (preferences.getBoolean("sifreSorma", true) == false) {
				Intent i = new Intent(this, MainActivity.class);
				startActivity(i);
				finish();
			}
		}
	}
	// internet yoksa yap�lacak i�lemler
	public void InternetBaglantiUyari() {
		try {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this); // Mesaj Penceresini Yaratal�m
			alertDialogBuilder
					.setTitle("�nternet Eri�im Hatas�!")
					.setMessage(
							"�nternet ba�lant�n�z� kontrol ediniz.!")
					.setCancelable(false)
					.setPositiveButton("Internet Ayarlar�na Git",
							new OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									startActivity(new Intent(
											android.provider.Settings.ACTION_WIRELESS_SETTINGS));
									finish();
									android.os.Process
											.killProcess(android.os.Process
													.myPid());
								}
							})
					.setNegativeButton("��k��", new OnClickListener() {
						// E�er hay�r butonuna bas�l�rsa uygulamaya devam
						// eder.
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							finish();
							android.os.Process.killProcess(android.os.Process
									.myPid());
						}
					});
			alertDialogBuilder.create();
			alertDialogBuilder.show();
			// son olarak alertDialogBuilder'� olu�turup ekranda
			// g�r�nt�letiyoruz.
		} catch (IllegalStateException e) { // yap�m�z� try-catch blogu
											// i�erisine ald�k
			// hata ihtimaline kar��.
			e.printStackTrace();
		}
	}

	public NetworkInfo InternetControl() {
		// internet ba�lant�s�n� kontrol et
		ConnectivityManager conMgr = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		return i;
	}

	public void btnGiris(View v) {

		AsyncLogin al = new AsyncLogin(this);
		al.execute(kullaniciAdi.getText().toString(), password.getText()
				.toString());
	}

	public void btnKaydol(View v) {
		Intent i = new Intent(this, KaydolActivity.class);
		startActivity(i);
		finish();
	}
}
