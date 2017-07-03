package com.tarena.music.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆形头像控件的定义
 * 
 */
public class CircleImageView extends ImageView {

	private Path path;
	private Paint paint;
	private PaintFlagsDrawFilter mPaintfdf;
	

	public CircleImageView(Context context) {
		super(context);
		init();
	}

	/** AttributeSet 对应了布局文件中各个标签的键值对 */
	public CircleImageView(Context context,
			AttributeSet atts, int syte) {
		super(context, atts, syte);
		/** 初始化动作 */
		init();

	}

	private void init() {
		mPaintfdf = new PaintFlagsDrawFilter
				(0, Paint.ANTI_ALIAS_FLAG | // 图像保真
				Paint.FILTER_BITMAP_FLAG);// 支持位图过滤
		// 创建绘制路径对象
		// Paint画笔
		paint = new Paint();
		// 画笔的抗锯齿
		paint.setAntiAlias(true);
		// 过滤图片
		paint.setFilterBitmap(true);
		// 设置画笔的颜色
		paint.setColor(Color.BLACK);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/** 重写onDraw方法 */
	@Override
	protected void onDraw(Canvas canvas) {
		// 1 获得控件的宽度和高度
		// 高度
		float h = getMeasuredHeight() - 3.0f;
		// 宽度
		float w = getMeasuredWidth() - 3.0f;
		// 2 计算绘制圆的路径 Path
		if(path==null){
	     path=new Path();
	     path.addCircle(w/2.0f, // 圆形圆心坐标x轴
	    		 h/2.0f, //圆形坐标y周
	    		 Math.min(w/2.0f, h/2.0f),//半径
	    		 Path.Direction.CCW);//绘制时的路径方向 ccw 逆时针方向 cw 顺时针方向
		 path.close();
		}
		// 3 使用canvas开始绘制圆形
		canvas.drawCircle(w/2.0f,//圆形圆心坐标x轴
				h/2.0f, //圆形坐标y周
				Math.min(w/2.0f, h/2.0f), //半径
				paint);//画笔
		// 4 获得canvas中的绘制对象个数
		// 6 设置canvas的毛边过滤
		int saveCount=canvas.getSaveCount();
		canvas.save();//提交刚刚生成的图案
		canvas.setDrawFilter(mPaintfdf);
		// 5 取出交集位置
		//设置过滤效果
		//取交集
		canvas.clipPath(path,//绘制路径图案
		android.graphics.Region.Op.REPLACE);//取出交集位置
		// 7 设置画板的颜色为白色
		canvas.setDrawFilter(mPaintfdf);
//		canvas.drawColor(Color.RED);
//		canvas.drawBitmap(mBitmap,0,0, paint);
		super.onDraw(canvas);
		// 8 还原画板canvas  在super之后操作
		canvas.restoreToCount(saveCount);
	}
	
//	private Bitmap mBitmap;
//	@Override
//	public void setImageDrawable(Drawable drawable) {
//          mBitmap=((BitmapDrawable)drawable).getBitmap();
//		super.setImageDrawable(drawable);
//	}
	
	
	
	
	
	

}
