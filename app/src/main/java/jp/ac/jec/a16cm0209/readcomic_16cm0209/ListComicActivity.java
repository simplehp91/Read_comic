package jp.ac.jec.a16cm0209.readcomic_16cm0209;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by nguyenhiep on 1/23/2017 AD.
 */

public class ListComicActivity extends ListActivity {

    private ComicAdapter mComicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list_comic);


        mComicAdapter = new ComicAdapter(this);

        setListAdapter(mComicAdapter);
        ListView listView = getListView();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub
                Log.i("TTTH", "Item Long Click Listener Event");
                Toast.makeText(ListComicActivity.this, "Item Click Listener Event", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private Intent intent;

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Log.i("TTTH", "Item Click Listener Event");
                if(position == 0 ){
                    if(intent == null){
                        intent = new Intent(ListComicActivity.this, DownloadActivity.class);
                    }
                    startActivity(intent);
                }
            }
        });
    }


}