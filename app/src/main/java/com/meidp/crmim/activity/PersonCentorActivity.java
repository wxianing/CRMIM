package com.meidp.crmim.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.ImageUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.DActionSheetDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.HashMap;

@ContentView(R.layout.activity_person_centor)
public class PersonCentorActivity extends BaseActivity {

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.nick_name)
    private EditText nickNameEt;
    @ViewInject(R.id.phone_num)
    private EditText phoneNumEt;
    private File tempFile;
    private Bitmap bitmap;
    @ViewInject(R.id.header_img)
    private ImageView headerImg;
    @ViewInject(R.id.duty_tv)
    private TextView duty;


    @Override
    public void onInit() {
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("个人资料");
        String photoUrl = (String) SPUtils.get(this, "PhotoURL", "");
//        ImageLoader.getInstance().displayImage(photoUrl, headerImg);

        String headStr = (String) SPUtils.get(this, "headPortrait", null);
        if (NullUtils.isNull(headStr)) {
            Bitmap headerBitmap = ImageUtils.stringtoBitmap(headStr);
            headerImg.setImageBitmap(headerBitmap);
        }
        String nickName = nickNameEt.getText().toString().trim();
        nickNameEt.setSelection(nickName.length());
        nickNameEt.clearFocus();

        String deptName = (String) SPUtils.get(this, "DeptName", "");
        duty.setText(deptName);
        String employeeName = (String) SPUtils.get(this, "EmployeeName", "");
        nickNameEt.setText(employeeName);

    }

    @Event(value = {R.id.back_arrows, R.id.title_right, R.id.header_img})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                final String nickName = nickNameEt.getText().toString().trim();
                final String phoneNum = phoneNumEt.getText().toString().trim();
                HashMap params = new HashMap();
                params.put("Mobile", phoneNum);
                params.put("NickName", nickName);
                HttpRequestUtils.getmInstance().send(PersonCentorActivity.this, Constant.UPDATE_MESSAGE, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                        });
                        if (appMsg != null && appMsg.getEnumcode() == 0) {
                            ToastUtils.shows(PersonCentorActivity.this, "修改成功");
                            SPUtils.save(PersonCentorActivity.this, "NICENAME", nickName);
                            SPUtils.save(PersonCentorActivity.this, "PHONE", phoneNum);
                        } else {
                            ToastUtils.shows(PersonCentorActivity.this, "修改失败");
                        }
                    }
                });
                break;
            case R.id.header_img:
                selectIconPhoto();
                break;
        }
    }

    private void selectIconPhoto() {
        new DActionSheetDialog(this).builder()
                .setTitle("请选择方式")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("打开相册",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                gallery();
                            }
                        })
                .addSheetItem("拍照",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                camera();
                            }
                        }).show();

    }

    /**
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    /**
     * 检查SD卡
     *
     * @return
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                ToastUtils.shows(this, "未找到存储卡，无法存储照片！");
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                this.headerImg.setImageBitmap(bitmap);
                if (tempFile != null) {
                    boolean delete = tempFile.delete();
                }
                String photoString = ImageUtils.bitmaptoString(bitmap);
                SPUtils.save(this, "headPortrait", photoString);
                Log.e("phoneString", photoString);
                upload(photoString);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void upload(final String photoString) {
        HashMap params = new HashMap();
        params.put("PothoData", photoString);
//        HttpRequestUtils.getmInstance().send(PersonCentorActivity.this, Constant.UPDATE_HEADER, params, new HttpRequestCallBack<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e("result", result);
//            }
//        });
        HttpRequestUtils.getmInstance().send(PersonCentorActivity.this, Constant.UPDATE_HEADER, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    Log.e("头像上传成功", result);
                    SPUtils.save(PersonCentorActivity.this, "headPortrait", photoString);
                }
            }
        });
    }
}
