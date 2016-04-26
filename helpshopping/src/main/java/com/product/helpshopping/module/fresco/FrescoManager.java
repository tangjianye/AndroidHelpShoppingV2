package com.product.helpshopping.module.fresco;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.product.helpshopping.R;
import com.product.common.utils.LogUtils;


/**
 * Volley网络通信单例管理类<br>
 * Created by tangjy on 2015/3/2.
 */
public class FrescoManager {
    private static final String TAG = FrescoManager.class.getSimpleName();
    private static Context sCtx;
    private static FrescoManager sINSTANTCE;

    private FrescoManager() {
    }

    public static synchronized FrescoManager getInstance() {
        if (sINSTANTCE == null) {
            sINSTANTCE = new FrescoManager();
        }
        return sINSTANTCE;
    }

    public void init(Context context) {
        if (!(context instanceof Application)) {
            throw new AssertionError();
        }

        sCtx = context;
        Fresco.initialize(context, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(context));
    }

    public GenericDraweeHierarchy getDefaultOptions(Context context) {
        GenericDraweeHierarchy options = new GenericDraweeHierarchyBuilder(context.getResources())
                .setPlaceholderImage(ContextCompat.getDrawable(context, R.drawable.ic_portrait))
                .setFailureImage(ContextCompat.getDrawable(context, R.drawable.ic_portrait))
                .setRoundingParams(RoundingParams.asCircle())
                        // .setProgressBarImage(new ProgressBarDrawable())
                .build();
        return options;
    }

    public void loadImage(SimpleDraweeView view, String uri) {
        loadImage(view, uri, 0, 0, mLoadingListener);
    }

    @Deprecated
    public void loadImage(SimpleDraweeView view, String uri, int width, int height) {
        loadImage(view, uri, width, height, mLoadingListener);
    }

    public void loadImage(SimpleDraweeView view, String uri, int width, int height, ControllerListener listener) {
        view.setHierarchy(getDefaultOptions(sCtx));

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                // .setResizeOptions(new ResizeOptions(view.getLayoutParams().width, view.getLayoutParams().height))
                .setProgressiveRenderingEnabled(true)
                .build();

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(view.getController())
                .setControllerListener(listener)
                .setAutoPlayAnimations(false)
                .build();

        view.setController(draweeController);
    }

    public void shutDown() {
        Fresco.shutDown();
    }

    private ControllerListener mLoadingListener = new ControllerListener() {
        @Override
        public void onFailure(String s, Throwable throwable) {
            LogUtils.i(TAG, "onFailure s = " + s + " ; throwable = " + throwable.toString());
        }

        @Override
        public void onSubmit(String s, Object o) {
            LogUtils.i(TAG, "onSubmit s = " + s);
        }

        @Override
        public void onFinalImageSet(String s, Object o, Animatable animatable) {
            LogUtils.i(TAG, "onFinalImageSet s = " + s);
        }

        @Override
        public void onIntermediateImageSet(String s, Object o) {
            LogUtils.i(TAG, "onIntermediateImageSet s = " + s);
        }

        @Override
        public void onIntermediateImageFailed(String s, Throwable throwable) {
            LogUtils.i(TAG, "onIntermediateImageFailed s = " + s + " ; throwable = " + throwable.toString());
        }

        @Override
        public void onRelease(String s) {
            LogUtils.i(TAG, "onRelease s = " + s);
        }
    };
}
