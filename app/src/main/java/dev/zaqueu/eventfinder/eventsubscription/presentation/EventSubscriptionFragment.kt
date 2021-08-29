package dev.zaqueu.eventfinder.eventsubscription.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.zaqueu.eventfinder.R
import dev.zaqueu.eventfinder.common.domain.model.CheckIn
import dev.zaqueu.eventfinder.common.presentation.BindingFragment
import dev.zaqueu.eventfinder.common.utils.handleSingleClick
import dev.zaqueu.eventfinder.common.utils.hideKeyboard
import dev.zaqueu.eventfinder.common.utils.isValidEmail
import dev.zaqueu.eventfinder.databinding.FragmetnEventSubscriptionBinding

class EventSubscriptionFragment(
    private val eventSubscriptionViewModel: EventSubscriptionViewModel
) :
    BindingFragment<FragmetnEventSubscriptionBinding>(FragmetnEventSubscriptionBinding::inflate) {
    private val arguments by navArgs<EventSubscriptionFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        bindView()
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnCheckIn.setOnClickListener {
            it.handleSingleClick {
                doCheckIn()
            }
        }
    }

    private fun bindView() {
        binding.apply {
            tvTitle.text = arguments.event.title
        }
    }

    private fun setupObservers() {
        eventSubscriptionViewModel.checkInState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CheckInState.Success -> handleCheckInSuccess()
                is CheckInState.Error -> handleCheckInError()
            }
        }
    }

    private fun doCheckIn() {
        val email = binding.tieEmail.text
        val name = binding.tieName.text
        if (name.isNullOrBlank()) {
            showToast(getString(R.string.insert_a_valid_name))
            return
        }
        if (email.isValidEmail().not() || email.isNullOrBlank()) {
            showToast(getString(R.string.insert_a_valid_email))
            return
        }
        eventSubscriptionViewModel.checkInEvent(
            CheckIn(
                eventId = arguments.event.id,
                name = name.toString(),
                email = email.toString()
            )
        )
        resetInputs()
    }

    private fun handleCheckInSuccess() {
        showToast(getString(R.string.check_in_succeed))
        requireView().hideKeyboard()
        resetInputs()
    }

    private fun handleCheckInError() {
        showToast(getString(R.string.something_went_wrong))
        requireView().hideKeyboard()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun resetInputs() {
        binding.apply {
            tieName.setText("")
            tieEmail.setText("")
            tieEmail.clearFocus()
        }
    }
}
