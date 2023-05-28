package com.example.xyz;

import android.app.usage.UsageStats;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class InstalledApps extends AppCompatActivity {

    ListView userInstalledApps;
    private AdapterView<Adapter> adapterView;
    private Object AdapterView;
    CheckBox checkBox;
    Button button;
    List<AppList> installedApps;
    private UsageStatsManager mUsageStatsManager;
    private LayoutInflater mInflater;
    private AppAdapter mAdapter;
    private PackageManager mPm;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed_apps);


        userInstalledApps = (ListView) findViewById(R.id.installed_app_list);
        installedApps = getInstalledApps();
        AppAdapter installedAppAdapter = new AppAdapter(InstalledApps.this, installedApps);
        userInstalledApps.setAdapter(installedAppAdapter);
//

        mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       ListView listView = (ListView) findViewById(R.id.apps);
       mAdapter = new AppAdapter(this,installedApps);
       listView.setAdapter(mAdapter);

        //ArrayAdapter<String> a=new ArrayAdapter<>(MainActivity3.this, android.R.layout.simple_list_item_multiple_choice,installedApps)


        //Toast.makeText(this, ""+installedApps.get(1).getLastUsed(), Toast.LENGTH_SHORT).show();

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> items;
                items = new ArrayList<String>();
                int count = 0;
                int size = userInstalledApps.getCount();
//                ListView myList = (ListView) getIntent().getSerializableExtra("mylist");
                for (int i = 0; i < size; i++) {
                    if (installedApps.get(i).isSelected()) {
                        items.add(installedApps.get(i).getName());
                        count++;
                    }
                }

//                Toast.makeText(InstalledApps.this, "Selected Apps:"+ count, Toast.LENGTH_SHORT).show();
                if(count != 0) {
                    Toast.makeText(InstalledApps.this, "Selected Apps:"+ count, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InstalledApps.this, SetTime.class);
                    intent.putExtra("mylist", items);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(InstalledApps.this, "Select any App", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Total Number of Installed-Apps(i.e. List Size)
        String abc = userInstalledApps.getCount() + "";
        TextView countApps = (TextView) findViewById(R.id.countApps);
        countApps.setText("Total Installed Aps: " + abc);
        Toast.makeText(this, abc + " Apps", Toast.LENGTH_SHORT).show();

    }

    private List<AppList> getInstalledApps() {
        List<AppList> apps = new ArrayList<>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        //List<PackageInfo> packs = getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!isSystemPackage(p))) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String packages = p.applicationInfo.packageName;
                apps.add(new AppList(appName, icon, packages));
            }
        }
        return apps;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    //Adapter
    public class AppAdapter extends BaseAdapter {

        public LayoutInflater layoutInflater;
        public List<AppList> listStorage;
        private ArrayList<Boolean> checkList=new ArrayList<Boolean>();
           private final ArrayList<UsageStats> mPackageStats = new ArrayList<>();


        public AppAdapter(Context context, List<AppList> customizedListView) {
            layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listStorage = customizedListView;

            for(int i=0;i<customizedListView.size();i++){
                checkList.add(false);
            }
    }

        @Override
        public int getCount() {
            return listStorage.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder listViewHolder;
            if(convertView == null){
                listViewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.installed_app_list, parent, false);

                listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.list_app_name);
                listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.app_icon);
                listViewHolder.packageInListView=(TextView)convertView.findViewById(R.id.app_package);
                listViewHolder.checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);
                convertView.setTag(listViewHolder);
            }

            else{
                listViewHolder = (ViewHolder)convertView.getTag();
            }
            listViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int a = (Integer)buttonView.getTag();
                    checkList.set((Integer)buttonView.getTag(),isChecked);
                }
            });
            listViewHolder.checkBox.setTag(Integer.valueOf(position));
            listViewHolder.textInListView.setText(listStorage.get(position).getName());
            listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());
            listViewHolder.packageInListView.setText(listStorage.get(position).getPackages());
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            checkBox.setTag(Integer.valueOf(position)); // set the tag so we can identify the correct row in the listener
            checkBox.setChecked(checkList.get(position)); // set the status as we stored it
            checkBox.setOnCheckedChangeListener(mListener); // set the listener
            return convertView;
        }

        class ViewHolder{
            CheckBox checkBox;
            TextView textInListView;
            ImageView imageInListView;
            TextView packageInListView;
        }
        CompoundButton.OnCheckedChangeListener mListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkList.set((Integer)buttonView.getTag(),isChecked); // get the tag so we know the row and store the status
                listStorage.get((Integer)buttonView.getTag()).setSelected(isChecked);
            }
        };
    }

    public class AppList {
        private final String name;
        private boolean isSelected;
        Drawable icon;
        private final String packages;
        int tMin,tHour;
        String lastUsed;
        String usageTime;

        public AppList(String name, Drawable icon, String packages) {
            this.name = name;
            this.icon = icon;
            this.packages = packages;
            this.isSelected = false;
            this.tHour=0;
            this.tMin=30;
        }
        public String getName() {
            return name;
        }
        public Drawable getIcon() {
            return icon;
        }
        public String getPackages() {
            return packages;
        }
        public boolean isSelected() {
            return isSelected;
        }
        public String time(){String time=tHour+"hr :"+tMin+"Min";return time;}
//
        public void setSelected(boolean selected) {
            this.isSelected = selected;
        }
    }

}