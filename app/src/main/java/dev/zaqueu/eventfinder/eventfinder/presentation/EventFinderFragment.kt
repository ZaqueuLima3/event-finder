package dev.zaqueu.eventfinder.eventfinder.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.zaqueu.eventfinder.common.presentation.BindingFragment
import dev.zaqueu.eventfinder.databinding.FragmentEventFinderBinding

class EventFinderFragment(
    private val eventFinderViewModel: EventFinderViewModel,
    private val eventsAdapter: EventsAdapter
) :
    BindingFragment<FragmentEventFinderBinding>(FragmentEventFinderBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventFinderViewModel.getEvents()
        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.rvEvents.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        eventFinderViewModel.eventViewState.observe(viewLifecycleOwner) { eventsViewState ->
            when (eventsViewState) {
                is EventFinderViewState.Error -> {
                    // TODO: handle error
                }
                is EventFinderViewState.Loading -> {
                    // TODO: handle loading
                }
                is EventFinderViewState.Success -> {
                    eventsAdapter.submitList(eventsViewState.events)
                    eventsAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}
