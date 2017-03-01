package dias_plbtw.com.asikinaja;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dias_plbtw.com.asikinaja.Fragments.AboutFragment;
import dias_plbtw.com.asikinaja.Fragments.BrowseFragment;
import dias_plbtw.com.asikinaja.Fragments.ConnectFragment;

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
                        new PrimaryDrawerItem().withName("").withIdentifier(1),
                        new PrimaryDrawerItem().withName("").withIdentifier(2),
                        new SectionDrawerItem().withName("Options"),
                        new PrimaryDrawerItem().withName("Browse").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Connect Us").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(4),
                        new PrimaryDrawerItem().withName("About").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Exit").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(6)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 3) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new BrowseFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 4) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new ConnectFragment()).commit();
                            }else if (drawerItem.getIdentifier() == 5) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new AboutFragment()).commit();
                            }else if (drawerItem.getIdentifier() == 6) {
                                System.exit(0);
                            }
                            if (intent != null) {

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
