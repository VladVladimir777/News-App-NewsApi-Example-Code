package com.example.code.newsappexamplecode.fragmentProfile.adapterProfileList

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.code.newsappexamplecode.R
import kotlinx.android.synthetic.main.item_profile_list.view.*

class AdapterProfileList(var data: List<ItemProfileList>) :
    RecyclerView.Adapter<AdapterProfileList.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_profile_list, viewGroup, false)
        )
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(data[i])
    }


    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ItemProfileList) = with(itemView) {
            itemView.etProfileItem.hint = item.hint
            itemView.etProfileItem.setText(item.text)
            if (adapterPosition == 2) itemView.etProfileItem.inputType = InputType.TYPE_CLASS_PHONE
            itemView.etProfileItem.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    data[adapterPosition].text = s.toString().trim()
                }

            })
        }
    }


    internal fun getItem(id: Int): ItemProfileList {
        return data[id]
    }

}