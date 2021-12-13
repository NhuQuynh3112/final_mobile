package com.demo.hangquynhshopdoll.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.hangquynhshopdoll.R
import com.demo.hangquynhshopdoll.models.Menus

class DatHang(val menuList: List<Menus?>?): RecyclerView.Adapter<DatHang.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DatHang.MyViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.chi_tiet_gau_bong, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatHang.MyViewHolder, position: Int) {
        holder.bind(menuList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(menuList == null) 0  else menuList.size
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        val menuName: TextView = view.findViewById(R.id.menuName)
        val menuPrice: TextView = view.findViewById(R.id.menuPrice)
        val menuQty: TextView = view.findViewById(R.id.menuQty)

        fun bind(menu: Menus) {
            menuName.text = menu?.name!!
            menuPrice.text = "Tổng tiền:" + String.format("%.2f",menu?.price * menu.totalInCart) + "VND"
            menuQty.text = "Qty :" + menu?.totalInCart

            Glide.with(thumbImage)
                .load(menu?.url)
                .into(thumbImage)
        }
    }
}