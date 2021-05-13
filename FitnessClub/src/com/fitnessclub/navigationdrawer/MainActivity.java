package com.fitnessclub.navigationdrawer;

import com.fitnessclub.fragments.FragmentAnasayfa;
import com.fitnessclub.fragments.FragmentGrafik;
import com.fitnessclub.fragments.FragmentSaglik;
import com.fitnessclub.fragments.FragmentArkadaslar;
import com.fitnessclub.fragments.FragmentKonumum;

import com.fitnessclub.fragments.FragmentOneriler;
import com.gelecegiyazanlar.navigationdrawer.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
//import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	// Sol Slider i�in Yap�lm�� �zel layout android.support.v4 �n
	// i�inde
	private DrawerLayout mDrawerLayout;
	// Sol Slider A��ld���nda G�r�necek ListView
	private ListView mDrawerList;
	// Navigation Drawer nesnesini ActionBar'da g�sterir.
	private ActionBarDrawerToggle mDrawerToggle;
	// ActionBar'�n titlesi dinamik olarak de�i�ecek draweri a��p
	// kapatt�k�a
	private String mTitle = "Fitness Club";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar act = getActionBar();
		act.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.actrenk)));
		// Content alan�na fragment y�klemek i�in
		FragmentManager fragmentManager = getFragmentManager();
		preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		editor = preferences.edit();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		FragmentAnasayfa fragmentHome = new FragmentAnasayfa();
		ft.add(R.id.content_frame, fragmentHome);
		ft.commit();

		mTitle = "Fitness Club";
		getActionBar().setTitle(mTitle);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		// iconu ve a��l�p kapand���nda g�r�necek texti
		// veriyoruz.
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			// drawer kapat�ld���nda tetiklenen method
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			// drawer a��ld���nda tetiklenen method
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Fitness Club");
				invalidateOptionsMenu();
			}
		};

		// A��l�p kapanmay� dinlemek i�in register
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Navigationdaki Drawer i�in listview adapteri
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.menu));

		// adapteri listviewe set ediyoruz
		mDrawerList.setAdapter(adapter);

		// actionbar home butonunu aktif ediyoruz
		getActionBar().setHomeButtonEnabled(true);

		// navigationu t�klanabilir hale getiriyoruz
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// sol slider a��ld���nda gelen listviewin t�klama eventi
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// itemleri arraya tekrar ald�k
				String[] menuItems = getResources()
						.getStringArray(R.array.menu);

				// dinamik title yapmak i�in actionbarda t�klanan�n
				// titlesi
				// g�r�necek
				mTitle = menuItems[position];

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();

				// fragmenti contente yerle�tirme.Her Fragment ayr� bir
				// activity
				// gibi d���n
				if (position == 0) {
					FragmentAnasayfa fragmentAnasayfa = new FragmentAnasayfa();
					ft.replace(R.id.content_frame, fragmentAnasayfa);
					ft.commit();
				} else if (position == 1) {
					FragmentKonumum fragmentKonumum = new FragmentKonumum(
							MainActivity.this);
					ft.replace(R.id.content_frame, fragmentKonumum);
					ft.commit();

				} else if (position == 2) {
					FragmentSaglik fragmentHedefler = new FragmentSaglik();
					ft.replace(R.id.content_frame, fragmentHedefler);
					ft.commit();
				} else if (position == 3) {
					FragmentGrafik fragmentGunluk = new FragmentGrafik();
					ft.replace(R.id.content_frame, fragmentGunluk);
					ft.commit();
				} else if (position == 4) {
					FragmentArkadaslar fragmentArkadaslar = new FragmentArkadaslar();
					ft.replace(R.id.content_frame, fragmentArkadaslar);
					ft.commit();
				} else if (position == 5) {
					FragmentOneriler fragmentOneriler = new FragmentOneriler();
					ft.replace(R.id.content_frame,fragmentOneriler );
					ft.commit();
				} else if (position == 6) {
					editor.putBoolean("sifreSorma", true);
					editor.commit();
					finish();
				}
				// draweri kapat
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// draweri sadece swipe ederek a�ma yerine sol tepedeki butona basarak
		// a�mak i�in
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// E�er geri butonuna bas�l�rsa
			try {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this); // Mesaj Penceresini Yaratal�m
				alertDialogBuilder
						.setTitle(
								"Programdan ��kmak �stedi�inize Emin Misiniz?")
						.setCancelable(false)
						.setPositiveButton("Evet", new OnClickListener() {

							public void onClick(DialogInterface dialog, int id) {
								// Uygulamam�z� sonland�r�yoruz
								finish();
								dialog.dismiss();
								android.os.Process
										.killProcess(android.os.Process.myPid());
							}

						}).setNegativeButton("Hay�r", new OnClickListener() {
							// E�er hay�r butonuna bas�l�rsa

							public void onClick(DialogInterface dialog,
									int which) {
								/*
								 * Toast.makeText(getApplicationContext(),
								 * "Programdan ��kmaktan vazge�tiniz.",
								 * Toast.LENGTH_SHORT).show();
								 */
							}
						});

				alertDialogBuilder.create().show();
				// son olarak alertDialogBuilder'� olu�turup ekranda
				// g�r�nt�letiyoruz.
				return super.onKeyDown(keyCode, event);
			} catch (IllegalStateException e) { // yap�m�z� try-catch blogu
												// i�erisine ald�k
				// hata ihtimaline kar��.
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}
