package com.axxess.cavistaassignment;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.axxess.cavistaassignment.ext.AnyExtKt;
import com.axxess.cavistaassignment.model.Comment;
import com.bumptech.glide.Glide;
import com.axxess.cavistaassignment.model.Data;
import com.cavistatest.viewmodel.CommentViewModel;
import com.google.android.material.button.MaterialButton;


public class ImageDetailsActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ImageView imageView;
    EditText comment;
    MaterialButton saveButton;
    Data mData;

    CommentViewModel mCommetViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_details);
        mData = (Data) getIntent().getSerializableExtra("data");
        mCommetViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        mToolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);
        saveButton = findViewById(R.id.save);
        comment = findViewById(R.id.comment);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mData.getTitle());
        String img = AnyExtKt.getImageUrl(mData);
        Glide.with(imageView).load(img).placeholder(R.drawable.loader)
                .into(imageView);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
        mCommetViewModel.getComment().observe(this, mComment -> {
            if (mComment != null) {
                comment.setText(mComment.getComment());
            }
        });
        mCommetViewModel.getComment(mData.getId());
        saveButton.setOnClickListener(v -> {
            if (comment.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, R.string.txt_comment, Toast.LENGTH_SHORT).show();
            } else {
                Comment mComment = new Comment();
                mComment.setId(mData.getId());
                mComment.setComment(comment.getText().toString());
                mCommetViewModel.saveComment(mComment);
                Toast.makeText(this, R.string.txt_comment_saved, Toast.LENGTH_SHORT).show();

            }
        });
    }

}


