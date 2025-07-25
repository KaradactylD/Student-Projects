// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class RavePhotographerFragment : Fragment() {

    private lateinit var photoButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var poseImageView: ImageView
    private lateinit var slasherViewModel: SlasherViewModel
    private lateinit var flashOverlay: View

    // Different "poses" for the pictures, I've only got 6 in there. Different outcomes for the lighting, used for scoring
    private val poses = listOf("C'mon, man! Play CREED!", "This is my jam!", "STAGE RUSH!", "Whaddya mean we're cut off?", "Daylight already?","THAT. WAS. AWESOME!!!!")
    private val lighting = listOf("Epic lighting", "Too dark", "Overexposed", "Perfect glow", "Green haze")

    // Which image goes with which pose
    private val poseImages = mapOf(
        "C'mon, man! Play CREED!" to R.drawable.zombie_pose1,
        "This is my jam!" to R.drawable.zombie_pose2,
        "STAGE RUSH!" to R.drawable.zombie_pose3,
        "Whaddya mean we're cut off?" to R.drawable.zombie_pose4,
        "Daylight already?" to R.drawable.zombie_pose5,
        "THAT. WAS. AWESOME!!!!" to R.drawable.zombie_pose6,

    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_rave_photographer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        photoButton = view.findViewById(R.id.buttonTakePhoto)
        resultTextView = view.findViewById(R.id.photoResultTextView)
        poseImageView = view.findViewById(R.id.zombiePoseImageView)
        flashOverlay = view.findViewById(R.id.flashOverlay)

        slasherViewModel = ViewModelProvider(
            requireActivity(),
            SlasherViewModelFactory(requireActivity().application as Application)
        )[SlasherViewModel::class.java]

        photoButton.setOnClickListener {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@setOnClickListener

            val shareButton = view.findViewById<Button>(R.id.shareToInstagramButton)
            shareButton.visibility = View.GONE
            photoButton.visibility = View.VISIBLE

            lifecycleScope.launch {
                val user = slasherViewModel.getUserInfo(userId).firstOrNull()
                if (user != null) {
                    val now = System.currentTimeMillis()
                    val cooldownMillis = 5 * 60 * 1000 // Gotta wait 5 minutes

                    if (now - user.lastPhotoTime < cooldownMillis) {
                        val minutesLeft = ((cooldownMillis - (now - user.lastPhotoTime)) / 60000) + 1
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "🕐 You must wait $minutesLeft minute(s) before taking another zombie photo.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        return@launch
                    }

                    // Only do the sound and flash if they're allowed to take pictures
                    val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.shutter_click)
                    mediaPlayer.setVolume(0.3f, 0.3f)
                    mediaPlayer.start()

                    withContext(Dispatchers.Main) {
                        flashOverlay.visibility = View.VISIBLE
                        flashOverlay.alpha = 1f
                        flashOverlay.animate()
                            .alpha(0f)
                            .setDuration(200)
                            .withEndAction {
                                flashOverlay.visibility = View.GONE
                            }
                    }

                    // lighting picked random, pictures shown random, reward money totally random and means absolutely nothing.
                    val pose = poses.random()
                    val light = lighting.random()

                    val reward = if (light.contains("Perfect") || light.contains("Epic")) {
                        Random.nextInt(50, 101)
                    } else {
                        Random.nextInt(10, 31)
                    }

                    // Score
                    withContext(Dispatchers.Main) {
                        resultTextView.text = "📸 $pose\n💡 Lighting: $light\n💰 +$$reward"
                        poseImages[pose]?.let {
                            poseImageView.setImageResource(it)
                            poseImageView.visibility = View.VISIBLE
                        }


                        shareButton.visibility = View.VISIBLE
                        photoButton.visibility = View.GONE


                        shareButton.setOnClickListener {
                            val containerView = view.findViewById<View>(R.id.zombiePoseContainer) ?: view
                            sharePoseToInstagram(containerView)
                        }
                    }

                    // Update user info - gotta wait 5 minutes to do another photo
                    val updatedUser = user.copy(
                        money = user.money + reward,
                        lastPhotoTime = now
                    )
                    slasherViewModel.updateUserInfo(updatedUser)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "You earned $$reward from your zombie photo!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private var hasShared = false

    // Share photo to Instagram
    private fun sharePoseToInstagram(viewToShare: View) {
        // Take screenshot to share
        val bitmap = Bitmap.createBitmap(viewToShare.width, viewToShare.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        viewToShare.draw(canvas)

        // Save to cache
        val file = File(requireContext().cacheDir, "shared_zombie_pose.png")
        FileOutputStream(file).use { out -> bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) }

        val uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            file
        )

        // Intent to share
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        // Use chooser instead of hardcoding Instagram - couldn't get it to work?
        startActivity(Intent.createChooser(shareIntent, "Share your zombie photo!"))

        // Only reward once per session
        if (!hasShared) {
            hasShared = true
            giveInstagramReward()
        }
    }

    // User gets reward as soon as they click the button... I'm sure there's a way to check and make sure they do it before you give it to them, but it's not that serious. :)
    private fun giveInstagramReward() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        lifecycleScope.launch {
            val user = slasherViewModel.getUserInfo(userId).firstOrNull()
            if (user != null) {
                val reward = 500
                val updatedUser = user.copy(money = user.money + reward)
                slasherViewModel.updateUserInfo(updatedUser)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "💰 You earned $500 for sharing!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}