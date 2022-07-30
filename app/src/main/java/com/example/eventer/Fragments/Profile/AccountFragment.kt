package com.example.eventer.Fragments.Profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.eventer.R
import com.example.eventer.Fragments.Auth.UserClass
import com.example.eventer.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView


class AccountFragment : Fragment() {

    //Views
    lateinit var userNameView: TextView
    lateinit var emailView: TextView
    private lateinit var logOutImageView: ImageView
    lateinit var profileImage: CircleImageView

    //Firebase
    private val user = FirebaseAuth.getInstance()
    private val userId = user.uid

    private var storage = FirebaseStorage.getInstance()
    private var database = FirebaseDatabase.getInstance()
    private lateinit var reference: StorageReference


    private var intent: Intent = Intent()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        userNameView = view.findViewById(R.id.showUserNameView)
        emailView = view.findViewById(R.id.showEmailView)
        profileImage = view.findViewById(R.id.profileImageView)
        logOutImageView = view.findViewById(R.id.logOutImg)



        profileImage.setOnClickListener {

            val intent = Intent()
            //выбираем тип файла "изображение"
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 33)
        }

        logOutImageView.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            gotoMainActivity()

        }

        if (userId != null) {
            database.reference.child("Users").child(userId).addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val  userClass= snapshot.getValue(UserClass::class.java)!!
                    userNameView.text=userClass.userName
                    emailView.text=userClass.email
                    Glide.with(context!!).load(userClass.ProfileImage).centerCrop().placeholder(R.drawable.kisspng).into(profileImage)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

            })
        }




    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.data != null) {
            val uriProfile: Uri? = data.data
            profileImage.setImageURI(uriProfile)

            reference = storage.reference.child("profile_picture").child(userId!!)

            if (uriProfile != null) {
                reference.putFile(uriProfile).addOnSuccessListener {

                    reference.downloadUrl.addOnSuccessListener {
                        database.reference.child("Users").child(userId).child("ProfileImage")
                            .setValue(it.toString())
                    }
                    Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun gotoMainActivity() {
        startActivity(Intent(context, MainActivity::class.java))
    }


}









