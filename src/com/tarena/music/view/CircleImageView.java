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
 * Բ��ͷ��ؼ��Ķ���
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

	/** AttributeSet ��Ӧ�˲����ļ��и�����ǩ�ļ�ֵ�� */
	public CircleImageView(Context context,
			AttributeSet atts, int syte) {
		super(context, atts, syte);
		/** ��ʼ������ */
		init();

	}

	private void init() {
		mPaintfdf = new PaintFlagsDrawFilter
				(0, Paint.ANTI_ALIAS_FLAG | // ͼ����
				Paint.FILTER_BITMAP_FLAG);// ֧��λͼ����
		// ��������·������
		// Paint����
		paint = new Paint();
		// ���ʵĿ����
		paint.setAntiAlias(true);
		// ����ͼƬ
		paint.setFilterBitmap(true);
		// ���û��ʵ���ɫ
		paint.setColor(Color.BLACK);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/** ��дonDraw���� */
	@Override
	protected void onDraw(Canvas canvas) {
		// 1 ��ÿؼ��Ŀ�Ⱥ͸߶�
		// �߶�
		float h = getMeasuredHeight() - 3.0f;
		// ���
		float w = getMeasuredWidth() - 3.0f;
		// 2 �������Բ��·�� Path
		if(path==null){
	     path=new Path();
	     path.addCircle(w/2.0f, // Բ��Բ������x��
	    		 h/2.0f, //Բ������y��
	    		 Math.min(w/2.0f, h/2.0f),//�뾶
	    		 Path.Direction.CCW);//����ʱ��·������ ccw ��ʱ�뷽�� cw ˳ʱ�뷽��
		 path.close();
		}
		// 3 ʹ��canvas��ʼ����Բ��
		canvas.drawCircle(w/2.0f,//Բ��Բ������x��
				h/2.0f, //Բ������y��
				Math.min(w/2.0f, h/2.0f), //�뾶
				paint);//����
		// 4 ���canvas�еĻ��ƶ������
		// 6 ����canvas��ë�߹���
		int saveCount=canvas.getSaveCount();
		canvas.save();//�ύ�ո����ɵ�ͼ��
		canvas.setDrawFilter(mPaintfdf);
		// 5 ȡ������λ��
		//���ù���Ч��
		//ȡ����
		canvas.clipPath(path,//����·��ͼ��
		android.graphics.Region.Op.REPLACE);//ȡ������λ��
		// 7 ���û������ɫΪ��ɫ
		canvas.setDrawFilter(mPaintfdf);
//		canvas.drawColor(Color.RED);
//		canvas.drawBitmap(mBitmap,0,0, paint);
		super.onDraw(canvas);
		// 8 ��ԭ����canvas  ��super֮�����
		canvas.restoreToCount(saveCount);
	}
	
//	private Bitmap mBitmap;
//	@Override
//	public void setImageDrawable(Drawable drawable) {
//          mBitmap=((BitmapDrawable)drawable).getBitmap();
//		super.setImageDrawable(drawable);
//	}
	
	
	
	
	
	

}
