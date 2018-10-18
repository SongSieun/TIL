package com.sesong.quickdustinfo.data;

import com.sesong.quickdustinfo.util.FineDustUtil;
import retrofit2.Callback;

public class LocationFineDustRepository implements FineDustRepository {
    private FineDustUtil mFineDustUtil;
    private double mLatitude;
    private double mLongitude;

    public LocationFineDustRepository() {
        mFineDustUtil = new FineDustUtil();
    }

    public LocationFineDustRepository(double lat, double lng) {
        this();
        mLatitude = lat;
        mLongitude = lng;
    }

    @Override
    public void getFineDustData(Callback callback) {
        mFineDustUtil.getApi().getFineDust(mLatitude, mLongitude).enqueue(callback);
    }

    @Override
    public boolean isAvailable() {
        if (mLatitude != 0.0 && mLongitude != 0.0) {
            return true;
        }
        return false;
    }
}