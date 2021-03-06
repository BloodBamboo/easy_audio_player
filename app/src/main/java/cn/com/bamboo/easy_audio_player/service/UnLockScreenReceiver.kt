package cn.com.bamboo.easy_audio_player.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cn.com.bamboo.easy_audio_player.MusicApp
import cn.com.bamboo.easy_audio_player.util.Constant
import cn.com.bamboo.easy_audio_player.view.LockScreenActivity
import cn.com.bamboo.easy_common.util.SharedPreferencesUtil

/**
 * 屏幕解锁监听
 */
class UnLockScreenReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            Log.e(
                "===",
                "UnLockScreenReceiver === ${(context?.applicationContext as MusicApp).lockScreenVisible} ====== LOCK_SCREEN = ${SharedPreferencesUtil.getData(
                    Constant.LOCK_SCREEN,
                    false
                ) as Boolean}"
            )
            if (intent?.action == Intent.ACTION_SCREEN_ON
                && SharedPreferencesUtil.getData(Constant.LOCK_SCREEN, false) as Boolean
                && !(context?.applicationContext as MusicApp).lockScreenVisible
            ) {
                context?.startActivity(Intent(context, LockScreenActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                })
            }
        }
    }
}