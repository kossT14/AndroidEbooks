package com.example.kosst.ebooksstore.objectmodels;

import android.util.Log;

/**
 * Created by kossT on 17.12.2016.
 */

public class Utils {

    public static void logInfo( String message )
    {

        if( message != null )
        {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String className = stackTrace[3].getClassName();
            String tag = className.substring( className.lastIndexOf( '.' ) + 1 );
            Log.w( DataSourceManager.TAG_LOG_BOOKS, message );
        }

    }

    public static void printStackTrace( Throwable e )
    {

        e.printStackTrace();
    }
}
