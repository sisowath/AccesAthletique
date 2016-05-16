package www.samnang_alex.com.applicationaccesathletique.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import www.samnang_alex.com.applicationaccesathletique.R;

public class AboutActivity extends AppCompatActivity {
    ImageView imgLogo;
    TextView linkSam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_about);
        imgLogo = (ImageView) findViewById(R.id.logoFooter);
        imgLogo.setImageResource(R.drawable.logo_square);
        TextView linkSam = (TextView) findViewById(R.id.textView3);
        linkSam.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
