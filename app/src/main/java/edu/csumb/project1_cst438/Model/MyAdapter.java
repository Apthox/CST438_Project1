package edu.csumb.project1_cst438.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import java.util.List;

import edu.csumb.project1_cst438.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> titles;
    private List<Integer> IDs;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    MyAdapter(Context context, List<Integer> IDs, List<String> titles){
        this.mInflater = LayoutInflater.from(context);
        this.IDs = IDs;
        this.titles = titles;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myTextView;
        ViewHolder(View itemView){
            super(itemView);
            myTextView = itemView.findViewById(R.id.dataname);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            if (mClickListener !=null){
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        }

        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            View view = mInflater.inflate(R.layout.recy_row,parent,false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            String dataText = titles.get(position);
            holder.myTextView.setText(dataText);
        }
        @Override
        public int getItemCount(){
            return titles.size();
        }
        String getTitle(int pos){
            return titles.get(pos);
        }

        int getID(int pos) {
            return IDs.get(pos);
        }

        void setClickListener(ItemClickListener itemClickListener){
            this.mClickListener = itemClickListener;
        }
}

