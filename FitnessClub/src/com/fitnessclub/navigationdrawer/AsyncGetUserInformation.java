package com.fitnessclub.navigationdrawer;
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
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncGetUserInformation extends AsyncTask<String, Void, String> {
	Context context;
	EditText boy, kilo;
	TextView ik, vke, tvs, vya, yva, durum, txtKilo, txtBoy;
	Button btnHesapla;

	public AsyncGetUserInformation(Context ctx, EditText boy, EditText kilo,
			TextView vke, TextView ik, TextView tvs, TextView vya,
			TextView yva, TextView durum, Button btnHesapla, TextView txtKilo,
			TextView txtBoy) {
		this.context = ctx;
		this.boy = boy;
		this.kilo = kilo;
		this.ik = ik;
		this.vke = vke;
		this.tvs = tvs;
		this.vya = vya;
		this.yva = yva;
		this.durum = durum;
		this.btnHesapla = btnHesapla;
		this.txtBoy = txtBoy;
		this.txtKilo = txtKilo;
	}

	private ProgressDialog Dialog;

	@Override
	protected void onPreExecute() {
		Dialog = new ProgressDialog(context);
		Dialog.setMessage("Bilgileriniz çekiliyor...");
		Dialog.show();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Dialog.dismiss();
		if (result.equalsIgnoreCase("false")) {
			Toast.makeText(context, "Verileriniz Bulunamadý.!",
					Toast.LENGTH_SHORT).show();
		} else {
			String[] data = result.split("-");
			boy.setText(data[0]);
			kilo.setText(data[1]);	
			txtKilo.setText("Kilonuz: " + data[1] + " kg");
			txtBoy.setText("Boyunuz: " + data[0] + " m");
			vke.setText("Vücut Kitle Endeksiniz: " + data[2]);
			ik.setText("Ýdeal Kilonuz: " + data[3].toString() + " kg");
			tvs.setText("Toplam vücut suyu: " + data[4] + " kg");
			vya.setText("Vücut Yüzey Alanýnýz: " + data[5] + " m^2");
			yva.setText("Yaðsýz vücut aðýrlýðýnýz: " + data[6] + " kg");
			durum.setText(data[7]);
			btnHesapla.setVisibility(View.GONE);
		}
	}

	@Override
	protected String doInBackground(String... params) {
		String userID = params[0];
		String getUser_url = "http://www.cbuwptema.esy.es/getUserInformation.php";
		try {
			URL url = new URL(getUser_url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url
					.openConnection();
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			OutputStream outputstream = httpUrlConnection.getOutputStream();
			BufferedWriter bufferedwriter = new BufferedWriter(
					new OutputStreamWriter(outputstream, "UTF-8"));
			String data = URLEncoder.encode("userID", "UTF-8") + "="
					+ URLEncoder.encode(userID, "UTF-8");
			bufferedwriter.write(data);
			bufferedwriter.flush();
			bufferedwriter.close();
			outputstream.close();
			InputStream inputStream = httpUrlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, "utf-8"), 8);
			String response = "";
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				response += line;
			}
			bufferedReader.close();
			inputStream.close();
			httpUrlConnection.disconnect();
			return response;
		} catch (Exception e) {
			return e.toString();
		}
	}
}