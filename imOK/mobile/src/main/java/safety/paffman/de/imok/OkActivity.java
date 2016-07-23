package safety.paffman.de.imok;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OkActivity extends AppCompatActivity {

    /**
     * E-Mail Headline
     */
    private static final String SUBJECT = "i´m ok!";

    /**
     * E-Mail Content
     */
    private static final String BODY_CONTENT = "something happend - but i´m ok!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok);
    }

    /**
     * Method to trigger E-Mail Message
     */
    private void sendMessage() {
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
//        Intent intent = new Intent(Intent.ACTION_SEND); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        intent.putExtra(Intent.EXTRA_TEXT, BODY_CONTENT);
        intent.setData(Uri.parse("mailto:el.paso2384@gmail.com")); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);
    }
}
