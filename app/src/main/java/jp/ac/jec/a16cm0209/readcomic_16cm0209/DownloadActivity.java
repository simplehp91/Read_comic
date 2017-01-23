package jp.ac.jec.a16cm0209.readcomic_16cm0209;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nguyenhiep on 1/23/2017 AD.
 */

public class DownloadActivity extends Activity {

    private ListView mLVChapter;
    String[] mArrChapter = { "Chapter 1 - Tap 1", "Chapter 2 - Tap 1",
            "Chapter 3 - Tap 1" };
    private AlertDialog.Builder builder;
    private ProgressDialog mProgressDialog;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_download);
        mLVChapter = (ListView) findViewById(R.id.download_lv);
        file = new File("/sdcard/chapter1.zip");
        createAlDiaglog();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mArrChapter);
        mLVChapter.setAdapter(adapter);

        mLVChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (builder != null) {
                    builder.show();

                }
            }
        });

    }

    public void createAlDiaglog() {
        final String itemDownload[] = { "Download"};
        Log.i("TTTH", "Clicked on Item Chapter download");
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Download chapter ");
        builder.setIcon(R.drawable.download);
        builder.setItems(itemDownload, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                mProgressDialog = new ProgressDialog(DownloadActivity.this);
                mProgressDialog.setMessage("Please wait");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.setMax(100);
                mProgressDialog
                        .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                DownloadFile downloadFile = new DownloadFile();
                downloadFile.execute("http://and-project-lbd.googlecode.com/files/chapter1.zip");
                mProgressDialog.show();
            }
        });

    }

    public class DownloadFile extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            int count;
            try {
                URL url = new URL(params[0]);
                URLConnection conn = url.openConnection();
                conn.connect();
                int lenghtOfFile = conn.getContentLength();
                // Download file
                InputStream input = new BufferedInputStream(url.openStream());

                OutputStream output = new FileOutputStream(file);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    publishProgress((int) (total * 100 / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                // TODO: handle exception

            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            mProgressDialog.dismiss();
            Intent in = new Intent(getApplicationContext(),
                    ReadComicActivity.class);
            in.putExtra("path", file.getPath());
            startActivity(in);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            mProgressDialog.setProgress(values[0]);

        }

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }
}

