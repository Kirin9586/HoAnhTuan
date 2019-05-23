package com.example.hoanhtuan_1706020096_11th1c;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

    public class Adapter extends ArrayAdapter<DsMon> {
        private int GroupID;
        private List<DsMon>ds;
        private Context context;
        public Adapter( Context context, int vg,List<DsMon>ds) {
            super(context, vg,ds);
            this.GroupID = vg;
            this.ds = ds;
            this.context = context;
        }
        class ViewHolder{
            TextView textView;
            TextView textView1;
            TextView textView2;
            Button btn;
        }

        @Override
        public View getView(int position,View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = new ViewHolder();
            if(rowView == null){
                rowView = LayoutInflater.from(getContext()).inflate(GroupID,parent,false);
                viewHolder.textView = rowView.findViewById(R.id.txt_mon);
                viewHolder.textView1 = rowView.findViewById(R.id.txt_ma);
                viewHolder.textView2 = rowView.findViewById(R.id.txt_tc);
                viewHolder.btn = rowView.findViewById(R.id.btn_infor);
            }
            else {
                viewHolder = (ViewHolder) rowView.getTag();
            }
            final DsMon ds =getItem(position);
            viewHolder.textView.setText(ds.getTen());
            viewHolder.textView1.setText(ds.getMa());
            viewHolder.textView2.setText(ds.getTinchi());
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,InformationActivity.class);
                    intent.putExtra("ten",ds.getTen());
                    intent.putExtra("ma",ds.getMa());
                    context.startActivity(intent);
                }
            });
            rowView.setTag(viewHolder);
            return rowView;
        }
}
