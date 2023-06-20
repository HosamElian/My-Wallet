package com.example.android.mywallet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class tellerAdapter extends ArrayAdapter<tellerPerson> {

    int resource;
    Context context;

    public tellerAdapter(Context context, int resource, List<tellerPerson> objects) {
        super ( context, resource, objects );
        this.resource=resource;
        this.context=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from (context).inflate (resource,parent,false );
        TextView tvname=convertView.findViewById ( R.id.enterNameOfTellerShow );
        TextView tvAmount=convertView.findViewById ( R.id.enterAmountShow );
        TextView tvCause=convertView.findViewById ( R.id.enterCauseShow );
        tellerPerson tellerPerson= getItem ( position );
        tvname.setText (tellerPerson.getNameTeller ());
        tvAmount.setText (String.valueOf (  tellerPerson.getAmuont ()));
        tvCause.setText (tellerPerson.getCause ());
        convertView.setBackgroundColor(position % 2 == 0 ? Color.GRAY : Color.WHITE);
        return convertView;
    }
}
