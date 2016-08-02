package com.pick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 10 on 2016-07-11.
 */
public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.MessageViewHolder> {


    View itemRootView;
    //아이템으로 그려질 자료 원천지를 선언
    private ArrayList<MessageContent> memberList = new ArrayList<MessageContent>();

    public void messageItemInset(MessageContent valueObject){
        memberList.add(valueObject);
    }
    public void messageAllInsert(ArrayList<MessageContent> members){
        memberList.addAll(members);
    }
    static MessageListActivity owner;
    static Bundle bundle;

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        public ImageView messageProfileImageWD;
        public TextView messageNameWD;
        public TextView messageContentWD;
        public com.github.ivbaranov.mfb.MaterialFavoriteButton bookMarkWD;
        public MessageViewHolder(View itemView) {
            super(itemView);
            messageProfileImageWD = (ImageView) itemView.findViewById(R.id.message_profile_image);
            messageNameWD = (TextView) itemView.findViewById(R.id.message_by);
            messageContentWD = (TextView) itemView.findViewById(R.id.message_content);
            bookMarkWD = (com.github.ivbaranov.mfb.MaterialFavoriteButton) itemView.findViewById(R.id.book_mark_button);


        }
    }
    /*
        아이템이 그려질 레이아웃을 Inflalation하여 viewHolder에 넘겨줌
     */

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemRootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(itemRootView);
    }

    /*
        이 메소드에서는 실제 위젯과 데이터를 바인딩 시킨다.
        또한 각 위젯에 이벤트를 등록하고 싶다면 여기서 이벤트를 등록시키도록 한다.
     */

    @Override
    public void onBindViewHolder(final MessageViewHolder viewHolder, int position) {
        final MessageContent valueObject = memberList.get(position);
        viewHolder.messageProfileImageWD.setImageDrawable(valueObject.messageProfileImage);
        viewHolder.messageNameWD.setText(valueObject.messageName);
        viewHolder.messageContentWD.setText(String.valueOf(valueObject.messageContent));

        itemRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentChatroom = new Intent(MessageListActivity.getItemContext(), ChatroomActivity.class);
                intentChatroom.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//
                MessageListActivity.getItemContext().startActivity(intentChatroom);
            }
        });

    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }


}
