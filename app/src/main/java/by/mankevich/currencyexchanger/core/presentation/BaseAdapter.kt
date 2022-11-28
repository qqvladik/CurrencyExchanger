package by.mankevich.currencyexchanger.core.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.mankevich.currencyexchanger.core.domain.IEntity

abstract class BaseAdapter<K : IEntity, VB: ViewBinding ,T : BaseViewHolder<K, VB>>(
    private var entitiesList: List<K>
) : RecyclerView.Adapter<T>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(entitiesList: List<K>){
        this.entitiesList = entitiesList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(entitiesList[position])
    }

    override fun getItemCount(): Int {
        return entitiesList.size
    }

}