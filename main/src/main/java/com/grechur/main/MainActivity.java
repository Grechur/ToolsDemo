package com.grechur.main;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.grechur.common.base.BaseActivity;
import com.grechur.main.databinding.ActivityMainBinding;
import com.grechur.main.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> implements BottomNavigationBar.OnTabSelectedListener{

    private Fragment currentFragment;
    private Fragment homeFragment;

    @Override
    protected void initView() {

        binding.bottomBar.addItem(new BottomNavigationItem(R.drawable.main_home_unsel,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.main_system_unsel,"体系"))
                .addItem(new BottomNavigationItem(R.drawable.main_navigation_unsel,"导航"))
                .addItem(new BottomNavigationItem(R.drawable.main_project_unsel,"项目"))
                .addItem(new BottomNavigationItem(R.drawable.main_mine_unsel,"我的"))
                .setActiveColor(R.color.all_bg)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();

        currentFragment = homeFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, homeFragment)
                .show(homeFragment).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                showFragment(homeFragment);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 展示Fragment
     */
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.container, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }
}
//        StringBuffer time = new StringBuffer();
//        time.append("bootTime:"+DevicesTools.bootTime()+"\n");
//        time.append("timeZone:"+DevicesTools.timeZone()+"\n");
//        time.append("languages:"+DevicesTools.languages(this)+"\n");
//        time.append("brightness:"+DevicesTools.brightness(this)+"\n");
//        time.append("batteryStatus:"+DevicesTools.batteryStatus(this)+"\n");
//        time.append("batteryTemp:"+DevicesTools.batteryTemp(this)+"\n");
//        time.append("cpuType:"+DevicesTools.cpuType()+"\n");
//        time.append("basebandVersion:"+DevicesTools.basebandVersion()+"\n");
//        time.append("getLinuxCore:"+DevicesTools.getLinuxCore()+"\n");
//        time.append("getSsid:"+DevicesTools.getSsid(this)+"\n");
//        time.append("getBSsid:"+DevicesTools.getBSsid(this)+"\n");
//        time.append("wifiList:"+DevicesTools.wifiList(this)+"\n");
//        time.append("totalDiskSize:"+DevicesTools.totalDiskSize()+"\n");
//        time.append("availableDiskSize:"+DevicesTools.availableDiskSize()+"\n");
//        time.append("carrierName:"+DevicesTools.carrierName(this)+"\n");
//        time.append("isRooted:"+DevicesTools.isRooted()+"\n");
//        time.append("elapsedRealtime:"+DevicesTools.elapsedRealtime()+"\n");
//        time.append("modelName:"+DevicesTools.modelName()+"\n");
//        time.append("manufacturerName:"+DevicesTools.manufacturerName()+"\n");
//        time.append("systemtVersion:"+DevicesTools.systemtVersion()+"\n");
//        time.append("carrierIpAddress:"+DevicesTools.carrierIpAddress(this)+"\n");
//        time.append("isUsingProxyPort:"+DevicesTools.isUsingProxyPort(this)+"\n");
//        time.append("board:"+DevicesTools.board()+"\n");
//        time.append("batteryPercentage:"+DevicesTools.batteryPercentage(this)+"\n");
//        time.append("nearbyBaseStat:"+DevicesTools.nearbyBaseStat(this)+"\n");
//        time.append("phoneNumber:"+DevicesTools.phoneNumber(this)+"\n");
//        time.append("isSimulatorNew:"+DevicesTools.isSimulatorNew(this)+"\n");
//        time.append("picNum:"+DevicesTools.picNum(this)+"\n");
//        time.append("getRecords:"+DevicesTools.getRecords(getContentResolver())+"\n");
//        time.append("uptimeMillis:"+DevicesTools.uptimeMillis()+"\n");
//        time.append("systemActive:"+DevicesTools.systemActive()+"\n");
//        textView.setText(time.toString());