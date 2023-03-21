package com.example.hirehub.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehub.databinding.OfferItemBinding
import com.example.hirehub.model.entities.Offer

class OfferAdapter(var offers: List<Offer>) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    inner class OfferViewHolder(private val itemBinding: OfferItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
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

    override fun getItemCount(): Int {
        return offers.size
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer: Offer = offers[position]
        holder.bind(offer)
    }
}

//class PaymentAdapter(private val paymentList: List<PaymentBean>) : RecyclerView.Adapter<PaymentAdapter.PaymentHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
//        val itemBinding = RowPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return PaymentHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
//        val paymentBean: PaymentBean = paymentList[position]
//        holder.bind(paymentBean)
//    }
//
//    override fun getItemCount(): Int = paymentList.size
//
//    class PaymentHolder(private val itemBinding: RowPaymentBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//        fun bind(paymentBean: PaymentBean) {
//            itemBinding.tvPaymentInvoiceNumber.text = paymentBean.invoiceNumber
//            itemBinding.tvPaymentAmount.text = paymentBean.totalAmount
//        }
//    }
//}