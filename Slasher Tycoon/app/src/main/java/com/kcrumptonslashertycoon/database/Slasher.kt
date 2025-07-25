// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

// My slashers - So far I've got Freddy, Jason, Ghostface, Victor Crowley, Jigsaw, Candyman and just added Carrie but haven't set up any stuff for her yet.
@Entity
data class Slasher(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name: String,
    val locationType: String,
    val isUnlocked: Boolean = false,
    val kills: Int = 0,
    val weapon: String,
    val visitorCount: Int = 0,
    val killsToday: Int = 0,
    val requiredLevel: Int = 1
)