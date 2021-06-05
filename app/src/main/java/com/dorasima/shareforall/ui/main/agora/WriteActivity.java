package com.dorasima.shareforall.ui.main.agora;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.model.AgoraMessage;
import com.dorasima.shareforall.data.model.LoggedInUser;
import com.dorasima.shareforall.ui.main.agora.dummy.DummyContent;

import java.io.ByteArrayOutputStream;

public class WriteActivity extends AppCompatActivity {

    private ImageView selected_image;
    private Button select_image;
    private Button finish;

    private EditText title_in;
    private EditText content_in;

    private LoggedInUser loggedInUser;

    private AgoraMessage.ServerAgoraItem newServerAgoraItem = null;

    public void finishBtn(View view){
        String nickname =loggedInUser.getNickName();
        Drawable profile = loggedInUser.getProfile();
        Drawable icon = selected_image.getDrawable();
        String title = title_in.getText().toString();
        String content =content_in.getText().toString();

        if( content== null || icon == null || title == null){
            Toast toast = Toast.makeText(this,"put everything",Toast.LENGTH_SHORT);
            toast.show();
            return ;
        }
        Intent intent = new Intent();
        DummyContent.DummyItem newItem =  new DummyContent.DummyItem(nickname,profile,icon,title,content,DummyContent.AGORA_ITEMS.size());

        byte[] bytesProfile = getByteArrayFromDrawable(profile);
        byte[] bytesIcon = getByteArrayFromDrawable(icon);

        newServerAgoraItem = new AgoraMessage.ServerAgoraItem(nickname, bytesIcon,bytesProfile,content,title,newItem.comments_index);

        DummyContent.addItem(newItem);

        intent.putExtra("dummyData" ,newItem); //사용자에게 입력받은값 넣기
        setResult(RESULT_OK,intent);

        SendNewAgoraArticle thread = new SendNewAgoraArticle();
        thread.start();
        try {
            synchronized(thread){
                thread.join();
            }
        }
        catch (InterruptedException e) {
            finish();
        }
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getSupportActionBar().hide();

        loggedInUser = getIntent().getParcelableExtra("loggedUser");

        selected_image = findViewById(R.id.selected_image);
        select_image = findViewById(R.id.select_image);
        finish = findViewById(R.id.write_finish);

        select_image.setOnClickListener(this::getImageBtn);
        finish.setOnClickListener(this::finishBtn);

        title_in = findViewById(R.id.title_input);
        content_in = findViewById(R.id.contents_input);

    }

    public byte[] getByteArrayFromDrawable(Drawable drawbles){
        Bitmap bitmap = ((BitmapDrawable)drawbles).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

        byte[] data = stream.toByteArray();

        return data;
    }
    public void getImageBtn(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{
            if(resultCode==RESULT_OK){
                Uri uri = data.getData();
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(
                        uri,null,null,null,null);
                cursor.moveToNext();

                int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String source = cursor.getString(index);

                Bitmap bitmap = BitmapFactory.decodeFile(source);
                selected_image.setImageBitmap(resizeBitmap(bitmap));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Bitmap resizeBitmap(Bitmap source){
        Bitmap resized = Bitmap.createScaledBitmap(source, 200, 200,false);
        if(source!=resized){
            source.recycle();
        }
        return resized;
    }

    class SendNewAgoraArticle extends Thread{
        @Override
        public void run() {
            super.run();
            if(newServerAgoraItem != null){
                // todo: 새로운 ServerAgoraItem 개체를 서버에 넘겨주기
            }
        }
    }
}