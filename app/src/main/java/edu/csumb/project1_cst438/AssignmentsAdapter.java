package edu.csumb.project1_cst438;

import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mAssignmentTitle;
        public LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            mAssignmentTitle = (TextView) itemView.findViewById(R.id.assignment_title_tv);
            parentLayout = itemView.findViewById(R.id.parent_layout); // problem starts here
        }
    }

    private List<Assignment> mAssignments;
    private Context mContext;


    public AssignmentsAdapter(Context context, List<Assignment> assignments) {
        mAssignments = assignments;
        mContext = context;
    }

    @Override
    public AssignmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View assignmentView = inflater.inflate(R.layout.item_assignment, parent, false);

        ViewHolder viewHolder = new ViewHolder(assignmentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AssignmentsAdapter.ViewHolder viewHolder, final int position) {
        Assignment assignment = mAssignments.get(position);

        TextView textView = viewHolder.mAssignmentTitle;
        textView.setText(assignment.getTitle());

        // space here for more items

        // implementation for onclick to got to another activity
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DisplayAssignmentInfo.class);
                intent.putExtra("Assignment", mAssignments.get(position)); // add the object to be viewed
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAssignments.size();
    }
}