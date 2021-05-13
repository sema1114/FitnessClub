package com.fitnessclub.fragments;

import java.util.Random;
import com.gelecegiyazanlar.navigationdrawer.R;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;

import android.support.v4.app.NotificationCompat;
import android.view.MotionEvent;
import android.widget.ViewFlipper;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class FragmentAnasayfa extends Fragment implements SensorEventListener {
	Integer a;
	public TextView textVieww;
	public SensorManager mSensorManager;
	public Sensor mStepCounterSensor;
	public Sensor mStepDetectorSensor;
	TextView txtGiris, txtHesap, txt1, adimSayisi, txtUserInfo;
	private ViewFlipper viewFlipper;
	private float lastX;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//hangi fregmente ait olduðunu belirten içerik
		View view = inflater.inflate(R.layout.layout_anasayfafragment,
				container, false);
		//resimlerin tanýmlandýðý komponent static 
		viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
		txtUserInfo = (TextView) view.findViewById(R.id.txtUserInfo);
		txtHesap = (TextView) view.findViewById(R.id.txtHesap);
		//login kýsmýnda kullanýcýnýn girdiði veriyi sisteme kaydeder cookie
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		SharedPreferences.Editor editor = preferences.edit();
		String userInfo = preferences.getString("userInfo", "N/A");
		editor.putBoolean("sifreSorma", false);//tekrar login giriþini engeller
		editor.commit();// iþlemi kaydediyor.
		//kullanýcý bilgilerini ekrana yazdýrýlýr.
		txtUserInfo.setText(userInfo);
		adimSayisi = (TextView) view.findViewById(R.id.txtSayiniz);
		//fragment olduðu için getactivity() alýnýyor.
		mSensorManager = (SensorManager) getActivity().getSystemService(
				Context.SENSOR_SERVICE);//servisini bize sunduðu servislerden alýr android manifestte
		mStepCounterSensor = mSensorManager
				.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
		mStepDetectorSensor = mSensorManager
				.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
		ImageView imageView = (ImageView) view
				.findViewById(R.id.imageViewHearts);
		
		//Kalp atým animasyonu
		Animation pulse = AnimationUtils.loadAnimation(getActivity(),
				R.anim.impulse);
		imageView.startAnimation(pulse);
		// flipper yani resmin yana kaymasýný saðlýyor.
		viewFlipper.setInAnimation(getActivity(), R.anim.slide_in_from_right);
		viewFlipper.startFlipping();
		return view;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//hareket ettiðinde sensör verisinin adým sayýsýna yazar.
		Sensor sensor = event.sensor;
		float[] values = event.values;
		int value = -1;
		if (values.length > 0) {
			value = (int) values[0];
		}
		if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
			adimSayisi.setText(String.valueOf(value));
			float hesap = (float) value / 1000;
			txtHesap.setText(String.valueOf(hesap));
			//adým 10 bin olduðunda uyarý verir.
			if(value==10000) Notifications();
		} else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
			// For test only. Only allowed value is 1.0 i.e. for step taken
			adimSayisi.setText("Test Adým(1.0) : " + value);
		}
	}

	@Override
	//arka planda çalýþýyorsa, telefonda orta tuþa basma durumu
	//adým sayýlmaz
	public void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mStepCounterSensor,
				SensorManager.SENSOR_DELAY_FASTEST);
		mSensorManager.registerListener(this, mStepDetectorSensor,
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	//sistemin durdurulmasý durumudur.
	//adým sayýlmaz
	public void onStop() {
		super.onStop();
		mSensorManager.unregisterListener(this, mStepCounterSensor);
		mSensorManager.unregisterListener(this, mStepDetectorSensor);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public void Notifications() {
		Intent aint = new Intent(getActivity(), FragmentAnasayfa.class);
		
		PendingIntent pi = PendingIntent.getActivity(getActivity(), 0, aint,
				PendingIntent.FLAG_CANCEL_CURRENT);
		Resources r = getResources();
		Notification notification = new NotificationCompat.Builder(
				getActivity())
				.setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_fc)
				.setContentTitle("Tebrikler!").setContentText("10.000 Adýma Attýnýz...")
				.setPriority(Notification.PRIORITY_MAX).setContentIntent(pi)
				.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
				.setLights(0xff00ff00, 300, 1000).build();//
		NotificationManager notificationManager = (NotificationManager) getActivity()
				.getSystemService(getActivity().NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}
}