package edu.csumb.project1_cst438.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.csumb.project1_cst438.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String>mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    MyAdapter(Context context, List<String>data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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
        public void onBindViewHolder(ViewHolder holder,int position){
            String dataText = mData.get(position);
            holder.myTextView.setText(dataText);
        }
        @Override
        public int getItemCount(){
            return mData.size();
        }
        String getItem(int id){
            return mData.get(id);
        }
        void setClickListener(ItemClickListener itemClickListener){
            this.mClickListener = itemClickListener;
        }
}
//    private String[] mDataset;
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        public TextView textView;
//        public MyViewHolder(TextView v){
//            super(v);
//            textView = v;
//        }
//    }
//    public MyAdapter(String[] myDataset){
//        mDataset = myDataset;
//    }
//    @Override
//    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        textView v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view,parent,false);
//
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;
//    }
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position){
//        holder.textView.setText(mDataset[position]);
//    }
//    @Override
//    public int getItemCount(){
//        return mDataset.length;
//    }
//}
