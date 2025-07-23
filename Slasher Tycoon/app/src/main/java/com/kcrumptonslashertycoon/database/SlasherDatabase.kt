// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Slasher::class, StoreItem::class, UserInfo::class, UserInventoryItem::class, SlasherNotification::class, UserTask::class, RoomEffect::class, Collectible::class  ], version = 26)
@TypeConverters(SlasherTypeConverters::class)
abstract class SlasherDatabase : RoomDatabase() {
    abstract fun slasherDao(): SlasherDao
    abstract fun storeItemDao(): StoreItemDao
    abstract fun userInfoDao(): UserInfoDao
    abstract fun userInventoryDao(): UserInventoryDao
    abstract fun notificationDao(): SlasherNotificationDao
    abstract fun userTaskDao(): UserTaskDao
    abstract fun roomEffectDao(): RoomEffectDao
    abstract fun collectibleDao(): CollectibleDao


    companion object {
        @Volatile private var INSTANCE: SlasherDatabase? = null

        // All my migrations... I'm up to version 26 because I kept adding stuff and changing my mind about things...
        private val MIGRATION_10_11 = object : Migration(10, 11) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE room_effects RENAME TO room_effects_old")

                database.execSQL(
                    """
                    CREATE TABLE room_effects (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        room TEXT NOT NULL,
                        description TEXT NOT NULL,
                        effectType TEXT NOT NULL,
                        visitorBoost INTEGER NOT NULL,
                        timestamp INTEGER NOT NULL
                    )
                    """.trimIndent()
                )

                database.execSQL(
                    """
                    INSERT INTO room_effects (id, room, description, effectType, visitorBoost, timestamp)
                    SELECT id, room, description, effectType, value, timestamp FROM room_effects_old
                    """.trimIndent()
                )

                database.execSQL("DROP TABLE room_effects_old")
            }
        }

        val MIGRATION_13_14 = object : Migration(13, 14) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_info ADD COLUMN xp INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE user_info ADD COLUMN level INTEGER NOT NULL DEFAULT 1")
            }
        }
        val MIGRATION_14_15 = object : Migration(14, 15) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add the new requiredLevel column
                database.execSQL("ALTER TABLE slashers ADD COLUMN requiredLevel INTEGER NOT NULL DEFAULT 1")
            }
        }
        val MIGRATION_15_16 = object : Migration(15, 16) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE store_items ADD COLUMN locationType TEXT NOT NULL DEFAULT ''")
            }
        }

        val MIGRATION_16_17 = object : Migration(16, 17) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_tasks ADD COLUMN effectType TEXT DEFAULT 'attraction' NOT NULL")
            }
        }

        val MIGRATION_17_18 = object : Migration(17, 18) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_tasks ADD COLUMN createdAt INTEGER NOT NULL DEFAULT 0")
            }
        }

        val MIGRATION_18_19 = object : Migration(18, 19) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_tasks ADD COLUMN levelRequired INTEGER NOT NULL DEFAULT 1")
            }
        }
        val MIGRATION_19_20 = object : Migration(19, 20) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE store_items ADD COLUMN levelRequired INTEGER NOT NULL DEFAULT 1")
            }
        }

        val MIGRATION_20_21 = object : Migration(20, 21) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_tasks ADD COLUMN taskType TEXT NOT NULL DEFAULT 'regular'")

                database.execSQL("ALTER TABLE user_tasks ADD COLUMN collectibleReward TEXT")
            }
        }

        val MIGRATION_22_23 = object : Migration(22, 23) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_info ADD COLUMN lastCookoffTime INTEGER NOT NULL DEFAULT 0")
            }
        }

        val MIGRATION_23_24 = object : Migration(23, 24) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_info ADD COLUMN lastPhotoTime INTEGER NOT NULL DEFAULT 0")
            }
        }

        val MIGRATION_24_25 = object : Migration(24, 25) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add new column with default value of 1
                database.execSQL("ALTER TABLE collectibles ADD COLUMN count INTEGER NOT NULL DEFAULT 1")
            }
        }

        fun getDatabase(context: Context): SlasherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SlasherDatabase::class.java,
                    "slasher-database"
                )
                    .addMigrations(MIGRATION_10_11)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            // Insert Slashers
                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).slasherDao().apply {
                                    insert(
                                        Slasher(
                                            name = "Jason Voorhees",
                                            locationType = "Cabin",
                                            isUnlocked = true,
                                            kills = 0,
                                            weapon = "Machete",
                                            requiredLevel = 2
                                        )
                                    )

                                    insert(
                                        Slasher(
                                            name = "Freddy Krueger",
                                            locationType = "Boiler Room",
                                            isUnlocked = true,
                                            kills = 0,
                                            weapon = "Glove Claws",
                                            requiredLevel = 1
                                        )
                                    )

                                    insert(
                                        Slasher(
                                            name = "Ghostface",
                                            locationType = "Suburbs",
                                            isUnlocked = false,
                                            kills = 0,
                                            weapon = "Knife",
                                            requiredLevel = 3
                                        )
                                    )

                                    insert(
                                        Slasher(
                                            name = "Victor Crowley",
                                            locationType = "Swamp",
                                            isUnlocked = false,
                                            kills = 0,
                                            weapon = "Hatchet",
                                            requiredLevel = 4
                                        )
                                    )

                                    insert(
                                        Slasher(
                                            name = "Jigsaw",
                                            locationType = "Warehouse",
                                            isUnlocked = false,
                                            kills = 0,
                                            weapon = "Traps",
                                            requiredLevel = 5
                                        )
                                    )
                                    insert(
                                        Slasher(
                                            name = "Candyman",
                                            locationType = "Hive",
                                            isUnlocked = false,
                                            kills = 0,
                                            weapon = "Hook",
                                            requiredLevel = 6
                                        )
                                    )
                                    insert(
                                        Slasher(
                                            name = "Carrie",
                                            locationType = "High School",
                                            isUnlocked = false,
                                            kills = 0,
                                            weapon = "Telekinesis",
                                            requiredLevel = 7
                                        )
                                    )
                                }
                            }

                            CoroutineScope(Dispatchers.IO).launch {
                                val db = getDatabase(context)
                                val notificationDao = db.notificationDao()

                                notificationDao.insert(
                                    SlasherNotification(
                                        slasherName = "Jason",
                                        message = "Jason is bored at the cabin. Throw a party!",
                                        locationType = "Cabin"
                                    )
                                )

                                notificationDao.insert(
                                    SlasherNotification(
                                        slasherName = "Freddy",
                                        message = "Freddy needs more fuel for the furnace.",
                                        locationType = "Boiler Room"
                                    )
                                )

                                val effectDao = db.roomEffectDao()
                                effectDao.insert(
                                    RoomEffect(
                                        room = "Boiler Room",
                                        description = "Fueled the furnace 🔥",
                                        effectType = "attraction",
                                        visitorBoost = 5
                                    ))
                            }
                        }
                    })
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}