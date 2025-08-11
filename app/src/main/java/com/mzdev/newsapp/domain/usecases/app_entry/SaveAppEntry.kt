package com.mzdev.newsapp.domain.usecases.app_entry

import com.mzdev.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}