package com.example.kaiyuan.mydictionarydemo;

import com.example.kaiyuan.mydictionarydemo.adapter.Dictionary;
import com.example.kaiyuan.mydictionarydemo.adapter.DictionaryAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class qiehuan extends Activity implements OnClickListener,TextWatcher{
	private SQLiteDatabase database;
	private Button fanyi,change;
	private AutoCompleteTextView input2;
	private TextView input1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main02);
		fanyi=(Button) findViewById(R.id.button1);
		
		input1= (TextView) findViewById(R.id.EditText01);
		input2=(AutoCompleteTextView) findViewById(R.id.editText1);
		fanyi.setEnabled(false);//输入为Null时，按钮不可用
		fanyi.setOnClickListener(this);
		input2.addTextChangedListener(this);
	
		change=(Button) findViewById(R.id.change);
	}
	//英汉切换
	public void change(View v){
		Intent intent=new Intent(qiehuan.this,MainActivity.class);
		qiehuan.this.startActivity(intent);
		qiehuan.this.finish();
	}
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		database=openDatabase();
		Cursor cursor=database.rawQuery("select chinese as _id from t_words where chinese like ?", 
								new String[]{s.toString()+"%"});//模糊查询
		
		DictionaryAdapter dictionAdapter=new DictionaryAdapter(this, cursor, true);
		input2.setAdapter(dictionAdapter);
		fanyi.setEnabled(true);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(input2.getText().toString()==null||input2.getText().toString().equals("")){
			Toast.makeText(qiehuan.this, "请输入要查询的汉字", 0);
		}
		String sql="select english from t_words where chinese like ?";
		Cursor cursor=database.rawQuery(sql, new String[]{"%"+input2.getText().toString()+"%"});
		String result="未找到改单词";
		if(cursor.getCount()>0){
			cursor.moveToFirst();
			result=cursor.getString(cursor.getColumnIndex("english"));
		}
		database.close();
		input1.setText(result);
		fanyi.setEnabled(false);
	}
private SQLiteDatabase openDatabase(){
	
	String file=Dictionary.DATABASE_PATH+"/"+Dictionary.DATABASE_FILENAME;
	SQLiteDatabase database=SQLiteDatabase.openOrCreateDatabase(file, null);
	
	return database;
	
	
	
}
}
