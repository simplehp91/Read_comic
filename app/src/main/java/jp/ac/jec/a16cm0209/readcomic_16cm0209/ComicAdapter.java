package jp.ac.jec.a16cm0209.readcomic_16cm0209;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nguyenhiep on 1/23/2017 AD.
 */

public class ComicAdapter extends BaseAdapter {

    ArrayList<Comic> arrComic = new ArrayList<Comic>();
    private Context mContext;

    public ComicAdapter(Context context) {
        mContext = context;

        arrComic.add(new Comic("Daragon Ball", "Bộ truyện daragon ball ",
                R.drawable.image1));
        arrComic.add(new Comic("Doremon", "Bộ truyện doremon ",
                R.drawable.image2));
        arrComic.add(new Comic("Conan", "Bộ truyện conan ", R.drawable.image3));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrComic.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrComic.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return arrComic.get(position).mImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(mContext);
        LinearLayout anItemLayout = (LinearLayout) inflater.inflate(
                R.layout.itemlayout, null);

        ImageView anIV = (ImageView) anItemLayout.findViewById(R.id.itemiv);
        TextView anLargeTV = (TextView) anItemLayout.findViewById(R.id.largetv);
        TextView anSmallTV = (TextView) anItemLayout.findViewById(R.id.smalltv);

        // anIV.setImageResource(mArrDrawable[position]);
        // anLargeTV.setText(mArrTitle[position]);
        // anSmallTV.setText(mArrContent[position]);
        anIV.setImageResource((int) arrComic.get(position).mImage);
        anLargeTV.setText(arrComic.get(position).mTitle);
        anSmallTV.setText(arrComic.get(position).mContent);
        return anItemLayout;
    }

}
