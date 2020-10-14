package ru.skillbranch.devintensive.models.data

import android.util.Log
import androidx.annotation.VisibleForTesting
import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.TextMessage
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class Chat(
    val id: String,
    val title: String,
    val members: List<User> = listOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived: Boolean = false
) {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun unreadableMessageCount(): Int {
        return messages.count { !it.isReaded }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun lastMessageDate(): Date? {
        return messages.lastOrNull()?.date
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun lastMessageShort(): Pair<String, String?> {
        if(messages.isEmpty())
            return "Cообщений еще нет" to members.joinToString(", ")

        val lastMessage = messages.last()
        return lastMessage.shortMessage()
    }

    private fun isSingle(): Boolean = members.size == 1

    fun toChatItem(): ChatItem {
        return if (isSingle()) {
            val user = members.first()
            ChatItem(
                id,
                user.avatar,
                Utils.toInitials(user.firstName, user.lastName) ?: "??",
                "${user.firstName ?: ""} ${user.lastName ?: ""}",
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                user.isOnline
            )
        } else {
            ChatItem(
                id,
                null,
                "",
                title,
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                false,
                ChatType.GROUP,
                lastMessageShort().second
            )
        }
    }

    companion object {
        fun archivedToChatItem(chats: List<Chat>): ChatItem{
            Log.d("M_Chat", "run archivedToChatItem")
            val lastChat = chats.sortedBy { it.lastMessageDate() }.last()
            return  ChatItem(
                "-1",
                "",
                "",
                "Архив чатов",
                lastChat.lastMessageShort().first,
                chats.sumBy { it.unreadableMessageCount() },
                lastChat.lastMessageDate()?.shortFormat(),
                false,
                ChatType.ARCHIVE,
                lastChat.lastMessageShort().second
            )
        }
    }
}

enum class ChatType{
    SINGLE,
    GROUP,
    ARCHIVE
}



