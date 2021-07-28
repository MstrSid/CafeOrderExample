package by.kos.cafeorderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import by.kos.cafeorderexample.databinding.ActivityCreateOrderBinding;
import by.kos.cafeorderexample.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        if(intent.hasExtra("order")) {
            binding.textView.setText(intent.getStringExtra("order"));
        } else Toast.makeText(this, "Got some error when get order", Toast.LENGTH_LONG).show();
    }
}