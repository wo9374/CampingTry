package com.example.mylogin.SNS;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;
import com.example.mylogin.SEARCH.Detail.DetailInformation;
import com.example.mylogin.SEARCH.Detail.ReserveRequest;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;

public class Photo extends Fragment {
    private View view;
    private Context ct;

    private ImageView photo;
    private EditText content;
    private Button write_btn;

    String mCurrentPhotoPath;
    final static int REQUEST_TAKE_PHOTO  = 1;
    final static int REQUEST_TAKE_ALBUM  = 2;

    private Uri photoURI;
    Bitmap bitmap;
    String urlUpload = "http://3.34.136.232/SnsPhotoUpload.php";
    String userid;
    String imageFileName;
    String comment;
    String usernickname;
    String imageFileNamePlus;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sns_photo, container, false);
        ct = container.getContext();

        if (getArguments() != null) {
            userid = getArguments().getString("userid");
            usernickname = getArguments().getString("nic");
        }

        //카메라 권한부분은 MainActivity에서 미리 받고 있음
        Diaglog();

        photo = view.findViewById(R.id.photo);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Diaglog();
            }
        });

        content = view.findViewById(R.id.content);
        write_btn = view.findViewById(R.id.write_btn);
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
                comment = content.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ct,response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ct,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        String imageData = imamgeToString(bitmap);
                        params.put("image", imageData);
                        params.put("userid", imageFileName);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ct);
                requestQueue.add(stringRequest);

                Response.Listener<String> responseListener3 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success)//검색 결과 성공
                            {
                                System.out.println("성공@@@@@@@@@@@@@@@@@");
                            } else { //검색 결과 없음
                                System.out.println("실패@@@@@@@@@@@@@");
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //실제 서버로 Volley를 이용해서 요청을 함.
                PhotoRequest photoRequest = new PhotoRequest(usernickname, comment, imageFileNamePlus, responseListener3);
                RequestQueue queue3 = Volley.newRequestQueue(ct);
                queue3.add(photoRequest);

            }
        });
        return view;
    }

    private  String imamgeToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return  encodedImage;
    }

    public void Diaglog(){
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OpenCamera();
            }
        };

        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getAlbum();
            }
        };

        new AlertDialog.Builder(ct)   //프로필 알림창 표시
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진 촬영", cameraListener)
                .setNeutralButton("앨범 선택", albumListener)
                .show();
    }

    public void OpenCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(ct,"com.example.mylogin",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void getAlbum() {
        Intent getAlbumintent = new Intent(Intent.ACTION_PICK);
        getAlbumintent.setType("image/*");
        getAlbumintent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(getAlbumintent, REQUEST_TAKE_ALBUM);
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = userid + "_" + timeStamp;
        imageFileNamePlus = imageFileName + ".jpg";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO: {
                if (resultCode == RESULT_OK) {
                    File file = new File(mCurrentPhotoPath);
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.fromFile(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (bitmap != null) {
                        ExifInterface ei = null;

                        try {
                            ei = new ExifInterface(mCurrentPhotoPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                ExifInterface.ORIENTATION_UNDEFINED);

                        Bitmap rotatedBitmap = null;
                        switch(orientation) {

                            case ExifInterface.ORIENTATION_ROTATE_90:
                                rotatedBitmap = rotateImage(bitmap, 90);
                                break;

                            case ExifInterface.ORIENTATION_ROTATE_180:
                                rotatedBitmap = rotateImage(bitmap, 180);
                                break;

                            case ExifInterface.ORIENTATION_ROTATE_270:
                                rotatedBitmap = rotateImage(bitmap, 270);
                                break;

                            case ExifInterface.ORIENTATION_NORMAL:
                            default:
                                rotatedBitmap = bitmap;
                        }
                        photo.setImageBitmap(rotatedBitmap);
                    }
                }
                break;
            }

            case REQUEST_TAKE_ALBUM:{
                if (resultCode == RESULT_OK) {
                    if (intent.getData()!=null){
                        try{
                            photoURI = intent.getData();
                            photo.setImageURI(photoURI);
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.v("알림","앨범에서 가져오기 에러");
                        }
                    }
                }
                break;
            }
        }
    }

    //찍은 사진 원래대로 회전 시키기 위한 메소드
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
}