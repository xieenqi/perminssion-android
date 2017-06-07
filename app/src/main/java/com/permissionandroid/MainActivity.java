package com.permissionandroid;

import android.Manifest;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.permissionlibrary.HiPermission;
import com.permissionlibrary.PermissionCallback;
import com.permissionlibrary.PermissonItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView animation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation_view = (LottieAnimationView) findViewById(R.id.animation_view);
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
                                showToast("你拒绝了授权");
                            }

                            @Override
                            public void onFinish() {
                                showToast("权限申请完成");
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

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
