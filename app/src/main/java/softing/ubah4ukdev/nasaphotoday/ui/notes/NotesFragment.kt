package softing.ubah4ukdev.nasaphotoday.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import softing.ubah4ukdev.nasaphotoday.MainActivity
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentNotesBinding
import softing.ubah4ukdev.nasaphotoday.domain.model.notes.Note
import softing.ubah4ukdev.nasaphotoday.domain.repository.notes.MockNotesRepositoryImpl
import softing.ubah4ukdev.nasaphotoday.ui.extensions.visible
import softing.ubah4ukdev.nasaphotoday.ui.notes.adapter.INoteClickable
import softing.ubah4ukdev.nasaphotoday.ui.notes.adapter.ItemTouchHelperCallback
import softing.ubah4ukdev.nasaphotoday.ui.notes.adapter.NotesAdapter
import softing.ubah4ukdev.nasaphotoday.ui.pictureepic.EpicFragment
import softing.ubah4ukdev.nasaphotoday.viewBinding

class NotesFragment : Fragment(R.layout.fragment_notes), INoteClickable {

    private val adapter by lazy { NotesAdapter(this) }
    private val notesRV by lazy {viewBinding.notesList}
    private lateinit var itemTouchHelper: ItemTouchHelper

    private val viewBinding: FragmentNotesBinding by viewBinding(
        FragmentNotesBinding::bind
    )

    private val notesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory(MockNotesRepositoryImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        notesRV.adapter = adapter

        itemTouchHelper = ItemTouchHelper(object : ItemTouchHelperCallback(adapter) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                adapter.noteByPosition(viewHolder.adapterPosition)?.let {
                    notesViewModel.deleteNote(it)
                    adapter.removeItem(viewHolder.adapterPosition)
                    adapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
                super.onSwiped(viewHolder, i)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                source: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val noteOne: Note? = adapter.noteByPosition(source.adapterPosition)?.also {
                    notesViewModel.noteUpdatePosition(it, target.adapterPosition)
                    adapter.removeItem(source.adapterPosition)
                }
                adapter.noteByPosition(target.adapterPosition)?.let {
                    notesViewModel.noteUpdatePosition(it, source.adapterPosition)
                }
                noteOne?.let {
                    adapter.addItem(
                        it,
                        if (target.adapterPosition > source.adapterPosition) target.adapterPosition - 1 else target.adapterPosition
                    )
                }
                adapter.notifyItemMoved(source.adapterPosition, target.adapterPosition)
                return super.onMove(recyclerView, source, target)
            }
        })
        itemTouchHelper.attachToRecyclerView(notesRV)

        (activity as MainActivity).findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
            ?.apply {
                title = getString(R.string.notes_title)
                val img: AppCompatImageView = findViewById(R.id.toolbar_image)
                img.setImageResource(R.drawable.img_notes)
            }

        (activity as MainActivity).findViewById<AppBarLayout>(R.id.app_layout_bar)?.apply {
            setExpanded(true, true)
        }

        (activity as MainActivity).findViewById<FloatingActionButton>(R.id.fab)
            ?.apply {
                visible { true }
                setOnClickListener {
                    notesViewModel.add_Note(Note(0))
                }
            }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            notesViewModel.loading.collect {
                viewBinding.progress.visible { it }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            notesViewModel.notes.collect {
                adapter.apply {
                    clear()
                    val notes = it ?: return@collect
                    addItems(notes)
                    notifyDataSetChanged()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            notesViewModel.note.collect {
                val note = it ?: return@collect
                adapter.apply {
                    addItem(note)
                    notifyItemInserted(adapter.itemCount)
                    notifyItemChanged(adapter.itemCount - 1)
                    notesRV.smoothScrollToPosition(adapter.itemCount)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            notesViewModel.error.collect {
                val error = it ?: return@collect
                viewBinding.progress.visible { false }

                Snackbar
                    .make(
                        viewBinding.root,
                        error,
                        Snackbar.LENGTH_INDEFINITE
                    )
                    .also {
                        it.view.also {
                            (it.findViewById(com.google.android.material.R.id.snackbar_text) as TextView?)?.maxLines =
                                EpicFragment.MAX_LINES
                        }
                    }
                    .show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            notesViewModel.fetchNotes()
        }
    }

    override fun onNoteClick(position: Int, note: Note) {
        Log.d("nasaDebug", note.toString())
    }

    override fun onNoteUpClick(noteOne: Note, noteTwo: Note) {
        notesRV.smoothScrollToPosition(noteOne.position)
        notesViewModel.noteUpdatePosition(noteOne, noteTwo.position)
        notesViewModel.noteUpdatePosition(noteTwo, noteOne.position)
        Log.d("nasaDebug", "noteOne: ${noteOne.toString()} \n\rnoteTwo: ${noteTwo.toString()}")
    }

    override fun onNoteDownClick(noteOne: Note, noteTwo: Note) {
        notesRV.smoothScrollToPosition(noteTwo.position)
        val tempPos: Int = noteOne.position
        notesViewModel.noteUpdatePosition(noteOne, noteTwo.position)
        notesViewModel.noteUpdatePosition(noteTwo, tempPos)
        Log.d("nasaDebug", "noteOne: ${noteOne.toString()} \n\rnoteTwo: ${noteTwo.toString()}")
    }
}