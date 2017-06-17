package a.b;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class PlayAc extends Activity implements MediaPlayer.OnCompletionListener{
	
	Handler mPercentHandler = new Handler();
	private SeekBar mySeekBar=null;
	private TextView curProgressText=null;
	private TextView curtimeAndTotaltime=null;
	private ImageButton play,pause,stop;
	private MediaPlayer mp;
	String str;
	Intent i;
	Button btn;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initSeekBar();
        i=getIntent();
        str=i.getStringExtra("nu");
        play = (ImageButton)findViewById(R.id.play);
        pause = (ImageButton)findViewById(R.id.pause);
        stop = (ImageButton)findViewById(R.id.stop);
        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				Intent intent=new Intent();
				intent.setClass(PlayAc.this, GeQu.class);
			    startActivity(intent);			   
			    finish();
			    
			}
		});
        play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				play();
			}
		});
    
	    pause.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				pause();
			}
		});
	
		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				stop();
			}
		});
		setup();
    }
    public void onCompletion(MediaPlayer mp) {
		stop();
	}

	private void loadClip() {

			
		try {
			
			if(str.equals("0")){
		
				mp=MediaPlayer.create(this, R.raw.aiqingsuiyue);
				mp.setOnCompletionListener(this);
				}
				else if(str.equals("1")){

					mp=MediaPlayer.create(this, R.raw.keguanbukeyi);
					mp.setOnCompletionListener(this);
					}
				else if(str.equals("2")){

					mp=MediaPlayer.create(this, R.raw.shangbuqi);
					mp.setOnCompletionListener(this);
					}


		}
		catch (Throwable t) {
			error(t);
		}
	}
	
    protected Object getFileTime(int currentPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	private void setup() {
		loadClip();
		play.setEnabled(true);
		pause.setEnabled(false);
		stop.setEnabled(false);
	}
    private void play() {
		mp.start();
		startSeekBarUpdate();
		play.setEnabled(false);
		pause.setEnabled(true);
		stop.setEnabled(true);
	}
	
	private void stop() {
		mp.stop();
		pause.setEnabled(false);
		stop.setEnabled(false);
		
		try {
			mp.prepare();
			mp.seekTo(0);
			play.setEnabled(true);
		}
		catch (Throwable t) {
			error(t);
		}
	}
	
	private void pause() {
		mp.pause();
		play.setEnabled(true);
		pause.setEnabled(false);
		stop.setEnabled(true);
	} 
	private void error(Throwable t) {
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder
			.setTitle("Title")
			.setMessage(t.toString())
			.setPositiveButton("OK", null)
			.show();
	}
	
	public void initSeekBar()  
    {  
        mySeekBar=(SeekBar)findViewById(R.id.seekBar1);  
        curProgressText=(TextView)findViewById(R.id.currentProgress);  
        curtimeAndTotaltime=(TextView)findViewById(R.id.curtimeandtotaltime);  
          
        mySeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {  
  
            public void onProgressChanged(SeekBar seekBar, int progress,  
                    boolean fromUser) {  
 
             
            }  
  
            public void onStartTrackingTouch(SeekBar arg0) {  

            }  
  
            public void onStopTrackingTouch(SeekBar arg0) {  
                int dest = mySeekBar.getProgress();      
                int mMax = mp.getDuration();  
                int sMax = mySeekBar.getMax();  
  
                mp.seekTo(mMax*dest/sMax);  
  
            }  
        });  
     }
	public void startSeekBarUpdate() {  
        mPercentHandler.post(start);  
    }  
  
    Runnable start = new Runnable() {  
  
        public void run() {  
 
            mPercentHandler.post(updatesb);  
        }  
  
    };  
  
    Runnable updatesb =new Runnable(){  
  
    public void run() {  
            int position = mp.getCurrentPosition();  
            int mMax = mp.getDuration();  
            int sMax = mySeekBar.getMax();  
            mySeekBar.setProgress(position * sMax / mMax); 
            curProgressText.setText("第 " + position / 1000 + "秒"
                    );
            curtimeAndTotaltime.setText(  
                   "共 " + mMax / 1000 + "秒");

            mPercentHandler.postDelayed(updatesb, 1000);  
    }  
  
    };  

}