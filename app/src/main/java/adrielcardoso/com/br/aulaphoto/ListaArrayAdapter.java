package adrielcardoso.com.br.aulaphoto;

/**
 * Created by adriel on 14/06/16.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaArrayAdapter extends ArrayAdapter<RangoEntity> {

    ArrayList<RangoEntity> myList = new ArrayList<RangoEntity>();
    LayoutInflater inflater;
    Context context;

    public ListaArrayAdapter(Context context, ArrayList<RangoEntity> myList) {
        super(context, 0, myList);
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.card, parent, false);

        RangoEntity model = getItem(position); // entity

        TextView data_date = (TextView) view.findViewById(R.id.data_date);
        data_date.setText(model.getStDescricao());

        TextView tipo = (TextView) view.findViewById(R.id.tipo);
        tipo.setText(model.getStTipo());

        TextView pontos = (TextView) view.findViewById(R.id.pontos);
        pontos.setText(model.getStPonto());

        return view;
    }

}
