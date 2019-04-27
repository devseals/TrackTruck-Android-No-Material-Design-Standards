package pe.edu.upc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_review.view.*
import pe.edu.upc.R
import pe.edu.upc.models.Review

class ReviewRecycleAdapter(val reviews : ArrayList<Review>):RecyclerView.Adapter<ReviewRecycleAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_review,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.count()
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val reviewUser: TextView = view.findViewById(R.id.reviewUser)
        val reviewContent : TextView = view.reviewContent
        val reviewTitle : TextView = view.reviewTitle
        val reviewDate: TextView = view.reviewDate

        fun bind(review: Review){

            reviewUser.text = review.name
            reviewContent.text = review.content
            reviewTitle.text = review.title
            reviewDate.text = review.date.subSequence(0,10)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviews[position])
    }
}