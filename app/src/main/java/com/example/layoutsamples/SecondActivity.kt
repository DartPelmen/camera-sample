package com.example.layoutsamples

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.layoutsamples.databinding.ActivitySecondBinding
import java.security.Permission

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val contract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ x->
        if(x.resultCode== RESULT_OK){
            binding.imageView.setImageBitmap(x?.data?.extras?.getByteArray("data") as Bitmap)
        }
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if(it) {
            contract.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.capture.setOnClickListener {
            launcher.launch(android.Manifest.permission.CAMERA)
        }

    }
}