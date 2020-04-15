package applications.applicationesp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText txt;
    EditText txt2;
    String response;
    Button b;
    Button b2;
    Button b3;
    private static Socket s ;
    private static PrintWriter printWriter;
    String message="";
    private static String ip="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button1);
        b2=(Button) findViewById(R.id.button2);
      //  b3=(Button) findViewById(R.id.button3);
        txt = (EditText) findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.textView) ;
        txt2=(EditText)findViewById(R.id.editText2);
    }

    class myTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                //SENDING THE MESSAGE
                printWriter= new PrintWriter(s.getOutputStream());
                printWriter.write(message);
                printWriter.flush();
                // closing all connections
                printWriter.close();
                s.close();
            }catch(IOException e)
            {e.printStackTrace();}
            return null;
        }
        protected void connection(){
            try {
                s = new Socket(ip, 80);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while(in!=null){
                    response = in.readLine();
                    in.close();
                   Reading();}
            }catch(IOException E){E.printStackTrace();}
        }
    }
    public void connect_server(View v ){


            ip=txt2.getText().toString();
        if (ip==""){Toast.makeText(MainActivity.this,"ip invalid", Toast.LENGTH_SHORT).show();
        }
            myTask mt2 = new myTask();
            mt2.connection();
            Toast.makeText(MainActivity.this,"connecting...", Toast.LENGTH_SHORT).show();


    }
    public void send_text(View v)
    {
        message= txt.getText().toString();
        myTask mt = new myTask();
        mt.execute();
        Toast.makeText(getApplicationContext(),"MT launched",Toast.LENGTH_LONG).show();

    }
 /*   public void disconnect(View v){
        try{
            s.close();
        } catch(IOException E){E.printStackTrace();}


    }*/

public void Reading(){
    Toast.makeText(getApplicationContext(),"connected: Answer",Toast.LENGTH_LONG).show();
}


}
