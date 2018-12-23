package ir.hatamiarash.locationtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class Activity_Main extends AppCompatActivity {
	
	FirebaseFirestore db;
	FirebaseFirestoreSettings settings;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FirebaseApp.initializeApp(this);
		db = FirebaseFirestore.getInstance();
		
		settings = new FirebaseFirestoreSettings.Builder()
				.setTimestampsInSnapshotsEnabled(true)
				.build();
		db.setFirestoreSettings(settings);
		
		button = findViewById(R.id.button);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, Object> user = new HashMap<>();
				user.put("id", 1);
				user.put("name", "Arash");
				user.put("email", "hatamiarash7@gmail.com");
				user.put("phone", "09182180519");
				
				db.collection("users")
						.add(user)
						.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
							@Override
							public void onSuccess(DocumentReference documentReference) {
								Log.d("FF", "DocumentSnapshot added with ID: " + documentReference.getId());
							}
						})
						.addOnFailureListener(new OnFailureListener() {
							@Override
							public void onFailure(@NonNull Exception e) {
								Log.w("FF", "Error adding document", e);
							}
						});
			}
		});
	}
}
