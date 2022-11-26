package by.mankevich.currencyexchanger.core.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.mankevich.currencyexchanger.core.domain.IEntity

abstract class BaseAdapter<K : IEntity, VB: ViewBinding ,T : BaseViewHolder<K, VB>>(
    var entitiesList: List<K>
) : RecyclerView.Adapter<T>() {

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(entitiesList[position])
    }

    override fun getItemCount(): Int {
        return entitiesList.size
    }

}