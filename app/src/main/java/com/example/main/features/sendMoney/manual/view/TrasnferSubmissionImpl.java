package com.example.main.features.sendMoney.manual.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.features.sendMoney.httpConnection.TransferPresenterImpl;
import com.example.main.main.view.FailedOperation;
import com.example.main.main.view.SucceededOperation;

public class TrasnferSubmissionImpl extends AppCompatActivity implements TrasnferSubmission {
    private String accessToken;
    private int receiverId;
    private String receiverUser ;
    private int amount;
    private int commission;
    private int total;
    private int providerId;
    private TextView userEditText ;
    private TextView amountEditText ;
    private TextView commissionEditText ;
    private TextView totalEditText ;
    private ProgressDialog dialog ;
    private TransferPresenterImpl presenter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasnfer_submission_impl);
        Intent intent = getIntent();
        accessToken = intent.getStringExtra("accessToken");
        receiverUser = intent.getStringExtra("userName");
        receiverId = intent.getIntExtra("receiverId" , -1 );
        Log.v("msg" , ""+receiverId);
        amount = intent.getIntExtra("amount" , -1  );
        commission = intent.getIntExtra("commission" , -1 );
        total = intent.getIntExtra("total" , -1 );
        providerId = intent.getIntExtra("providerId"  , -1);
        Toast.makeText(this , " " +accessToken +" " + " " +receiverId +" "
                +commission + " " +amount +" " +" " +total +" " + providerId , Toast.LENGTH_SHORT).show();
        userEditText = (TextView) findViewById(R.id.receiver_text);
        amountEditText = (TextView) findViewById(R.id.amount_text);
        commissionEditText = (TextView) findViewById(R.id.commission_text);
        totalEditText = (TextView) findViewById(R.id.total_text);
        userEditText.setText("receiver username"+receiverUser);
        amountEditText.setText("amount"+amount);
        commissionEditText.setText("commission "+commission);
        totalEditText.setText("total"+total);
        presenter = new TransferPresenterImpl(this) ;
    }

    public void OnSubmit(View view) {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait ");
        dialog.show();
        presenter.transfer(receiverId , amount , providerId , commission , accessToken);
    }

    @Override
    public void showFailure() {
        dialog.dismiss();
        Intent intent = new Intent(this , FailedOperation.class);
        startActivity(intent);
    }

    @Override
    public void showSuccess() {
        dialog.dismiss();
        Intent intent = new Intent(this , SucceededOperation.class);
        startActivity(intent);

    }
}
