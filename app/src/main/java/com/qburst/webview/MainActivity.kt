package com.qburst.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.qburst.webview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


   
    private lateinit var webViewVariable:WebView
    private lateinit var btnLoadingWebView:Button

    private lateinit var btnSavePDF: Button
    private lateinit var printJob: PrintJob
    private var printBtnPressed = false



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        // Initialize Variables
        webViewVariable = binding.webView
        btnLoadingWebView = binding.btnWebView
        btnSavePDF = binding.btnSavePdf

        // Web View Button
        btnLoadingWebView.setOnClickListener {
            btnLoadingWebView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            webViewSetup(webViewVariable)
        }

        // Pdf Button
        btnSavePDF.setOnClickListener {
            printWebPage(webViewVariable)
        }



        // Set up back arrow click listener
        binding.customToolbar.setOnClickListener{
            if (webViewVariable.canGoBack()){
                webViewVariable.goBack()
            }else{
                // Handle custom too bar back button if need
                finish()
            }
        }


    }



    private fun printWebPage(webViewVariable: WebView) {
        printBtnPressed = true
        val printManager =  this.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val jobName = " webpage" + webViewVariable.url
        val printAdapter = webViewVariable.createPrintDocumentAdapter(jobName)
        printJob = printManager.print(jobName, printAdapter, PrintAttributes.Builder().build())

    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup(webViewVariable: WebView) {
        webViewVariable.webViewClient = WebViewClient()
        webViewVariable.apply {
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
            settings.setSupportZoom(true)
            loadUrl("https://www.geeksforgeeks.org/android-webview-in-kotlin/")
        }

    }


    // WebViewClient to handle page loading
    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressBar.visibility = View.VISIBLE
        }

        // ProgressBar will disappear once page is loaded
        override fun onPageFinished(view: WebView, url: String?) {
            super.onPageFinished(view, url)

            webViewVariable = view
            binding.webView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.btnSavePdf.visibility = View.VISIBLE
        }

    }


    }