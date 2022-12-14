package com.example.dibujocanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dibujos D = new Dibujos(this);
        setContentView(D);
    }

    class Dibujos extends View {

        float x = 0, y = 0, x1 = 0, y1 = 0;
        Path path = new Path();
        Path path1 = new Path();
        Path path2 = new Path();
        String accion = "";
        public  Dibujos(Context context){
            super(context);
        }
        protected void onDraw(Canvas canvas){
            canvas.drawColor(getResources().getColor(R.color.c1));
            Paint pintura = new Paint();
            pintura.setColor(getResources().getColor(R.color.c3));
            pintura.setTextSize(30);
            pintura.setAntiAlias(true);
            canvas.save();
            canvas.drawText("Txt1 (100,100)",100,100,pintura);
            canvas.translate(300,200);//desplazar origen
            canvas.drawText("Txt2 (1,1) translate(300,200)",1,1,pintura);
            canvas.restore();
            canvas.save();
            /*canvas.translate(100,100);
            canvas.drawText("Telemática 3",100,1000,pintura);
            canvas.restore();
            /*canvas.translate(1000,1000);
            canvas.rotate(45);
            canvas.drawText("Textote",0,0,pintura);*/

            Rect bounds = new Rect();
            String txt = "TOP SECRET";
            pintura.setColor(Color.RED);
            pintura.setTextSize(55);
            pintura.getTextBounds(txt,0,txt.length(),bounds);
            float cX = bounds.exactCenterX();
            float cY = bounds.exactCenterY();
            canvas.translate(5,1000);
            pintura.setStyle(Paint.Style.STROKE);
            pintura.setStrokeWidth(5);
            canvas.rotate(45,cX,cY);
            canvas.drawRect(bounds,pintura);
            pintura.setColor(Color.RED);
            canvas.drawText(txt, 0,0,pintura);
            canvas.restore();

            //Trazar líneas sobre una trayectoria
            pintura.setColor(Color.BLACK);
            pintura.setStyle(Paint.Style.STROKE);
            path.moveTo(x,y);//punto inicial
            path.lineTo(200, 300);
            canvas.drawPath(path, pintura);
            path.lineTo(80, 300);
            canvas.drawPath(path, pintura);
            path.lineTo(0, 0);
            canvas.drawPath(path, pintura);

            path2.moveTo(718, 0);//punto inicial
            path2.lineTo(718, 150);
            canvas.drawPath(path2, pintura);
            path2.lineTo(568, 150);
            canvas.drawPath(path2, pintura);
            path2.lineTo(568, 0);
            canvas.drawPath(path2, pintura);

            if(accion=="down"){
                path1.moveTo(x1,y1);
            }
            if(accion=="move"){
                path1.lineTo(x1,y1);
            }
            canvas.drawPath(path1, pintura);
        }

        public boolean onTouchEvent (MotionEvent evento){
            x1 = evento.getX();
            y1 = evento.getY();
            if(evento.getAction() == MotionEvent.ACTION_DOWN){
                accion = "down";
            }
            if(evento.getAction() == MotionEvent.ACTION_MOVE){
                accion = "move";
            }
            invalidate();
            return true;
        }
    }
}