// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import com.kcrumptonslashertycoon.database.UserInventoryItem

// The pop up menu where you pick whatever item to use
class SelectInventoryItemDialogFragment(
    private val inventoryItems: List<UserInventoryItem>,
    private val onItemSelected: (UserInventoryItem) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val itemNames = inventoryItems.map { "${it.itemName} (x${it.quantity})" }

        val adapter = object : ArrayAdapter<String>(
            requireContext(),
            R.layout.dialog_item_inventory,
            itemNames
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                view.setBackgroundColor(Color.TRANSPARENT)
                return view
            }
        }

        // Setting up how it looks
        val customTitle = TextView(requireContext()).apply {
            text = "Choose an item to use"
            textSize = 34f
            setTextColor(Color.BLACK)
            setPadding(32, 32, 32, 16)
            typeface = ResourcesCompat.getFont(context, R.font.slash_font)
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setCustomTitle(customTitle)
            .setAdapter(adapter) { _, which ->
                onItemSelected(inventoryItems[which])
            }
            .setNegativeButton("Cancel", null)
            .create()


        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.apply {
                textSize = 30f
                setTextColor(Color.BLACK)
                typeface = ResourcesCompat.getFont(context, R.font.slash_font)
            }
        }

        return dialog
    }
}