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

		final  String[] Textlist = { "Suyu seviniz. Güne iki bardak su içerek baþlayýp, gün boyunca 2- 2,5 litre su tüketmeye çalýþýnýz."
				,"Her sebze ve meyveyi mevsiminde en az iki defa tüketiniz. Doðanýn tamamýný kullanmýþ sayýlýrsýnýz.  "
				,"Çocuklar için sütü, büyükler için de özellikle yoðurdu her gün sofranýzdan eksik etmeyiniz. Yaþamýn sýrlarýndan biri olan probiyotikleri bünyenize almýþ olursunuz.  "
				,"Hasta olmasanýz bile, þifalý otlarý/bitkileri kullanarak vücut direncinizi (immun sistemi) kuvvetli tutunuz.  "
				,"Evinizde kurutulmuþ nane, ýhlamur, adaçayý, kekik, kuþburnu, fesleðen, keten tohumu, zencefil, çörekotu, günlük, yeþil cay ile soðan ve sarýmsaðý her zaman bulundurunuz. Her gün bunlardan en az birini kullanmaya çalýþýnýz ki bunlar vücudunuzun koruyucu þövalyeleridir. "
				,"Sarýmsak, soðan, tere, maydanoz, nane, dereotu, roka, fesleðen turu yeþillikleri fazla tüketiniz. Bunlar vücudunuzun yakýn korumalarýdýr.  "
				,"Salatanýzý mümkün olduðu kadar çok çeþitten oluþturunuz."
				,"Hazýr çorbalar yerine kendi yaptýðýnýz çorbalarý tercih ediniz. Gýdanýn en doðalýný elde etmiþ olursunuz"
				,"Kýþ için ev yapýmý domates salçasýný tercih ediniz. Domates bize harika bir antioksidandýr."
				,"Katký maddeleri içeren gýdalarý, mevsim dýþý sebze ve meyveleri fazla tüketmeyiniz. Bünyenizi fazla dinamitlememiþ olursunuz. "
				,"Yýlda dört kez, on beþ gün hiç et tüketilmemesi yararlýdýr. "
				,"Günlük 3-4 adet badem, ceviz ve fýndýk almanýz sizi her daim kuvvetli kýlar.  "
				,"Haftada en az 2 kez bakliyat ve balýk tüketmeðe çalýþýnýz.  "
				,"Kýþýn dýþarýda iþleriniz yoðun ise; güne pekmez içerek baþlayýnýz. Bu uygulama vücudunuzun antifrizidir. "
				,"Zihinsel çalýþýyorsanýz kuru üzüm yiyiniz. Beyniniz enerjisiz kalmasýn."
				,"Gece uyku ortamýnýn karanlýk olmasý, yorgunluk durumlarýnda ise öðleyin kýsa süreli uykular iyidir. Vücudumuzdaki pek çok restorasyon iþlemi gece, kýsa süreli uykularda da günlük tamiratlar yapýlmaktadýr. "
				,"Her gün 5 dakika gözlerinizi kapatýp hiçbir þey düþünmemeyi öðreniniz. Bu sizin yeniden doðumunuz gibidir. "
				,"Yað tercihinizi genelde zeytinyaðýndan tarafa kullanýnýz. Vücudunuz hep bunu bekler.  "
				,"Kahvaltýnýn mutlaka tam yapýlmasý, öðle öðününün orta, akþam öðününün de hafif alýnmasý her daim iyidir. "
				,"Tuz ve þekeri bünyenize ölçülü alýnýz. Bunlarýn azý karar fazlasý hep zarardýr.  "
				,"Günlük bir elma ve bir havucun bünyenizde harikalar yarattýðýný unutmayýnýz."
				,"Fýrsat buldukça topraða çýplak ayakla basýnýz. Tüm olumsuzluklarýnýz topraða geçer.  "
				,"Ekmek tercihinizi kepekliden yana kullanýnýz. Baðýrsaklar kepekli tam posalarla tanýþsýn.  "
				,"Sabahlarý ofis ve evinizi 5 dakika tam havalandýrarak maksimum düzeyde oksijen, günlük 30 dakika tempolu yürümekle de tüm organlarýnýzý kazanýrsýnýz. "
		
		};

		Random random = new Random();
		
       textView=(TextView)view.findViewById(R.id.textView1);
		
		String randomText = Textlist[random.nextInt(Textlist.length)];

		textView.setText(randomText);

		return view;
	}
}
