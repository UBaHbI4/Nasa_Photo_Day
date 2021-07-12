package softing.ubah4ukdev.nasaphotoday.ui.picturemars


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
import softing.ubah4ukdev.nasaphotoday.domain.model.Mars


/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.ui.pictureearth

Created by Ivan Sheynmaer

2021.07.12
v1.0
 */
class MarsAdapter :
    RecyclerView.Adapter<MarsAdapter.ViewHolder?>() {

    private val marsList = ArrayList<Mars>()

    private val CAMERA_NAME_TEXT = "Camera name: "
    private val CAMERA_FULL_NAME_TEXT = "Camera full name: "
    private val ROVER_NAME_TEXT = "Rover name: "
    private val ROVER_LANDING_DATE_TEXT = "Rover landing date: "
    private val ROVER_LAUNCH_DATE_TEXT = "Rover launch date: "
    private val ROVER_STATUS_TEXT = "Rover status : "
    private val DATE = "Date: "

    fun addItems(moviesList: ArrayList<Mars>) {
        marsList.addAll(moviesList)
        notifyDataSetChanged()
    }

    fun clear() = marsList.clear()

    fun getData(): ArrayList<Mars> = marsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mars,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mars = marsList[position]

        with(holder) {

            mars.cameraName?.let {
                (CAMERA_NAME_TEXT + it).also { cameraName.text = it }
            }

            mars.cameraFullName?.let {
                (CAMERA_FULL_NAME_TEXT + it).also { cameraFullName.text = it }
            }

            mars.roverName?.let {
                (ROVER_NAME_TEXT + it).also { roverName.text = it }
            }

            mars.roverLandingDate?.let {
                (ROVER_LANDING_DATE_TEXT + it).also { roverLandingDate.text = it }
            }

            mars.roverLaunchDate?.let {
                (ROVER_LAUNCH_DATE_TEXT + it).also { roverLaunchDate.text = it }
            }

            mars.roverStatus?.let {
                (ROVER_STATUS_TEXT + it).also { status.text = it }
            }

            mars.earth_date?.let {
                (DATE + it).also { date.text = it }
            }

            mars.imgSrc?.let {
                Glide.with(photoMars)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.ic_wikipedia)
                    .placeholder(R.drawable.ic_no_image)
                    .into(photoMars)
            }

            itemView.rootView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale)
        }
    }

    override fun getItemCount() = marsList.size

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val photoMars: AppCompatImageView = itemView.findViewById(R.id.photo_mars)
        val cameraName: TextView = itemView.findViewById(R.id.camera_name)
        val cameraFullName: TextView = itemView.findViewById(R.id.camera_full_name)
        val roverName: TextView = itemView.findViewById(R.id.rover_name)
        val roverLandingDate: TextView = itemView.findViewById(R.id.rover_landing_date)
        val roverLaunchDate: TextView = itemView.findViewById(R.id.rover_launch_date)
        val status: TextView = itemView.findViewById(R.id.status)
        val date: TextView = itemView.findViewById(R.id.date)
    }

}