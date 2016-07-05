package adrielcardoso.com.br.aulaphoto;

/**
 * Created by adriel on 14/06/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    public Banco(Context context) {
        super(context, "MEUBANCO", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RangoDAO.TABLE_NAME +
                "(" + RangoDAO.FIELD_ID + " INTEGER PRIMARY KEY, " +
                RangoDAO.descricao + " TEXT,"  +
                RangoDAO.foto + " TEXT,"  +
                RangoDAO.ponto + " TEXT,"  +
                RangoDAO.tipo + " TEXT,"  +
                RangoDAO.lat + " TEXT,"  +
                RangoDAO.lon + " TEXT"  +
                " );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}