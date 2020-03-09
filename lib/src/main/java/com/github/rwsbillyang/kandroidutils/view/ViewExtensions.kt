package com.github.rwsbillyang.kandroidutils.view

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment


/**
 * 扩展函数：自动调用glide进行图片加载
 * */
//fun ImageView.loadImg(url: String?, placeHolder: Drawable? = null,listener: RequestListener<Drawable>? = null) {
//    if (url == null) {
//        placeHolder?.let{
//            this.setImageDrawable(it)
//        }
//    } else {
//        Glide.with(this.context).load(url).listener(listener).into(this)
//    }
//}

/**
 * 扩展成员属性：通过Boolean变量控制view是否显示
 * */
var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

/**
 * 扩展函数：隐藏view
 * */
fun View.hide() {
    visible = false
}
/**
 * 扩展函数：显示view
 * */
fun View.show() {
    visible = true
}


/**
 * toast类型
 * */
//enum class ToastType{
//    SUCCESS, NORMAL, WARNING, ERROR
//}

/**
 * 扩展函数，显示toast
 * */
fun Context.toast(msgId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, msgId, duration).show()
fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, msg, duration).show()
fun Fragment.toast(msgId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this.requireContext(), msgId, duration).show()
fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this.requireContext(), msg, duration).show()


/**
 * 扩展函数，显示toast
 * */
//fun Context.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT, type: ToastType = ToastType.NORMAL) {
//    when (type) {
//        ToastType.WARNING -> Toasty.warning(this, msg, duration, true).show()
//        ToastType.ERROR -> Toasty.error(this, msg, duration, true).show()
//        ToastType.NORMAL -> Toasty.info(this, msg, duration, false).show()
//        ToastType.SUCCESS -> Toasty.success(this, msg, duration, true).show()
//    }
//}



/**
 * 扩展函数，Fragment显示对话框
 * */
fun Fragment.showDialog(title: String, msg: String,
                         positiveBtnText: String, negativeBtnText: String?,
                         positiveBtnClickListener: DialogInterface.OnClickListener,
                         negativeBtnClickListener: DialogInterface.OnClickListener?): AlertDialog {
    val builder = AlertDialog.Builder(this.requireContext())
        .setTitle(title)
        .setMessage(msg)
        .setCancelable(true)
        .setPositiveButton(positiveBtnText, positiveBtnClickListener)
    if (negativeBtnText != null)
        builder.setNegativeButton(negativeBtnText, negativeBtnClickListener)
    val alert = builder.create()
    alert.show()
    return alert
}
/**
 * 扩展函数，Fragment显示对话框
 * */
fun Fragment.showDialog(titleResId: Int, msgResId: Int,
                        positiveBtnTextResId: Int, negativeBtnTextResId: Int?,
                        positiveBtnClickListener: DialogInterface.OnClickListener,
                        negativeBtnClickListener: DialogInterface.OnClickListener?,extraMsg:String? = null): AlertDialog {
    val context = this.requireContext()
    val title = context.resources.getString(titleResId)
    val msg = if(extraMsg == null) context.resources.getString(msgResId) else context.resources.getString(msgResId,extraMsg)
    val positiveBtnText = context.resources.getString(positiveBtnTextResId)
    val negativeBtnText = negativeBtnTextResId?.let{ context.resources.getString(negativeBtnTextResId) }

    return showDialog(title, msg, positiveBtnText, negativeBtnText,positiveBtnClickListener, negativeBtnClickListener)
}



@Deprecated("Use navigation in Android architecture components instead")
fun AppCompatActivity.gotoFragment(fragment: Fragment,containerId:Int, pushStack: Boolean = true)
{

    if(pushStack){
        supportFragmentManager
            .beginTransaction()
            .replace(containerId,fragment)
            .addToBackStack(null)
            .commit()
    }else
    {
        supportFragmentManager
            .beginTransaction()
            .replace(containerId,fragment)
            .commit()
    }
}

@Deprecated("Use navigation in Android architecture components instead")
fun Fragment.gotoFragment(fragment: Fragment,containerId:Int, pushStack: Boolean = true)
{
    if(pushStack){
        requireFragmentManager()
            .beginTransaction()
            .replace(containerId,fragment)
            .addToBackStack(null)
            .commit()
    }else
    {
        requireFragmentManager()
            .beginTransaction()
            .replace(containerId,fragment)
            .commit()
    }
}

/**
 * 为Button设置左侧内部的内嵌图片
 * */
fun Button.setInlineLeftDrawable(drawableRes: Int, context: Context){
    val drawable = AppCompatResources.getDrawable(context, drawableRes)

    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
    {
        this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    } else {
        this.setCompoundDrawables(drawable, null, null, null)
    }
}