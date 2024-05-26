package com.example.oyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SureliOyunActivity extends AppCompatActivity {
    private TextView tvIlBilgi, tvIl, tvDogruSayisi, tvYanlisSayisi, tvSure;
    private EditText etTahmin;
    private Button btnHarfAl, btnTahminEt, btnTekrarOyna;

    private int dogruSayisi = 0, yanlisSayisi = 0, toplamSure = 60000;
    private String[] iller = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};

    private Random rndIl, rndHarfAl;
    private int rndIlSayi, rndHarfAlSayisi;
    private String gelenIl, gelenTahmin;
    private String ilBoyutu;
    private ArrayList<Character> ilHarfleri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sureli_oyun);
        tvIlBilgi = (TextView) findViewById(R.id.tvIlBilgi);
        tvIl = (TextView) findViewById(R.id.tvIl);
        tvDogruSayisi = (TextView) findViewById(R.id.tvDogruSayisi);
        tvYanlisSayisi = (TextView) findViewById(R.id.tvYanlisSayisi);
        etTahmin = (EditText) findViewById(R.id.etTahmin); // Correction: findViewById(R.id.etTahmin)
        btnHarfAl = (Button) findViewById(R.id.btnHarfAl);
        btnTahminEt = (Button) findViewById(R.id.btnTahminEt);
        tvSure = (TextView) findViewById(R.id.tvSure);
        btnTekrarOyna=(Button)findViewById(R.id.btnTekrarOyna);

        new CountDownTimer(toplamSure, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                tvSure.setText("Süre: " + secondsRemaining + " sn");
            }

            @Override
            public void onFinish() {
                tvSure.setText("Süre doldu!");
                btnHarfAl.setEnabled(false);
                btnTahminEt.setEnabled(false);
                btnTekrarOyna.setVisibility(View.VISIBLE);
                Toast.makeText(SureliOyunActivity.this, "Oyun bitti!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        rndHarfAl = new Random();
        randomDegerleriBelirle();
    }

    public void btnHarfAl2(View v) {
        if (ilHarfleri.size() > 0) {
            rndHarfAlSayisi = rndHarfAl.nextInt(ilHarfleri.size());
            String[] txtHarfler = tvIl.getText().toString().split(" ");
            char[] gelenIlHarfler = gelenIl.toCharArray(); // harfleri parçaladım gelen ilin.

            for (int i = 0; i < gelenIl.length(); i++) {
                if (txtHarfler[i].equals("_") && gelenIlHarfler[i] == ilHarfleri.get(rndHarfAlSayisi)) { //Gelen harfin değeri _ 'ye eşitmi diye kontrol ettim.
                    // ve random olarak gelen değer o indekste mi diye kontrol ettim
                    txtHarfler[i] = String.valueOf(ilHarfleri.get(rndHarfAlSayisi));
                    ilBoyutu = "";

                    for (int j = 0; j < gelenIl.length(); j++) {
                        if (j == i) {
                            ilBoyutu += txtHarfler[j] + " ";
                        } else if (j < gelenIl.length() - 1) {
                            ilBoyutu += txtHarfler[j] + " ";
                        } else { //Son indeksten sonra boşluk koyma hatasını yapmaması için yazdım.
                            ilBoyutu += txtHarfler[j];
                        }
                    }
                    break; //Verdiği aynı harfleri aynı anda eklemesin diye.
                }

            }

            System.out.println(ilHarfleri.get(rndHarfAlSayisi)); //Logcat'de kontrol etmek için yazdım yukarıdaki gibi.
            tvIl.setText(ilBoyutu);
            ilHarfleri.remove(rndHarfAlSayisi);

        }

    }

    public void setBtnTekrarOyna(View v){
        Intent tekrarOyna = new Intent(this, SureliOyunActivity.class);
        finish();
        startActivity(tekrarOyna);


    }
    public void btnTahminEt2(View v) {
        gelenTahmin = etTahmin.getText().toString(); //içindeki değeri aldım.

        if (!TextUtils.isEmpty(gelenTahmin)) {
            if (gelenTahmin.equals(gelenIl)) {
                etTahmin.setText("");
                randomDegerleriBelirle();
                System.out.println("Doğru Tahminde Bulundunuz!");
                dogruSayisi++;
                Toast.makeText(this, "Doğru Sayınız: " + dogruSayisi, Toast.LENGTH_SHORT).show();
                tvDogruSayisi.setText("Doğru Sayısı: " + dogruSayisi);
            } else {
                System.out.println("Yanlış Tahminde Bulundunuz!");
                yanlisSayisi++;
                Toast.makeText(this, "Yanlis Sayiniz: " + yanlisSayisi, Toast.LENGTH_SHORT).show();
                tvYanlisSayisi.setText("Yanlış Sayısı: " + yanlisSayisi);
            }
        } else {
            System.out.println("Tahmin Değeri Boş Olamaz!");
        }

    }

    private void randomDegerleriBelirle() {

        ilBoyutu = "";
        rndIl = new Random();
        rndIlSayi = rndIl.nextInt(iller.length);
        gelenIl = iller[rndIlSayi];

        System.out.println(rndIlSayi + " =" + gelenIl); //Logcat'den gelen ili kontrol ettiğin yer.Sunarken söylersin.

        tvIlBilgi.setText(gelenIl.length() + " " + "Harfli İlimiz");


        for (int i = 0; i < gelenIl.length(); i++) {
            if (i < gelenIl.length() - 1) {
                ilBoyutu += "_ ";
            } else {
                ilBoyutu += "_";
            }
            tvIl.setText(ilBoyutu);

            ilHarfleri = new ArrayList<>();
            for (char c : gelenIl.toCharArray()) {
                ilHarfleri.add(c);
            }
        }
    }
}
