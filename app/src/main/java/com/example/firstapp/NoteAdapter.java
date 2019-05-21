package com.example.firstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ConcurrentModificationException;
import java.util.List;

public class NoteAdapter  extends BaseAdapter {
    public NotesActivity context;
    private int layout;
    private List<Notes> notes;
    public  NoteAdapter(NotesActivity context, int layout, List<Notes>notes){
        this.context = context;
        this.layout  = layout;
        this.notes = notes;
    }
    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    private class ViewHolder{
        TextView textView;
        ImageView deleteImg;

    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            // LayoutInflater initialization
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.taskname);
            viewHolder.deleteImg= (ImageView)convertView.findViewById(R.id.delete);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final Notes task = notes.get(position);
        viewHolder.textView.setText(task.getName());
        // events processing
        viewHolder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DeleteDialog(task.getName(),task.GetId());
            }
        });
        return convertView;
    }
}
