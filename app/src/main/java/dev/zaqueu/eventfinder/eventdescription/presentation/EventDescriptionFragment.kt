package dev.zaqueu.eventfinder.eventdescription.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dev.zaqueu.eventfinder.R
import dev.zaqueu.eventfinder.common.presentation.BindingFragment
import dev.zaqueu.eventfinder.common.utils.handleSingleClick
import dev.zaqueu.eventfinder.databinding.FragmentEventDescriptionBinding

class EventDescriptionFragment(
    private val glide: RequestManager
) :
    BindingFragment<FragmentEventDescriptionBinding>(FragmentEventDescriptionBinding::inflate) {
    private val arguments by navArgs<EventDescriptionFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        bindView()
        setupListeners()
    }

    private fun setupListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.content.btnCheckIn.setOnClickListener {
            it.handleSingleClick {
                val navigation =
                    EventDescriptionFragmentDirections.actionEventDescriptionToEventSubscription(
                        arguments.event
                    )
                findNavController().navigate(navigation)
            }
        }
    }

    private fun bindView() {
        binding.apply {
            content.tvTitle.text = arguments.event.title
            content.tvDescription.text = arguments.event.description
            glide
                .load(arguments.event.image)
                .placeholder(R.drawable.placeholder)
                .into(ivCover)
        }
    }
}
