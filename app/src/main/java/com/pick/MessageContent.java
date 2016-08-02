package com.pick;

import android.graphics.drawable.Drawable;

/**
 * Created by 10 on 2016-07-28.
 */
public class MessageContent {
    public Drawable messageProfileImage;
    public String messageName;
    public String messageContent;

    public MessageContent(String memberName, String messageContent, Drawable memberImage) {
        this.messageName = memberName;
        this.messageContent = messageContent;
        this.messageProfileImage = memberImage;
    }
}
