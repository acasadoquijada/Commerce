package com.sdos.commerce.ui.viewmodels


import android.content.Context
import com.manday.coredata.ExecutorViewModel
import com.sdos.commerce.data.room.RoomController

class SplashFragmentViewModel: ExecutorViewModel() {

    fun initialize(context: Context, dbInitialized: () -> Unit) {
        if (RoomController.getInstance() == null) {
            doInBackgroundAndWait {
                RoomController.initialize(context) {
                    waitAndRunInForeground{
                        dbInitialized.invoke()
                    }
                }
            }
        }
        else {
            waitAndRunInForeground{
                dbInitialized.invoke()
            }
        }
    }
}