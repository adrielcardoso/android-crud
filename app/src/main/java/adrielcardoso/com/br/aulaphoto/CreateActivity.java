package adrielcardoso.com.br.aulaphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adriel on 13/06/16.
 */
public class CreateActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView imageView;
    Button foto;
    Button salvar;
    String mCurrentPhotoPath;
    EditText descricao;
    RadioGroup radioGroup;
    RatingBar ratingBar;
    RangoDAO rangoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.rangoDao = new RangoDAO(this);

        setContentView(R.layout.activity_create);

        descricao = (EditText) findViewById(R.id.descricao);
        radioGroup= (RadioGroup) findViewById(R.id.escolha);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        imageView = (ImageView) findViewById(R.id.iamgebatida);
        imageView.setImageResource(R.drawable.semimagem);

        foto = (Button) findViewById(R.id.foto);
        foto.setOnClickListener(this);

        salvar = (Button) findViewById(R.id.buttonSalvar);
        salvar.setOnClickListener(this);

    }

    public void parseSave()
    {
        boolean permission = true;

        if(descricao.getText().toString().trim().equals("")){
            descricao.setError("Defina uma curta descrição");
            permission = false;
        }

        if(radioGroup.getCheckedRadioButtonId() < 1){
            permission = false;
            Toast.makeText(this, "Selecione uma das opções", Toast.LENGTH_SHORT).show();
        }


        if(permission){

            int selectedId = radioGroup.getCheckedRadioButtonId();

            RadioButton radioButton = (RadioButton) findViewById(selectedId);

            RangoEntity model = new RangoEntity(
                    descricao.getText().toString(),
                    radioButton.getText().toString(),
                    String.valueOf(ratingBar.getRating()),
                    mCurrentPhotoPath
            );

            if(rangoDao.insert(model)){
                setResult(RESULT_OK);
                finish();
            }
        }

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this, "CLICK", Toast.LENGTH_SHORT).show();

        switch (v.getId()){
            case R.id.foto : dispatchTakePictureIntent();
            break;

            case R.id.buttonSalvar : parseSave();
                break;
        }
    }

    private void dispatchTakePictureIntent(){

       try{

           Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {}
//
//            if (photoFile != null) {
//
           takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                   createImageFile());
//
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }

           startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
       }catch (IOException e){

           Log.i("ERRROR", e.getMessage());
       }

    }

    private File createImageFile() throws IOException {

           String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
           String imageFileName = "JPEG_" + timeStamp + "_";

           File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

           File image = File.createTempFile(
                   imageFileName,
                   ".jpg"
           );

           mCurrentPhotoPath = "file:" + image.getAbsolutePath();

           return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

}
