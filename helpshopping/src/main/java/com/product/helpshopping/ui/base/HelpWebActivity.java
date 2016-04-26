package com.product.helpshopping.ui.base;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.product.common.utils.LogUtils;
import com.product.common.utils.StringUtils;
import com.product.helpshopping.R;


/**
 * 初始化Web activity，它用于显示帮助、介绍等页面。
 *
 * @author tangjy
 */
public abstract class HelpWebActivity extends HelpBaseActivity {
    private static final String TAG = HelpWebActivity.class.getSimpleName();
    protected WebView mWebView = null;
    protected String mUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_web);
        initViews();
        initEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    protected void setContentLayer() {
//        setContentView(R.layout.activity_base_web);
//    }

    private void initViews() {
        mWebView = (WebView) findViewById(R.id.web_container);
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    private void initEvents() {
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoadingDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissLoadingDialog();
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//        case android.R.id.home:
//            onBackPressed();
//            break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    protected void loadUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            showToast(R.string.common_url_invalid);
            return;
        }
        LogUtils.i(TAG, "url = " + url);

        mWebView.loadUrl(url);
    }
}
