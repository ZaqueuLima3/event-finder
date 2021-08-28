package dev.zaqueu.eventfinder.eventfinder.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.zaqueu.eventfinder.common.domain.model.Event
import dev.zaqueu.eventfinder.common.presentation.BindingFragment
import dev.zaqueu.eventfinder.databinding.FragmentEventFinderBinding
import dev.zaqueu.eventfinder.eventdescription.presentation.EventArgs

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
        setupAdapterListeners()
    }

    private fun setupAdapterListeners() {
        eventsAdapter.setOnEventClickListener(::onEventClicked)
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

    private fun onEventClicked(event: Event) {
        val navigation =
            EventFinderFragmentDirections.actionEventFinderToEventDescription(event.toEventArgs())
        findNavController().navigate(navigation)
    }
}

private fun Event.toEventArgs() = EventArgs(
    title = title,
    description = description,
    image = image
)
