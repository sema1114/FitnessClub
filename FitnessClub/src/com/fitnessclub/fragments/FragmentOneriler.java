package com.fitnessclub.fragments;

import java.util.Random;
import com.gelecegiyazanlar.navigationdrawer.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOneriler extends Fragment{

	Integer a;
	TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.layout_onerilerfragment, container,false);

		final  String[] Textlist = { "Suyu seviniz. G�ne iki bardak su i�erek ba�lay�p, g�n boyunca 2- 2,5 litre su t�ketmeye �al���n�z."
				,"Her sebze ve meyveyi mevsiminde en az iki defa t�ketiniz. Do�an�n tamam�n� kullanm�� say�l�rs�n�z.  "
				,"�ocuklar i�in s�t�, b�y�kler i�in de �zellikle yo�urdu her g�n sofran�zdan eksik etmeyiniz. Ya�am�n s�rlar�ndan biri olan probiyotikleri b�nyenize alm�� olursunuz.  "
				,"Hasta olmasan�z bile, �ifal� otlar�/bitkileri kullanarak v�cut direncinizi (immun sistemi) kuvvetli tutunuz.  "
				,"Evinizde kurutulmu� nane, �hlamur, ada�ay�, kekik, ku�burnu, fesle�en, keten tohumu, zencefil, ��rekotu, g�nl�k, ye�il cay ile so�an ve sar�msa�� her zaman bulundurunuz. Her g�n bunlardan en az birini kullanmaya �al���n�z ki bunlar v�cudunuzun koruyucu ��valyeleridir. "
				,"Sar�msak, so�an, tere, maydanoz, nane, dereotu, roka, fesle�en turu ye�illikleri fazla t�ketiniz. Bunlar v�cudunuzun yak�n korumalar�d�r.  "
				,"Salatan�z� m�mk�n oldu�u kadar �ok �e�itten olu�turunuz."
				,"Haz�r �orbalar yerine kendi yapt���n�z �orbalar� tercih ediniz. G�dan�n en do�al�n� elde etmi� olursunuz"
				,"K�� i�in ev yap�m� domates sal�as�n� tercih ediniz. Domates bize harika bir antioksidand�r."
				,"Katk� maddeleri i�eren g�dalar�, mevsim d��� sebze ve meyveleri fazla t�ketmeyiniz. B�nyenizi fazla dinamitlememi� olursunuz. "
				,"Y�lda d�rt kez, on be� g�n hi� et t�ketilmemesi yararl�d�r. "
				,"G�nl�k 3-4 adet badem, ceviz ve f�nd�k alman�z sizi her daim kuvvetli k�lar.  "
				,"Haftada en az 2 kez bakliyat ve bal�k t�ketme�e �al���n�z.  "
				,"K���n d��ar�da i�leriniz yo�un ise; g�ne pekmez i�erek ba�lay�n�z. Bu uygulama v�cudunuzun antifrizidir. "
				,"Zihinsel �al���yorsan�z kuru �z�m yiyiniz. Beyniniz enerjisiz kalmas�n."
				,"Gece uyku ortam�n�n karanl�k olmas�, yorgunluk durumlar�nda ise ��leyin k�sa s�reli uykular iyidir. V�cudumuzdaki pek �ok restorasyon i�lemi gece, k�sa s�reli uykularda da g�nl�k tamiratlar yap�lmaktad�r. "
				,"Her g�n 5 dakika g�zlerinizi kapat�p hi�bir �ey d���nmemeyi ��reniniz. Bu sizin yeniden do�umunuz gibidir. "
				,"Ya� tercihinizi genelde zeytinya��ndan tarafa kullan�n�z. V�cudunuz hep bunu bekler.  "
				,"Kahvalt�n�n mutlaka tam yap�lmas�, ��le ���n�n�n orta, ak�am ���n�n�n de hafif al�nmas� her daim iyidir. "
				,"Tuz ve �ekeri b�nyenize �l��l� al�n�z. Bunlar�n az� karar fazlas� hep zarard�r.  "
				,"G�nl�k bir elma ve bir havucun b�nyenizde harikalar yaratt���n� unutmay�n�z."
				,"F�rsat bulduk�a topra�a ��plak ayakla bas�n�z. T�m olumsuzluklar�n�z topra�a ge�er.  "
				,"Ekmek tercihinizi kepekliden yana kullan�n�z. Ba��rsaklar kepekli tam posalarla tan��s�n.  "
				,"Sabahlar� ofis ve evinizi 5 dakika tam havaland�rarak maksimum d�zeyde oksijen, g�nl�k 30 dakika tempolu y�r�mekle de t�m organlar�n�z� kazan�rs�n�z. "
		
		};

		Random random = new Random();
		
       textView=(TextView)view.findViewById(R.id.textView1);
		
		String randomText = Textlist[random.nextInt(Textlist.length)];

		textView.setText(randomText);

		return view;
	}
}
