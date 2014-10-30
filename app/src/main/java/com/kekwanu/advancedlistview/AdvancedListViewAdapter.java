package com.kekwanu.advancedlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import com.kekwanu.advancedlistview.StateData;

/**
 * Created by onwuneme on 10/29/14.
 */
public class AdvancedListViewAdapter extends BaseAdapter {
    private final static String TAG = AdvancedListViewAdapter.class.getCanonicalName();
    private ArrayList<StateData> content;
    private Context context;

    public AdvancedListViewAdapter(Context context){//}, ArrayList<State> content) {
        super();
        Log.i(TAG, "AdvancedListViewAdapter - constructor...");

        this.context = context;
        this.content = new ArrayList<StateData>();
    }

    public void setListItems(ArrayList<StateData> newList) {
        Log.d(TAG,"setListItems");

        content = newList;
        notifyDataSetChanged();
    }

    public void addItem(StateData stateObj){
        Log.d(TAG,"addItem");

        content.add(stateObj);
        notifyDataSetChanged();
    }

    public void removeItem(StateData stateObj){
        Log.d(TAG,"addItem");

        content.remove(stateObj);
        notifyDataSetChanged();
    }

    public void clear() {
        Log.d(TAG,"clear");

        content.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public StateData getItem(int position) {
        return content.get(position);
    }

    @Override
    public long getItemId(int position) {
        return content.indexOf( content.get(position) );
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){ //the first time around, the view is null, so inflate it

            //inflate using the system inflater. This returns a reference to the inflater,
            // which inflates the resource XML to the corresponding view
            LayoutInflater inflater = LayoutInflater.from(context);

            //use the inflate method this way.
            convertView = inflater.inflate(R.layout.state_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getItem(position));
                }
            });

        }

        ((ViewHolder) convertView.getTag()).name.setText(getItem(position).getName());

        return convertView;

    }

    static class ViewHolder {
        //SmartImageView image;
        TextView name;
    }
}
