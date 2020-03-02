package com.icom.terramar.app.ui.controllers.utils

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.Html
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.test.appwhere.R
import java.io.File
import java.io.FileOutputStream


abstract class Utils {


    companion object {
        fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
                for (permission in permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        return false
                    }
                }
            }
            return true
        }

        fun shouldShowPermissionsAlert(activity: Activity?, vararg permissions: String): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity != null) {
                for (permission in permissions) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                        return true
                    }
                }
            }
            return false
        }


        fun addBottomDots(context: Context, llDots: LinearLayout, length: Int, currentPage: Int, bulletOffColor: Int) {
            val dots = arrayOfNulls<TextView>(length)
            val colorsActive = ContextCompat.getColor(context, R.color.colorAccent)
            val colorsInactive = ContextCompat.getColor(context, bulletOffColor)

            llDots.removeAllViews()
            for (i in dots.indices) {
                dots[i] = TextView(context)

                dots[i]?.text = Html.fromHtml("&#8226;")
                dots[i]?.textSize = 45f
                dots[i]?.setTextColor(colorsInactive)
                llDots.addView(dots[i])
            }

            if (dots.isNotEmpty()) {
                dots[currentPage]?.setTextColor(colorsActive)
            }
        }

        fun hideSoftKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val focusedView = activity.currentFocus
            if (focusedView != null) {
                imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
            }
        }

        fun compareIcons(context: Context, viewIcon: Drawable, vararg idDrawables: Int): Boolean {
            for (item in idDrawables) {
                if (ContextCompat.getDrawable(context, item)!!.constantState == viewIcon.constantState) {
                    return java.lang.Boolean.TRUE
                }
            }
            return java.lang.Boolean.FALSE
        }

        /*
        fun getPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(Constans.ACTIVITY_PREFERENCES, Context.MODE_PRIVATE)


        }


         */





        /*
        fun getPathFromImageGallery(activity: Activity, uri: Uri): String {
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            val cursor = activity.contentResolver.query(uri,
                filePathColumn, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[Constans.INT_NUMBER_ZERO])
                val picturePath = cursor.getString(columnIndex)
                cursor.close()
                return picturePath
            }
            return Constans.EMPTY_STRING
        }




        fun saveImage(image: Bitmap?, name: String): String? {
            if (image != null) {
                var savedImagePath: String? = null

                val imageFileName = "AR$name.png"
                val storageDir = File(Environment.getExternalStorageDirectory().toString() + File.separator + Constans.FOLDER + File.separator)
                var success = true
                if (!storageDir.exists()) {
                    success = storageDir.mkdirs()
                }
                if (success) {
                    val imageFile = File(storageDir, imageFileName)
                    savedImagePath = imageFile.absolutePath
                    try {
                        val fOut = FileOutputStream(imageFile)
                        image.compress(Bitmap.CompressFormat.PNG, 100, fOut)
                        fOut.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
                return savedImagePath
            } else {
                return Constans.EMPTY_STRING
            }
        }
*/
        fun takescreenShot (view: View): Bitmap {
            val screenView = view.rootView
            screenView.isDrawingCacheEnabled = true

            val bitmap = Bitmap.createBitmap(screenView.drawingCache)
            screenView.isDrawingCacheEnabled = false

            return bitmap


        }




        fun getCtx(): Context? {
            val context : Context? = null

            val applicationContext = context?.applicationContext
            return applicationContext
        }


        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        private fun showNotification(mBuilder: NotificationCompat.Builder, icon: Int, title: String,
                                     message: String, notificationId: Int, resultPendingIntent: PendingIntent,
                                     context: Context
        ) {

            val notificationBuilder = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setStyle(NotificationCompat.BigTextStyle())
                .setSmallIcon(icon)
                //.setLargeIcon(BitmapFactory.decodeResource(context.resources, icon))
                .setContentText(message)

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //The id of the channel.
                val CHANNEL_ID = "my_channel_01"
                //The user-visible name of the channel.
                val name = "chanel"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
                notificationBuilder.setChannelId(CHANNEL_ID)
                notificationManager.createNotificationChannel(mChannel)
            }
            notificationManager.notify(notificationId, notificationBuilder.build())
        }


    }


}