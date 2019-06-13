package example.com.blockgame_net;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BreakoutGame extends Activity {

    // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    BreakoutView breakoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize gameView and set it as the view
        breakoutView = new BreakoutView(this);
        setContentView(breakoutView);

    }

    // Here is our implementation of GameView
    // It is an inner class.
    // Note how the final closing curly brace }
    // is inside SimpleGameEngine

    // Notice we implement runnable so we have
    // A thread and can override the run method.
    class BreakoutView extends SurfaceView implements Runnable {

        // This is our thread
        Thread gameThread = null;
        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        SurfaceHolder ourHolder;

        // A boolean which we will set and unset
        // when the game is running- or not.
        volatile boolean playing;

        // Game is paused at the start
        boolean paused = true;

        // A Canvas and a Paint object
        Canvas canvas;
        Paint paint;

        // This variable tracks the game frame rate
        long fps;

        // This is used to help calculate the fps
        private long timeThisFrame;
        // The size of the screen in pixels
        int screenX;
        int screenY;
        // A paddle
        Paddle paddle;
        // A ball
        Ball ball;
        ArrayList<Ball> balls;
        // Up to 200 bricks
        Brick[] bricks = new Brick[200];
        int numBricks = 0;
        // For sound FX
        SoundPool soundPool;
        int beep1ID = -1;
        int beep2ID = -1;
        int beep3ID = -1;
        int loseLifeID = -1;
        int explodeID = -1;
        // The score
        int score = 0;
        // Lives
        int lives = 3;
        // Item graph id array (0:nothing)
        int[] items = {0,R.drawable.muchball,R.drawable.theboom,R.drawable.smallpaddle,R.drawable.bigpaddle};
        Bitmap item_graph;
        Item item=null;
        ArrayList<Item> all_item;
        long startTime,playingTime;
        // When the we initialize (call new()) on gameView
        // This special constructor method runs
        public BreakoutView(Context context) {
            // The next line of code asks the
            // SurfaceView class to set up our object.
            // How kind.
            super(context);

            // Initialize ourHolder and paint objects
            ourHolder = getHolder();
            setFocusable(true);
            paint = new Paint();

            // Get a Display object to access screen details
            Display display = getWindowManager().getDefaultDisplay();
            // Load the resolution into a Point object
            Point size = new Point();
            display.getSize(size);
            screenX = size.x;
            screenY = size.y;
            paddle = new Paddle(screenX, screenY);
            // Create a ball
            ball = new Ball(screenX, screenY);
            balls=new ArrayList<Ball>();
            balls.add(ball);
            createBricksAndRestart();
            //init all_item
            all_item=new ArrayList<Item>();
            // Load the sounds

            // This SoundPool is deprecated but don't worry
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

            try{
                // Create objects of the 2 required classes
                AssetManager assetManager = context.getAssets();
                AssetFileDescriptor descriptor;

                // Load our fx in memory ready for use
                descriptor = assetManager.openFd("beep1.ogg");
                beep1ID = soundPool.load(descriptor, 0);

                descriptor = assetManager.openFd("beep2.ogg");
                beep2ID = soundPool.load(descriptor, 0);

                descriptor = assetManager.openFd("beep3.ogg");
                beep3ID = soundPool.load(descriptor, 0);

                descriptor = assetManager.openFd("loseLife.ogg");
                loseLifeID = soundPool.load(descriptor, 0);

                descriptor = assetManager.openFd("explode.ogg");
                explodeID = soundPool.load(descriptor, 0);

            }catch(IOException e){
                // Print an error message to the console
                Log.e("error", "failed to load sound files");
            }
        }

        @Override
        public void run() {

            while (playing) {

                // Capture the current time in milliseconds in startFrameTime
                long startFrameTime = System.currentTimeMillis();
                // Update the frame
                if(!paused){
                    update();
                }

                // Draw the frame
                draw();

                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                    playingTime=System.currentTimeMillis()/1000;
                    for(int i=0;i<(playingTime-startTime)/60;i++) {
                        fps /= 2;     //speed up *2 per 60 sec
                    }
                }

            }

        }

        // Everything that needs to be updated goes in here
        // Movement, collision detection etc.
        public void update() {
            paddle.update(fps);
            ball.update(fps);
            for(int i=0;i<all_item.size();i++)
                if(all_item.get(i).getState())
                    all_item.get(i).update(fps);
                else
                    all_item.remove(i);
            // Check for ball colliding with a brick
            for(int i = 0; i < numBricks; i++){

                if (bricks[i].getVisibility()){

                    if(RectF.intersects(bricks[i].getRect(),ball.getRect())) {
                        bricks[i].setInvisible();
                        ball.reverseYVelocity();
                        score = score + 10;
                        soundPool.play(explodeID, 1, 1, 0, 0, 1);
                        Random random = new Random();
                        int index=random.nextInt(5);
                        if(index!=0) {
                            item_graph = BitmapFactory.decodeResource(getResources(), items[index]);
                            item=new Item(bricks[i].getRect().right,bricks[i].getRect().bottom,item_graph,index,screenX);
                            all_item.add(item);
                        }
                    }
                }
            }
            // Check for ball colliding with paddle
            if(RectF.intersects(paddle.getRect(),ball.getRect())) {
                ball.setRandomXVelocity();
                ball.reverseYVelocity();
                ball.clearObstacleY(paddle.getRect().top - 2);
                soundPool.play(beep1ID, 1, 1, 0, 0, 1);
            }
            //Bounce the paddle when eat item
            for(int i=0;i<all_item.size();i++) {
                if (RectF.intersects(paddle.getRect(),all_item.get(i).getRect())) {
                    Log.e("cclo","paddle with item");
                    switch(all_item.get(i).type){
                        case 1:
                            balls.add(new Ball((int)ball.xVelocity,(int)ball.yVelocity));
                            paddle.changesize(130);
                            break;
                        case 2:
                            lives--;
                            break;
                        case 4:
                            paddle.changesize(100);
                            break;
                        case 5:
                            paddle.changesize(screenX);
                            break;

                    }
                    all_item.remove(i);
                }
            }

            // Bounce the ball back when it hits the bottom of screen
            // And deduct a life
            
            if(ball.getRect().bottom > screenY){
                ball.reverseYVelocity();
                ball.clearObstacleY(screenY - 2);

                // Lose a life
                lives --;
                soundPool.play(loseLifeID, 1, 1, 0, 0, 1);
                paused = true;
                if(lives == 0){

                    createBricksAndRestart();
                }
                paddle.reset(screenX);
                ball.reset(screenX, screenY);
            }

            // Bounce the ball back when it hits the top of screen
            if(ball.getRect().top < 0){
                ball.reverseYVelocity();
                ball.clearObstacleY(12);
                soundPool.play(beep2ID, 1, 1, 0, 0, 1);
            }
            // If the ball hits left wall bounce
            if(ball.getRect().left < 0){
                ball.reverseXVelocity();
                ball.clearObstacleX(2);
                soundPool.play(beep3ID, 1, 1, 0, 0, 1);
            }
            // If the ball hits right wall bounce
            if(ball.getRect().right > screenX - ball.ballWidth){
                ball.reverseXVelocity();
                ball.clearObstacleX(screenX - 22);
                soundPool.play(beep3ID, 1, 1, 0, 0, 1);
            }
            // Pause if cleared screen
            if(score == numBricks * 10){
                paused = true;
                createBricksAndRestart();
            }
            // Item fall
            for(int i=0;i<all_item.size();i++) {
                if (all_item.get(i).bottom > screenY) {
                    all_item.get(i).changeState(false);
                }
            }
        }

        public void createBricksAndRestart(){
            // Put the ball back to the start
            ball.reset(screenX, screenY);
            all_item=new ArrayList<Item>();
//            paddle.changesize(130);
            int brickWidth = screenX / 8;
            int brickHeight = screenY / 20;
            // Build a wall of bricks
            numBricks = 0;
            for(int column = 0; column < 8; column ++ ){
                for(int row = 0; row < 5; row ++ ){
                    bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                    numBricks ++;
                }
            }
            // Reset scores and lives
            score = 0;
            lives = 3;
            startTime=System.currentTimeMillis()/1000;
        }

        // Draw the newly updated scene
        public void draw() {

            // Make sure our drawing surface is valid or we crash
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();

                // Draw the background color
                canvas.drawColor(Color.argb(255,  26, 128, 182));

                // Choose the brush color for drawing
                paint.setColor(Color.argb(255,  255, 255, 255));

                // Draw the paddle
                canvas.drawRect(paddle.getRect(), paint);
                // Draw the ball
                canvas.drawRect(ball.getRect(), paint);
                paint.setColor(Color.argb(255,150,200,100));
                canvas.drawLine(ball.getRect().right+10,ball.getRect().top,ball.getRect().right+10,ball.getRect().bottom,paint);
                canvas.drawLine(ball.getRect().left,ball.getRect().bottom,ball.getRect().right,ball.getRect().bottom,paint);
                // Draw the bricks
                // Change the brush color for drawing
                paint.setColor(Color.argb(255,  200, 129, 200));
                // Draw the bricks if visible
                for(int i = 0; i < numBricks; i++){
                    if(bricks[i].getVisibility()) {
                        canvas.drawRect(bricks[i].getRect(), paint);
                    }
                }
                //draw item
                for(int i=0;i<all_item.size();i++) {
                    if (all_item.get(i).getState()) {
                        canvas.drawBitmap(all_item.get(i).item, all_item.get(i).x-(screenX/16), all_item.get(i).y, new Paint());
                        //debug item left,right,top,bottom
                        //canvas.drawLine(all_item.get(i).left,all_item.get(i).bottom,all_item.get(i).right,all_item.get(i).bottom,paint);
                    }else{
                        all_item.remove(i);
                    }
                }
                // Draw the HUD
                // Choose the brush color for drawing
                paint.setColor(Color.argb(255,  255, 255, 255));

                // Draw the score
                paint.setTextSize(40);
                canvas.drawText("Score: " + score + "   Live: " + lives, 10,50, paint);

                // Has the player cleared the screen?
                if(score == numBricks * 10){
                    paint.setTextSize(90);
                    paint.setColor(Color.rgb(0,0,0));
                    canvas.drawText("YOU HAVE WON!", 10,screenY/2, paint);
                    paused=true;
                }

                // Has the player lost?
                if(lives <= 0) {
                    paint.setTextSize(90);
                    paint.setColor(Color.rgb(0, 0, 0));
                    canvas.drawText("YOU HAVE LOST!", 10, screenY / 2, paint);
                }


                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas);
            }

        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        // If SimpleGameEngine Activity is started theb
        // start our thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                // Player has touched the screen
                case MotionEvent.ACTION_DOWN:
                    paused = false;

                    if(motionEvent.getX() > screenX / 2){
                        paddle.setMovementState(paddle.RIGHT);
                    }
                    else{
                        paddle.setMovementState(paddle.LEFT);
                    }
                    break;

                // Player has removed finger from screen
                case MotionEvent.ACTION_UP:
                    paddle.setMovementState(paddle.STOPPED);
                    break;
            }
            return true;
        }

    }
    // This is the end of our BreakoutView inner class

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        breakoutView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        breakoutView.pause();
    }

}
