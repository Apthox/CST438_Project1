package edu.csumb.project1_cst438.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.csumb.project1_cst438.R;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {
    private List<String> categories;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    ExampleAdapter(Context context, List<String>data){
        this.mInflater = LayoutInflater.from(context);
        this.categories = data;
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

