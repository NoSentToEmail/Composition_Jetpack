package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("score")
fun bindScore(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        score
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}


fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

//@BindingAdapter("resultEmoji")
//fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
//    ImageView.setImageResource(getSmileResId(winner))
//}
//
//private fun getSmileResId(winner: Boolean): Int {
//    return if (winner) {
//        R.drawable.ic_smile
//    } else {
//        R.drawable.ic_sad
//    }
//}

@BindingAdapter("enoughCountOfRightAnswers")
fun bindEnoughCount(textView: TextView, enough: Boolean){
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercentOfRightAnswers")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean){
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int{
    val colorResId = if(goodState){
        android.R.color.holo_green_light
    }else{
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}
@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickLisneter: OnOptionClickListener){
    textView.setOnClickListener{
        clickLisneter.onOptionClick(textView.text.toString().toInt())
    }
}