package com.sampleapplication.deviceinformation;


import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceInformationFragment extends Fragment {

    @BindView(R.id.device_name_view)
    ListItemsView mDeviceName;
    @BindView(R.id.android_version_view)
    ListItemsView mViewAndroidVersion;
    @BindView(R.id.manufacturer_view)
    ListItemsView mViewManufacturerView;
    @BindView(R.id.model_view)
    ListItemsView mModelView;
    @BindView(R.id.build_version_view)
    ListItemsView mBuildVersionView;
    @BindView(R.id.board_view)
    ListItemsView mBoardView;
    @BindView(R.id.product_view)
    ListItemsView mProductView;
    @BindView(R.id.ble_supported_view)
    ListItemsView mViewBLESupported;
    @BindView(R.id.native_hd_supported_view)
    ListItemsView mViewNativeHDSupported;
    @BindView(R.id.lollipop_scanner_supported_view)
    ListItemsView mViewLollipopScannerSupported;
    @BindView(R.id.offloaded_filtering_supported_view)
    ListItemsView mViewOffloadedFilteringSupported;
    @BindView(R.id.offloaded_scan_batching_view)
    ListItemsView mViewOffloadedScanBatching;
    @BindView(R.id.peripheral_mode_supported_view)
    ListItemsView mViewPeripheralModeSupported;
    @BindView(R.id.multiple_advertisement_supported_view)
    ListItemsView mViewMultipleAdvSupported;
    @BindView(R.id.resolution_view)
    ListItemsView mViewResolution;
    @BindView(R.id.dimen_in_pixel_view)
    ListItemsView mViewDimensInPixel;
    @BindView(R.id.dimen_in_dip_view)
    ListItemsView mViewDimensInDIP;
    @BindView(R.id.size_view)
    ListItemsView mViewSize;
    @BindView(R.id.aspect_view)
    ListItemsView mViewAspect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        setHeaderTextInView();
        setInfoTextInView();

        return view;

    }

    private void setInfoTextInView() {

        // finds device name
        String deviceName = Build.DEVICE;
        mDeviceName.setInfoTextView(deviceName);

        //finds version number
        String version = Build.VERSION.RELEASE;
        mViewAndroidVersion.setInfoTextView(version);

        //finds manufacturer
        String manufacturer = Build.MANUFACTURER;
        mViewManufacturerView.setInfoTextView(manufacturer);

        // find model
        String model = Build.MODEL;
        mModelView.setInfoTextView(model);

        String buildVersion = Build.DISPLAY;
        mBuildVersionView.setInfoTextView(buildVersion);

        //find board
        String board = Build.BOARD;
        mBoardView.setInfoTextView(board);

        //find product
        String product = Build.PRODUCT;
        mProductView.setInfoTextView(product);

        // checks if BLE is supported
        boolean isBLESupported = getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
        if (isBLESupported) {
            mViewBLESupported.setInfoTextView("YES");
            mViewBLESupported.setInfoTextColor(true);
        }else {
            mViewBLESupported.setInfoTextView("NO");
            mViewBLESupported.setInfoTextColor(false);
        }

        // check if multiple advertisement supported
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            boolean isMultipleAdvertisementSupported = bluetoothAdapter.isMultipleAdvertisementSupported();
            if (isMultipleAdvertisementSupported) {
                mViewMultipleAdvSupported.setInfoTextView("YES");
                mViewMultipleAdvSupported.setInfoTextColor(true);
            }else {
                mViewMultipleAdvSupported.setInfoTextView("NO");
                mViewMultipleAdvSupported.setInfoTextColor(false);
            }
        }else {
            mViewMultipleAdvSupported.setInfoTextView("NO");
            mViewMultipleAdvSupported.setInfoTextColor(false);
        }

        // check if offloaded filtering supported
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            boolean isOffLoadedFilteringSupported = bluetoothAdapter.isOffloadedFilteringSupported();
            if (isOffLoadedFilteringSupported) {
                mViewOffloadedFilteringSupported.setInfoTextView("YES");
                mViewOffloadedFilteringSupported.setInfoTextColor(true);
            }else {
                mViewOffloadedFilteringSupported.setInfoTextView("NO");
                mViewOffloadedFilteringSupported.setInfoTextColor(false);
            }
        }else {
            mViewOffloadedFilteringSupported.setInfoTextView("NO");
            mViewOffloadedFilteringSupported.setInfoTextColor(false);
        }

        //check if offloaded scan batching is supported
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           boolean isOffloadedScanSupported = bluetoothAdapter.isOffloadedScanBatchingSupported();
            if (isOffloadedScanSupported) {
                mViewOffloadedScanBatching.setInfoTextView("YES");
                mViewOffloadedScanBatching.setInfoTextColor(true);
            }else {
                mViewOffloadedScanBatching.setInfoTextView("NO");
                mViewOffloadedScanBatching.setInfoTextColor(false);
            }
        }else {
            mViewOffloadedScanBatching.setInfoTextView("NO");
            mViewOffloadedScanBatching.setInfoTextColor(false);
        }

        // finds screen density
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int density = displayMetrics.densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                mViewResolution.setInfoTextView("LDPI");
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                mViewResolution.setInfoTextView("MDPI");
                break;
            case DisplayMetrics.DENSITY_HIGH:
                mViewResolution.setInfoTextView("HDPI");
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                mViewResolution.setInfoTextView("XHDPI");
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                mViewResolution.setInfoTextView("XXHDPI");
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                mViewResolution.setInfoTextView("XXXHDPI");
                break;
        }

        // calculates screen dimension in pixel
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        mViewDimensInPixel.setInfoTextView(""+width+"X"+height);

        // calculates screen dimension in dip
        int widthdip = Math.round(displayMetrics.widthPixels/displayMetrics.density);
        int heightdip = Math.round(displayMetrics.heightPixels/displayMetrics.density);
        mViewDimensInDIP.setInfoTextView(""+widthdip+"X"+heightdip);

        // find screen configuration
        int screenlayout = getActivity().getResources().getConfiguration().screenLayout;
        screenlayout &= Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenlayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                mViewSize.setInfoTextView("Small");
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                mViewSize.setInfoTextView("Normal");
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                mViewSize.setInfoTextView("Large");
                break;
        }

        switch (screenlayout & Configuration.SCREENLAYOUT_LONG_MASK) {
            case Configuration.SCREENLAYOUT_LONG_YES:
                mViewAspect.setInfoTextView("Long");
                break;
            case Configuration.SCREENLAYOUT_LONG_NO:
                mViewAspect.setInfoTextView("Not Long");
                break;
            case Configuration.SCREENLAYOUT_LONG_UNDEFINED:
                mViewAspect.setInfoTextView("Undefined");
                break;
        }


    }

    private void setHeaderTextInView() {
        mDeviceName.setInfoHeadingText("Device Name");
        mViewAndroidVersion.setInfoHeadingText("Android Version");
        mViewManufacturerView.setInfoHeadingText("Manufacturer");
        mModelView.setInfoHeadingText("Model");
        mBuildVersionView.setInfoHeadingText("Build Version");
        mBoardView.setInfoHeadingText("Board");
        mProductView.setInfoHeadingText("Product");
        mViewBLESupported.setInfoHeadingText("Bluetooth Low Energy Supported");
        mViewNativeHDSupported.setInfoHeadingText("Native HID Supported");
        mViewLollipopScannerSupported.setInfoHeadingText("Lollipop Scanner API Supported");
        mViewOffloadedFilteringSupported.setInfoHeadingText("Offloading Filtering Supported");
        mViewOffloadedScanBatching.setInfoHeadingText("Offloading scan batching supported");
        mViewPeripheralModeSupported.setInfoHeadingText("Peripheral Mode Supported");
        mViewMultipleAdvSupported.setInfoHeadingText("Multiple Advertisement Supported");
        mViewResolution.setInfoHeadingText("Resolution");
        mViewDimensInPixel.setInfoHeadingText("Dimension(px)");
        mViewDimensInDIP.setInfoHeadingText("Dimension(dip)");
        mViewSize.setInfoHeadingText("Size");
        mViewAspect.setInfoHeadingText("Aspect");

    }
}
