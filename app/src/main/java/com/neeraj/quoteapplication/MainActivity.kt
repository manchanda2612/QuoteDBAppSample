package com.neeraj.quoteapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.neeraj.quoteapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val quoteDao = QuoteDatabase.getDatabaseInstance(applicationContext).getQuoteDAO()
        val quoteRepository = QuoteRepository(quoteDao)

        mViewModel = ViewModelProvider(this, MainViewModelFactory(quoteRepository)).get(MainViewModel::class.java)

        mViewModel.getQuotes().observe(this, Observer {
            mBinding.txvMainActivity.text = it.toString()
        })


        mBinding.btnMainActivity.setOnClickListener {
            val quote = Quote(101,"The quick brown fox jumps over the little lazy dog.","Neeraj Manchanda")
            mViewModel.insertQuote(quote)

        }


    }
}