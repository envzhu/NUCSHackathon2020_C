package com.better.alarm.background

import android.os.AsyncTask
import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class Http : AsyncTask<String, Void, Boolean>() {

    override fun doInBackground(vararg params: String): Boolean? {
        var httpURLConnection: HttpURLConnection? = null
        val cmd = params[0]
        val url = URL(params[1])
        val filename = params[2]

        try {
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connect()

            val status = httpURLConnection.responseCode
            if (status == HttpURLConnection.HTTP_OK) {
                Log.e("Http","Start GET")
                val dataInputStream = DataInputStream(httpURLConnection.inputStream)
                val dataOutputStream = DataOutputStream(FileOutputStream(filename))

                val buffer = ByteArray(4096)
                var readByte = dataInputStream.read(buffer)
                while (readByte != -1) {
                    dataOutputStream.write(buffer, 0, readByte)
                    readByte = dataInputStream.read(buffer)
                }

                dataInputStream.close()
                dataOutputStream.close()
                Log.e("Http","GET Successfull!")
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect()
            }
        }
        return false
    }
}