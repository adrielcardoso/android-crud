package adrielcardoso.com.br.aulaphoto;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RangoDAO rangoDAO;
    TextView message;
    ArrayList<RangoEntity> items = new ArrayList<RangoEntity>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rangoDAO = new RangoDAO(this);

        message = (TextView) findViewById(R.id.message);
        listView = (ListView) findViewById(R.id.listView);

        setListView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_cart:
                // search action

                Intent create = new Intent(this, CreateActivity.class);

                startActivityForResult(create, 2);

                return true;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            setListView();
        }
    }

    public void setListView()
    {

        Cursor data = rangoDAO.selectAll();

        if(data.getCount() == 0){

            listView.setVisibility(View.INVISIBLE);
            message.setVisibility(View.VISIBLE); // set message

        }else {

            listView.setVisibility(View.VISIBLE);
            message.setVisibility(View.GONE);

            items = new ArrayList<RangoEntity>();

            data.moveToFirst();
            while (!data.isAfterLast()) {

                RangoEntity entity = new RangoEntity();

                entity.setStDescricao(data.getString(data.getColumnIndexOrThrow(RangoDAO.descricao)));
                entity.setPathFoto(data.getString(data.getColumnIndexOrThrow(RangoDAO.foto)));
                entity.setStPonto(data.getString(data.getColumnIndexOrThrow(RangoDAO.ponto)));
                entity.setStTipo(data.getString(data.getColumnIndexOrThrow(RangoDAO.tipo)));

                items.add(entity);

                data.moveToNext();
            }

            ListaArrayAdapter adapter = new ListaArrayAdapter(this, items);

            listView.setAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
