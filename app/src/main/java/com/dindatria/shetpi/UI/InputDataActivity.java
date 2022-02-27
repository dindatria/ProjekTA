package com.dindatria.shetpi.UI;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.PostPutDelSapiResponses2;
import com.dindatria.shetpi.R;
import com.dindatria.shetpi.utils.FileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputDataActivity extends AppCompatActivity {
    EditText edit_Nama, edit_jekel, edit_ket, edit_tgl, edit_idsapi2;
    Button btnSimpan, btnDataSapi;
    ImageView img_sapi,img_tgl;
    Spinner spinJekel;
    private DatePickerDialog datePickerDialog;
    private TextView select_image_sapi;
    private SimpleDateFormat simpleDateFormat;
    private Uri fileUri = null;
    private Uri uri;

    public static final int IMAGE_CAPTURE_CODE = 101;
    private Bitmap bitmap;
    private String ListJekel[]={"Pilih Jenis Kelamin","Jantan","Betina"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        edit_Nama =  findViewById(R.id.editNama);
        spinJekel =findViewById(R.id.editJekel);
        edit_idsapi2=  findViewById(R.id.edit_IdSapi2);
        edit_ket = findViewById(R.id.editKeterangan);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnDataSapi = findViewById(R.id.btnSimpan2);
        select_image_sapi = findViewById(R.id.seleact_image_sapi);
        img_sapi = findViewById(R.id.img_sapi);
        edit_tgl= findViewById(R.id.editTgl);
        img_tgl =findViewById(R.id.imgTgl);

        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,ListJekel);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJekel.setAdapter(arrayAdapter);


        Date tgl =new Date();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateStr = simpleDateFormat.format(tgl);
        edit_tgl.setText(dateStr);

        img_tgl.setOnClickListener(view ->{
            setDate();
        });

        check_Permission();
        btnSimpan();
        btnViewDataSapi();
        select_image_sapi.setOnClickListener(v -> {
            setSelectFileImage();
        });
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {

            Calendar calendar1 = Calendar.getInstance();

            calendar1.set(year,month,dayOfMonth);

            edit_tgl.setText(simpleDateFormat.format(calendar1.getTime()));

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {

        File files = new File(fileUri.getPath());
        Log.i("here is error", files.getAbsolutePath());

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        files);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, files.getName(), requestFile);
    }
    private  void btnViewDataSapi(){
        btnDataSapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputDataActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void btnSimpan() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String nama_Sapi =  edit_Nama.getText().toString();
                String gender_sapi = spinJekel.getSelectedItem().toString();
                String description = edit_ket.getText().toString();
                String id_sapii = edit_idsapi2.getText().toString();

                if (id_sapii.isEmpty()){
                    edit_idsapi2.setError("Id Sapi Wajib Diisi");
                    edit_idsapi2.requestFocus();
                    return;
                }
                if (nama_Sapi.isEmpty()){
                    edit_Nama.setError("Nama Sapi Wajib Diisi");
                    edit_Nama.requestFocus();
                    return;
                }

                if (gender_sapi.equals("Pilih Jenis Kelamin")){
                    Toast.makeText(InputDataActivity.this,"Silakan Pilih Jenis Kelamin",Toast.LENGTH_LONG).show();
                    return;
                }

                if (description.isEmpty()){
                    edit_ket.setError("Jenis Sapi Wajib Diisi");
                    edit_ket.requestFocus();
                    return;
                }

                RequestBody nama_sapi = RequestBody.create(MediaType.parse("text/plain"),
                        edit_Nama.getText().toString());
                RequestBody jenis_kelamin = RequestBody.create(MediaType.parse("text/plain"),
                        spinJekel.getSelectedItem().toString());
                RequestBody keterangan = RequestBody.create(MediaType.parse("text/plain"),
                        edit_ket.getText().toString());
                RequestBody id_sapi2 = RequestBody.create(MediaType.parse("text/plain"),
                        edit_idsapi2.getText().toString());
                RequestBody  tanggal_lahir= RequestBody.create(MediaType.parse("text/plain"),
                        edit_tgl.getText().toString());

                Call<PostPutDelSapiResponses2> postPutSapiResponseCall = ApiClient.getApiInterface().postDataSapi(prepareFilePart("foto_sapi", fileUri),
                        id_sapi2,nama_sapi, jenis_kelamin, tanggal_lahir,keterangan );
                postPutSapiResponseCall.enqueue(new Callback<PostPutDelSapiResponses2>() {
                    @Override
                    public void onResponse(Call<PostPutDelSapiResponses2> call, Response<PostPutDelSapiResponses2> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus() == 200) {
//                                Toast.makeText(getApplicationContext(), "Berhasil Menambahkan data" +ID_DATA_MASUK,  Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
                            } else {

                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<PostPutDelSapiResponses2> call, Throwable t) {

                    }
                });
            }

        });

    }

    private void check_Permission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.
                PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    public void setSelectFileImage() {
        final CharSequence[] items = {"Camera", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(InputDataActivity.this);
        builder.setTitle("Tambahkan Gambar");
        builder.setItems(items, ((dialog, which) -> {
            if (items[which].equals("Camera")) {
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCamera, IMAGE_CAPTURE_CODE);

            } else if (items[which].equals("Cancel")) {
                dialog.dismiss();
            }
        }));
        builder.show();
    }

    //    hasil dari capture image dan pick file image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CAPTURE_CODE) {

            bitmap = (Bitmap) data.getExtras().get("data");

            //   preview image
            img_sapi.setImageBitmap(bitmap);

            //   convert image
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bitmap, "Title", null);

            Log.i("Path", path);

                    fileUri = Uri.parse(path);

            String imgPath = FileUtil.getPath(InputDataActivity.this, fileUri);

            fileUri = Uri.parse(imgPath);

        }
    }

}