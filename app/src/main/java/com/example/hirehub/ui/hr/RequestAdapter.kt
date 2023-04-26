package com.example.hirehub.ui.hr

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.databinding.OfferItemBinding
import com.example.hirehub.databinding.RequestItemBinding
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.OfferCategory
import com.example.hirehub.model.entities.OfferWithCategory
import com.example.hirehub.model.entities.relations.UserOfferPair

//import com.example.hirehub.model.entities.OfferWithCategoryOffer

class RequestAdapter :
    ListAdapter<UserOfferPair, RequestAdapter.UserOfferPairViewHolder>(OFFER_COMPARATOR) {

    private var onClickListener: OnClickListener? = null

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    class UserOfferPairViewHolder(private val itemBinding: RequestItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(userOffer: UserOfferPair) {
            //Log.d("OfferAdapter", offer.offer_name + offer.category_name)
            itemBinding.tvRequest.text = userOffer.user.userName + " sent request for " +
                userOffer.offers[0].offerName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserOfferPairViewHolder {
        val binding = RequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserOfferPairViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserOfferPairViewHolder, position: Int) {
        val offer: UserOfferPair = getItem(position)
        holder.bind(offer)
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(offer)
        }
    }

    companion object {
        private val OFFER_COMPARATOR = object : DiffUtil.ItemCallback<UserOfferPair>() {
            override fun areItemsTheSame(oldItem: UserOfferPair, newItem: UserOfferPair): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: UserOfferPair, newItem: UserOfferPair): Boolean {
                return oldItem.offers == newItem.offers && oldItem.user == newItem.user
            }
        }
    }

    interface OnClickListener {
        fun onClick(item: UserOfferPair)
    }
}

