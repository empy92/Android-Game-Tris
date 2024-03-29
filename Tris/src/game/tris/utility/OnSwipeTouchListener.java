package game.tris.utility;

import game.tris.activity.SettingsActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class OnSwipeTouchListener implements OnTouchListener{
	
	final String BACKGROUND = "backgroundKey";
    private final GestureDetector gestureDetector;
    private Context ctx;
    private ArcadeTextView arcade;
    private Background background;
    public OnSwipeTouchListener (Context ctx, ArcadeTextView arcade, Background background){
    	this.ctx = ctx;
    	this.arcade = arcade;
    	this.background = background;
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
        
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
        	if(background.getColortoString().compareTo(Background.UNKNOWN)==0)
        	Toast.makeText(ctx, background.getUnlockSegret(), 
					Toast.LENGTH_SHORT).show();
        	return super.onSingleTapConfirmed(e);
        }
    }

    public void onSwipeRight() {
    	((SettingsActivity) ctx).setAnimationRight(arcade);
    	background.changeRight();
    	((SettingsActivity) ctx).SavePreferences(BACKGROUND, background.getColor());
    }

    public void onSwipeLeft() {
    	((SettingsActivity) ctx).setAnimationLeft(arcade);
    	background.changeLeft();
    	((SettingsActivity) ctx).SavePreferences(BACKGROUND, background.getColor());
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
	    return gestureDetector.onTouchEvent(event);
	}
}