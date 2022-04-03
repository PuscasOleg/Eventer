package com.example.eventer.Fragments.Profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.eventer.R
import com.example.eventer.Fragments.Auth.RegisterUser
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
    private lateinit var logOutBtn: TextView
    private lateinit var verification: TextView
    lateinit var profileImage: CircleImageView

    //Firebase
    private val user = FirebaseAuth.getInstance()
    private val userId = user.uid
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()


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
        logOutBtn = view.findViewById(R.id.logOutTxtView)
        profileImage = view.findViewById(R.id.profileImageView)
        verification = view.findViewById(R.id.emailVerificationView)








        profileImage.setOnClickListener {

            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 33)
        }

        logOutBtn.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            gotoMainActivity()

        }

        val reference = FirebaseDatabase.getInstance().getReference("Users")


        if (userId != null) {
            reference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val registerUserProfile: RegisterUser =
                        snapshot.getValue(RegisterUser::class.java)!!


                    userNameView.text = registerUserProfile.userName
                    emailView.text = registerUserProfile.email
                    Glide.with(activity!!).load(registerUserProfile.ProfileImage).into(profileImage)



            }

                override fun onCancelled(error: DatabaseError) {

                    Toast.makeText(activity, "Failure", Toast.LENGTH_LONG).show()

                }

            })


        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!!.data != null) {

            val profileUri: Uri = data.data!!

            profileImage.setImageURI(profileUri)

            val referance: StorageReference =
                storage.reference.child("profile_picture")
                    .child(FirebaseAuth.getInstance().uid.toString())

            referance.putFile(profileUri).addOnSuccessListener {
                Toast.makeText(activity, "Successful", Toast.LENGTH_LONG).show()

                referance.downloadUrl.addOnSuccessListener {
                    database.reference.child("Users")
                        .child(FirebaseAuth.getInstance().uid.toString()).child("ProfileImage")
                        .setValue(it.toString())
                }
            }


        }

    }


    private fun gotoMainActivity() {
       startActivity(Intent(this.context,MainActivity::class.java))


    }


}









