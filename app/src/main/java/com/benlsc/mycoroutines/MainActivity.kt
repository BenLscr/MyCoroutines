package com.benlsc.mycoroutines

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.benlsc.mycoroutines.async.XMLAsyncTask
import com.benlsc.mycoroutines.coroutineMerged.CoroutineMergedActivity
import com.benlsc.mycoroutines.injection.Injection
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(), IPickResult {


    private val mainViewModel : MainViewModel by lazy { ViewModelProviders.of(this, Injection.provideViewModelFactory(applicationContext)).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolbar()

        addListener()

        addObserver()
    }

    private fun configureToolbar() = setSupportActionBar(toolbar)

    private fun addListener() {
        try_database.setOnClickListener {
            mainViewModel.tryCoroutineWithRoom()
            //mainViewModel.tryClassicWithRoom()
        }
        dl_wikipedia.setOnClickListener {
            mainViewModel.fetchDoc()
        }
        take_photo.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }
        show_photo_main.setOnClickListener {
            doCompressionMain(photo)
        }
        show_photo_coroutine.setOnClickListener {
            doCompressionCoroutine(photo)
        }
        coroutine_fragment.setOnClickListener {
            startNewActivity()
        }
        test_many_coroutines.setOnClickListener {
            mainViewModel.testManyCoroutines()
        }
        runblocking_coroutines.setOnClickListener {
            mainViewModel.testRunBlocking()
        }
    }

    private fun addObserver() {
        mainViewModel.document.observe(this, Observer {
            Log.e("Wikipedia", it.toString())
            val adapter = RSSAdapter()
            adapter.getNewXML(it)
        })
        mainViewModel.propertyLiveData.observe(this, Observer {
            description.text = it.description
            name.text = it.agent?.name
            surname.text = it.agent?.surname
            postalcode.text = it.address?.postalCode.toString()
        })
    }

    private fun wikipediaAsynTask() {
        val adapter = RSSAdapter()
        val asyncTask = XMLAsyncTask(adapter)
        asyncTask.execute()
    }

    /**
     * COMPRESSION
     */

    private var photo: Bitmap? = null

    override fun onPickResult(r: PickResult) {
        if (r.error == null) {
            photo = r.bitmap
        } else {
        }
    }

    /*
     * Main
     */
    private fun doCompressionMain(photo: Bitmap?) {
        if (photo != null) {
            var newPhoto: Bitmap = photo
            for (i in 1..1000) {
                newPhoto = Bitmap.createScaledBitmap(photo, 50, 50, true)
                newPhoto = Bitmap.createScaledBitmap(photo, 2000, 2000, true)
            }
            showNewPhoto(newPhoto)
        }
    }

    /**
     * Coroutine
     */
    private fun doCompressionCoroutine(photo: Bitmap?) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (photo != null) {
                var newPhoto: Bitmap = photo
                for (i in 1..1000) {
                    newPhoto = Bitmap.createScaledBitmap(photo, 50, 50, true)
                    newPhoto = Bitmap.createScaledBitmap(photo, 2000, 2000, true)
                }
                withContext(Dispatchers.Main) {
                    showNewPhoto(newPhoto)
                }
            }
        }
    }

    private fun showNewPhoto(photo: Bitmap) {
        photo_container.setImageBitmap(photo)
    }

    /**
     * Activity
     */
    private fun startNewActivity() {
        val intent = Intent(this, CoroutineMergedActivity::class.java)
        startActivity(intent)
    }

}
