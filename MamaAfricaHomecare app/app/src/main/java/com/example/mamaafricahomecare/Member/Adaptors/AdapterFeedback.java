package com.example.mamaafricahomecare.Member.Adaptors;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.Member.Models.FeedbackModel;
import com.example.mamaafricahomecare.R;

import java.util.List;

public class AdapterFeedback extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FeedbackModel> items;

    private Context ctx;
    private SessionHandler session;
    private UserModel user;
    public static final String TAG = "Feedback";
    public AdapterFeedback(Context context, List<FeedbackModel> items) {
        this.items = items;
        ctx = context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView txv_comment,txv_reply;
        private CardView card_reply,card_comment;
        public OriginalViewHolder(View v) {
            super(v);

            txv_comment = v.findViewById( R.id.txv_comment);
            txv_reply =v.findViewById(R.id.txv_reply);
            card_reply =v.findViewById(R.id.card_reply);
            card_comment =v.findViewById(R.id.card_comment);

            session=new SessionHandler(ctx);
            user=session.getUserDetails();

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_feedback, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder view = (OriginalViewHolder) holder;

            final FeedbackModel p = items.get(position);

            if(p.getComment().equals("0")){
                view.txv_comment.setVisibility(View.GONE);
                view.card_comment.setVisibility(View.GONE);
            }else{
                view.txv_comment.setText(p.getComment());
            }

            if(p.getReply().equals("0")){
                view.txv_reply.setVisibility(View.GONE);
                view.card_reply.setVisibility(View.GONE);
            }else{
                view.txv_reply.setText(p.getReply());
            }

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}