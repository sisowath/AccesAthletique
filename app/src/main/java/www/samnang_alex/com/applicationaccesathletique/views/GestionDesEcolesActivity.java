package www.samnang_alex.com.applicationaccesathletique.views;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import www.samnang_alex.com.applicationaccesathletique.DAO.EcoleHelper;
import www.samnang_alex.com.applicationaccesathletique.R;

public class GestionDesEcolesActivity extends AppCompatActivity {

    EditText txtNomEcole;
    Button btnAjouterUneEcole;
    Button btnModifierUneEcole;
    Button btnSupprimerUneEcole;
    TextView lblEcoleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_des_ecoles);
        txtNomEcole = (EditText) findViewById(R.id.txtNomEcole);
        btnAjouterUneEcole = (Button) findViewById(R.id.btnAjouterUneEcole);
        btnAjouterUneEcole.setText("Ajouter une école");
        btnAjouterUneEcole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnModifierUneEcole = (Button) findViewById(R.id.btnModifierUneEcole);
        btnModifierUneEcole.setText("Modifier une école");
        btnModifierUneEcole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSupprimerUneEcole = (Button) findViewById(R.id.btnSupprimerUneEcole);
        btnSupprimerUneEcole.setText("Supprimer une école");
        btnSupprimerUneEcole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lblEcoleData = (TextView) findViewById(R.id.lblEcoleData);
        EcoleHelper ecoleHelper = new EcoleHelper(this);
        Cursor cursor = ecoleHelper.findAll();
        cursor.moveToFirst();
        String affichage = "";
        while(cursor.moveToNext()) {
            affichage += cursor.getString(cursor.getColumnIndex("nom"));
        }
        lblEcoleData.setText(affichage);
        cursor.close();
        ecoleHelper.close();
    }
}