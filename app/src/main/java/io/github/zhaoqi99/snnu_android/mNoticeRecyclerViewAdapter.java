package io.github.zhaoqi99.snnu_android;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;

import java.util.List;

public class mNoticeRecyclerViewAdapter extends RecyclerView.Adapter<mNoticeRecyclerViewAdapter.ViewHolder>{
    private List<NoticeMessage> messageList;

    public mNoticeRecyclerViewAdapter( List<NoticeMessage> messageList)
    {
        this.messageList = messageList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title;
        TextView Date;
        TextView Department;
        public ViewHolder(View view)
        {
            super(view);
            Title = (TextView)view.findViewById(R.id.txt_Title);
            Date = (TextView)view.findViewById(R.id.txt_Date);
            Department = (TextView)view.findViewById(R.id.txt_Department);
        }
    }
    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoticeMessage message = messageList.get(position);
        holder.Title.setText(message.GetTitle());
        holder.Date.setText(message.GetDate());
        holder.Department.setText(message.GetDepartment());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_recycler_layout,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                String url=messageList.get(position).GetLink();
                Uri uri=Uri.parse(url);
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }
}
