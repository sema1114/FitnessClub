package com.fitnessclub.fragments;
import com.gelecegiyazanlar.navigationdrawer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
//import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentKonumum extends Fragment {

	MapView mMapView;
	private GoogleMap googleMap;
	Context c;
	GPSTracker gps;

	public FragmentKonumum(Context c) {
		this.c = c;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// inflate edip tanï¿½ttï¿½m
		View v = inflater.inflate(R.layout.layout_konumumfragment, container,
				false);
		mMapView = (MapView) v.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);
		mMapView.onResume();
		gps = new GPSTracker(c);
		if (gps.canGetLocation == true) {
			try {
				MapsInitializer.initialize(getActivity()
						.getApplicationContext());
			} catch (Exception e) {
				e.printStackTrace();
			}
			googleMap = mMapView.getMap();
			//clear iï¿½aret ï¿½ubuï¿½u siler
			//googleMap.clear();
			// Enlem ve Boylam Alï¿½ndï¿½
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			// Harita ï¿½zerindeki ï¿½ubuk oluï¿½turuldu
			MarkerOptions marker = new MarkerOptions().position(
					new LatLng(latitude, longitude))
					.title("Buradassýnýz...!");

			// iï¿½aret ï¿½ubuï¿½unun iconu ve rengi tanï¿½mlandï¿½
			marker.icon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_RED));

			// ï¿½ubuï¿½u haritaya ekleme iï¿½lemleri
			googleMap.addMarker(marker);
			CameraPosition cameraPosition = new CameraPosition.Builder()
			//zoom kï¿½ï¿½ï¿½ldï¿½kï¿½e gï¿½rï¿½ntï¿½ uzaklaï¿½ï¿½r bï¿½yï¿½dï¿½kï¿½e yakï¿½nlaï¿½ï¿½r.
					.target(new LatLng(latitude, longitude)).zoom(17).build();
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

			return v;
		} else {
			gps.showSettingsAlert();
			return v;
		}
	}

	// program beklemeye alï¿½ndï¿½ï¿½ï¿½nda sonlandï¿½ï¿½ï¿½nda vs iï¿½lemlerde ne yapacaï¿½ï¿½nï¿½
	// belirttik
	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}
}
