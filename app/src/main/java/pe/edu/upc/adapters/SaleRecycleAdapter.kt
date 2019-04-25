package pe.edu.upc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_sale.view.*
import pe.edu.upc.R
import pe.edu.upc.models.Sale

class SaleRecycleAdapter(val sales: ArrayList<Sale>): RecyclerView.Adapter<SaleRecycleAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_sale, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sales.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sales[position])
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val saleAmountTxt = view.saleAmount
        val saleRegister = view.saleRegister
        val saleDate = view.saleDate
        val saleContent = view.saleContent

        fun bind(sale: Sale){
            saleAmountTxt.text = sale.amount.toString()
            saleRegister.text = sale.employeeName
            saleDate.text = sale.date
            saleContent.text =sale.content
        }
    }

}