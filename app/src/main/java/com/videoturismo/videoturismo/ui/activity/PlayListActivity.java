package com.videoturismo.videoturismo.ui.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.videoturismo.videoturismo.R;
import com.videoturismo.videoturismo.adapter.MusicaAdapter;
import com.videoturismo.videoturismo.io.VideoTurismoAdapter;
import com.videoturismo.videoturismo.model.Musica;
import com.videoturismo.videoturismo.ui.decoration.DividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListActivity extends AppCompatActivity implements Callback<ArrayList<Musica>>, MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl,MediaPlayer.OnCompletionListener {

    private Call<ArrayList<Musica>> call;
    private MusicaAdapter mMusicaAdapter;
    private RecyclerView rv;
    private ImageButton ibPlay;
    private ImageButton ibNext;
    private ImageButton ibPre;
    int contadorDeCanciones = 0;
    static MediaPlayer mp;
    static MediaController mc;

    static int vpos = 0;

    private Musica mMusica;
    private ArrayList<String> musicaPath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        final TextView tituloMain = (TextView) findViewById(R.id.selected_track_title);

        mp = new MediaPlayer();
        mc = new MediaController(this){
            @Override
            public void hide(){

            }
        };
        ibNext = (ImageButton) findViewById(R.id.ib_next);
        ibPre = (ImageButton)findViewById(R.id.ib_pre);
        ibPlay = (ImageButton)findViewById(R.id.ib_ply);

//        ibPre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMusicaAdapter.play(0);
//                tituloMain.setText(mMusicaAdapter.getTitulo());
//            }
//        });
//        ibNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //if()
//
//                mMusicaAdapter.play(2);
//                tituloMain.setText(mMusicaAdapter.getTitulo());
//            }
//        });
//        ibPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mMusicaAdapter.play(rv.getChildAdapterPosition(v));
//                tituloMain.setText(mMusicaAdapter.getTitulo());
//
//            }
//        });
        rv = (RecyclerView) findViewById(R.id.recycler_view_musica);
        rv.setHasFixedSize(false);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);

        mLinearLayoutManager.removeAllViews();


        mMusicaAdapter = new MusicaAdapter(this);

        contadorDeCanciones = mMusicaAdapter.getItemCount();
        mMusicaAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vpos = rv.getChildAdapterPosition(v);
                Log.d("ID",musicaPath.get(rv.getChildAdapterPosition(v)));
                if (!mp.isPlaying()){
                    try {
                        mp.setDataSource(musicaPath.get(rv.getChildAdapterPosition(v)));
                        mp.prepare();
                        mp.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else {
                    try {
                        mp.stop();
                        mp.reset();
                        mp.setDataSource(musicaPath.get(rv.getChildAdapterPosition(v)));
                        mp.prepare();
                        mp.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        rv.setAdapter(mMusicaAdapter);
        rv.setLayoutManager(mLinearLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));




        call = VideoTurismoAdapter.getApiService().getMusicaRock();
        call.enqueue(this);
        mp.setOnPreparedListener(this);


        mp.setOnCompletionListener(this);


        //llamarJson(getIntent().getStringExtra("Genero"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void llamarJson (String genero){


        switch (genero){
            case "Pop":
//                rv.setLayoutManager(mLinearLayoutManager);
//                rv.setAdapter(mMusicaAdapter);
//                call = VideoTurismoAdapter.getApiService().getMusicaPop();
//                call.enqueue(PlayListActivity.this);

                break;
            case"Rock":
                break;
            case "Clasicas":
                break;
            case"Regional":
                break;
            default:
                break;
        }

    }

    @Override
    public void onResponse(Call<ArrayList<Musica>> call, Response<ArrayList<Musica>> response) {
        if(response.isSuccessful()){
            ArrayList<Musica> musica = response.body();


            for(int i =0; i<musica.size();i++){

                mMusica = musica.get(i);
                musicaPath.add("http://192.168.5.161/streaming"+mMusica.getRutaCancion().replaceAll(" ","%20"));
                Log.d("OnResponse Musica Ruta", "Size of Musica ="+ musicaPath.get(i));
            }

            mMusicaAdapter.setDataSet(musica);
            Log.d("OnResponse Musica", "Size of Musica ="+ musica.size());



        }
    }

    @Override
    public void onFailure(Call<ArrayList<Musica>> call, Throwable t) {

        Toast.makeText(this, "Error en la red ", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void onBackPressed() {


//        if (mp.isPlaying()){
//            mp.stop();
//            mp.release();
//        }
//        finish();
        super.onBackPressed();
    }
    public void onDestroy(){

        if (mp.isPlaying()){
            mp.stop();
            mp.release();
        }
        mp = null;
        super.onDestroy();
    }



    @Override
    public void onPrepared(MediaPlayer mp) {


        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.drawer_layout_musica));
        mc.setEnabled(true);
        mc.show();

    }

    @Override
    public void start() {
        mp.start();

    }

    @Override
    public void pause() {
        mp.pause();

    }

    @Override
    public int getDuration() {
        return mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mp.seekTo(pos);

    }

    @Override
    public boolean isPlaying() {
        return mp.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }


    @Override
    public void onCompletion(MediaPlayer mp) {

        vpos = vpos + 1;
        Log.d("PAth", "Contador!!!!!! de isPlaying" + ": " + mp.isPlaying());
        if (!mp.isPlaying()) {
            mp.stop();
            Log.d("PAth", "Contador!!!!!! de isPlaying" + ": " + vpos);

            if (vpos < musicaPath.size()) {
                Log.d("PAth", "Contador!!!!!! de IF vpos: " + vpos+ " numero de repros "+ musicaPath.size());
                mp.reset();
                try {
                    Log.d("PAth", musicaPath.get(vpos));
                    mp.setDataSource(musicaPath.get(vpos));
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("PAth", musicaPath.get(vpos) + " Contador!!!!!!: " + vpos);
            }
            else{
                vpos=0;
                Toast.makeText(this,"Ya se termino la Lista ",Toast.LENGTH_LONG).show();
                Log.d("PAth", "Contador!!!!!! de Else vpos: " + vpos+ " numero de repros "+ musicaPath.size());
                mp.reset();
                try {
                    Log.d("PAth", musicaPath.get(vpos));
                    mp.setDataSource(musicaPath.get(vpos));
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            if(mp.isPlaying()){
                mp.stop();
            }
            if (vpos < musicaPath.size()) {
                Log.d("PAth", "Contador!!!!!! de Else vpos: " + vpos+ " numero de repros "+ musicaPath.size());
                mp.reset();
                try {
                    Log.d("PAth", musicaPath.get(vpos));
                    mp.setDataSource(musicaPath.get(vpos));
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("PAth", musicaPath.get(vpos) + " Contador!!!!!!: " + vpos);
            }else{
                vpos=0;
                Toast.makeText(this,"Ya se termino la Lista ",Toast.LENGTH_LONG).show();
                Log.d("PAth", "Contador!!!!!! de Else vpos: " + vpos+ " numero de repros "+ musicaPath.size());
                mp.reset();
                try {
                    Log.d("PAth", musicaPath.get(vpos));
                    mp.setDataSource(musicaPath.get(vpos));
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
