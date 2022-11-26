package by.mankevich.currencyexchanger.base.presentation

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.mankevich.currencyexchanger.base.domain.IEntity

private const val TAG = "RAMBaseViewHolder"

abstract class BaseViewHolder<T: IEntity, VB: ViewBinding>(
    val binding: VB,
    private val onItemClick: (T) -> Unit
): RecyclerView.ViewHolder(binding.root){

    fun bind(entity: T){
        itemView.setOnClickListener{
            Log.d(TAG, "item Click ")
            onItemClick(entity)
        }
        bindView(entity)
    }

    protected abstract fun bindView(entity: T)
}