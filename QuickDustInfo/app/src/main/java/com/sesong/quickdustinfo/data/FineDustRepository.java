package com.sesong.quickdustinfo.data;

import com.sesong.quickdustinfo.model.FineDust;
import retrofit2.Callback;

public interface FineDustRepository {
    boolean isAvailable();
    void getFineDustData(Callback<FineDust> callback);
}
