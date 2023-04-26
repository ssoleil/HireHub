package com.example.hirehub.ui.seeker

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.databinding.OfferItemBinding
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.OfferWithCategory

//import com.example.hirehub.model.entities.OfferWithCategoryOffer

class OfferAdapter :
    ListAdapter<OfferWithCategory, OfferAdapter.OfferViewHolder>(OFFER_COMPARATOR) {

    private var onClickListener: OnClickListener? = null

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    class OfferViewHolder(private val itemBinding: OfferItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(offer: OfferWithCategory) {
            //Log.d("OfferAdapter", offer.offer_name + offer.category_name)
            itemBinding.tvCategory.text = offer.category_name
            itemBinding.tvOfferName.text = offer.offer_name
            itemBinding.tvCity.text = offer.offer_city
            itemBinding.tvCompanyName.text = offer.offer_company_name
            itemBinding.tvDescription.text = offer.offer_description
            itemBinding.tvPosition.text = offer.offer_position
            itemBinding.tvSalary.text = offer.offer_salary

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer: OfferWithCategory = getItem(position)
        holder.bind(offer)
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(offer)
        }
    }

    companion object {
        private val OFFER_COMPARATOR = object : DiffUtil.ItemCallback<OfferWithCategory>() {
            override fun areItemsTheSame(oldItem: OfferWithCategory, newItem: OfferWithCategory): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: OfferWithCategory, newItem: OfferWithCategory): Boolean {
                return oldItem.offer_id == newItem.offer_id
            }
        }
    }

    // Declaring the interface in adapter or we can declare it in seperate file
    interface OnClickListener {
        fun onClick(item: OfferWithCategory)
    }
}

