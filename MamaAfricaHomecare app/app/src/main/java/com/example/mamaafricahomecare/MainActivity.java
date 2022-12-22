package com.example.mamaafricahomecare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.Donors.Donations;
import com.example.mamaafricahomecare.Donors.MakeDonation;
import com.example.mamaafricahomecare.Member.AddElder;
import com.example.mamaafricahomecare.Member.Elders;
import com.example.mamaafricahomecare.Member.Profile;
import com.example.mamaafricahomecare.Member.SelectCareGiver;
import com.example.mamaafricahomecare.Staff.CareGiver.AssignedElders;
import com.example.mamaafricahomecare.Staff.CareGiver.Feed;
import com.example.mamaafricahomecare.Staff.CareGiver.RequestDashboard;
import com.example.mamaafricahomecare.Staff.Dashboard;
import com.example.mamaafricahomecare.Staff.Doctors.BookingDashboard;
import com.example.mamaafricahomecare.Staff.LabTech.TechDashboard;
import com.example.mamaafricahomecare.Staff.Pharmacist.PharmDahboard;
import com.example.mamaafricahomecare.Staff.StoreManager.StoreDashboard;
import com.example.mamaafricahomecare.Staff.Supervisor.AdmittedElders;
import com.example.mamaafricahomecare.Staff.Supervisor.EldersApproved;
import com.example.mamaafricahomecare.Staff.Supervisor.EldersPendingApproval;
import com.example.mamaafricahomecare.Supplier.SupplierDashboard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mamaafricahomecare.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private  DrawerLayout drawer;
    private NavigationView navView;
    private SessionHandler session;
    private UserModel user;
    private FloatingActionButton btn_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        session=new SessionHandler(this);
        user=session.getUserDetails();

        binding.appBarMain.fab.setVisibility(View.GONE);

        if(session.isLoggedIn()){
            if(user.getUser_type().equals("Family member")){
                binding.appBarMain.fab.setVisibility(View.VISIBLE);
            }
            if(user.getUser_type().equals("Care giver")){
                binding.appBarMain.fab.setVisibility(View.VISIBLE);
            }
        }
        binding.appBarMain.fab.setOnClickListener(view -> {
            Intent nn;
            if(user.getUser_type().equals("Family member")){
                nn=new Intent(getApplicationContext(), SelectCareGiver.class);
               startActivity(nn);
            }else{
                    nn=new Intent(getApplicationContext(), Feed.class);
                    startActivity(nn);
            }

        });
         drawer = binding.drawerLayout;
         navView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this,
                navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.getMenu().findItem(R.id.nav_register).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), SelectRegister.class);
            startActivity(in);
            return false;
        });  navView.getMenu().findItem(R.id.nav_help).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), Help.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_about).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), About.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_login).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), Login.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_profile).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), Profile.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_orders).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), SupplierDashboard.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_add_elder).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), AddElder.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_elder).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), Elders.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_elder_pending).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), EldersPendingApproval.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_elder_approved).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), EldersApproved.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_elder_admitted).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), AdmittedElders.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_assigned).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), AssignedElders.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_appointments).setOnMenuItemClickListener(v->{
            if (user.getUser_type().equals("Care giver")) {
                Intent in=new Intent(getApplicationContext(), Dashboard.class);
                startActivity(in);
            }else{
                Intent in=new Intent(getApplicationContext(), BookingDashboard.class);
                startActivity(in);
            }
            return false;
        });
        navView.getMenu().findItem(R.id.nav_drugs).setOnMenuItemClickListener(v->{

                Intent in=new Intent(getApplicationContext(), DrugsDashbboard.class);
                startActivity(in);

            return false;
        });
        navView.getMenu().findItem(R.id.nav_items_request).setOnMenuItemClickListener(v->{
            if (user.getUser_type().equals("Care giver")) {
                Intent in=new Intent(getApplicationContext(), RequestDashboard.class);
                startActivity(in);
            }else if(user.getUser_type().equals("Store manager")){
                Intent in=new Intent(getApplicationContext(), RequestDashboard.class);
                startActivity(in);
            }else{
//                Intent in=new Intent(getApplicationContext(), BookingDashboard.class);
//                startActivity(in);
            }
            return false;
        });

        navView.getMenu().findItem(R.id.nav_lab_test).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), TechDashboard.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_pharm).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), PharmDahboard.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_donations).setOnMenuItemClickListener(v->{
            Intent in;
            if(user.getUser_type().equals("Donor")){

                 in=new Intent(getApplicationContext(), Donations.class);
            }else{

                 in=new Intent(getApplicationContext(), StoreDashboard.class);
            }
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_make_donation).setOnMenuItemClickListener(v->{
            Intent in=new Intent(getApplicationContext(), MakeDonation.class);
            startActivity(in);
            return false;
        });
        navView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(v->{
            session.logoutUser();
            Toast.makeText(this, "You have logout successfully", Toast.LENGTH_SHORT).show();
            Intent in=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
            return false;
        });

        checkLogin();
    }

    private void checkLogin(){
        navView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        navView.getMenu().findItem(R.id.nav_profile).setVisible(false);
        navView.getMenu().findItem(R.id.nav_add_elder).setVisible(false);
        navView.getMenu().findItem(R.id.nav_elder).setVisible(false);
        navView.getMenu().findItem(R.id.nav_elder_approved).setVisible(false);

        navView.getMenu().findItem(R.id.nav_elder_pending).setVisible(false);
        navView.getMenu().findItem(R.id.nav_elder_admitted).setVisible(false);

        navView.getMenu().findItem(R.id.nav_assigned).setVisible(false);
        navView.getMenu().findItem(R.id.nav_appointments).setVisible(false);

        navView.getMenu().findItem(R.id.nav_lab_test).setVisible(false);
        navView.getMenu().findItem(R.id.nav_pharm).setVisible(false);

        navView.getMenu().findItem(R.id.nav_donations).setVisible(false);
        navView.getMenu().findItem(R.id.nav_make_donation).setVisible(false);

        navView.getMenu().findItem(R.id.nav_items_request).setVisible(false);
        navView.getMenu().findItem(R.id.nav_drugs).setVisible(false);
        navView.getMenu().findItem(R.id.nav_orders).setVisible(false);



        if(session.isLoggedIn()){
            navView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            navView.getMenu().findItem(R.id.nav_profile).setVisible(true);

            navView.getMenu().findItem(R.id.nav_register).setVisible(false);
            navView.getMenu().findItem(R.id.nav_login).setVisible(false);

            if(user.getUser_type().equals("Family member")){
                navView.getMenu().findItem(R.id.nav_profile).setVisible(true);
                navView.getMenu().findItem(R.id.nav_add_elder).setVisible(true);
                navView.getMenu().findItem(R.id.nav_elder).setVisible(true);
            }
            if(user.getUser_type().equals("Supervisor")){
                navView.getMenu().findItem(R.id.nav_profile).setVisible(true);
                navView.getMenu().findItem(R.id.nav_elder_pending).setVisible(true);
                navView.getMenu().findItem(R.id.nav_elder_approved).setVisible(true);
                navView.getMenu().findItem(R.id.nav_elder_admitted).setVisible(true);

            } if(user.getUser_type().equals("Care giver")) {
                navView.getMenu().findItem(R.id.nav_profile).setVisible(true);
                navView.getMenu().findItem(R.id.nav_assigned).setVisible(true);
                navView.getMenu().findItem(R.id.nav_appointments).setVisible(true);
                navView.getMenu().findItem(R.id.nav_items_request).setVisible(true);

            } if(user.getUser_type().equals("Othopedic surgeon")
                    ||user.getUser_type().equals("General doctor")){
                navView.getMenu().findItem(R.id.nav_profile).setVisible(true);
                navView.getMenu().findItem(R.id.nav_appointments).setVisible(true);
            }
            if(user.getUser_type().equals("Lab technician")){
                navView.getMenu().findItem(R.id.nav_profile).setVisible(true);
                navView.getMenu().findItem(R.id.nav_lab_test).setVisible(true);
            }
            if(user.getUser_type().equals("Pharmacist")){
                navView.getMenu().findItem(R.id.nav_profile).setVisible(true);
                navView.getMenu().findItem(R.id.nav_pharm).setVisible(true);
            }
            if(user.getUser_type().equals("Donor")){
                navView.getMenu().findItem(R.id.nav_donations).setVisible(true);
                navView.getMenu().findItem(R.id.nav_make_donation).setVisible(true);
            }
            if(user.getUser_type().equals("Store manager")){
                navView.getMenu().findItem(R.id.nav_donations).setVisible(true);
                navView.getMenu().findItem(R.id.nav_items_request).setVisible(true);
                navView.getMenu().findItem(R.id.nav_drugs).setVisible(true);
            }
            if(user.getUser_type().equals("Supplier")){
                navView.getMenu().findItem(R.id.nav_orders).setVisible(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}