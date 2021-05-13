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
import android.widget.Toast;

public class AsyncLogin extends AsyncTask<String, Void, String> {

	Context context;
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	AsyncLogin(Context ctx) {
		this.context = ctx;
	}
	private ProgressDialog Dialog;
	@Override
	protected void onPreExecute() {
		Dialog = new ProgressDialog(context);
		Dialog.setMessage("Bilgileriniz Kontrol Ediliyor...");
		Dialog.show();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Dialog.dismiss();
		if (result != null) {
			String[] sonuc = result.split("-");
			String deger=sonuc[0];
			if (deger.equals("true")) {
				preferences = PreferenceManager
						.getDefaultSharedPreferences(context);
				editor = preferences.edit();
				String userID=sonuc[1];
				String Name=sonuc[2];
				String Surname=sonuc[3];
				editor.putString("userID", userID);
				editor.putString("userInfo",Name+" "+Surname);
				editor.commit();
				Toast.makeText(context, "Giriþ Baþarýlý.!", Toast.LENGTH_SHORT)
						.show();
				Intent i = new Intent(context, MainActivity.class);
				context.startActivity(i);
				((Activity) context).finish();
			} else {
				Toast.makeText(context, "Giriþ Baþarýsýz.!", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected String doInBackground(String... params) {
		String giris_url = "http://www.cbuwptema.esy.es/login.php";
		String userName = params[0];
		String Sifre = params[1];
		try {
			URL url = new URL(giris_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(outputStream, "UTF-8"));
			String data = URLEncoder.encode("userName", "UTF-8") + "="
					+ URLEncoder.encode(userName, "UTF-8") + "&"
					+ URLEncoder.encode("Sifre", "UTF-8") + "="
					+ URLEncoder.encode(Sifre, "UTF-8");
			bufferedWriter.write(data);
			bufferedWriter.flush();
			bufferedWriter.close();
			outputStream.close();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, "utf-8"),8);
			String response = "";
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				response += line;
			}
			bufferedReader.close();
			inputStream.close();
			httpURLConnection.disconnect();
			return response;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return "Hata Icerigi" + "\n" + e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "Hata Icerigi" + "\n" + e;
		}
	}
}
