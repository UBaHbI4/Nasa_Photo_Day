package softing.ubah4ukdev.nasaphotoday.ui.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.domain.model.notes.Note
import softing.ubah4ukdev.nasaphotoday.ui.extensions.visible
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.ui.notes.adapter

Created by Ivan Sheynmaer

2021.07.22
v1.0
 */
class NotesAdapter(noteClickable: INoteClickable) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder?>() {

    private val notes = ArrayList<Note>()
    private val iNoteClickable = noteClickable

    //Добавить список заметок
    fun addItems(noteList: ArrayList<Note>) = notes.addAll(noteList)

    //Добавить заметку
    fun addItem(note: Note) = notes.add(note)

    //Добавить заметку в позицию position
    fun addItem(note: Note, position: Int) = notes.add(position, note)

    //Удалить заметку с позицией position
    fun removeItem(position: Int) {
        notes.removeAt(position)
    }

    //Вернуть заметку с позицией position
    fun noteByPosition(position: Int): Note? {
        return notes[position] ?: null
    }

    //Очистить список заметок
    fun clear() = notes.clear()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val note = notes[position]
        with(holder) {
            title.text = note.title

            noteText.text = note.note
            ("Дата создания: " + SimpleDateFormat("dd.MM.yyyy")
                .format(Date(note.dateModify))).also { dateModify.text = it }

            ("Дата изменения: " + SimpleDateFormat("dd.MM.yyyy")
                .format(Date(note.dateCreated))).also { dateCreated.text = it }
            rect.setBackgroundColor(note.color);

            "Приоритет: ${note.priority}".also { priority.text = it }

            //Кнопка вверх в заметке
            moveUp.apply {
                setOnClickListener {
                    layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                        iNoteClickable.onNoteDownClick(
                            notes[currentPosition],
                            notes[currentPosition - 1]
                        )
                        notes.removeAt(currentPosition).apply {
                            notes.add(currentPosition - 1, this)
                        }
                        notifyItemMoved(currentPosition, currentPosition - 1)
                        notifyItemChanged(currentPosition)
                        notifyItemChanged(currentPosition - 1)
                    }
                }
                visible { position > 0 }
            }
            //Кнопка вниз в заметке
            moveDown.apply {
                setOnClickListener {
                    layoutPosition.takeIf { it < notes.size - 1 }?.also { currentPosition ->
                        iNoteClickable.onNoteDownClick(
                            notes[currentPosition],
                            notes[currentPosition + 1]
                        )
                        notes.removeAt(currentPosition).apply {
                            notes.add(currentPosition + 1, this)
                        }
                        notifyItemMoved(currentPosition, currentPosition + 1)
                        notifyItemChanged(currentPosition)
                        notifyItemChanged(currentPosition + 1)
                    }
                }
                visible { position < itemCount - 1 }
            }
            itemView.rootView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale)
        }
    }

    override fun getItemCount() = notes.size

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val title: TextView = itemView.findViewById(R.id.title)
        val noteText: TextView = itemView.findViewById(R.id.note_text)
        val rect: LinearLayoutCompat = itemView.findViewById(R.id.rect)
        val dateCreated: TextView = itemView.findViewById(R.id.date_created)
        val dateModify: TextView = itemView.findViewById(R.id.date_modify)
        val priority: TextView = itemView.findViewById(R.id.priority)
        val moveUp: AppCompatImageButton = itemView.findViewById(R.id.up_btn)
        val moveDown: AppCompatImageButton = itemView.findViewById(R.id.down_btn)

        override fun onClick(v: View?) {
            iNoteClickable.onNoteClick(
                adapterPosition,
                notes[adapterPosition]
            )
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}