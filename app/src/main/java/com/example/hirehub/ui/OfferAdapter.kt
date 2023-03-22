package com.example.hirehub.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.databinding.OfferItemBinding
import com.example.hirehub.model.entities.Offer

class OfferAdapter : ListAdapter<Offer, OfferAdapter.OfferViewHolder>(OFFER_COMPARATOR) {

    class OfferViewHolder(private val itemBinding: OfferItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(offer: Offer) {
            itemBinding.tvOfferName.text = offer.offerName
            itemBinding.tvCategory.text = offer.offerCategoryId
            itemBinding.tvCity.text = offer.offerCity
            itemBinding.tvCompanyName.text = offer.offerCompanyName
            itemBinding.tvDescription.text = offer.offerDescription
            itemBinding.tvPosition.text = offer.offerPosition
            itemBinding.tvSalary.text = offer.offerSalary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer: Offer = getItem(position)
        holder.bind(offer)
    }

    companion object {
        private val OFFER_COMPARATOR = object : DiffUtil.ItemCallback<Offer>() {
            override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
                return oldItem.offer_id == newItem.offer_id
            }
        }
    }
}