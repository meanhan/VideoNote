package com.xuhan.videonote.movielistsubject;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;
import com.xuhan.videonote.entity.MovieEntity;

/**
 * @author xuhan
 */

public class MovieSubjectView extends LinearLayout {

    private Paint gradientPaint;
    private int[] currentGradient;

    private TextView movieName;
    private TextView movieRating;
    private ImageView movieImage;

    private ArgbEvaluator evaluator;

    public MovieSubjectView(Context context) {
        super(context);
    }

    public MovieSubjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieSubjectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MovieSubjectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        evaluator = new ArgbEvaluator();

        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        inflate(getContext(), R.layout.view_movie_subject, this);

        movieName = (TextView) findViewById(R.id.subject_name);
        movieImage = (ImageView) findViewById(R.id.subject_image);
        movieRating = (TextView) findViewById(R.id.subject_rating);
    }

    private void initGradient() {
        float centerX = getWidth() * 0.5f;
        Shader gradient = new LinearGradient(
                centerX, 0, centerX, getHeight(),
                currentGradient, null,
                Shader.TileMode.MIRROR);
        gradientPaint.setShader(gradient);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (currentGradient != null) {
            initGradient();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), gradientPaint);
        super.onDraw(canvas);
    }

    public void setMovie(MovieEntity.SubjectsEntity subject) {
        currentGradient = colors(R.array.gradient);
        if (getWidth() != 0 && getHeight() != 0) {
            initGradient();
        }
        movieName.setText(subject.getOriginal_title());
        movieRating.setText(getContext().getString(R.string.rating, subject.getRating().getAverage()));
        Glide.with(getContext()).load(subject.getImages().getLarge()).into(movieImage);
        invalidate();

        movieImage.animate()
                .scaleX(1f).scaleY(1f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(100)
                .start();
    }

    public void onScroll(float fraction, MovieEntity.SubjectsEntity oldF, MovieEntity.SubjectsEntity newF) {
        movieImage.setScaleX(fraction);
        movieImage.setScaleY(fraction);
        currentGradient = mix(fraction, colors(R.array.gradient), colors(R.array.gradient));
        initGradient();
        invalidate();
    }

    private int[] mix(float fraction, int[] c1, int[] c2) {
        return new int[]{
                (Integer) evaluator.evaluate(fraction, c1[0], c2[0]),
                (Integer) evaluator.evaluate(fraction, c1[1], c2[1]),
                (Integer) evaluator.evaluate(fraction, c1[2], c2[2])
        };
    }


    private int[] colors(@ArrayRes int res) {
        return getContext().getResources().getIntArray(res);
    }

}
