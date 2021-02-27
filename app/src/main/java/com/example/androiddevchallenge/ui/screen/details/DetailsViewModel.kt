package com.example.androiddevchallenge.ui.screen.details

import com.example.androiddevchallenge.data.DataSource
import com.example.androiddevchallenge.ui.screen.BaseViewModel

class DetailsViewModel(private val puppyId: String) : BaseViewModel() {

    val puppy = DataSource.puppies.first { it.id == puppyId }
}
