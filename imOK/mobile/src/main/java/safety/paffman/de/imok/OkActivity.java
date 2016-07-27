package safety.paffman.de.imok;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        setOnClickListener();
    }

    /**
     * Adds the onclick function to the button
     */
    private void setOnClickListener() {
        final Button closeBtn = (Button)findViewById(R.id.buttonOk);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    /**
     * Method to trigger E-Mail Message
     */
    private void sendMessage() {
        String[] TO = getEMailAddresses();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        //emailIntent.setData(Uri.parse("mailto:el.paso2384@gmail.com"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        emailIntent.putExtra(Intent.EXTRA_TEXT, BODY_CONTENT);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(OkActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * reads all entered E-Mail Addresses and uses them as e-mail receipient
     *
     * @return
     */
    private String[] getEMailAddresses() {
        final TextView emailText = (TextView) findViewById(R.id.emailAddressText);
        String allAddresses = emailText.getText().toString();
        return allAddresses.split(",");
    }
}
