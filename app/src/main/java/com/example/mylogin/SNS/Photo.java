package com.example.mylogin.SNS;

import android.Manifest;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mylogin.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class Photo extends Fragment implements SurfaceHolder.Callback{
    private View view;

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Button btn_pic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sns_photo, container, false);

        PermissionListener permission=new PermissionListener() {
            @Override
            public void onPermissionGranted() { // 퍼미션 허용시
                camera = Camera.open();
                camera.setDisplayOrientation(90);
                surfaceView = (SurfaceView)view.findViewById(R.id.surfaceView);
                surfaceHolder = surfaceView.getHolder();
                surfaceHolder.addCallback(Photo.this);
                surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) { //퍼미션 거부시
                Toast.makeText(view.getContext(),"권한 거부", Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(view.getContext())
                .setPermissionListener(permission)
                .setRationaleMessage("사진 권한 허용 요구")
                //권한 묻기
                .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용하시기 바랍니다.")
                //거부시 메세지
                .setPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                //카메라, 쓰기, 녹화 권한
                .check();


        btn_pic = (Button)view.findViewById(R.id.btn_pic);
        btn_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }

    private  void refreshCamera(Camera camera) {
        if(surfaceHolder.getSurface() == null){
            return;
        }

        try {
            camera.stopPreview();
        }catch (Exception e){
            e.printStackTrace();
        }

        setCamera(camera);
    }

    private void setCamera(Camera cam) {
        camera=cam;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(camera != null){
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
}