package com.tahakorkem.tuttur

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import com.tahakorkem.tuttur.databinding.FragmentCountryListBinding
import com.tahakorkem.tuttur.fragment.BaseFragment

class CountryListFragment :
    BaseFragment<FragmentCountryListBinding>(R.layout.fragment_country_list) {

    private lateinit var adapter: CountryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CountryAdapter()

        binding.recyclerViewCountries.adapter = adapter

        adapter.submitList(listOf(
            Country(1, "Afghanistan", "\uD83C\uDDE6\uD83C\uDDEB"),
            Country(2, "Albania", "\uD83C\uDDE6\uD83C\uDDF1"),
            Country(3, "Algeria", "\uD83C\uDDE9\uD83C\uDDFF"),
            Country(4, "Andorra", "\uD83C\uDDE6\uD83C\uDDE9"),
            Country(5, "Angola", "\uD83C\uDDE6\uD83C\uDDF4"),
            Country(6, "Antigua and Barbuda", "\uD83C\uDDE6\uD83C\uDDEC"),
            Country(7, "Argentina", "\uD83C\uDDE6\uD83C\uDDF7"),
            Country(8, "Armenia", "\uD83C\uDDE6\uD83C\uDDF2"),
            Country(9, "Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
            Country(10, "Austria", "\uD83C\uDDE6\uD83C\uDDF9"),
            Country(11, "Azerbaijan", "\uD83C\uDDE6\uD83C\uDDFF"),
            Country(12, "Bahamas", "\uD83C\uDDE7\uD83C\uDDF8"),
            Country(13, "Bahrain", "\uD83C\uDDE7\uD83C\uDDED"),
            Country(14, "Bangladesh", "\uD83C\uDDE7\uD83C\uDDE9"),
            Country(15, "Barbados", "\uD83C\uDDE7\uD83C\uDDE7"),
            Country(16, "Belarus", "\uD83C\uDDE7\uD83C\uDDFE"),
            Country(17, "Belgium", "\uD83C\uDDE7\uD83C\uDDEA"),
            Country(18, "Belize", "\uD83C\uDDE7\uD83C\uDDFF"),
            Country(19, "Benin", "\uD83C\uDDE7\uD83C\uDDEF"),
            Country(20, "Bhutan", "\uD83C\uDDE7\uD83C\uDDF9"),
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_country, menu)

        val searchMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun filter(query: String?) {
        adapter.filter(query)
    }

}