package com.tahakorkem.tuttur

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.tahakorkem.tuttur.databinding.BottomSheetMovieBinding
import com.tahakorkem.tuttur.dialog.BaseBottomSheetDialogFragment

class MovieBottomSheet :
    BaseBottomSheetDialogFragment<BottomSheetMovieBinding>(R.layout.bottom_sheet_movie) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movie = arguments?.getParcelable("movie")

        behaviour.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    STATE_EXPANDED -> binding.expanded = true
                    STATE_COLLAPSED -> binding.expanded = false
                    else -> Unit
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
        })

        binding.root.setOnClickListener {
            when (behaviour.state) {
                STATE_EXPANDED -> behaviour.state = STATE_COLLAPSED
                STATE_COLLAPSED -> behaviour.state = STATE_EXPANDED
                else -> Unit
            }
        }
    }

    companion object {
        const val TAG = "MovieBottomSheet"
    }
}