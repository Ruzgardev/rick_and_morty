package com.ruzgaruludag.rickandmorty.newtask.base

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.kaopiz.kprogresshud.KProgressHUD
import com.ruzgaruludag.rickandmorty.R
import com.ruzgaruludag.rickandmorty.newtask.networking.NetworkManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
/**

 copied from mali project
 18.02.2024

 **/
@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    // TODO: 4.08.2023 Properties
    //@Inject
    //lateinit var userStorage: UserStorage

    @Inject
    lateinit var apiInterface: NetworkManager

    //lateinit var baseViewModel: BaseViewModel

    lateinit var progress: KProgressHUD

    // TODO: 4.08.2023 Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        progress = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            //.setLabel("Please wait")
            //.setDetailsLabel("Downloading data")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
    }

    // TODO: 4.08.2023 Delay
    fun launchWithDelay(duration: Long, function: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(duration)
            function()
        }
    }

    // TODO: 4.08.2023 Alert
    fun displayAlertDialog(
        title: String?,
        message: String?,
        positiveActionText: String,
        positiveCallBack: (() -> Unit)? = null,
    ){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveActionText, DialogInterface.OnClickListener { dialog, id ->
                positiveCallBack?.invoke()
            })
            .create()
            .show()
    }

    fun displayAlertDialogWithCancel(
        title: String?,
        message: String?,
        positiveActionText: String,
        negativeActionText: String,
        positiveCallBack: (() -> Unit)? = null,
        negativeCallBack: (() -> Unit)? = null
    ){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveActionText, DialogInterface.OnClickListener { dialog, id ->
                positiveCallBack?.invoke()
            })
            .setNegativeButton(negativeActionText, DialogInterface.OnClickListener { dialog, id ->
                negativeCallBack?.invoke()
            })
            .create()
            .show()
    }

    fun displayAlertDialogWithTextEdit(
        title: String?,
        message: String?,
        positiveActionText: String,
        negativeActionText: String,
        positiveCallBack: ((String) -> Unit)? = null,
        negativeCallBack: (() -> Unit)? = null
    ) {
        val inputView = TextInputEditText(this)
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setView(inputView)
            .setPositiveButton(positiveActionText, DialogInterface.OnClickListener { dialog, id ->
                if (inputView.text != null) {
                    val text = inputView.text.toString()
                    positiveCallBack?.invoke(text)
                }
            })
            .setNegativeButton(negativeActionText, DialogInterface.OnClickListener { dialog, id ->
                negativeCallBack?.invoke()
            })
            .create()
            .show()
    }

    fun displayAlertDialogNeutralButton(
        title: String?,
        message: String?,
        positiveActionText: String,
        negativeActionText: String,
        neutralActionText: String,
        positiveCallBack: (() -> Unit)? = null,
        negativeCallBack: (() -> Unit)? = null,
        neutralCallBack: (() -> Unit)? = null
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveActionText, DialogInterface.OnClickListener { dialog, id ->
                positiveCallBack?.invoke()
            })
            .setNegativeButton(negativeActionText, DialogInterface.OnClickListener { dialog, id ->
                negativeCallBack?.invoke()
            })
            .setNeutralButton(neutralActionText, DialogInterface.OnClickListener { dialog, id ->
                neutralCallBack?.invoke()
            })
            .create()
            .show()
    }

    // TODO: 5.08.2023 Indicator
    fun startIndicator(bgColor: Int){
        progress.setBackgroundColor(bgColor)
        progress.show()
        /*parentLayout = findViewById(android.R.id.content)
        val blackoutLayout = RelativeLayout(this)
        blackoutLayout.tag = "blackoutLayoutTag"
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        blackoutLayout.layoutParams = params
        blackoutLayout.gravity = Gravity.CENTER
        blackoutLayout.setBackgroundColor(bgColor)
        parentLayout.addView(blackoutLayout)

        val lottie = LottieAnimationView(this)
        lottie.tag = "lottieAnimationViewTag"
        val lottieParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        lottie.layoutParams = lottieParams
        lottie.scale = 0.2f
        lottie.loop(true)
        lottie.setAnimation("loadingalt.json")
        lottie.playAnimation()
        blackoutLayout.addView(lottie)
        blackoutLayout.bringToFront()*/
    }

    fun stopIndicator(){
        progress.dismiss()
        /*parentLayout = findViewById(android.R.id.content)
        for (layout in parentLayout.children.toList()) {
            if (layout.tag == "blackoutLayoutTag"){
                parentLayout.removeView(layout)
            }
        }*/
    }
}