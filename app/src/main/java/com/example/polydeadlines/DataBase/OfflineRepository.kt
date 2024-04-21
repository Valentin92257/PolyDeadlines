package com.example.polydeadlines.DataBase

import com.example.polydeadlines.Model.Panel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class OfflineRepository @Inject constructor(private val panelDao: PanelDao) : PanelsRepository {
    override fun getAllPanels(): List<Panel> = panelDao.getAll()

    override suspend fun insertPanel(panel: Panel) = panelDao.insert(panel)

    override suspend fun deleteItem(panel: Panel) = panelDao.delete(panel)

    override suspend fun updateItem(panel: Panel) = panelDao.update(panel)
}