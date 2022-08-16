package com.tahakorkem.tuttur

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.tahakorkem.tuttur.databinding.FragmentMovieListBinding
import com.tahakorkem.tuttur.fragment.BaseFragment
import kotlin.random.Random

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieAdapter(MovieCallback {
            val bottomSheet = MovieBottomSheet()
            bottomSheet.arguments = bundleOf("movie" to it)
            bottomSheet.show(parentFragmentManager, MovieBottomSheet.TAG)
        })

        binding.recyclerViewMovies.adapter = adapter

        adapter.submitList((1..100).map { i ->
            Movie(
                id = i,
                title = "Movie $i",
                description = """
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean a justo ex. Pellentesque vestibulum finibus tempus. Curabitur feugiat interdum odio, posuere faucibus lacus blandit vel. Vestibulum viverra erat vitae nunc mollis tincidunt. Vestibulum luctus viverra ultricies. Morbi dapibus augue nisl, sed viverra felis ultricies eget. Nam volutpat venenatis tortor, in auctor libero maximus at.

                    Cras pharetra varius sem vel dapibus. Nunc nec ipsum vitae magna maximus faucibus at vel lacus. Mauris mi arcu, convallis in auctor et, scelerisque non dolor. Nulla lobortis auctor odio. Morbi commodo, est hendrerit malesuada condimentum, lectus leo condimentum erat, eget pharetra est libero vitae lorem. Maecenas nisl quam, maximus sit amet sem eget, tincidunt viverra orci. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.

                    Mauris lobortis pharetra scelerisque. Nunc eget nibh a libero volutpat luctus. Maecenas commodo gravida justo in semper. Maecenas ultricies diam nisl, a placerat leo euismod vel. Ut fermentum pretium justo vitae bibendum. Cras molestie pharetra ultrices. Etiam sed turpis in nisi tempor vehicula. Suspendisse quis augue vitae massa blandit sollicitudin. Etiam tempor, ligula eu euismod pharetra, nisi ligula blandit magna, ut mattis lacus nulla sed metus. Nunc tincidunt massa varius quam scelerisque, eget placerat turpis volutpat. Donec sagittis felis eu nunc malesuada vehicula. Fusce sit amet suscipit mauris, ac venenatis metus. Nullam at quam massa. Mauris varius ligula eget posuere feugiat. Praesent enim quam, laoreet eget convallis id, consequat eget libero.

                    Suspendisse consectetur vehicula est vel sollicitudin. Aenean egestas posuere massa, eu egestas orci. Nullam dictum ex ac nibh consequat lacinia. Maecenas luctus ornare tristique. Ut ut leo sed lorem dignissim consectetur quis vel turpis. Praesent aliquam velit mattis nibh volutpat suscipit id ut sem. Ut mattis aliquam odio, ut placerat nisi dignissim et. Nulla feugiat neque quam. Suspendisse suscipit felis eget posuere lobortis. Donec arcu felis, pellentesque vel sapien quis, consectetur varius sapien. Cras sit amet orci pharetra, imperdiet mauris at, commodo ligula. Donec malesuada aliquet laoreet. Nullam ornare varius efficitur. In euismod dui purus, ut rhoncus metus semper non.

                    Cras augue nisi, maximus vulputate faucibus a, gravida at quam. Sed ultricies dui in velit facilisis, in condimentum neque auctor. Phasellus aliquam accumsan bibendum. Nullam et feugiat odio, et semper turpis. Proin venenatis placerat gravida. Cras sed sem semper, rhoncus tellus vitae, condimentum quam. Proin lacinia neque massa, faucibus viverra arcu tempus nec. Vestibulum bibendum semper tortor id mattis.
                """.trimIndent(),
                rating = Random.nextFloat() * 4 + 1
            )
        })
    }

}