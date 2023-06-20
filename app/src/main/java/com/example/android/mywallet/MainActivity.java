package com.example.android.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvAdd;
    ListView tellerLv;
    DbTeller db;
    TextView tvPrice;
    TextView tvlt;
    int price=0;
    int Expenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        db=new DbTeller ( this );
        tvAdd=findViewById ( R.id.tvOfadd );
        tellerLv=findViewById ( R.id.listOfPerson );
        tvPrice =findViewById ( R.id.Expensesis);
        tvlt=findViewById ( R.id.ExpensesisLt );
        price=db.getPrice ();
        Expenses=R.string.Expenses;
        tvPrice.setText (  String.valueOf (price));

        final ArrayList<tellerPerson>tellerPerson=db.getAllteller ();

        tellerAdapter tellerAdapter=new tellerAdapter ( this,R.layout.tickitoflv,tellerPerson );
        tellerLv.setAdapter ( tellerAdapter );
        //go to Add Activity
        tvAdd.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent addIntent =new Intent ( MainActivity.this,AddNewPerson.class );
                startActivity ( addIntent );
            }
        } );

        //go to update
        tellerLv.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                tellerPerson mycursor = tellerPerson.get(position);
                Intent UpdateIntent =new Intent ( MainActivity.this,Update.class );
                UpdateIntent.putExtra ( "id",mycursor.getId () );
                startActivity ( UpdateIntent );
            }

        } );

    }

    @Override
    protected void onResume() {
        super.onResume ( );
        ArrayList<tellerPerson>tellerPeople=db.getAllteller ();


        tellerAdapter tellerAdapter=new tellerAdapter ( this,R.layout.tickitoflv,tellerPeople );
        tellerLv.setAdapter ( tellerAdapter );
        tvPrice.setText (  String.valueOf (  price));
    }

}
