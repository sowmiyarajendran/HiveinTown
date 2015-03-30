package com.androidexample.hiveintown;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public static final int MEDIA_TYPE_IMAGE1 = 1;
    public static final int MEDIA_TYPE_IMAGE2 = 2;
    public static final int MEDIA_TYPE_IMAGE3=3;
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE1_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE2_REQUEST_CODE = 200;
    private static final int CAMERA_CAPTURE_IMAGE3_REQUEST_CODE = 300;
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "HIVEIN";
    private final String TAG = "MainActivity";
    // file url to store image/video
    private Uri fileUri;
    private byte[] temp1,temp2,temp3;
    //reference to previews and button
    private ImageView photoPreview;
    private ImageView addProofPreview,IdentityPreview;
    private Button hSave, hReset, hPhoto, hAddress,hIdentity;
private ImageView image1,image2,image3;
private String day,month;
    private String name,    age, phoneno, address,    visiting;
    private String sex,doc1,doc2;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText helperName;
    private EditText helperAge;
    private EditText helperPhoneNo;
    private EditText helperAddress;
    private EditText visitingAppartments;
    private Editable temp;
    private Spinner spinner1,spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//references to input variables in xml files
        helperName = (EditText) findViewById(R.id.name);
        helperAge = (EditText) findViewById(R.id.age);
        helperPhoneNo = (EditText) findViewById(R.id.phoneno);
        helperAddress = (EditText) findViewById(R.id.homeaddress);
        visitingAppartments = (EditText) findViewById(R.id.appartments);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
//references to buttons in xml files.
        hSave=(Button)findViewById(R.id.buttonSave);
        hReset=(Button)findViewById(R.id.buttonReset);
        hPhoto=(Button)findViewById(R.id.buttonPhoto);
        hAddress=(Button)findViewById(R.id.buttonAddress);
        hIdentity=(Button)findViewById(R.id.buttonIdentityProof);
        //referenes to previews in xml files.
        photoPreview=(ImageView)findViewById(R.id.imageViewPhoto);
        addProofPreview=(ImageView)findViewById(R.id.imageViewAddProof);
        IdentityPreview=(ImageView)findViewById(R.id.imageIdentity);
        //reference to spinner dropdown\
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        //array adapter for spinner
        ArrayAdapter arrayAdapter1=  ArrayAdapter.createFromResource(this,R.array.spinner1, R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter arrayAdapter2=ArrayAdapter.createFromResource(this,R.array.spinner2, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        //setup spinner and adapter
        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);
        Log.d(TAG,"after setting");
        //add listener to spinners
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);





       //function for save button
        hSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"on hsave listener");
                onSave();
                       }
        });

        // getting text from the domestic helper entry fields
        // getting text from the domestic helper entry fields

        //function for reset button
        hReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clearall();

            }
        });
        //function for opening camera for take photo button.
        hPhoto.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        capturePhoto();
    }
});
        //function for opening camera for take address proof button.
        hAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureAddressProof();

            }
        });
        hIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caputureIdentity();
            }
        });

        // Checking camera availability
        if (!isDeviceSupportCamera()) {  makeText(MainActivity.this, "Sorry! Your device doesn't support camera", LENGTH_LONG).show();
               finish();
        }
    }//oncreate closed

    private void caputureIdentity() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE3);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE3_REQUEST_CODE);
    }

    private void onSave() {
        Log.d(TAG,"onsave");
        name= helperName.getText().toString();
        age=helperAge.getText().toString();
        int selected=radioGroup.getCheckedRadioButtonId();
       radioButton=(RadioButton)findViewById(selected);
       sex=radioButton.getText().toString();
        phoneno=helperPhoneNo.getText().toString();
        address=helperAddress.getText().toString();
        visiting=visitingAppartments.getText().toString();
        image1=photoPreview;
        image2=addProofPreview;
        image3=IdentityPreview;
        doc1=day;
        doc2=month;
        Log.d(TAG,""+day+month);
        Log.d(TAG,""+name+age+sex+phoneno+address+visiting+ day+ month + temp1+temp2+temp3);
        DbHandler dbHandler= new DbHandler(this);

        //dbHandler.getContact(phoneno);

            dbHandler.addContact(new HelperDatabase(name,age,sex,phoneno,address,visiting,temp1,doc1,temp2,doc2,temp3));
            Toast.makeText(this,"Data Added",LENGTH_LONG).show();
            clearall();

    }

    private boolean isDeviceSupportCamera() {

        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
              return true;
         else
             return false;

    }
//clearall
    void clearall(){
        helperName.setText("");
        helperAge.setText("");
        radioGroup.check(R.id.radioButtonMale);
        helperAddress.setText("");
        helperPhoneNo.setText("");
        visitingAppartments.setText("");
        photoPreview.setImageBitmap(null);
        addProofPreview.setImageBitmap(null);
        IdentityPreview.setImageBitmap(null);
        spinner1.setSelection(0);
        spinner2.setSelection(0);

    }


//capturing camera image
private void capturePhoto() {

    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE1);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
    // start the image capture Intent
    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE1_REQUEST_CODE);
}
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }
//capturing address proof
private void captureAddressProof() {
    Log.d(TAG,"CaptureAddProof");
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE2);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
    // start the image capture Intent
    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE2_REQUEST_CODE);


}
    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE1_REQUEST_CODE) {


            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view

                try {
                    // hide video preview
                    addProofPreview.setVisibility(View.VISIBLE);
                    IdentityPreview.setVisibility(View.VISIBLE);
                    photoPreview.setVisibility(View.VISIBLE);

                    // bimatp factory
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    // downsizing image as it throws OutOfMemory Exception for larger
                    // images
                    options.inSampleSize = 8;

                    final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),options);
                    photoPreview.setImageBitmap(bitmap);
                    ContentValues cv= new ContentValues();
                    ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    temp1=outputStream.toByteArray();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                makeText(getApplicationContext(),
                        "User cancelled image capture", LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE2_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                // video successfully recorded
               Log.d(TAG,"onimage2ok");
                try {
                    // hide video preview
                    addProofPreview.setVisibility(View.VISIBLE);
                    IdentityPreview.setVisibility(View.VISIBLE);
                    photoPreview.setVisibility(View.VISIBLE);

                    // bimatp factory
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    // downsizing image as it throws OutOfMemory Exception for larger
                    // images
                    options.inSampleSize = 8;

                    final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),options);
                    addProofPreview.setImageBitmap(bitmap);
                    ContentValues cv= new ContentValues();
                    ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    temp2=outputStream.toByteArray();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                makeText(getApplicationContext(),
                        "User cancelled image capturing", LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                makeText(getApplicationContext(),
                        "Sorry! Failed to record anything", LENGTH_SHORT)
                        .show();
            }
        }else if(requestCode== CAMERA_CAPTURE_IMAGE3_REQUEST_CODE){
            if (resultCode == RESULT_OK) {
                try {
                    // hide video preview
                    photoPreview.setVisibility(View.VISIBLE);
                    IdentityPreview.setVisibility(View.VISIBLE);

                    addProofPreview.setVisibility(View.VISIBLE);

                    // bimatp factory
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    // downsizing image as it throws OutOfMemory Exception for larger
                    // images
                    options.inSampleSize = 8;

                    final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                            options);
                    IdentityPreview.setImageBitmap(bitmap);

                    ContentValues cv= new ContentValues();
                    ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    temp3=outputStream.toByteArray();







                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                makeText(getApplicationContext(),
                        "User cancelled image capturing", LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                makeText(getApplicationContext(),
                        "Sorry! Failed to record anything", LENGTH_SHORT)
                        .show();
            }

        }
    }



//Helper Methods
    // Creating file uri to store image/video

    public Uri getOutputMediaFileUri(int type) {
        Log.d(TAG,"geto/pmediafileuri");
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        Log.d("MAinActivity","Before creating media Directory");
    // External sdcard location
    File mediaStorageDir = new File(
            Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            IMAGE_DIRECTORY_NAME);

    // Create the storage directory if it does not exist
    if (!mediaStorageDir.exists()) {
        Log.d("MAinActivity","after Media Directory creation"+IMAGE_DIRECTORY_NAME);
        if (!mediaStorageDir.mkdirs()) {
            Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                    + IMAGE_DIRECTORY_NAME + " directory");
            return null;
        }
    }

    // Create a media file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
            Locale.getDefault()).format(new Date());
    File mediaFile;
    if (type == MEDIA_TYPE_IMAGE1) {
        Log.d("MAinActivity","BEFORE FILE IMAGE creation");
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "PIC_" + timeStamp + ".jpg");
    } else if (type == MEDIA_TYPE_IMAGE2) {
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "PROOF_" + timeStamp + ".jpg");
    }else if(type==MEDIA_TYPE_IMAGE3){
        mediaFile= new File(mediaStorageDir.getPath()+File.separator + "IDPR_"+ timeStamp + ".jpg");
    }
        else
     {
        return null;
    }

    return mediaFile;

}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner=(Spinner)parent;
        if(spinner.getId()==R.id.spinner1){
            Log.d(TAG,"spinner1");
             day=parent.getItemAtPosition(position).toString();
         //   Toast.makeText(this,"Selected"+day,Toast.LENGTH_SHORT).show();
        }else if(spinner.getId()==R.id.spinner2){
            Log.d(TAG,"spinner2");

             month=parent.getItemAtPosition(position).toString();
         //   Toast.makeText(this,"Selected"+month,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Please Select SomeType", LENGTH_SHORT).show();


    }






    
}
