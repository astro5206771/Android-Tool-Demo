package com.ai.ipu.filetool;

import android.support.v7.app.ActionBarActivity;

import com.ai.ipu.filetool.util.FileTool;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	private EditText etSave;
	private TextView tvRead;
	private Button btnSave,btnRead,btnDelete,btnSaveExter,btnReadExter,btnDeleteExter,btnClearExter;
	
	private String fileName = "fileTool.txt";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etSave = (EditText) findViewById(R.id.et_content);
		tvRead = (TextView) findViewById(R.id.tv_content);
		btnSave = (Button) findViewById(R.id.btn_save);
		btnRead = (Button) findViewById(R.id.btn_read);
		btnDelete = (Button) findViewById(R.id.btn_delete);
		btnSaveExter = (Button) findViewById(R.id.btn_saveExternal);
		btnReadExter = (Button) findViewById(R.id.btn_readExternal);
		btnDeleteExter = (Button) findViewById(R.id.btn_deleteExternal);
		btnClearExter = (Button) findViewById(R.id.btn_clearExternal);
		 
		btnSave.setOnClickListener(this);
		btnRead.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnSaveExter.setOnClickListener(this);
		btnReadExter.setOnClickListener(this);
		btnDeleteExter.setOnClickListener(this);
		btnClearExter.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {
		String content;
		switch (v.getId()) {
		case R.id.btn_save:
			content = etSave.getText().toString();
			content = "{\"data\":{\"timeStamp\":1508839103251,\"imei\":\"7J9F873MO835334\",\"acionName\":“0”,\"packageName\":“com.xx.xxxx.music”,\"className\":“com.xx.xxxxx.music.xxmusic.xxxxActivity”,\"appName\":\"xxMusic\",\"userBehavior\":\"listenMusic\",\"serialNumber\":\"123\"},\"msgType\":\" uploadUserBehavior \"}";
			FileTool.saveToFile(this, content, fileName);
			break;
		case R.id.btn_read:
			content  = FileTool.readFromFile(this, fileName);
//			content = FileTool.readFromFileByReadBuffer(this, fileName);
			tvRead.setText(content);
			break;
		case R.id.btn_delete:
			boolean isDelete = FileTool.deleteFile(this, fileName);
			Toast.makeText(this, isDelete + "", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_saveExternal:
			content = etSave.getText().toString();
			boolean issave = FileTool.saveToFile(content, Environment.getExternalStorageDirectory() +"/filetool/files", fileName);
			Toast.makeText(this, issave + "", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_readExternal:
			content = FileTool.readFromFile(Environment.getExternalStorageDirectory() +"/filetool/files", fileName);
			tvRead.setText(content);
			break;
		case R.id.btn_deleteExternal:
			boolean isdeleteExternal = FileTool.deleteExternalFile(Environment.getExternalStorageDirectory() +"/filetool/files", fileName);
			Toast.makeText(this, isdeleteExternal + "", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_clearExternal:
			boolean isclear = FileTool.clearExternalFile(Environment.getExternalStorageDirectory() +"/filetool/files", fileName);
			Toast.makeText(this, isclear + "", Toast.LENGTH_LONG).show();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
