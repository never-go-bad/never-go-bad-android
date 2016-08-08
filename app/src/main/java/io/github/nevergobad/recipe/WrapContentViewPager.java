package io.github.nevergobad.recipe;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aoriani on 8/7/16.
 */


// Stock view pager does not handle `wrap_content`=> http://stackoverflow.com/q/8394681/1525801
// So we override `onMeasure` to use the height of the highest child, which is the result that we
// want.
// We shall make sure that all children is instantiated by set convenient value
// to offscreen limits
public class WrapContentViewPager extends ViewPager {
    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int childCount = getChildCount();
        int containerHeight = 0;
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            containerHeight = Math.max(containerHeight, child.getMeasuredHeight());
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(containerHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
