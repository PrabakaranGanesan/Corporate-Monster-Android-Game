package com.prabakaran_g.corporatemonster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class Corporateview extends View
{
private Bitmap corporatemonster[] = new Bitmap[2];

    private int corporateX = 10;
    private int corporateY;
    private int corporateSpeed;

    private int canvasWidth, canvasHeight;


    private int yellowX,yellowY,yellowSpeed = 25;
    private Paint yellowPaint = new Paint();

    private int greenX,greenY,greenSpeed = 16;
    private Paint greenPaint = new Paint();


    private int redX,redY,redSpeed = 28;
    private Paint redPaint = new Paint();


    private int score , lifeCounterofVijay;

    private boolean touch = false;




private Bitmap backgroundImage;

private Paint scorePaint = new Paint();

private Bitmap life[] = new Bitmap[2];

    public Corporateview(Context context) {
        super(context);

        corporatemonster[0]= BitmapFactory.decodeResource(getResources(),R.drawable.logo);

        corporatemonster[1]= BitmapFactory.decodeResource(getResources(),R.drawable.logo1);


        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);

        yellowPaint.setColor(Color.BLUE);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);




        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);



        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        corporateY = 550;
        score = 0;
        lifeCounterofVijay = 5;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();




        canvas.drawBitmap(backgroundImage,0,0,null);

        int minY = corporatemonster[0].getHeight();
        int maxY = canvasHeight - corporatemonster[0].getHeight() * 3;
        corporateY =corporateY + corporateSpeed;

        if(corporateY < minY)
        {
            corporateY = minY;
        }

        if(corporateY > maxY)
        {
            corporateY = maxY;



        }

        corporateSpeed = corporateSpeed + 2;


        if(touch)
        {

            canvas.drawBitmap(corporatemonster[1], corporateX,corporateY,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(corporatemonster[0],corporateX,corporateY,null);

        }






        yellowX =yellowX - yellowSpeed;
        if(hitBallChecker(yellowX,yellowY))
        {
            score = score + 50;
            yellowX = -100;

        }


        if(yellowX < 0)
        {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxY - minY)) +  minY;
        }
        canvas.drawCircle(yellowX,yellowY,15,yellowPaint);




        greenX =greenX - greenSpeed;
        if(hitBallChecker(greenX,greenY))
        {
            score = score + 10;
            greenX = -100;

        }


        if(greenX < 0)
        {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxY - minY)) +  minY;
        }
        canvas.drawCircle(greenX,greenY,25,greenPaint);




        redX =redX - redSpeed;
        if(hitBallChecker(redX,redY))
        {

            redX = -100;
            lifeCounterofVijay--;

            if(lifeCounterofVijay == 0)
            {
                Toast.makeText(getContext(), "Game over", Toast.LENGTH_SHORT).show();

                Intent gameoverintent = new Intent(getContext(),GameOverActivity.class);
                gameoverintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverintent.putExtra("score",score);

                getContext().startActivity(gameoverintent);


            }

        }


        if(redX < 0)
        {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxY - minY)) +  minY;
        }
        canvas.drawCircle(redX,redY,45,redPaint);


        canvas.drawText("Score : " + score, 20,60,scorePaint);



        for(int i=0; i<5; i++)
        {
            int x = (int) (580 + life[0].getWidth() * 1.0  * i);
            int y =30;

            if(i < lifeCounterofVijay)
            {
                canvas.drawBitmap(life[0],x,y,null);

            }
            else
            {
                canvas.drawBitmap(life[1],x,y,null);

            }


        }









    }

    public boolean hitBallChecker(int x,int y)
    {
        if(corporateX < x && x < (corporateX + corporatemonster[0].getWidth()) && corporateY < y && y < (corporateY + corporatemonster[0].getHeight()))
        {
            return true;

        }
        return false;



    }



    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;

            corporateSpeed = -28;

        }
        return true;
    }
}
