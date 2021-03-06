package com.tjhello.demo.adeasy

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import com.tjhello.adeasy.info.ADInfo
import kotlinx.android.synthetic.main.test_activity_layout.*

/**
 * 作者:天镜baobao
 * 时间:2019/12/4  17:51
 * 说明:允许使用，但请遵循Apache License 2.0
 * 使用：
 * Copyright 2019/12/4 天镜baobao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class TestActivity : AppActivity() {

    override fun onInitValue(savedInstanceState: Bundle?) {
        adEasy.isAutoShowBanner(true)
    }

    override fun onInitView() {
        setContentView(R.layout.test_activity_layout)

        refreshBtVideo()
        refreshBtIns()
        refreshBtInsVideo()
        refreshBtBanner()

        btShowVideo.setOnClickListener {
            adEasy.showVideo{adInfo, isReward ->
                refreshBtVideo()
                Toast.makeText(this,"Close Video :$isReward", Toast.LENGTH_LONG).show()
            }
        }
        btShowInterstitialVideo.setOnClickListener {
            adEasy.showInterstitialVideo {
                refreshBtIns()
                Toast.makeText(this,"Close InterstitialVideo",Toast.LENGTH_LONG).show()
            }
        }
        btShowInterstitial.setOnClickListener {
            adEasy.showInterstitial {
                refreshBtInsVideo()
                Toast.makeText(this,"Close Interstitial",Toast.LENGTH_LONG).show()
            }
        }

        btShowBanner.setOnClickListener {
            refreshBtBanner()
            adEasy.showBanner()
        }

        btHideBanner.setOnClickListener {
            adEasy.hideBanner()
        }
    }

    override fun onLoadData() {

    }

    private fun refreshBtVideo(){
        if(adEasy.hasVideo()){
            btShowVideo.alpha = 1f
        }else{
            btShowVideo.alpha = 0.5f
        }
    }

    private fun refreshBtIns(){
        if(adEasy.hasInterstitial()){
            btShowInterstitial.alpha = 1f
        }else{
            btShowInterstitial.alpha = 0.5f
        }
    }

    private fun refreshBtInsVideo(){
        if(adEasy.hasInterstitialVideo()){
            btShowInterstitialVideo.alpha = 1f
        }else{
            btShowInterstitialVideo.alpha = 0.5f
        }
    }

    private fun refreshBtBanner(){
        if(adEasy.hasBanner()){
            btShowBanner.alpha = 1f
        }else{
            btShowBanner.alpha = 0.5f
        }
    }

    override fun onAdLoad(adInfo: ADInfo, isSelf: Boolean) {
        when(adInfo.type){
            ADInfo.TYPE_VIDEO->refreshBtVideo()
            ADInfo.TYPE_INTERSTITIAL->refreshBtIns()
            ADInfo.TYPE_INTERSTITIAL_VIDEO->refreshBtInsVideo()
            ADInfo.TYPE_BANNER->refreshBtBanner()
        }
    }

    override fun onCreateBanner(): ViewGroup? {
        return bannerLayout
    }
}