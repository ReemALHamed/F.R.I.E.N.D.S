package com.example.friends

import android.content.Context
import java.io.IOException
import java.io.InputStream

object Utils {
    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        //read JSON file from the assets folder
        val jsonString: String = try {
            val file: InputStream = context.assets.open(fileName!!)
            val size: Int = file.available()
            val buffer = ByteArray(size)
            file.read(buffer)
            file.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}