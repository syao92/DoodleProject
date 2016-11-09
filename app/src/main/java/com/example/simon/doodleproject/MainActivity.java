package com.example.simon.doodleproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                doodleView.clear();
            }
        });

        Button sizeButton = (Button) findViewById(R.id.sizeButton);
        sizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                SeekBar sizeSeekBar = new SeekBar(MainActivity.this);
                builder.setMessage("Choose your size:").setTitle("Size Choice?");
                builder.setView(sizeSeekBar);
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                sizeSeekBar.setMax(20);
                sizeSeekBar.setProgress((int) doodleView.get_size());

                sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                        int minimum = 2;
                        int step = 1;
                        float value = minimum + (progress * step);
                        doodleView.set_size(value);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                        float value = doodleView.get_size();
                        seekBar.setProgress((int) value);
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button opacityButton = (Button) findViewById(R.id.opacityButton);
        opacityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                SeekBar opacitySeekBar = new SeekBar(MainActivity.this);
                builder.setMessage("Choose your opacity:").setTitle("Opacity Choice?");
                builder.setView(opacitySeekBar);
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                opacitySeekBar.setMax(255);
                opacitySeekBar.setProgress((int) doodleView.getOpacity());

                opacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                        int minimum = 0;
                        int step = 1;
                        float value = minimum + (progress * step);
                        doodleView.set_opacity((int) value);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                        int value = doodleView.getOpacity();
                        seekBar.setProgress(value);
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
        SeekBar redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        redSeekBar.setMax(255);
        redSeekBar.setProgress(doodleView.getRed());
        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                int minimum = 0;
                int step = 1;
                int value = (int) (minimum + (progress * step));
                doodleView.set_color(doodleView.getOpacity(), value, doodleView.getGreen(),
                        doodleView.getBlue());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenSeekBar.setMax(255);
        greenSeekBar.setProgress(doodleView.getGreen());
        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                int minimum = 0;
                int step = 1;
                int value = (int) (minimum + (progress * step));
                doodleView.set_color(doodleView.getOpacity(), doodleView.getRed(), value,
                        doodleView.getBlue());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueSeekBar.setMax(255);
        blueSeekBar.setProgress(doodleView.getBlue());
        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                int minimum = 0;
                int step = 1;
                int value = (int) (minimum + (progress * step));
                doodleView.set_color(doodleView.getOpacity(), doodleView.getRed(), doodleView.getGreen(),
                        value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ImageButton undoButton = (ImageButton) findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                doodleView.undo();
            }
        });

        ImageButton redoButton = (ImageButton) findViewById(R.id.redoButton);
        redoButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodle);
                doodleView.redo();
            }
        });

    }

}