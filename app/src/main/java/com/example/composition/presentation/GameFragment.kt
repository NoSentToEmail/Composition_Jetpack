package com.example.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>() //// 1 способ реализации, получения аргумнта ЛВЛ

    private  val viewModel by lazy {
//        val args = GameFragmentArgs.fromBundle(requireArguments()) // 2 способ реализации
        ViewModelProvider(
            this,
            GameViewModelFactory(
                args.level ,
                requireActivity().application
            )
        )[GameViewModelFragment::class.java]
    }

//    private val tvOpions by lazy {
//        mutableListOf<TextView>().apply {
//            add(binding.tvOption1)
//            add(binding.tvOption2)
//            add(binding.tvOption3)
//            add(binding.tvOption4)
//            add(binding.tvOption5)
//            add(binding.tvOption6)
//
//        }
//    }

    private var _binding: FragmentGameBinding ?= null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
//        setClickListenersToOptions()
    }

//    private fun setClickListenersToOptions(){
//        for(tvOption in tvOpions){
//            tvOption.setOnClickListener{
//                viewModel.chooseAnswer(tvOption.text.toString().toInt())
//            }
//        }
//    }

    private fun  observeViewModel() {
//        viewModel.question.observe(viewLifecycleOwner){
//            binding.tvSum.text = it.sum.toString()
//            binding.tvLeftNumber.text = it.visibleNumber.toString()
//            for(i in 0 until tvOpions.size){
//                tvOpions[i].text = it.options[i].toString()
//            }
//        }
//        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner){
//            binding.progressBar.setProgress(it,true)
//        }
//        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner){
//
//            binding.tvAnswersProgress.setTextColor(getColorByState(it))
//        }

//        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner){
//            val color = getColorByState(it)
//            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }
//        viewModel.formattedTime.observe(viewLifecycleOwner){
//            binding.tvTimer.text = it
//        }
//        viewModel.minPercent.observe(viewLifecycleOwner){
//            binding.progressBar.secondaryProgress = it
//        }
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishFragment(it)
        }
//        viewModel.progressAnswers.observe(viewLifecycleOwner){
//            binding.tvAnswersProgress.text = it
//        }
    }

    private fun launchGameFinishFragment(finish: GameResult){
        findNavController().navigate(
            GameFragmentDirections.actionGameFragment2ToGameFinishedFragment2(finish)
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun getColorByState(goodState: Boolean): Int{
//        val colorResId = if(goodState){
//            android.R.color.holo_green_light
//        }else{
//            android.R.color.holo_red_light
//        }
//        return ContextCompat.getColor(requireContext(), colorResId)
//    }

}