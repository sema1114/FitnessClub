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
		// inflate edip tan�tt�m
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
			//clear i�aret �ubu�u siler
			//googleMap.clear();
			// Enlem ve Boylam Al�nd�
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			// Harita �zerindeki �ubuk olu�turuldu
			MarkerOptions marker = new MarkerOptions().position(
					new LatLng(latitude, longitude))
					.title("Buradass�n�z...!");

			// i�aret �ubu�unun iconu ve rengi tan�mland�
			marker.icon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_RED));

			// �ubu�u haritaya ekleme i�lemleri
			googleMap.addMarker(marker);
			CameraPosition cameraPosition = new CameraPosition.Builder()
			//zoom k���ld�k�e g�r�nt� uzakla��r b�y�d�k�e yak�nla��r.
					.target(new LatLng(latitude, longitude)).zoom(17).build();
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

			return v;
		} else {
			gps.showSettingsAlert();
			return v;
		}
	}

	// program beklemeye al�nd���nda sonland���nda vs i�lemlerde ne yapaca��n�
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
