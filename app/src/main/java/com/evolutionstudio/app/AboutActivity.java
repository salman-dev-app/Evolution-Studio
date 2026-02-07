package com.evolutionstudio.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AboutActivity extends AppCompatActivity {
    
    private TextView tvCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvCredits = findViewById(R.id.tv_credits_content);

        findViewById(R.id.btnFacebook).setOnClickListener(v -> openUrl("https://facebook.com/salmandevapp"));
        findViewById(R.id.btnGithub).setOnClickListener(v -> openUrl("https://github.com/salman-dev-app"));
        findViewById(R.id.btnTelegram).setOnClickListener(v -> openUrl("https://t.me/salmandevapp"));
        
        loadCredits();
    }

    private void loadCredits() {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                new InputStreamReader(getAssets().open("credits.txt"), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            tvCredits.setText(stringBuilder.toString());
        } catch (IOException e) {
            tvCredits.setText("Developed by Md Salman Biswas\nEvolution Studio");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
