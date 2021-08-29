package dev.zaqueu.eventfinder.eventfinder.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.zaqueu.eventfinder.common.domain.model.Event
import dev.zaqueu.eventfinder.common.presentation.BindingFragment
import dev.zaqueu.eventfinder.databinding.FragmentEventFinderBinding
import dev.zaqueu.eventfinder.eventdescription.presentation.toEventArgs

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
        setupListeners()
    }

    private fun setupListeners() {
        binding.swipeContainer.setOnRefreshListener {
            requestEvents()
        }
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
                is EventFinderViewState.Error -> handleError()
                is EventFinderViewState.Loading -> handleLoading()
                is EventFinderViewState.Success -> handleSuccess(eventsViewState.events)
            }
        }
    }

    private fun handleSuccess(events: List<Event>) {
        eventsAdapter.submitList(events)
        eventsAdapter.notifyDataSetChanged()
        binding.apply {
            tvError.visibility = View.GONE
            ivError.visibility = View.GONE
            shimmerFrameLayout.visibility = View.GONE
            shimmerFrameLayout.stopShimmer()
            rvEvents.visibility = View.VISIBLE
            tvEmphasis.visibility = View.VISIBLE

        }
    }

    private fun handleLoading() {
        binding.apply {
            tvError.visibility = View.GONE
            ivError.visibility = View.GONE
            rvEvents.visibility = View.GONE
            tvEmphasis.visibility = View.GONE
            shimmerFrameLayout.visibility = View.VISIBLE
            shimmerFrameLayout.startShimmer()
        }
    }

    private fun handleError() {
        binding.apply {
            tvError.visibility = View.VISIBLE
            ivError.visibility = View.VISIBLE
            rvEvents.visibility = View.GONE
            tvEmphasis.visibility = View.GONE
            shimmerFrameLayout.visibility = View.GONE
            shimmerFrameLayout.stopShimmer()
        }
    }

    private fun requestEvents() {
        eventFinderViewModel.getEvents()
        binding.swipeContainer.isRefreshing = false
    }

    private fun onEventClicked(event: Event) {
        val navigation =
            EventFinderFragmentDirections.actionEventFinderToEventDescription(event.toEventArgs())
        findNavController().navigate(navigation)
    }
}
