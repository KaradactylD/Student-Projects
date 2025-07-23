// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcrumptonslashertycoon.database.StoreItem
import com.kcrumptonslashertycoon.database.UserInfo
import com.kcrumptonslashertycoon.database.UserInventoryItem
import com.kcrumptonslashertycoon.repository.SlasherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StoreViewModel(private val repository: SlasherRepository, private val roomName: String? = null, private val userLevel: Int) : ViewModel() {

    // Store items filtered to only show the ones for whatever room you're in
    val storeItems: StateFlow<List<StoreItem>> =
        if (roomName != null) {
            repository.getItemsForExactLevel(roomName, userLevel)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        } else {
            repository.getStoreItems()
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        }

    // Loading the items
    fun insertStoreItemsIfEmpty() {
        viewModelScope.launch {
            repository.getStoreItems().collect { items ->

                if (items.isEmpty()) {
                    val starterItems = listOf(
                        StoreItem( // Freddy
                            name = "Crimson Paint",
                            price = 350,
                            description = "A can of blood-red paint, perfect for walls and ceilings.",
                            locationType = "Boiler Room",
                            levelRequired = 1
                        ),
                        StoreItem(
                            name = "Warm milk",
                            price = 350,
                            description = "Gross.",
                            locationType = "Boiler Room",
                            levelRequired = 1
                        ),
                        StoreItem(
                            name = "Coffee grounds",
                            price = 250,
                            description = "Stay awake.",
                            locationType = "Boiler Room",
                            levelRequired = 1
                        ),
                        StoreItem(
                            name = "Blade Sharpener",
                            price = 210,
                            description = "Sharpen Freddy's glove blades quickly.",
                            locationType = "Boiler Room",
                            levelRequired = 1
                        ),
                        StoreItem(
                            name = "Furnace Fuel",
                            price = 100,
                            description = "Keeps Freddy's Furnace nice and toasty.",
                            locationType = "Boiler Room",
                            levelRequired = 1
                        ),
                        StoreItem(
                            name = "Bribe the DJ",
                            price = 200,
                            description = "Nothing but sleep-inducing music for the next 2 hours.",
                            locationType = "Boiler Room",
                            levelRequired = 1
                        ),
                        StoreItem(
                            name = "Laundry Detergent",
                            price = 600,
                            description = "Has this been tested on blood stains?",
                            locationType = "Boiler Room",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Sleeping Pills",
                            price = 500,
                            description = "Spike the punch at the highschool dance.",
                            locationType = "Boiler Room",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Possessed Telephone",
                            price = 900,
                            description = "Call Glen... see if he answers... ",
                            locationType = "Boiler Room",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Marionette Puppet",
                            price = 1000,
                            description = "Phillips's on the roof!",
                            locationType = "Boiler Room",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Self-Cleaning Sheets",
                            price = 400,
                            description = "You better hope these have a money-back guarantee.",
                            locationType = "Boiler Room",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Roach Motel",
                            price = 1100,
                            description = "Lifting weights can't save you now.",
                            locationType = "Boiler Room",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Dirty Linens",
                            price = 650,
                            description = "It's laundry day in Cellblock D.",
                            locationType = "Boiler Room",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "Television",
                            price = 850,
                            description = "Welcome to Prime Time, Bitch!",
                            locationType = "Boiler Room",
                            levelRequired = 6
                        ),

                        StoreItem( // Jason
                            name = "Bear Traps",
                            price = 250,
                            description = "Great for slowing down campers in the woods.",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Fake Party Flyers",
                            price = 100,
                            description = "Attract college students with free pizza.",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Kegger Kegs",
                            price = 300,
                            description = "Nothing says bait like beer in the woods.",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Machete Cleaner",
                            price = 150,
                            description = "Cleaner for Jason's machete",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Trip Wire",
                            price = 250,
                            description = "Calm down, Debra!",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Lantern",
                            price = 250,
                            description = "Light the way.",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Arrow",
                            price = 450,
                            description = "Who's dancing now?",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Sleeping Bag",
                            price = 900,
                            description = "Nature's pinata.",
                            locationType = "Cabin",
                            levelRequired = 2
                        ),
                        StoreItem(
                            name = "Harpoon",
                            price = 600,
                            description = "This looks better in 3D.",
                            locationType = "Cabin",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Tent Pole",
                            price = 600,
                            description = "Together forever.",
                            locationType = "Cabin",
                            levelRequired = 5
                        ),

                        StoreItem( // Ghostface
                            name = "Mask Polish",
                            price = 150,
                            description = "Keeps Ghostface looking fresh and terrifying.",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Popcorn",
                            price = 120,
                            description = "Can't watch a movie without it!",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Phone",
                            price = 120,
                            description = "Prank calls aren't even funny.",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Randy's Rule Book",
                            price = 300,
                            description = "Someone forgot Rule #1",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "DVD Box Set",
                            price = 650,
                            description = "Remakes are for losers.",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Voice Changer",
                            price = 900,
                            description = "Used for prank calls, party laughs, and cold-blooded murder",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Floor Plan",
                            price = 650,
                            description = "Nowhere to hide.",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),
                        StoreItem(
                            name = "Cease and Desist Letter",
                            price = 500,
                            description = "Identity theft is a crime, Jim.",
                            locationType = "Suburbs",
                            levelRequired = 3
                        ),

                        StoreItem( // Victor Crowley
                            name = "Hatchet Sharpener",
                            price = 150,
                            description = "Keeps Victor’s hatchet sharp.",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Gas Can",
                            price = 350,
                            description = "Because nothing says ‘well-thought-out plan’ like a flammable liquid and a bunch of morons.",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Mardi Gras Beads",
                            price = 450,
                            description = "Party hard, die harder.",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Midnight Swamp Tour",
                            price = 1000,
                            description = "Everyone loves a haunted boat ride",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Belt Sander",
                            price = 800,
                            description = "She didn't really go to NYU!",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Danielle Harris",
                            price = 3000,
                            description = "Danielle Harris sucks.",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Bayou Beavers DVD",
                            price = 1300,
                            description = "Filmed by Doug Shapiro.",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),
                        StoreItem(
                            name = "Crab Killer",
                            price = 950,
                            description = "Nobody likes itchy chicks.",
                            locationType = "Swamp",
                            levelRequired = 4
                        ),

                        StoreItem( // Jigsaw
                            name = "Tape Recorder",
                            price = 250,
                            description = "Press Play. Start the game.",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Bike Chain",
                            price = 225,
                            description = "Billy will be so happy!",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Dr. Gordon's Penlight",
                            price = 400,
                            description = "They must sell a ton of these...",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Blacklight",
                            price = 500,
                            description = "Remember the rules.",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Rusty saw",
                            price = 1500,
                            description = "Most people are so ungrateful to be alive.",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Dirty, Used syringes",
                            price = 1500,
                            description = "Embrace the pain to find what you seek.",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Start the Meat Grinder",
                            price = 1800,
                            description = "Bloated, rotting pigs that mirror society's waste.",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),
                        StoreItem(
                            name = "Child's Stuffed Animal",
                            price = 2000,
                            description = "Being shackled to grief will rot you from the inside...",
                            locationType = "Warehouse",
                            levelRequired = 5
                        ),

                        StoreItem( // Candyman
                            name = "Mirror",
                            price = 850,
                            description = "I see you, Helen...",
                            locationType = "Hive",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "Candy",
                            price = 950,
                            description = "Lure them in.",
                            locationType = "Hive",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "The Swarm",
                            price = 1000,
                            description = "They're coming.",
                            locationType = "Hive",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "Art Supplies",
                            price = 800,
                            description = "Paint with purpose. Bleed with intent.",
                            locationType = "Hive",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "Bonfire Ash",
                            price = 1300,
                            description = "Still hot. Still humming. Still chanting lullabies in voices too small to scream.",
                            locationType = "Hive",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "Forbidden Mural",
                            price = 1400,
                            description = "You painted what the world erased. Now they must remember.",
                            locationType = "Hive",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "Bees",
                            price = 1800,
                            description = "Weeping willow with your tears running down, why do you always weep and frown?",
                            locationType = "Hive",
                            levelRequired = 6
                        ),
                        StoreItem(
                            name = "Hook Shine",
                            price = 600,
                            description = "Keeps Candyman's Hook shiny!",
                            locationType = "Hive",
                            levelRequired = 6
                        ),

                        StoreItem( // Carrie
                            name = "Hair Brush",
                            price = 650,
                            description = "Girls like to look pretty.",
                            locationType = "High School",
                            levelRequired = 7
                        ),
                        StoreItem(
                            name = "Unlock the Prayer Closet",
                            price = 850,
                            description = "Prayer can't save us.",
                            locationType = "High School",
                            levelRequired = 7
                        ),
                        StoreItem(
                            name = "Prom Invitation",
                            price = 950,
                            description = "Tommy Ross! I can't believe it!",
                            locationType = "High School",
                            levelRequired = 7
                        ),
                        StoreItem(
                            name = "Bucket of Pig's Blood",
                            price = 1000,
                            description = "Perfect for honoring Prom Queens.",
                            locationType = "High School",
                            levelRequired = 7
                        ),
                        StoreItem(
                            name = "Telekinetic Rampage",
                            price = 1300,
                            description = "Make them ALL pay.",
                            locationType = "High School",
                            levelRequired = 7
                        ),
                        StoreItem(
                            name = "Box of Tampons",
                            price = 500,
                            description = "Plug it up!",
                            locationType = "High School",
                            levelRequired = 7
                        ),
                        StoreItem(
                            name = "Broken Crucifix",
                            price = 400,
                            description = "My faith is slipping.",
                            locationType = "High School",
                            levelRequired = 7
                        ),
                        StoreItem(
                            name = "Bloody Yearbook Page",
                            price = 600,
                            description = "\"What a long, strange trip it's been.\"",
                            locationType = "High School",
                            levelRequired = 7
                        ),

                        StoreItem( // Leprechaun
                            name = "Pogo Stick",
                            price = 500,
                            description = "He played pogo on his lung!",
                            locationType = "Lair",
                            levelRequired = 8
                        ),
                        StoreItem(
                            name = "WD40",
                            price = 500,
                            description = "They'll never hear me coming now!",
                            locationType = "Lair",
                            levelRequired = 8
                        ),
                        StoreItem(
                            name = "Ozzie's Ear",
                            price = 500,
                            description = "Found in Alex's truck.",
                            locationType = "Lair",
                            levelRequired = 8
                        ),
                    )
                    starterItems.forEach { repository.insertStoreItem(it) }
                }
            }
        }
    }

    private val _toastMessages = MutableSharedFlow<String>()
    val toastMessages = _toastMessages.asSharedFlow()

    fun buyItem(user: UserInfo, item: StoreItem) {
        viewModelScope.launch {
            if (user.money >= item.price) {
                val updatedUser = user.copy(money = user.money - item.price)
                repository.updateUserInfo(updatedUser)

                val inventoryItem = UserInventoryItem(
                    userId = user.userId,
                    itemId = item.id,
                    itemName = item.name,
                    quantity = 1
                )
                _toastMessages.emit("${item.name} added to inventory.")
                repository.addToInventory(inventoryItem)
                //repository.deleteStoreItem(item) - removes item from the store but that makes it too easy... I don't know if I wanna use it?

            } else {
                _toastMessages.emit("Not enough money to buy ${item.name}")
            }
        }
    }

    fun getInventoryForUser(userId: String): Flow<List<UserInventoryItem>> {
        return repository.getInventory(userId)
    }

    fun removeItemFromInventory(item: UserInventoryItem) {
        viewModelScope.launch {
            if (item.quantity > 1) {
                val updatedItem = item.copy(quantity = item.quantity - 1)
                repository.updateItem(updatedItem)
            } else {
                repository.deleteItem(item)
            }
        }
    }
}