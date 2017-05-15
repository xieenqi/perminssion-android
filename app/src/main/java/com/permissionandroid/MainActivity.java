package com.permissionandroid;

import android.Manifest;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.permissionlibrary.HiPermission;
import com.permissionlibrary.PermissionCallback;
import com.permissionlibrary.PermissonItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PermissonItem> permissonItems = new ArrayList<PermissonItem>();
                permissonItems.add(new PermissonItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
                HiPermission.create(MainActivity.this)
//                        .title(getString(R.string.permission_cus_title))
                        .permissions(permissonItems)
                        .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))
//                        .msg(getString(R.string.permission_cus_msg))
//                        .style(R.style.PermissionBlueStyle)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i("log", "onClose");
//                                showToast(getString(R.string.permission_on_close));
                            }

                            @Override
                            public void onFinish() {
//                                showToast(getString(R.string.permission_completed));
                            }

                            @Override
                            public void onDeny(String permisson, int position) {
                                Log.i("log", "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {
                                Log.i("log", "onGuarantee");
                            }
                        });
            }
        });
    }
}
