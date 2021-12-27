package com.google.codelabs.mdc.kotlin.shrine

import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry
import com.google.codelabs.mdc.kotlin.shrine.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter

class ProductGridFragment : Fragment() {

    private var appBar: Toolbar? = null
    private var recyclerView: RecyclerView? = null
    private var nestedScroll: NestedScrollView? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.shr_product_grid_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appBar = view.findViewById(R.id.app_bar)
        recyclerView = view.findViewById(R.id.recycler_view)
        nestedScroll = view.findViewById(R.id.product_grid)

        (activity as AppCompatActivity).setSupportActionBar(appBar)

        setHasOptionsMenu(true)

        initRecyclerView()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nestedScroll!!.background = context?.getDrawable(R.drawable.shr_product_grid_background_shape)
        }

        appBar?.setNavigationOnClickListener(NavigationIconClickListener(activity!!, nestedScroll!!, AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(context!!, R.drawable.shr_branded_menu),
            ContextCompat.getDrawable(context!!, R.drawable.shr_close_menu)))

    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    private fun initRecyclerView(){
        recyclerView?.setHasFixedSize(true)

        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }

        recyclerView?.layoutManager = gridLayoutManager
        val adapter = StaggeredProductCardRecyclerViewAdapter(
            ProductEntry.initProductEntryList(resources))
        recyclerView?.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        recyclerView?.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))
    }


}
