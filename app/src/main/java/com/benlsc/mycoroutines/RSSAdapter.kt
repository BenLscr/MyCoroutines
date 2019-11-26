package com.benlsc.mycoroutines

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benlsc.mycoroutines.async.XMLAsyncTask
import org.w3c.dom.Document

class RSSAdapter : RecyclerView.Adapter<RSSAdapter.ViewHolder>(),
    XMLAsyncTask.DocumentConsumer {

    private var document: Document? = null

    override fun setXMLDocument(document: Document) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getNewXML(document: Document) {
        this.document = document
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder(val mView: View): RecyclerView.ViewHolder(mView) {

    }

}