package com.akash.sportup.ui.views

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.akash.sportup.R
import com.akash.sportup.databinding.FragmentPlayerSearchBinding
import com.akash.sportup.ui.utils.PicassoCircleTransformation
import com.akash.sportup.ui.viewmodels.PlayerSearchViewModel
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerSearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var PlayerSearchFragmentBinding : FragmentPlayerSearchBinding
    lateinit var playerSearchViewModel : PlayerSearchViewModel
    lateinit var rootView : View

    lateinit var imgPlayerImage : ImageView
    lateinit var imgPlayerTeamLogo : ImageView
    lateinit var imgPlayerBackground : ImageView

    lateinit var progressBarView : RelativeLayout
    lateinit var linearTeamSearch : LinearLayout
    
    
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
        // Inflate the layout for this fragment

        playerSearchViewModel = ViewModelProvider(this)[PlayerSearchViewModel::class.java]
        PlayerSearchFragmentBinding = FragmentPlayerSearchBinding.inflate(inflater, container, false)
        rootView = PlayerSearchFragmentBinding.root
        return PlayerSearchFragmentBinding.root
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PlayerSearchFragmentBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = playerSearchViewModel
        }
        PlayerSearchFragmentBinding?.playerSearchFragment = this
        setObservatationActions()
        defineUI()
        searchOperation()

    }
    
    public fun defineUI(){
        this.imgPlayerImage = rootView.findViewById(R.id.imgPlayerImage)
        this.imgPlayerTeamLogo = rootView.findViewById(R.id.imgPlayerTeamLogo)
        this.imgPlayerBackground = rootView.findViewById(R.id.imgPlayerBackground)
        this.linearTeamSearch = rootView.findViewById(R.id.linearPlayerSearch)
        this.progressBarView = rootView.findViewById(R.id.progressPlayerSearch)
        linearTeamSearch.visibility = View.GONE
        progressBarView.visibility = View.VISIBLE
        lifecycleScope.launch{
            playerSearchViewModel.fetchPlayerSearchResult("Ronaldo")
        }
    }

    public fun searchOperation(){
        val searchView: SearchView = rootView.findViewById(R.id.svSearchPlayer)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String): Boolean {
                linearTeamSearch.visibility = View.GONE
                progressBarView.visibility = View.VISIBLE
                lifecycleScope.launch{
                    playerSearchViewModel.fetchPlayerSearchResult(name)
                }
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
    }

    private fun setObservatationActions(){
        playerSearchViewModel.getObservableData().observe(viewLifecycleOwner) {
            Log.i("ApiTestPlayer", "observed " + it.data + " " + it.ErrorMessage)
            showRoundImage(imgPlayerImage, it.data?.playerDetails?.playersList?.get(0)?.strThumb)
            showRoundImage(imgPlayerTeamLogo, it.data?.playerDetails?.playersList?.get(0)?.strThumb)
            linearTeamSearch.visibility = View.VISIBLE
            progressBarView.visibility = View.GONE
            //showImage(imgPlayerBackground, it.data?.playerDetails?.playersList?.get(0)?.strBanner)
           // nextEventAdapter.updateData(it.data?.lastEvents?.eventList)
        }
    }

    protected fun showRoundImage(targetImageView: ImageView?, path: String?) {
        Picasso
            .get()
            .load(path)
            .fit()
            .noFade()
            .transform(PicassoCircleTransformation())
            .into(targetImageView)
    }

    protected fun showImage(targetImageView: ImageView?, path: String?) {
        Picasso
            .get()
            .load(path)
            .fit()
            .noFade()
            .into(targetImageView)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlayerSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayerSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}