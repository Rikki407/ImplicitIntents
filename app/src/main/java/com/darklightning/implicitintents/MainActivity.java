package com.darklightning.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String URL_STRING = "http://www.udacity.com";
    public static final String ADDRESS="1600 Amphitheater Parkway, CA";
    Button openwebsite,openlocationinmap,shareText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openwebsite = (Button) findViewById(R.id.open_website);
        openlocationinmap = (Button) findViewById(R.id.open_location_in_map);
        shareText = (Button) findViewById(R.id.share_text_content);
        shareText.setOnClickListener(this);
        openlocationinmap.setOnClickListener(this);
        openwebsite.setOnClickListener(this);
    }
    private void openWebPage(String url)
    {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Web browser not available",Toast.LENGTH_LONG).show();
        }
    }
    private void openLocation(String address)
    {
        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("geo")
                            .path("0.0")
                            .query(address)
                            .build();
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"No map application available",Toast.LENGTH_LONG).show();
        }

    }
    private void shareTextData()
    {
        String mimeType = "text/plain";
        String title = "Learning how to share";
        String texttoshare = "Hey There";

        ShareCompat.IntentBuilder.from(this).setChooserTitle(title).setType(mimeType).setText(texttoshare).startChooser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.open_website:
                openWebPage(URL_STRING);
                break;
            case R.id.open_location_in_map:
                openLocation(ADDRESS);
                break;
            case R.id.share_text_content:
                shareTextData();
                break;
            case R.id.create_your_own:
                break;
        }
    }
}
