package com.neeraj.quoteapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDAO(): QuoteDao


    companion object {

        private var mInstance: QuoteDatabase? = null

        fun getDatabaseInstance(context: Context): QuoteDatabase {

            if (mInstance == null) {

                synchronized(this) {

                    mInstance = Room.databaseBuilder(
                        context,
                        QuoteDatabase::class.java,
                        "quote_database"
                    )

                        .createFromAsset("quotes.db")
                        .build()
                }
            }

            return mInstance!!

        }

    }

}