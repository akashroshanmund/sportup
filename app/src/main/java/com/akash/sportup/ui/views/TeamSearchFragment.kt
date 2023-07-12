package com.akash.sportup.ui.views

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akash.sportup.R
import com.akash.sportup.databinding.FragmentTeamSearchBinding
import com.akash.sportup.ui.adapters.TeamSearchRvAdapter
import com.akash.sportup.ui.utils.PicassoCircleTransformation
import com.akash.sportup.ui.viewmodels.TeamSearchViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.net.InetAddress

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamSearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var TeamSearchFragmentBinding : FragmentTeamSearchBinding
    lateinit var teamSearchViewModel : TeamSearchViewModel

    lateinit var rootView : View
    lateinit var lastEventAdapter : TeamSearchRvAdapter
    lateinit var rvTeamLastEvents: RecyclerView
    lateinit var nextEventAdapter : TeamSearchRvAdapter
    lateinit var rvTeamNextEvents: RecyclerView
    lateinit var imgTeamLogo: ImageView

    lateinit var rlProgressView : RelativeLayout
    lateinit var scrollTeamSearch : ScrollView
    lateinit var rlTeamrNoResult : RelativeLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        teamSearchViewModel = ViewModelProvider(this)[TeamSearchViewModel::class.java]
        TeamSearchFragmentBinding = FragmentTeamSearchBinding.inflate(inflater, container, false)
        rootView = TeamSearchFragmentBinding.root
        return TeamSearchFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**  create view binding */
        TeamSearchFragmentBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = teamSearchViewModel
        }
        TeamSearchFragmentBinding?.teamSearchFragment = this

        setObservatationActions()
        defineUI()

        if(isInternetAvailable()) {
            lifecycleScope.launch {
                teamSearchViewModel.fetchTeamSearchResult("Chelsea")
            }
        }else{
            Toast.makeText(activity, "No Internet", Toast.LENGTH_LONG).show()
        }

    }

    private fun defineUI(){
        rvTeamLastEvents = rootView.findViewById(R.id.rvTeamLastEvents)
        rvTeamNextEvents = rootView.findViewById(R.id.rvTeamNextEvents)
        imgTeamLogo = rootView.findViewById(R.id.imgTeamLogo)
        scrollTeamSearch = rootView.findViewById(R.id.scrollTeamSearch)
        rlProgressView = rootView.findViewById(R.id.progressLayout)
        rlTeamrNoResult = rootView.findViewById(R.id.rlTeamNoResult)

        rlProgressView.visibility = View.VISIBLE
        scrollTeamSearch.visibility = View.GONE

        nextEventAdapter = TeamSearchRvAdapter()
        lastEventAdapter = TeamSearchRvAdapter()

        val nextEventLinearManager = LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL,
            false
        )
        val lastEventLinearManager = LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL,
            false
        )
        rvTeamNextEvents.layoutManager = nextEventLinearManager
        rvTeamLastEvents.layoutManager = lastEventLinearManager

        rvTeamNextEvents.adapter = nextEventAdapter
        rvTeamLastEvents.adapter = lastEventAdapter

        /** Search view operation handling */

        val searchView: SearchView = rootView.findViewById(R.id.svTeamSearch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String): Boolean {

                if (isInternetAvailable()){
                    lifecycleScope.launch {
                        teamSearchViewModel.fetchTeamSearchResult(name)
                    }

                rlProgressView.visibility = View.VISIBLE
                scrollTeamSearch.visibility = View.GONE
            }else{
                    Toast.makeText(activity, "No Internet", Toast.LENGTH_LONG).show()
                }

                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })


    }

    private fun setObservatationActions(){
        teamSearchViewModel.getObservableData().observe(viewLifecycleOwner) {
            Log.i("ApiTestPlayer", "observed " + it.data + " " + it.ErrorMessage)

            lastEventAdapter.updateData(it.data?.lastEvents?.eventList)
            nextEventAdapter.updateData(it.data?.nextEvents?.eventList)
            showImage(imgTeamLogo,it.data?.teamDetails?.teamsList?.get(0)?.strTeamBadge)


            rlProgressView.visibility = View.GONE
            scrollTeamSearch.visibility = View.VISIBLE

            if(it.data?.teamDetails?.teamsList?.get(0)?.idTeam == null || it.data == null){
                rlTeamrNoResult.visibility = View.VISIBLE
                scrollTeamSearch.visibility = View.GONE
            }else{
                rlTeamrNoResult.visibility = View.GONE
                scrollTeamSearch.visibility = View.VISIBLE
            }
        }
    }





    protected fun showImage(targetImageView: ImageView?, path: String?) {

        Picasso
            .get()
            .load(path)
            .fit()
            .noFade()
            .transform(PicassoCircleTransformation())
            .into(targetImageView)
    }

    fun isInternetAvailable(): Boolean {
        var connectivityManager : ConnectivityManager = ( activity?.getSystemService(Context.CONNECTIVITY_SERVICE)) as ConnectivityManager;
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo()!!
            .isConnected();
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeamSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeamSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}