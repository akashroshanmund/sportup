package com.akash.sportup.ui.adapters

import com.akash.sportup.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.akash.sportup.data.datamodels.Events
import com.akash.sportup.ui.utils.PicassoCircleTransformation
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class TeamSearchRvAdapter : RecyclerView.Adapter<TeamSearchRvAdapter.EventViewHolder>() {
    private val listOfItems: ArrayList<Events> = ArrayList<Events>()
    var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        mContext = parent.context
        val itemView: View =
            LayoutInflater.from(mContext).inflate(R.layout.team_event_cardview, parent, false)
        return EventViewHolder(itemView, mContext)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindData(listOfItems[position])
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    fun updateData(data: List<Events>?) {
        if(data == null) return
        clearData()
        Log.i("ApiTestPlayer", "Recycler View"+ data)
        listOfItems.addAll(data!!)
        notifyDataSetChanged()
    }

    fun clearData() {
        val size = listOfItems.size
        listOfItems.clear()
        notifyItemRangeRemoved(0, size)
    }

    class EventViewHolder(itemView: View, private val mContext: Context?) :
        RecyclerView.ViewHolder(itemView) {
        private val tvEventStadium: TextView
        private val tvEventDate: TextView
        private val tvTeamName1: TextView
        private val tvTeamName2: TextView
        private val tvTeamScore: TextView
        private val imEventCard: ImageView


        init {
            tvEventStadium = itemView.findViewById(R.id.tvEventStadium)
            tvEventDate = itemView.findViewById(R.id.tvEventDate)
            tvTeamName1 = itemView.findViewById(R.id.tvTeamName1)
            tvTeamName2 = itemView.findViewById(R.id.tvTeamNam2)
            tvTeamScore = itemView.findViewById(R.id.tvTeamScore)
            imEventCard = itemView.findViewById(R.id.imEventCard)


        }

        fun bindData(item: Events) {
            tvEventStadium.setText(item.strVenue)
            tvEventDate.text = item.strTimeLocal
            tvTeamName1.text = item.strHomeTeam
            tvTeamName2.text = item.strAwayTeam
            tvTeamScore.text = item.intHomeScore + " : " + item.intAwayScore
            if (item.strThumb != "") {
                Picasso
                    .get()
                    .load(item.strThumb)
                    .fit()
                    .noFade()
                    .into(imEventCard)
            }


        }
    }


}

