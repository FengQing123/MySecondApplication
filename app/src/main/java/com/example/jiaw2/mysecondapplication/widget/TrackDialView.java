package com.example.jiaw2.mysecondapplication.widget;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.jiaw2.mysecondapplication.R;

/**
 * Created by jiaw2 on 2016/12/7.
 */
public class TrackDialView extends View {

    private Context context;
    private Paint paint;
    private Paint paint_2;
    private Paint paint_3;
    private int maxNum;
    private int startAngle;
    private int sweepAngle;
    private int sweepWidth;//圆的宽度

    //view的宽高
    private int mWidth;
    private int mHeight;

    //圆的半径
    private int radius;

    //当前的值
    private int currentNum = 600;
    private int baseNum = 525;
    private String content = "驾驶指数";

    private String[] text = {"极差", "中等", "良好", "优秀", "极好"};
    private String[] text_num = {"550", "600", "650", "700"};
    private int[] indicatorColor = {0xffffffff, 0x00ffffff, 0x99ffffff, 0xffffffff};


    public TrackDialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setBackgroundColor(0xFFFF6347);
        initAttr(attrs);
        initPaint();
    }

    public TrackDialView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrackDialView(Context context) {
        this(context, null);
    }

    public int getCurrentNum() {
        return currentNum;
    }

    private void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
        invalidate();
    }

    private void setContent(String content) {
        this.content = content;
    }

    public void setCurrentNumNameAnim(final int num, String content) {
        float duration = (float) Math.abs(num - currentNum) / maxNum * 1500 + 725; //根据进度差计算动画时间
        ObjectAnimator anim = ObjectAnimator.ofInt(this, "currentNum", num);
        anim.setDuration((long) Math.min(duration, 2000));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                int color = calculateColor(value);
                int colors = getColor(num);
                setBackgroundColor(colors);
            }
        });
        anim.start();
        setCurrentNum(num);
        setContent(content);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundDialView);
        maxNum = array.getInt(R.styleable.RoundDialView_maxNum, 725);
        startAngle = array.getInt(R.styleable.RoundDialView_startAngle, 180);
        sweepAngle = array.getInt(R.styleable.RoundDialView_sweepAngle, 180);
        //内外圆的宽度
        sweepWidth = dp2px(8);
        array.recycle();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xffffffff);
        paint_2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_2.setDither(true);
        paint_2.setStyle(Paint.Style.STROKE);
        paint_2.setColor(0xffffffff);
        paint_3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        if (wMode == MeasureSpec.EXACTLY) {
            mWidth = wSize;
        } else {
            mWidth = dp2px(300);
        }
        if (hMode == MeasureSpec.EXACTLY) {
            mHeight = hSize;
        } else {
            mHeight = dp2px(400);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radius = getMeasuredWidth() / 4; //不要在构造方法里初始化，那时还没测量宽高，view宽度的1/4
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);//将原点移动到view的中心
//        drawRound(canvas);  //画圆
        drawScale(canvas);//画刻度
        drawIndicator(canvas);//画当前进度值
        drawCenterText(canvas);//画中间的文字
        canvas.restore();
    }

    private void drawRound(Canvas canvas) {
        canvas.save();
        //圆
        paint.setAlpha(0x40);//设置透明度
        paint.setStrokeWidth(sweepWidth);//设置笔的宽度
        RectF rectf = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rectf, startAngle, sweepAngle, false, paint);
        canvas.restore();
    }

    private void drawScale(Canvas canvas) {
        canvas.save();
        float angle = (float) sweepAngle / 40;//刻度间隔
        canvas.rotate(-270 + startAngle); //将起始刻度点旋转到正上方（270)

        for (int i = 0; i <= 40; i++) {
            if (i % 10 == 0) {
                paint.setStrokeWidth(dp2px(1));
                paint.setAlpha(0x70);
                canvas.drawLine(0, -radius - sweepWidth / 2, 0, -radius + sweepWidth / 2, paint);
                drawText(canvas, text[i / 10], paint);
            } else {
                paint.setStrokeWidth(dp2px(1));
                paint.setAlpha(0x70);
                canvas.drawLine(0, -radius - sweepWidth / 2, 0, -radius + sweepWidth / 2, paint);
            }

            if (i == 5 || i == 15 || i == 25 || i == 35) {
                drawText(canvas, text_num[(i - 5) / 10], paint);
            }
            canvas.rotate(angle);
        }
        canvas.restore();
    }

    private void drawIndicator(Canvas canvas) {
        canvas.save();
        canvas.rotate(-270 + startAngle); //将起始刻度点旋转到正上方（270)
        int sweep;
        if (currentNum <= baseNum) {
            sweep = 0;
        } else if (currentNum <= maxNum && currentNum > baseNum) {
            sweep = (int) ((float) (currentNum - baseNum) / (float) (maxNum - baseNum) * sweepAngle);
        } else {
            sweep = sweepAngle;
        }
        float angle = (float) sweepAngle / 40;
        for (int i = 0; i <= Math.round(40 * (float) sweep / sweepAngle); i++) {
            paint_2.setStrokeWidth(dp2px(2));
            paint_2.setAlpha(0x70);
            canvas.drawLine(0, -radius - sweepWidth / 2, 0, -radius + sweepWidth / 2, paint_2);
            canvas.rotate(angle);
        }
        canvas.restore();
    }

    private void drawCenterText(Canvas canvas) {
        canvas.save();

        paint_3.setStyle(Paint.Style.FILL);
        paint_3.setTextSize(radius / 2);
        paint_3.setColor(0xffffffff);
        canvas.drawText(currentNum + "", -paint_3.measureText(currentNum + "") / 2, 0, paint_3);

        paint_3.setTextSize(radius / 8);
        Rect r = new Rect();
        paint_3.getTextBounds(content, 0, content.length(), r);
        canvas.drawText(content, -r.width() / 2, -radius / 2, paint_3);
        canvas.restore();
    }

    private void drawText(Canvas canvas, String text, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(sp2px(10));
        float width = paint.measureText(text); //相比getTextBounds来说，这个方法获得的类型是float，更精确些
//        Rect rect = new Rect();
//        paint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text, -width / 2, -radius + dp2px(15), paint);
        paint.setStyle(Paint.Style.STROKE);
    }

    private int calculateColor(int value) {
        ArgbEvaluator evealuator = new ArgbEvaluator();
        float fraction = 0;
        int color = 0;
        if (value <= maxNum / 2) {
            fraction = (float) value / (maxNum / 2);
            color = (int) evealuator.evaluate(fraction, 0xFFFF6347, 0xFFFF8C00); //由红到橙
        } else {
            fraction = ((float) value - maxNum / 2) / (maxNum / 2);
            color = (int) evealuator.evaluate(fraction, 0xFFFF8C00, 0xFF00CED1); //由橙到蓝
        }
        return color;
    }

    private int getColor(int num) {
        int color = 0;
        switch (num) {
            case 550:
                color = Color.parseColor("#e65452");
                break;
            case 600:
                color = Color.parseColor("#e26b3e");
                break;
            case 650:
                color = Color.parseColor("#3fa8d6");
                break;
            case 700:
                color = Color.parseColor("#3ac390");
                break;
        }
        return color;
    }


    //一些工具方法
    protected int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    protected int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
