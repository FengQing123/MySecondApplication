package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.example.jiaw2.mysecondapplication.AppContext;
import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.baidu.LocationUtil;
import com.example.jiaw2.mysecondapplication.util.UIHelper;

/**
 * Created by jiaw2 on 2016/12/5.
 */
public class BaiduLocationActivity extends Activity implements View.OnClickListener {
    private static final String TAG = BaiduLocationActivity.class.getSimpleName();
    private static TextView LocationResult;
    private LocationUtil locationUtil;
    private Button btn_startLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baidulocation_layout);
        LocationResult = (TextView) findViewById(R.id.LocationResult);
        LocationResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_startLocation = (Button) findViewById(R.id.btn_startLocation);

        // -----------location config ------------
        locationUtil = AppContext.getInstance().locationUtil;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationUtil.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationUtil.setLocationOption(locationUtil.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationUtil.setLocationOption(locationUtil.getOption());
        }
    }

    @Override
    protected void onDestroy() {
//        locationUtil.unregisterListener(mListener); //注销掉监听
//        locationUtil.stop(); //停止定位服务
        super.onDestroy();
    }

    /**
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    public static BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nerror code : ");
                sb.append(location.getLocType());
                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());
                sb.append("\nradius : ");
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");
                sb.append(location.getCityCode());
                sb.append("\ncity : ");
                sb.append(location.getCity());
                sb.append("\nDistrict : ");
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");
                sb.append(location.getStreet());
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\nDescribe: ");
                sb.append(location.getLocationDescribe());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());
                sb.append("\nPoi: ");
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    // 运营商信息
                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                logMsg(sb.toString(), location.getSpeed());
                startOrStopTrack(location.getSpeed());
            }
        }
    };

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public static void logMsg(String str, float speed) {
        try {
            if (LocationResult != null) {
                LocationResult.setText(str);
                Log.e(TAG, str);
                UIHelper.ToastMessage(AppContext.getInstance(), "定位中...速度=" + String.valueOf(speed));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startOrStopTrack(float speed) {
        if (speed > 10) {
            //启动轨迹上传功能
            Log.e(TAG, "启动轨迹上传功能");
        } else {
            //关闭轨迹上传功能
            Log.e(TAG, "关闭轨迹上传功能");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startLocation:
                LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    UIHelper.ToastMessage(BaiduLocationActivity.this, "请打开手机定位功能");
                    // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
                }
                if (btn_startLocation.getText().toString().equals(getString(R.string.startlocation))) {
                    locationUtil.start();// 定位SDK
                    // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
                    btn_startLocation.setText(getString(R.string.stoplocation));
                } else {
                    locationUtil.stop();
                    btn_startLocation.setText(getString(R.string.startlocation));
                }
                break;
        }
    }
}
