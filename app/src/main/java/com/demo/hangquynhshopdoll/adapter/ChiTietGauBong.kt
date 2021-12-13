package com.demo.hangquynhshopdoll.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.hangquynhshopdoll.R
import com.demo.hangquynhshopdoll.models.Hours
import com.demo.hangquynhshopdoll.models.RestaurentModel
import java.text.SimpleDateFormat
import java.util.*

class ChiTietGauBong(val restaurantList: List<RestaurentModel?>?, val clickListener: RestaurantListClickListener): RecyclerView.Adapter<ChiTietGauBong.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChiTietGauBong.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cac_loai_gau_bong, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChiTietGauBong.MyViewHolder, position: Int) {
        holder.bind(restaurantList?.get(position))
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(restaurantList?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList?.size!!
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        val tvRestaurantName: TextView = view.findViewById(R.id.tvRestaurantName)
        val tvRestaurantAddress: TextView = view.findViewById(R.id.tvRestaurantAddress)
        val tvRestaurantHours: TextView = view.findViewById(R.id.tvRestaurantHours)

        fun bind(restaurentModel: RestaurentModel?) {
            tvRestaurantName.text = restaurentModel?.name
            tvRestaurantAddress.text = "Địa chỉ: "+restaurentModel?.address
            tvRestaurantHours.text = " Giờ trong ngày: " + getTodaysHours(restaurentModel?.hours!!)

            Glide.with(thumbImage)
                .load(restaurentModel?.image)
                .into(thumbImage)
        }
    }

    private fun getTodaysHours(hours: Hours): String? {
        val calendar: Calendar =  Calendar.getInstance()
        val date: Date = calendar.time
        val day : String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
        return when(day) {
            "Sunday" -> hours.Sunday
            "Monday" -> hours.Monday
            "Tuesday" -> hours.Tuesday
            "Wednesday" -> hours.Wednesday
            "Thursday" -> hours.Thursday
            "Friday" -> hours.Friday
            "Saturday" -> hours.Saturday
            else -> hours.Sunday
        }
    }

    interface RestaurantListClickListener {
        fun onItemClick(restaurantModel: RestaurentModel)
    }

}