package com.example.android.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewPerson extends AppCompatActivity {
    EditText editNameTeller;
    EditText editAmount;
    EditText editCause;
    Button butadd;
    DbTeller db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_add_new_person );
        editNameTeller=findViewById (R.id.enterNameOfTeller  );
        editAmount=findViewById ( R.id.enterAmount );
        editCause=findViewById ( R.id.enterCause );
        butadd=findViewById ( R.id.butadd );
        db=new DbTeller (  this);
        butadd.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                String name=editNameTeller.getText ().toString ();
                int amount =Integer.parseInt ( editAmount.getText ().toString () );
                String cause=editCause.getText ().toString ();
                tellerPerson tellerPerson=new tellerPerson ( name,amount,cause );
                db.addteller ( tellerPerson );
                Toast.makeText ( AddNewPerson.this,R.string.ADD_SUCCESS,Toast.LENGTH_SHORT ).show ();
                Intent addIntent =new Intent ( AddNewPerson.this,MainActivity.class );

                startActivity ( addIntent );
            }
        } );
    }
}
