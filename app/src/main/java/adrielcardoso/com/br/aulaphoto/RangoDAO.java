package adrielcardoso.com.br.aulaphoto;

/**
 * Created by adriel on 14/06/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class RangoDAO {

    private Banco db;

    public final static String TABLE_NAME = "TEXTO";
    public final static String FIELD_ID = "_id";
    public final static String descricao = "descricao";
    public final static String tipo = "tipo";
    public final static String ponto = "ponto";
    public final static String foto = "foto";
    public final static String lat = "lat";
    public final static String lon = "lon";

    public RangoDAO(Context context) {
        this.db = new Banco(context);
    }

    public boolean insert(RangoEntity texto) {

        try{
            Log.i("PASSSSANDO ", "PONTO 1");
            ContentValues cv = new ContentValues();

            cv.put(descricao, texto.getStDescricao());
            cv.put(tipo, texto.getStTipo());
            cv.put(ponto, texto.getStPonto());
            cv.put(foto, texto.getPathFoto());
            cv.put(lat, texto.getLat());
            cv.put(lon, texto.getLon());

            Log.i("PASSSSANDO ", "PONTO 2");
            db.getWritableDatabase().insert(TABLE_NAME, null, cv);

            return true;
        }catch (Exception e){
            return false;
        }

    }

    private Cursor select(String selection, String[] selectionArgs) {
        return db.getReadableDatabase().query(TABLE_NAME, null, selection, selectionArgs, null, null, FIELD_ID + " DESC");
    }

    public RangoEntity getClassInstance(Cursor c) {
        int id = c.getInt(c.getColumnIndex(FIELD_ID));
        return new RangoEntity();
    }

    public Cursor selectAll() {
        Cursor todoCursor = db.getReadableDatabase().rawQuery("select * from TEXTO order by _id desc", new String[]{});
        return todoCursor;
    }

}