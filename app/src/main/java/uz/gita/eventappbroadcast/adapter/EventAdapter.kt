package uz.gita.eventappbroadcast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.eventappbroadcast.utils.SwitchData
import uz.gita.eventappbroadcast.data.ActionEnum
import uz.gita.eventappbroadcast.databinding.ItemSwitchBinding

class EventAdapter(private val dataList: List<SwitchData>) : Adapter<EventAdapter.ItemHolder>() {

    private var onClick: ((ActionEnum, Boolean) -> Unit)? = null

    fun setClickListener(block: (ActionEnum, Boolean) -> Unit) {
        onClick = block
    }

    inner class ItemHolder(private val binding: ItemSwitchBinding) : ViewHolder(binding.root) {

        init {
            binding.itemSwitch.setOnCheckedChangeListener { _, isChecked ->
                val id = dataList[adapterPosition].id
                onClick?.invoke(id, isChecked)
            }
        }

        fun bind() {
            binding.apply {
                itemSwitch.setText(dataList[adapterPosition].nameRes)

                val icon = dataList[adapterPosition].iconRes
                val drawable = ContextCompat.getDrawable(binding.root.context, icon)
                itemSwitch.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawable,
                    null,
                    null,
                    null
                )
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ItemHolder {
        return ItemHolder(
            ItemSwitchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventAdapter.ItemHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = dataList.size
}