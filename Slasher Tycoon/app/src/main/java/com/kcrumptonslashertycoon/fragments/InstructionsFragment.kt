// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan
import android.text.style.MetricAffectingSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kcrumptonslashertycoon.R

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_instructions, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val instructionsTextView = view.findViewById<TextView>(R.id.instructionsTextView)
        val backButton = view.findViewById<Button>(R.id.buttonBack)

        val fullText = """
    Welcome To
      Slasher Tycoon 
                  
    🧰 Use items from your inventory to complete tasks and solve puzzles.

    💰 Earn money, XP, and collectibles.

    🔥 The more visitors and kills a slasher gets, the stronger they become.

    🌶️ Test your skills in the Chili Cookoff — impress the Sawyers with your terrible recipe!

    📸 Head to the Rave and capture zombies in the ultimate undead photo op.

    🔪 Complete tasks, upgrade your slashers, and become a horror legend.

    Good luck… and try not to die.
    """.trimIndent()

        val spannable = SpannableString(fullText)

        // Changing font, color, size of the title
        val titleStart = fullText.indexOf("Welcome To")
        val titleEnd = fullText.indexOf("Slasher Tycoon ") + "Slasher Tycoon ".length

        // Set custom font
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.slash_font)
        typeface?.let {
            spannable.setSpan(
                CustomTypefaceSpan(it),
                titleStart,
                titleEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        // Sets the size
        spannable.setSpan(
            RelativeSizeSpan(3.4f),
            titleStart,
            titleEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Color
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF1744")),
            titleStart,
            titleEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Centered the title
        spannable.setSpan(
            AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
            titleStart,
            titleEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        instructionsTextView.text = spannable

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        class CustomTypefaceSpan(private val typeface: Typeface) : MetricAffectingSpan() {
            override fun updateDrawState(paint: TextPaint) {
                paint.typeface = typeface
            }

            override fun updateMeasureState(paint: TextPaint) {
                paint.typeface = typeface
            }
        }
    }


