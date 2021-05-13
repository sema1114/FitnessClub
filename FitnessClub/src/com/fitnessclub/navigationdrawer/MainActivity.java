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
	// Sol Slider iï¿½in Yapï¿½lmï¿½ï¿½ ï¿½zel layout android.support.v4 ï¿½n
	// iï¿½inde
	private DrawerLayout mDrawerLayout;
	// Sol Slider Aï¿½ï¿½ldï¿½ï¿½ï¿½nda Gï¿½rï¿½necek ListView
	private ListView mDrawerList;
	// Navigation Drawer nesnesini ActionBar'da gï¿½sterir.
	private ActionBarDrawerToggle mDrawerToggle;
	// ActionBar'ï¿½n titlesi dinamik olarak deï¿½iï¿½ecek draweri aï¿½ï¿½p
	// kapattï¿½kï¿½a
	private String mTitle = "Fitness Club";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar act = getActionBar();
		act.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.actrenk)));
		// Content alanï¿½na fragment yï¿½klemek iï¿½in
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

		// iconu ve aï¿½ï¿½lï¿½p kapandï¿½ï¿½ï¿½nda gï¿½rï¿½necek texti
		// veriyoruz.
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			// drawer kapatï¿½ldï¿½ï¿½ï¿½nda tetiklenen method
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			// drawer aï¿½ï¿½ldï¿½ï¿½ï¿½nda tetiklenen method
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Fitness Club");
				invalidateOptionsMenu();
			}
		};

		// Aï¿½ï¿½lï¿½p kapanmayï¿½ dinlemek iï¿½in register
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Navigationdaki Drawer iï¿½in listview adapteri
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.menu));

		// adapteri listviewe set ediyoruz
		mDrawerList.setAdapter(adapter);

		// actionbar home butonunu aktif ediyoruz
		getActionBar().setHomeButtonEnabled(true);

		// navigationu tï¿½klanabilir hale getiriyoruz
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// sol slider aï¿½ï¿½ldï¿½ï¿½ï¿½nda gelen listviewin tï¿½klama eventi
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// itemleri arraya tekrar aldï¿½k
				String[] menuItems = getResources()
						.getStringArray(R.array.menu);

				// dinamik title yapmak iï¿½in actionbarda tï¿½klananï¿½n
				// titlesi
				// gï¿½rï¿½necek
				mTitle = menuItems[position];

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();

				// fragmenti contente yerleï¿½tirme.Her Fragment ayrï¿½ bir
				// activity
				// gibi dï¿½ï¿½ï¿½n
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
		// draweri sadece swipe ederek aï¿½ma yerine sol tepedeki butona basarak
		// aï¿½mak iï¿½in
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
			// Eðer geri butonuna basýlýrsa
			try {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this); // Mesaj Penceresini Yaratalým
				alertDialogBuilder
						.setTitle(
								"Programdan Çýkmak Ýstediðinize Emin Misiniz?")
						.setCancelable(false)
						.setPositiveButton("Evet", new OnClickListener() {

							public void onClick(DialogInterface dialog, int id) {
								// Uygulamamýzý sonlandýrýyoruz
								finish();
								dialog.dismiss();
								android.os.Process
										.killProcess(android.os.Process.myPid());
							}

						}).setNegativeButton("Hayýr", new OnClickListener() {
							// Eðer hayýr butonuna basýlýrsa

							public void onClick(DialogInterface dialog,
									int which) {
								/*
								 * Toast.makeText(getApplicationContext(),
								 * "Programdan çýkmaktan vazgeçtiniz.",
								 * Toast.LENGTH_SHORT).show();
								 */
							}
						});

				alertDialogBuilder.create().show();
				// son olarak alertDialogBuilder'ý oluþturup ekranda
				// görüntületiyoruz.
				return super.onKeyDown(keyCode, event);
			} catch (IllegalStateException e) { // yapýmýzý try-catch blogu
												// içerisine aldýk
				// hata ihtimaline karþý.
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}
