package game.tris.utility;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static boolean isplayingAudio=false;
    
    
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
