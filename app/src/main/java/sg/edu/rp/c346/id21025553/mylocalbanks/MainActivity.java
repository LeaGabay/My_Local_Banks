package sg.edu.rp.c346.id21025553.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Creating variables
    TextView tvDBS, tvOCBC, tvUOB;
    RadioGroup faveBank;

    String wordClicked = "";
    int checkEven = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking variables to UI elements
        tvDBS = findViewById(R.id.tvDBS);
        tvOCBC = findViewById(R.id.tvOCBC);
        tvUOB = findViewById(R.id.tvUOB);
        faveBank = findViewById(R.id.rgFavBank);

        registerForContextMenu(tvDBS);
        registerForContextMenu(tvOCBC);
        registerForContextMenu(tvUOB);

    }

    //  Options Menu Section (Translation)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.EnglishSelection) {
            tvDBS.setText(getString(R.string.dbs));
            tvOCBC.setText(getString(R.string.ocbc));
            tvUOB.setText(getString(R.string.uob));

            return true;

        } else if (id == R.id.chineseSelection) {
            tvDBS.setText(getString(R.string.dbs_chn));
            tvOCBC.setText(getString(R.string.ocbc_chn));
            tvUOB.setText(getString(R.string.uob_chn));

            return true;

        } else if (id == R.id.toggleFave){
            int checkFave = faveBank.getCheckedRadioButtonId();
            if (checkEven % 2 != 0) {
                if (checkFave == R.id.rbDBS) {
                    tvDBS.setTextColor(Color.parseColor("#FF0000"));

                } else if (checkFave == R.id.rbOCBC) {
                    tvOCBC.setTextColor(Color.parseColor("#FF0000"));

                } else {
                    tvUOB.setTextColor(Color.parseColor("#FF0000"));

                }
                checkEven += 1;
            } else {
                tvDBS.setTextColor(Color.parseColor("black"));
                tvOCBC.setTextColor(Color.parseColor("black"));
                tvUOB.setTextColor(Color.parseColor("black"));
                faveBank.clearCheck();
                checkEven += 1;
            }
        } else {
            Toast.makeText(MainActivity.this, "Error in translation", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // Creating the Context Menu - Contacting via web or phone
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main2, menu);

        if (v == tvDBS){
            wordClicked = "DBS";
        } else if (v == tvOCBC){
            wordClicked = "OCBC";
        } else if (v == tvUOB){
            wordClicked = "UOB";
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.BankPhone) {
            if (wordClicked.equalsIgnoreCase("DBS")) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 18001111111L)); // Number too long to be integer so we use "long" data type
                startActivity(intentCall);
            } else if (wordClicked.equalsIgnoreCase("OCBC")) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 18003633333L));
                startActivity(intentCall);
            } else {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 18002222121L));
                startActivity(intentCall);
            }
        } else {
            if (wordClicked.equalsIgnoreCase("DBS")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dbs.com.sg"));
                startActivity(intent);
            } else if (wordClicked.equalsIgnoreCase("OCBC")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ocbc.com"));
                startActivity(intent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uob.com.sg"));
                startActivity(intent);
            }
        }

        return super.onContextItemSelected(item);
    }

}