package com.fitnessclub.fragments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.gelecegiyazanlar.navigationdrawer.R;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class FragmentGrafik extends Fragment {
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	public BarChart chart;
	public String userID;
	String response = "";
	BarChart chart_cal;
	String response_1 = "";
	BarChart chart_ay_cal;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.layout_grafikfragment, container,
				false);
		chart = (BarChart) view.findViewById(R.id.chart);
		chart_cal = (BarChart) view.findViewById(R.id.chart_cal);
		chart_ay_cal=(BarChart) view.findViewById(R.id.chart_aylik_cal);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		SharedPreferences.Editor editor = preferences.edit();
		userID = preferences.getString("userID", "1");
		new GetContacts(getActivity()).execute();
		return view;
	}

	public ArrayList<IBarDataSet> getDataSet(List<String> dataBar) {
		ArrayList<IBarDataSet> dataSets = null;
		ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
		BarEntry v1e1 = new BarEntry(Float.valueOf(dataBar.get(0)), 0); // Ocak
		valueSet1.add(v1e1);
		BarEntry v1e2 = new BarEntry(Float.valueOf(dataBar.get(1)), 1); // Mart
		valueSet1.add(v1e2);
		BarEntry v1e3 = new BarEntry(Float.valueOf(dataBar.get(2)), 2); // Mayýs
		valueSet1.add(v1e3);
		BarEntry v1e4 = new BarEntry(Float.valueOf(dataBar.get(3)), 3); // Temmuz
		valueSet1.add(v1e4);
		BarEntry v1e5 = new BarEntry(Float.valueOf(dataBar.get(4)), 4); // Eylül
		valueSet1.add(v1e5);
		BarEntry v1e6 = new BarEntry(Float.valueOf(dataBar.get(5)), 5); // Kasým
		valueSet1.add(v1e6);
		BarEntry v1e7 = new BarEntry(Float.valueOf(dataBar.get(6)), 6); // Kasým
		valueSet1.add(v1e7);
		BarDataSet barDataSet2 = new BarDataSet(valueSet1, "Haftalýk Veriler");
		barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
		dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(barDataSet2);
		return dataSets;
	}

	public ArrayList<String> getXAxisValues() {
		ArrayList<String> xAxis = new ArrayList<String>();
		xAxis.add("Pazartesi");
		xAxis.add("Salý");
		xAxis.add("Çarþamba");
		xAxis.add("Perþembe");
		xAxis.add("Cuma");
		xAxis.add("Cumartesi");
		xAxis.add("Pazartesi");
		return xAxis;
	}
	

	// Arkaplan iþlemleri
	private class GetContacts extends AsyncTask<Void, Void, Void> {
		private ProgressDialog Dialog;
		Context c;

		GetContacts(Context c) {
			this.c = c;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Dialog = new ProgressDialog(getActivity());
			Dialog.setMessage("Ýstatiksel bilgiler yükleniyor...");
			Dialog.show();
		}

		List<String> myList;

		@Override
		protected Void doInBackground(Void... arg0) {
			// Making a request to url and getting response
			String url = "http://www.cbuwptema.esy.es/getStepCount.php"; 
			myList = new ArrayList<String>();
			// php sorgusu
			try {
				URL urll = new URL(url);
				HttpURLConnection httpURLConnection = (HttpURLConnection) urll
						.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				OutputStream outputStream = httpURLConnection.getOutputStream();
				BufferedWriter bufferedWriter = new BufferedWriter(
						new OutputStreamWriter(outputStream, "UTF-8"));
				String data = URLEncoder.encode("userID", "UTF-8") + "="
						+ URLEncoder.encode(userID, "UTF-8");
				bufferedWriter.write(data);
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStream.close();
				InputStream inputStream = httpURLConnection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream, "utf-8"), 8);

				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					response += line;
				}
				bufferedReader.close();
				inputStream.close();
				httpURLConnection.disconnect();
			} catch (Exception e) {

			}
		
			if (response != null) {
				try {
					JSONArray json = new JSONArray(response);
					for (int i = 0; i < json.length(); i++) {
						HashMap<String, String> map = new HashMap<String, String>();
						JSONObject e = json.getJSONObject(i);
						myList.add(e.getString("stepCount"));

					}
				} catch (final JSONException e) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(c,
									"Json parsing error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}
					});

				}

			} else {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(
								c,
								"Couldn't get json from server. Check LogCat for possible errors!",
								Toast.LENGTH_LONG).show();
					}
				});
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Dialog.dismiss();
			super.onPostExecute(result);
			BarData data = new BarData(getXAxisValues(), getDataSet(myList));
			chart.setData(data);
			chart.setDrawBorders(true);
			chart.setDescription(" ");
			chart.setDescriptionTextSize(25);
			chart.animateY(3000);
			chart.invalidate();
			new GetContacts_1(getActivity()).execute();
		}
	}

	// Arkaplan 2 iþlemleri
	private class GetContacts_1 extends AsyncTask<Void, Void, Void> {
		Context c;

		GetContacts_1(Context c) {
			this.c = c;
		}

		List<String> myListt;

		@Override
		protected Void doInBackground(Void... arg0) {
			// Making a request to url and getting response
			String url = "http://www.cbuwptema.esy.es/getCalCount.php";
			myListt = new ArrayList<String>();
			// php sorgusu
			try {
				URL urll = new URL(url);
				HttpURLConnection httpURLConnection = (HttpURLConnection) urll
						.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				OutputStream outputStream = httpURLConnection.getOutputStream();
				BufferedWriter bufferedWriter = new BufferedWriter(
						new OutputStreamWriter(outputStream, "UTF-8"));
				String data = URLEncoder.encode("userID", "UTF-8") + "="
						+ URLEncoder.encode(userID, "UTF-8");
				bufferedWriter.write(data);
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStream.close();
				InputStream inputStream = httpURLConnection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream, "utf-8"), 8);

				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					response_1 += line;
				}
				bufferedReader.close();
				inputStream.close();
				httpURLConnection.disconnect();
			} catch (Exception e) {

			}

			if (response_1 != null) {
				try {
					JSONArray json = new JSONArray(response_1);
					for (int i = 0; i < json.length(); i++) {
						HashMap<String, String> map = new HashMap<String, String>();
						JSONObject e = json.getJSONObject(i);
						myListt.add(e.getString("cal"));

					}
				} catch (final JSONException e) {
					    
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(c,
									"Json parsing error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}
					});

				}

			} else {
				
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(
								c,
								"Couldn't get json from server. Check LogCat for possible errors!",
								Toast.LENGTH_LONG).show();
					}
				});
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			  BarData data_cal = new BarData(getXAxisValues(), getDataSet(myListt));
			  chart_cal.setData(data_cal); chart_cal.setDrawBorders(true);
			  chart_cal.setDescriptionTextSize(25);
			  chart_cal.setDescription(" ");
			  chart_cal.animateY(3000); 
			  chart_cal.invalidate();		
			  
			  BarData data_aylik_cal = new BarData(getXAxisValuesAylik(),getDataSetAylik());
			  chart_ay_cal.setData(data_aylik_cal); 
			  chart_ay_cal.setDrawBorders(true);
			  chart_ay_cal.setDescriptionTextSize(25);
			  chart_ay_cal.setDescription(" ");
			  chart_ay_cal.animateY(3000); 
			  chart_ay_cal.invalidate();		

		}
	}
//AYLIK ÝÇÝN VERÝLER
	public ArrayList<IBarDataSet> getDataSetAylik() {
		ArrayList<IBarDataSet> dataSets = null;
		ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
		BarEntry v1e1 = new BarEntry(0, 0); // Ocak
		valueSet1.add(v1e1);
		BarEntry v1e2 = new BarEntry(0,1); // Mart
		valueSet1.add(v1e2);
		BarEntry v1e3 = new BarEntry(0, 2); // Mayýs
		valueSet1.add(v1e3);
		BarEntry v1e4 = new BarEntry(0, 3); // Temmuz
		valueSet1.add(v1e4);
		BarEntry v1e5 = new BarEntry(0, 4); // Eylül
		valueSet1.add(v1e5);
		BarEntry v1e6 = new BarEntry(0, 5); // Kasým
		valueSet1.add(v1e6);
		BarEntry v1e7 = new BarEntry(0, 6); // Kasým
		valueSet1.add(v1e7);
		BarEntry v1e8 = new BarEntry(0, 7); // Temmuz
		valueSet1.add(v1e8);
		BarEntry v1e9 = new BarEntry(0, 8); // Eylül
		valueSet1.add(v1e9);
		BarEntry v1e10 = new BarEntry(0, 9); // Kasým
		valueSet1.add(v1e10);
		BarEntry v1e11 = new BarEntry(0, 10); // Kasým
		valueSet1.add(v1e11);
		BarEntry v1e12 = new BarEntry(0, 11); // Kasým
		valueSet1.add(v1e12);
		BarDataSet barDataSet2 = new BarDataSet(valueSet1, "Haftalýk Veriler");
		barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
		dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(barDataSet2);
		return dataSets;
	}

	public ArrayList<String> getXAxisValuesAylik() {
		ArrayList<String> xAxis = new ArrayList<String>();
		xAxis.add("Ocak");
		xAxis.add("Þubat");
		xAxis.add("Mart");
		xAxis.add("Nisan");
		xAxis.add("Mayýs");
		xAxis.add("Haziran");
		xAxis.add("Temmuz");
		xAxis.add("Aðustos");
		xAxis.add("Eylül");
		xAxis.add("Ekim");
		xAxis.add("Kasým");
		xAxis.add("Aralýk");
		return xAxis;
	}
}
