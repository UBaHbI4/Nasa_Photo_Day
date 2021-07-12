package softing.ubah4ukdev.nasaphotoday.ui.pictureepic


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.domain.model.Epic

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.ui.pictureearth

Created by Ivan Sheynmaer

2021.07.12
v1.0
 */
class EpicAdapter :
    RecyclerView.Adapter<EpicAdapter.ViewHolder?>() {

    private val earths = ArrayList<Epic>()

    private val IDENTIFIER = "Identifier: "

    fun addItems(moviesList: ArrayList<Epic>) {
        earths.addAll(moviesList)
        notifyDataSetChanged()
    }

    fun clear() = earths.clear()

    fun getData(): ArrayList<Epic> = earths

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_earth,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val earth = earths[position]

        with(holder) {
            earth.caption?.let {
                caption.text = it
            }

            earth.identifier?.let {
                (IDENTIFIER + it).also { identifier.text = it }
            }

            earth.date?.let {
                date.text = it
            }

            earth.image?.let {
                Glide.with(photos)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.ic_no_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(photos)
            }

            itemView.rootView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale)
        }
    }

    override fun getItemCount() = earths.size

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val caption: TextView = itemView.findViewById(R.id.caption)
        val photos: AppCompatImageView = itemView.findViewById(R.id.photo_earth)
        val identifier: TextView = itemView.findViewById(R.id.identifier)
        val date: TextView = itemView.findViewById(R.id.date)
    }
}