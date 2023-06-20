package com.example.android.mywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    DbTeller db;
    EditText editTextname,editTextamount,editTextcause;
    Button butUpdate;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update );
         id = getIntent ( ).getIntExtra ( "id", 1 );
        db = new DbTeller ( this );
        final tellerPerson tellerPerson = db.getPersonById ( id );
        editTextname = findViewById ( R.id.enterNameOfTellerUpdate );
        editTextamount = findViewById ( R.id.enterAmountUpdate );
        editTextcause = findViewById ( R.id.enterCauseUpdate );
        butUpdate=findViewById ( R.id.UpdateUpdate );
        editTextname.setText ( tellerPerson.getNameTeller ( ) );
        editTextamount.setText ( String.valueOf ( tellerPerson.getAmuont ( ) ) );
        editTextcause.setText ( tellerPerson.getCause ( ) );
        butUpdate.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                try {


                String name=editTextname.getText ().toString ();
                int amount = Integer.parseInt ( editTextamount.getText ().toString () );
                String cause=editTextcause.getText ().toString ();
                tellerPerson tellerPerson1=new tellerPerson (id,name,amount,cause  );
                db.Update(tellerPerson1);
                Toast.makeText ( Update.this,R.string.UpdateSuccess,Toast.LENGTH_SHORT ).show ();
                Intent intent=new Intent ( Update.this,MainActivity.class );
                startActivity ( intent );}catch (Exception e){
                    System.out.println("Error " + e.getMessage());
                }
            }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.menu ,menu);
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId ()){
            case R.id.menu:
           showAlert();
        }
        return super.onOptionsItemSelected ( item );
    }

    private void showAlert() {
        AlertDialog.Builder alertDialod =new AlertDialog.Builder ( this );
        alertDialod.setTitle ( R.string.Confirmation ).setMessage (R.string.areu )
                .setPositiveButton ( R.string.yes, new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete
                        db.Delete ( id );
                        Toast.makeText ( Update.this,R.string.DELETE_DONE,Toast.LENGTH_SHORT ).show ();
                        Intent intent=new Intent ( Update.this,MainActivity.class );
                        startActivity ( intent );
                    }
                } ).setNegativeButton ( R.string.no, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss ( );
            }
        } );
        AlertDialog dialog=alertDialod.create ();
        dialog.show ();
    }
}
