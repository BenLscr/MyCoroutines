package com.benlsc.mycoroutines.async

import android.os.AsyncTask
import android.util.Log
import org.w3c.dom.Document
import java.io.InputStream
import java.lang.Exception
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class XMLAsyncTask(private val consumer: DocumentConsumer) : AsyncTask<Any, Any, Any>() {

    override fun doInBackground(vararg p0: Any?): Any {
        try {
            val url = URL("https://fr.wikipedia.org/w/api.php?hidebots=1&days=7&limit=50&hideWikibase=1&action=feedrecentchanges&feedformat=rss")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val stream: InputStream = connection.inputStream
            stream.use {
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(it)
            }
        } catch (ex: Exception) {
            Log.e("XMLAsyncTask", "Exception while downloading", ex)
            throw RuntimeException(ex)
        }
    }

    override fun onPostExecute(result: Any?) {
        Log.e("XMLAsyncTask","Finished")
        if (result is Document) {
            Log.e("Wikipedia", result.toString())
            consumer.setXMLDocument(result)
        }
    }

    interface DocumentConsumer {
        fun setXMLDocument(document: Document)
    }

}