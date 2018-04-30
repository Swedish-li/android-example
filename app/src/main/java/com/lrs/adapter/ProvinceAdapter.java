package com.lrs.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lrs.activity.R;
import com.lrs.model.Province;

import java.util.List;

/**
 * Province Adapter
 * <p>
 * Created by Administrator on 2018/4/30 0030.
 */

public class ProvinceAdapter extends ArrayAdapter<Province> {
    private final int resourceId;

    public ProvinceAdapter(@NonNull Context context, @LayoutRes int textViewResourceId, @NonNull List<Province> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Province province = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            // 缓存view
            viewHolder = new ViewHolder();
            viewHolder.idView = view.findViewById(R.id.province_id);
            viewHolder.nameView = view.findViewById(R.id.province_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.idView.setText(Integer.toString(province.getId()));
        viewHolder.nameView.setText(province.getName());

        return view;
    }

    private class ViewHolder {
        TextView idView;
        TextView nameView;

    }
}
