package com.mughitszufar.whatsappclown.listener


interface ChatsClickListener {
    fun onChatClicked(
        chatId: String?,
        otherUserId: String?,
        chatsImageUrl: String?,
        chatsName: String?)
}