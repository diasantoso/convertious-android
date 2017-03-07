package com.convertme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.convertme.Fragments.AboutFragment;
import com.convertme.Fragments.BrowseFragment;
import com.convertme.Fragments.ConnectFragment;
import com.convertme.TwitterAPI.InitAPITwitter;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import dias_plbtw.com.convertme.R;

public class NavigationActivity extends AppCompatActivity {

    private Drawer result = null;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        setNavigationBar();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelay, new BrowseFragment()).commit();
    }

    public void setNavigationBar()
    {
        final android.app.FragmentManager fragmentManager = getFragmentManager();

        if(result!=null)
            result.removeAllItems();

        // Create the AccountHeader
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .addDrawerItems(
                        new SectionDrawerItem().withName(""),
                        new SectionDrawerItem().withName(""),
                        new SectionDrawerItem().withName("Options"),
                        new PrimaryDrawerItem().withName("Browse").withIcon(FontAwesome.Icon.faw_globe).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Connect Us").withIcon(FontAwesome.Icon.faw_facebook).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Share Twitter").withIcon(FontAwesome.Icon.faw_twitter).withIdentifier(5),
                        new PrimaryDrawerItem().withName("About").withIcon(FontAwesome.Icon.faw_user).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Exit").withIcon(FontAwesome.Icon.faw_close).withIdentifier(7)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 3) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new BrowseFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 4) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new ConnectFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 5) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new InitAPITwitter()).commit();
                            } else if (drawerItem.getIdentifier() == 6) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new AboutFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 7) {
                                finish();
                            }
                        }
                        return false;
                    }
                })
                .withSelectedItem(-1)
                .withSavedInstance(savedInstanceState)
                .build();
    }
}
