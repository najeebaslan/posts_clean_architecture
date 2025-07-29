package com.najeeb.income_expense_tracker.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.najeeb.income_expense_tracker.data.models.JobModel
import com.najeeb.income_expense_tracker.data.models.listJobs

class HomeViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var state by mutableStateOf(restoreSelectedJobs());

    private fun getJobs() = listJobs
    fun toggleFavoriteState(jobId: Int) {
        val myJobs = state.toMutableList();
        val indexJob = myJobs.indexOfFirst { it.id == jobId }
        myJobs[indexJob] = myJobs[indexJob].copy(isFavorite = !myJobs[indexJob].isFavorite)
        storeSelectedJobs(myJobs[indexJob])
        state = myJobs;
    }


    private fun storeSelectedJobs(jobs: JobModel) {
        val savedJobsList =
            savedStateHandle.get<List<Int>?>(SELECTED_JOBS_KEY).orEmpty().toMutableList();
        if (jobs.isFavorite) savedJobsList.add(jobs.id)
        else savedJobsList.remove(jobs.id)
        savedStateHandle[SELECTED_JOBS_KEY] = savedJobsList;
    }

    private fun restoreSelectedJobs(): List<JobModel> {
        val savedIds = savedStateHandle.get<List<Int>>(SELECTED_JOBS_KEY).orEmpty()
        return getJobs().map { job ->
            job.copy(isFavorite = savedIds.contains(job.id))
        }
    }

    companion object {
        // This for stored constants types in ViewModel
        const val SELECTED_JOBS_KEY = "selectedJobs"

    }
}