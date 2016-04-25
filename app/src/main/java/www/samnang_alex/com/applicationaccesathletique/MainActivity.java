package www.samnang_alex.com.applicationaccesathletique;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class MainActivity extends Activity {

    ImageView imgLogo;
    TextView lblCoordonnesEntreprise;
    Button btnCreerUneBlessure;
    Button btnAjouterUnEvenement;
    Button btnGenererRapport;
    Button btnGestionDeLaBD;
    TextView lblCopyrightSamnang;

    public static final Font BOLD_UNDERLINED = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.UNDERLINE);
    public static final Font mesTitres = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.BLACK);
    public static final Font mesSousTitres = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.acces_athletique_logo);
        lblCoordonnesEntreprise = (TextView) findViewById(R.id.lblCoordonneesEntreprise);
        lblCoordonnesEntreprise.setText("1587, Rainaud\nMontréal (Québec), Canada H1A 4L5\nTéléphone : 514 795-5683");
        lblCoordonnesEntreprise.setTextColor(Color.BLACK);
        //txtCoordonnesEntreprise.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnCreerUneBlessure = (Button) findViewById(R.id.btnCreerUneBlessure);
        btnCreerUneBlessure.setBackgroundColor(Color.parseColor("#0080ff"));
        btnCreerUneBlessure.setTextColor(Color.WHITE);
        btnCreerUneBlessure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreerUneBlessureActivity.class);
                startActivity(intent);
            }
        });
        btnAjouterUnEvenement = (Button) findViewById(R.id.btnAjouterUnEvenement);
        btnAjouterUnEvenement.setBackgroundColor(Color.parseColor("#0080ff"));
        btnAjouterUnEvenement.setTextColor(Color.WHITE);
        btnAjouterUnEvenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "En construction ...", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(36);
                toast.show();
            }
        });
        btnGenererRapport = (Button) findViewById(R.id.btnGenererRapportPDF);
        btnGenererRapport.setBackgroundColor(Color.parseColor("#0080ff"));
        btnGenererRapport.setTextColor(Color.WHITE);
        btnGenererRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "En construction ...", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(36);
                toast.show();
                /* oi-haut source de :
                http://stackoverflow.com/questions/15321186/how-to-display-toast-at-center-of-screen
                http://stackoverflow.com/questions/5274354/how-can-we-increase-the-font-size-in-toast
                */
            }
        });
        btnGestionDeLaBD = (Button) findViewById(R.id.btnGestionDeLaBD);
        btnGestionDeLaBD.setBackgroundColor(Color.parseColor("#0080ff"));
        btnGestionDeLaBD.setTextColor(Color.WHITE);
        btnGestionDeLaBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "En construction ...", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(36);
                toast.show();
            }
        });
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //btnGenererRapport.setText("Widhth : " + size.x + " :: Height : " + size.y);
        if(size.x < 1000) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            /*ImageView logo = (ImageView) findViewById(R.id.imgLogo);
            lp.setMargins(0, 500, 0, 0);
            logo.setLayoutParams(lp);*/
            lblCoordonnesEntreprise.setTextSize(18);
            lp.setMargins(50, 80, 50, 0);
            btnCreerUneBlessure.setLayoutParams(lp);
            lp.setMargins(50, 20, 50, 20);
            btnAjouterUnEvenement.setLayoutParams(lp);
            btnGenererRapport.setLayoutParams(lp);
            btnGestionDeLaBD.setLayoutParams(lp);
            /* Source de :
                http://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels
                http://stackoverflow.com/questions/12728255/in-android-how-do-i-set-margins-in-dp-programmatically
            */
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.iGererAthlete :
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Titre");
                alertDialogBuilder.setMessage("Message");
                break;
            case R.id.iGereEquipe :

                break;
            case R.id.iGererEcole :

                break;
        }
        return true;
    }
}