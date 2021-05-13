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
import com.fitnessclub.fragments.FragmentSaglik;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncUpdateUserInformation extends AsyncTask<String, Void, String> {

	Context context;

	public AsyncUpdateUserInformation(Context ctx) {
		this.context = ctx;
	}

	private ProgressDialog Dialog;

	@Override
	protected void onPreExecute() {
		Dialog = new ProgressDialog(context);
		Dialog.setMessage("Bilgileriniz güncelleniyor...");
		Dialog.show();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Dialog.dismiss();
		if (result.equalsIgnoreCase("true")) {
			Toast.makeText(context, "Güncelleme Baþarýlý.!", Toast.LENGTH_SHORT)
					.show();
			Intent i=new Intent(context,MainActivity.class);
			context.startActivity(i);
		} else {
			Toast.makeText(context, "Güncelleme Baþarýsýz.!",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected String doInBackground(String... params) {
		String setUserInfo_url = "http://www.cbuwptema.esy.es/updateUserInformation.php";
		String Weight = params[0];
		String Height = params[1];
		String vke = params[2];
		String ik = params[3];
		String tvs = params[4];
		String vya = params[5];
		String yva = params[6];
		String userID = params[7];
		String Durum = params[8];
		try {
			URL url = new URL(setUserInfo_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(outputStream, "UTF-8"));
			String data = URLEncoder.encode("Weight", "UTF-8") + "="
					+ URLEncoder.encode(Weight, "UTF-8") + "&"
					+ URLEncoder.encode("Height", "UTF-8") + "="
					+ URLEncoder.encode(Height, "UTF-8") + "&"
					+ URLEncoder.encode("vke", "UTF-8") + "="
					+ URLEncoder.encode(vke, "UTF-8") + "&"
					+ URLEncoder.encode("ik", "UTF-8") + "="
					+ URLEncoder.encode(ik, "UTF-8") + "&"
					+ URLEncoder.encode("tvs", "UTF-8") + "="
					+ URLEncoder.encode(tvs, "UTF-8") + "&"
					+ URLEncoder.encode("vya", "UTF-8") + "="
					+ URLEncoder.encode(vya, "UTF-8") + "&"
					+ URLEncoder.encode("yva", "UTF-8") + "="
					+ URLEncoder.encode(yva, "UTF-8") + "&"
					+ URLEncoder.encode("userID", "UTF-8") + "="
					+ URLEncoder.encode(userID, "UTF-8") + "&"
					+ URLEncoder.encode("Durum", "UTF-8") + "="
					+ URLEncoder.encode(Durum, "UTF-8") + "&";
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
