package com.example.sample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ComplaintList extends ArrayAdapter<Complaint> {

    private Activity context;
    private List<Complaint> complaintList;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView description = (TextView) listViewItem.findViewById(R.id.description);
        TextView location = (TextView) listViewItem.findViewById(R.id.location);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.image);

        Complaint complaint = complaintList.get(position);

        description.setText(complaint.desc);
        location.setText("Lat : " + complaint.latitude + "  Long : " + complaint.longitude);

        /*byte[] decodedString = Base64.decode(complaint.encode, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        image.setImageBitmap(decodedByte); */

        byte[] decodeByte = Base64.decode(complaint.encode,0);
        Bitmap b= BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.length);
        image.setImageBitmap(b);
        return listViewItem;
    }

    public ComplaintList (Activity context, List<Complaint> complaintList) {
        super(context, R.layout.list_layout, complaintList);
        this.context = context;
        this.complaintList = complaintList;
    }
}
