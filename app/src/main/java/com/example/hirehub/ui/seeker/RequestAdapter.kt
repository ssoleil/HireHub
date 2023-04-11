package com.example.hirehub.ui.seeker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.databinding.MyRequestBinding
import com.example.hirehub.model.entities.Offer

class RequestAdapter :
    ListAdapter<Offer, RequestAdapter.RequestViewHolder>(OFFER_COMPARATOR) {

    class RequestViewHolder(private val itemBinding: MyRequestBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(offer: Offer) {

            val off = offer.offerName + ", " + offer.offerPosition + ", " +
                    offer.offerSalary + "\n"  + offer.offerCompanyName

            itemBinding.tvStatus.text = offer.offerStatus
            itemBinding.tvOffer.text = off

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val binding = MyRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
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

