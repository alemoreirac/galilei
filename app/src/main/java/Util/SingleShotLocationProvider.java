package Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Pefoce on 04/05/2018.
 */

public class SingleShotLocationProvider
{
    public static LocationManager locationManager = null;
    public static LocationListener localListener= null;

    public static interface LocationCallback
    {
        public void onNewLocationAvailable(Double latitude,Double longitude);
    }

    public static void cancelUpdate()
    {
        if(locationManager!=null)
        locationManager.removeUpdates(localListener);
    }

    // calls back to calling thread, note this is for low grain: if you want higher precision, swap the
    // contents of the else and if. Also be sure to check gps permission/settings are allowed.
    // call usually takes <10ms
    public static void requestSingleUpdate(final Context context, final LocationCallback callback)
    {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        {
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (isGPSEnabled)
            {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions((Activity)context,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                localListener =  new LocationListener()

                {
                    @Override
                    public void onLocationChanged(Location location)
                    {
                        callback.onNewLocationAvailable(location.getLatitude(), location.getLongitude());
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras)
                    {
                    }

                    @Override
                    public void onProviderEnabled(String provider)
                    {
                    }

                    @Override
                    public void onProviderDisabled(String provider)
                    {
                    }
                };

                locationManager.requestSingleUpdate(criteria,localListener, null);
            }
        }
    }
}