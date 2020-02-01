package com.google.androidtesting.Repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetDatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "book.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final int NEEDED_MEGABYTE_MEMORY = 200;
    private static final long ONE_MEGABYTE = 1048576;
    private static AssetDatabaseOpenHelper helper;

    /**
     * Class constructor. Construct the base class, check the database existence,
     * copy it to DATABASE_APP_PATH location if not exist.
     */
    private AssetDatabaseOpenHelper(Context context, String DBPath) {
        super(context, DBPath + "/" + DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static AssetDatabaseOpenHelper newInstance(Context context) {
        if (helper == null) {
            String databasePath = databasePath(context);
            if (databasePath != null) {
                helper = new AssetDatabaseOpenHelper(context, databasePath);
                return helper;
            }
        }
        return helper;
    }


    /**
     * Check if database copied and saved in user device or not .
     *
     * @param context The Activity context.
     * @return boolean True if database exists or false if not exists.
     */
    public static boolean iaDatabaseExists(Context context) {
        // get database path or null if database not exist
        File internalPathFolder = new File(internalDatabasePath(context));
        File databaseFile = new File(internalPathFolder.toString(), DATABASE_NAME);
        Log.e("TAG_PATH", "internal : " + databaseFile.getPath());
        if (internalPathFolder.exists() && databaseFile.exists()) {
            // database exists in internal storage
            return true;
        }

        // check if database exists in external storage
        File externalPathFolder = new File(externalDatabasePath(context));
        databaseFile = new File(externalPathFolder.toString(), DATABASE_NAME);
        Log.e("TAG_PATH", "external : " + databaseFile.getPath());
        if (externalPathFolder.exists() && databaseFile.exists()) {
            // database exists in external storage
            return true;
        }
        // database not exists in internal or external storage
        return false;

    }

    /**
     * Static method. Check the database exist or not
     *
     * @return database state
     */
    private static String databasePath(Context context) {
        // get database path or null if database not exist
        File internalPath = new File(internalDatabasePath(context));
        if (internalPath.exists()) {
            // database exists in internal storage
            return internalPath.toString();
        }

        // check if database exists in external storage
        File externalPath = new File(externalDatabasePath(context));
        if (externalPath.exists()) {
            // database exists in external storage
            return externalPath.toString();
        }
        // database not exists in internal or external storage
        return null;
    }


    /**
     * get internal database path.
     *
     * @param context the context that called this method.
     * @return String (Path) of database.
     */
    private static String internalDatabasePath(Context context) {
        return context.getFilesDir().getPath() + "/databases";
    }


    /**
     * get External database path.
     *
     * @param context the context that called this method.
     * @return String (Path) of database.
     */
    private static String externalDatabasePath(Context context) {
        return Environment.getExternalStorageDirectory().getPath() + "/" + context.getPackageName() + "/files";
    }


    /**
     * Static method. Copy the database from application asset to the final location.
     */
    public static void copyDataBase(Context context) throws IOException {
        String path = initializeDBLocation(context);
        if (path == null) return;
        File filePath = new File(path);
        if (!filePath.exists()) {
            // file path not exist. create file.
            filePath.mkdirs();
        }

        // start copy database in storage.
        String databasePath = (path + "/" + DATABASE_NAME);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(databasePath));
        InputStream inputStream = context.getAssets().open(DATABASE_NAME);
        byte[] buffer = new byte[3072];

        while (inputStream.read(buffer) > 0) {
            outputStream.write(buffer);
        }

        // flush data and close connection.
        outputStream.close();
        inputStream.close();

    }


    /**
     * Static method. Get the available storage size for the internal storage.
     *
     * @return the available storage size in MB.
     */
    private static long getAvaliableStorageInternal() {

        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long bytesAvailable = (long) stat.getFreeBlocks() * (long) stat.getBlockSize();
        return bytesAvailable / ONE_MEGABYTE;
    }

    /**
     * Static method. Get the available storage size for the external storage.
     *
     * @return the available storage size in MB.
     */
    private static long getAvaliableStorageExternal() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable = (long) stat.getFreeBlocks() * (long) stat.getBlockSize();
        return bytesAvailable / ONE_MEGABYTE;
    }


    /**
     * Static method. Initialize the location of the database file.
     *
     * @return the true if the database is ready or there is enough storage, otherwise it returns false.
     */
    public static String initializeDBLocation(Context context) {
        long availableInternal = getAvaliableStorageInternal();
        long availableExternal = getAvaliableStorageExternal();

        if (availableInternal < NEEDED_MEGABYTE_MEMORY) {
            if (availableExternal > NEEDED_MEGABYTE_MEMORY && canWriteInSdCard()) {
                // have space in external storage , app can write in user SD card.
                return externalDatabasePath(context);
            }
            // app cannot write in external storage.
            return null;
        }
        // have space in internal storage ( Copy database in internal storage).
        return internalDatabasePath(context);
    }


    /**
     * onCreate database.
     *
     * @param db Database object.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * upgrade database .
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * check if app can read and write in Device Storage.
     *
     * @return true if app can write in storage , else return false.
     */
    public static boolean canWriteInSdCard() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equalsIgnoreCase(state);
    }
}

