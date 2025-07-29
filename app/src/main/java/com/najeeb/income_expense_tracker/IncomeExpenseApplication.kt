package com.najeeb.income_expense_tracker
import android.app.Application
import android.content.Context

class IncomeExpenseApplication : Application() {
  init { application = this }

  companion object {
    lateinit var application: IncomeExpenseApplication
    fun getAppContext(): Context = application.applicationContext
  }
}