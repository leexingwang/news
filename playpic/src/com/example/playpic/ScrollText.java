package com.example.playpic;  
  
import java.util.ArrayList;  
import java.util.List;  
  
import android.content.Context;  
import android.graphics.Canvas;  
import android.graphics.Color;  
import android.graphics.Paint;  
import android.graphics.Paint.FontMetrics;  
import android.graphics.Rect;  
import android.graphics.Typeface;  
import android.util.AttributeSet;  
import android.util.DisplayMetrics;  
import android.view.SurfaceHolder;  
import android.view.SurfaceView;  
import android.view.WindowManager;  
  
public class ScrollText extends SurfaceView implements SurfaceHolder.Callback ,Runnable{  
  
    private int width,height;  
    private SurfaceHolder sfh;  
    private Thread th;  
    private boolean flag;  
    private Paint backPaint,textPaint;  
    /**���ֿ�ʼ���ֵ�λ������*/  
    private float posx,posy;  
    /**ÿ���ı� ��ǰ�ƶ��ı������Լ��ı���ԭʼ�ƶ�λ��*/  
    private Float[] step,stepBack;  
    private String txtContent;  
    /**�洢ÿ���ı�ԭʼ������λ��*/  
    private Float[] tposy;  
    /**�洢ÿ���ı�����*/  
    private String[] texts;  
      
    public ScrollText(Context context) {  
        super(context);  
        initView();  
    }  
      
    public ScrollText(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        initView();  
    }  
  
    private void initView(){  
        sfh=this.getHolder();  
        sfh.addCallback(this);    
        this.setKeepScreenOn(true);  
        this.setFocusable(true);  
    //  this.setFocusableInTouchMode(true);  
        backPaint=new Paint();  
        backPaint.setColor(Color.BLACK);  
        textPaint=new Paint();  
          
        textPaint.setTextSize(30.0f);  
        textPaint.setColor(Color.BLUE);  
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);  
        textPaint.setTextAlign(Paint.Align.LEFT);  
        textPaint.setAntiAlias(true);  
    }  
      
    public void initDisplayMetrics(WindowManager windowManager){  
           /* ȡ����Ļ�ֱ��ʴ�С */  
        DisplayMetrics dm=new DisplayMetrics();  
        windowManager.getDefaultDisplay().getMetrics(dm);  
        this.width=dm.widthPixels;  
        this.height=dm.heightPixels;  
        this.posx=width/6;  
      /*  FontMetrics fm = textPaint.getFontMetrics();   
        float baseline = fm.descent - fm.ascent;*/  
        this.posy=height-100;  
          
    }  
      
    public void setTxtContent(String txt){  
        this.txtContent=txt;  
    }  
      
    @Override  
    public void surfaceCreated(SurfaceHolder holder) {  
        this.flag=true;  
          
        if(th==null||!th.isAlive()){  
            th=new Thread(this);  
            th.start();  
        }  
    }  
      
    @Override  
    public void surfaceChanged(SurfaceHolder holder, int format, int width,  
            int height) {  
          
    }  
  
    @Override  
    public void surfaceDestroyed(SurfaceHolder holder) {  
          
        this.flag=false;  
    }  
    /** 
     * �������л��Ʒ��� 
     */  
    private void drawAll(){  
        Canvas canvas=null;  
        try{  
            canvas=sfh.lockCanvas();  
            drawText(canvas, txtContent);  
              
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(canvas!=null){  
                sfh.unlockCanvasAndPost(canvas);  
            }  
        }  
    }  
      
    private void drawText(Canvas canvas,String text){  
        //clear screen  
        canvas.drawRect(new Rect(0,0,getWidth(),getHeight()), backPaint);  
          
        initDrawText(text);  
              
        int len=tposy.length;  
        // draw text content  
        for(int n=0;n<len;n++){  
            if(texts[n]==null)  
                return;  
            float ty=tposy[n]-step[n];  
              
            canvas.drawText(texts[n], posx, ty, textPaint);  
            step[n]+=5.0f;  
              
            if(n==len-1&&ty<0){  
                step=stepBack.clone();  
            }  
            /*if (ty<0){ Log.e("msgreset", "back step"); 
                step[n] = stepBack[n].floatValue(); 
            }*/  
        }  
        postInvalidate();  
    }  
    /** 
     * ��������Ϣ����ǰ���ı���Ϣ�������ַָ���ּ�࣬����λ�ü��㴦�� 
     * @param text 
     */  
    private void initDrawText(String text){  
        if(texts==null){  
            texts=getTexts(text);  
        }  
        if(tposy==null){  
            tposy=getTextLinePosy();  
        }  
        if(stepBack==null){  
            stepBack=new Float[tposy.length];  
            int i=0;  
            float interval=0.0f;  
            FontMetrics fm = textPaint.getFontMetrics();    
            float baseline = fm.descent - fm.ascent;  
            while(i<stepBack.length){  
                stepBack[i]=interval;  
                interval-=baseline;  
                i++;  
            }  
        }  
        if(step==null){  
            step=stepBack.clone();  
        }  
    }  
    /** 
     * ��ȡ�ָ����ı���Ϣ 
     * @param text 
     * @return 
     */  
    private String[] getTexts(String text){  
        List<String> totalList=new ArrayList<String>(10);  
        String[] str=text.split("\n");  
        int len=str.length;  
          
        for(int i=0;i<len;i++){  
            String[] ss=autoSplit(str[i], textPaint, getWidth()/3*2);  
            for(String s:ss){  
                totalList.add(s);  
            }  
        }  
        if(texts==null)  
            texts=(String[]) totalList.toArray(new String[0]);  
        /*if(texts==null) 
         texts = autoSplit(text, textPaint, getWidth()/3*2); */  
        return texts;  
    }  
    /** 
     * ��ȡÿ���ı�����������Ϣ 
     * @return 
     */  
    private Float[] getTextLinePosy(){  
         FontMetrics fm = textPaint.getFontMetrics();    
         float baseline = fm.descent - fm.ascent;     
         float y =  posy+baseline;  //����ϵͳ��������ĵײ��������ı���������Ҫ��������ĸ߶�       
                
         int len=texts.length;  
         Float[] groups=new Float[len];  
                 
            for(int i=0;i<len;i++) {     
                groups[i]=y;  
                y =y+ baseline + fm.leading; //��������м��    
            }    
            return groups;  
    }  
    /** 
     * �Զ��ָ��ı� 
     * @param content ��Ҫ�ָ���ı� 
     * @param p  ���ʣ�����������������ı��Ŀ�� 
     * @param width ���Ŀ���ʾ���أ�һ��Ϊ�ؼ��Ŀ�ȣ� 
     * @return һ���ַ������飬����ÿ�е��ı� 
     */  
    private String[] autoSplit(String content, Paint p, float width) {  
      
             
       /* String[] lineTexts = new String[1000];  
        int lenStr=0; 
        int lineNum=0,w=0,start=0,end=1,n=0; 
         
            lenStr=content.length(); 
            for(int j=0;j<lenStr;j++){ 
                char ch=content.charAt(j); 
                String str_ch=String.valueOf(ch); 
                float[] ch_w=new float[1]; 
                p.getTextWidths(str_ch, ch_w); 
                 
                if(str_ch=="\n"){ 
                    lineNum++; 
                //  start=j+1; 
                    end=j+1; 
                    w=0; 
                     
                    lineTexts[n++] = (String) content.subSequence(start, end); 
                    start=end; 
                }else{ 
                    w+=(int)(Math.ceil(ch_w[0])); 
                    if(w>width){ 
                        lineNum++; 
                //      start=j; 
                        end=j; 
                        j--; 
                        w=0; 
                         
                        lineTexts[n++] = (String) content.subSequence(start, end); 
                        start=end; 
                    }else{ 
                        if(j==(lenStr-1)){ 
                            lineNum++; 
                            lineTexts[n++] = (String) content.subSequence(start, end); 
                            break; 
                        } 
                    } 
                } 
            } 
            Log.e("msg", "lineNum= "+lineNum);*/  
          
         
        float textWidth = p.measureText(content);  
        if(textWidth <= width) {  
            return new String[]{content};  
        }  
          
        int length = content.length();  
        int start = 0, end = 1, i = 0;  
        int lines = (int) Math.ceil(textWidth / width); //��������  
        String[] lineTexts = new String[lines];  
          
        while(start < length) {  
            if(p.measureText(content, start, end) > width) { //�ı���ȳ����ؼ����ʱ  
                    lineTexts[i++] = content.substring(start, end);//(String) content.subSequence(start, end);  
                    start = end;  
                }  
            if(end == length) { //����һ�е��ı�  
                lineTexts[i] = content.substring(start, end);//(String) content.subSequence(start, end);  
                break;  
            }  
            end += 1;  
        }  
              
        return lineTexts;  
    }  
      
    @Override  
    public void run() {  
        while(flag){  
            drawAll();  
            try {  
                Thread.sleep(200);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
  
}  