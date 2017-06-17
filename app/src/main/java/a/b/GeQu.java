package a.b;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.widget.TextView;

public class GeQu extends ListActivity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.gequlist);
	        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map1 = new HashMap<String, String>();
			HashMap<String, String> map2 = new HashMap<String, String>();
			HashMap<String, String> map3 = new HashMap<String, String>();
			map1.put("nu", "0");
			map1.put("gm","aiqingsuiyue");
			map2.put("nu", "1");
			map2.put("gm", "keguanbukeyi");
			map3.put("nu", "2");
			map3.put("gm", "shangbuqi");
			list.add(map1);
			list.add(map2);
			list.add(map3);
			SimpleAdapter listAdapter = new SimpleAdapter(this, list,
					R.layout.user, new String[] { "nu", "gm" },
					new int[] { R.id.nu1,R.id.gm1});
			setListAdapter(listAdapter);
	 }


	protected void onListItemClick(ListView l, View v, int position, long id) {
	
		Intent i=new Intent();
		i.putExtra("nu", position+"");
		i.setClass(GeQu.this, PlayAc.class);
		startActivity(i);
		super.onListItemClick(l, v, position, id);
	}}
	 