package com.example.hirehub.ui.seeker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.databinding.OfferItemBinding
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
//import com.example.hirehub.model.entities.OfferWithCategoryOffer

class RequestAdapter :
    ListAdapter<Offer, RequestAdapter.OfferViewHolder>(OFFER_COMPARATOR) {

    private var onClickListener: OnClickListener? = null

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
//class OfferAdapter : ListAdapter<OfferWithCategoryOffer, OfferAdapter.OfferViewHolder>(OFFER_COMPARATOR) {

    class OfferViewHolder(private val itemBinding: OfferItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(offer: Offer) {
//        fun bind(offer: OfferWithCategoryOffer) {
//            itemBinding.tvOfferName.text = offer.offer.offerName
//            itemBinding.tvCategory.text = offer.offerCategory.categoryName
//            itemBinding.tvCity.text = offer.offer.offerCity
//            itemBinding.tvCompanyName.text = offer.offer.offerCompanyName
//            itemBinding.tvDescription.text = offer.offer.offerDescription
//            itemBinding.tvPosition.text = offer.offer.offerPosition
//            itemBinding.tvSalary.text = offer.offer.offerSalary
            itemBinding.tvOfferName.text = offer.offerName
            itemBinding.tvCategory.text = offer.offerCategoryId.toString()
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
//        val offer: OfferWithCategoryOffer = getItem(position)
        holder.bind(offer)
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(offer)
        }
    }

    companion object {
        private val OFFER_COMPARATOR = object : DiffUtil.ItemCallback<Offer>() {
//        private val OFFER_COMPARATOR = object : DiffUtil.ItemCallback<OfferWithCategoryOffer>() {
            override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
                return oldItem.offer_id == newItem.offer_id
            }
        }
    }

    // Declaring the interface in adapter or we can declare it in seperate file
    interface OnClickListener {
        fun onClick(item: Offer)
    }
}

