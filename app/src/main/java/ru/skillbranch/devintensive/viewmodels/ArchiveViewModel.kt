/**
 * Created by Ilia Shelkovenko on 14.10.2020.
 */

package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class ArchiveViewModel : ViewModel() {
    private val chatRepository = ChatRepository
    private val archiveChats = Transformations.map(chatRepository.loadChats()) { archiveChats ->
        return@map archiveChats
            .filter { it.isArchived }
            .map { it.toChatItem() }
            .sortedBy { it.id.toInt()
            }
    }

    fun getArchiveChatData() : LiveData<List<ChatItem>> {
        return archiveChats
    }

    fun addToArchive(chatId: String) {
        val chat = chatRepository.find(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = true))
    }

    fun restoreFromArchive(chatId: String){
        val chat = chatRepository.find(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))
    }
}