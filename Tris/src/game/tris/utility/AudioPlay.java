package game.tris.utility;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {
	
	  	private static MediaPlayer mediaPlayer;
	   	private static MediaPlayer mediaPlayerBackground;
	   	private static boolean isplayingAudio=false;
	    
	    
	    //##################################### ANOTHER MEDIA PLAYER FOR THE BACKGROUND #################################
	    
	    // play sound with looping
	    public static void playAudioBackground(Context c,int id){
	         mediaPlayerBackground = MediaPlayer.create(c,id);
	         mediaPlayerBackground.setLooping(true);
	         
	         if(!mediaPlayerBackground.isPlaying()){
	        	 mediaPlayerBackground.start();             
	         }          
	     }
	    
	    public static void stopAudioBackground(){          
	        mediaPlayerBackground.stop();
	    }
	    
	    //###############################################################################################################
    // play sound with looping
    public static void playAudio(Context c,int id){
         mediaPlayer = MediaPlayer.create(c,id);
         mediaPlayer.setLooping(true);
         
         if(!mediaPlayer.isPlaying()){
        	 isplayingAudio=true;
        	 mediaPlayer.start();             
         }          
     }
 
    // play sound without loop
    public static void playAudioNoLoop(Context c,int id){
        mediaPlayer = MediaPlayer.create(c,id);
        
        if(!mediaPlayer.isPlaying()){
       	 isplayingAudio=true;
       	 mediaPlayer.start();             
        }          
    }
    
    // stop sound
    public static void stopAudio(){     
         isplayingAudio=false;       
         mediaPlayer.stop();
    }  
    
    // reset sound
    public static void resetAudio(){         
         mediaPlayer.reset();
    }  
    
    // release audio
    public static void releaseAudio(){     
        isplayingAudio=false;       
        mediaPlayer.release();
   }
    
    // check if music play or not
    public static boolean isPlayingAudio(){     
        return isplayingAudio;       
   }   
}
