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

public class AsyncRegister extends AsyncTask<String, Void, String> {

	Context context;
	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	AsyncRegister(Context ctx) {
		this.context = ctx;
	}

	private ProgressDialog Dialog;
	String response = "";

	@Override
	protected void onPreExecute() {
		Dialog = new ProgressDialog(context);
		Dialog.setMessage("Kayýt Oluþturuluyor...");
		Dialog.show();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Dialog.dismiss();
		if (result.equalsIgnoreCase("true")) {
			Toast.makeText(context, "Baþarýlý Þekilde Kayýt Oluþturuldu.!",
					Toast.LENGTH_SHORT).show();
			Intent i = new Intent(context, LoginActivity.class);
			context.startActivity(i);
			((Activity) context).finish();
		} else
			Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected String doInBackground(String... params) {
		String register_url = "http://www.cbuwptema.esy.es/register.php";
		String userName = params[0];
		String Name = params[1];
		String Surname = params[2];
		String Sex = params[3];
		String Password = params[4];
		String BirthDate = params[5];
		try {
			URL url = new URL(register_url);
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
					+ URLEncoder.encode("Name", "UTF-8") + "="
					+ URLEncoder.encode(Name, "UTF-8") + "&"
					+ URLEncoder.encode("Surname", "UTF-8") + "="
					+ URLEncoder.encode(Surname, "UTF-8") + "&"
					+ URLEncoder.encode("Sex", "UTF-8") + "="
					+ URLEncoder.encode(Sex, "UTF-8") + "&"
					+ URLEncoder.encode("Password", "UTF-8") + "="
					+ URLEncoder.encode(Password, "UTF-8") + "&"
					+ URLEncoder.encode("BirthDate", "UTF-8") + "="
					+ URLEncoder.encode(BirthDate, "UTF-8");
			bufferedWriter.write(data);
			bufferedWriter.flush();
			bufferedWriter.close();
			outputStream.close();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, "utf-8"), 8);
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
