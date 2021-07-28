package by.kos.cafeorderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import by.kos.cafeorderexample.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.etPassword.getText().toString().isEmpty() || binding.etName.getText().toString().isEmpty()){
                    toast = Toast.makeText(view.getContext(), getString(R.string.warning_fill_fields), Toast.LENGTH_LONG);
                    toast.show();
                } else{
                    Intent intent = new Intent(view.getContext(), CreateOrderActivity.class);
                    intent.putExtra("Name", binding.etName.getText().toString().trim());
                    intent.putExtra("Password", binding.etPassword.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });
    }

}