package com.example.kosst.ebooksstore.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;

import  com.example.kosst.ebooksstore.database.Observable;
import com.example.kosst.ebooksstore.objectmodels.Utils;

import com.example.kosst.ebooksstore.database.DbProvider.DBObserver;

public class DbProvider extends Observable<DBObserver>
{
	private static final boolean ON_SDCARD = true;

	private com.example.kosst.ebooksstore.database.DbHelper mHelper;

	private static final class Holder
	{
		private static final DbProvider sInstance = new DbProvider();
	}

	public static DbProvider getInstance()
	{
		return Holder.sInstance;
	}

	private DbProvider()
	{
		mHelper = com.example.kosst.ebooksstore.database.DbHelper.newInstance( ON_SDCARD );
	}

	public Cursor query( boolean distinct, String tables, String[] projection, String selection,
			String[] selectionArgs, String groupBy, String having, String sortOrder, String limit )
	{
		try
		{
			SQLiteDatabase db = mHelper.getReadableDatabase();
			SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
			qb.setDistinct( distinct );
			qb.setTables( tables );

			Utils.logInfo( "query on " + tables );

			return qb.query( db, projection, selection, selectionArgs, groupBy, having, sortOrder, limit );
		}
		catch( SQLiteException e )
		{
			Utils.printStackTrace( e );
			return null;
		}
	}

	public Cursor query( String query, String[] selectionArgs )
	{
		try
		{
			return mHelper.getReadableDatabase().rawQuery( query, selectionArgs );
		}
		catch( SQLiteException e )
		{
			Utils.printStackTrace( e );
			return null;
		}

	}

	public long insert( String tableName, ContentValues record )
	{
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.beginTransaction();

		try
		{
			long result = db.insertWithOnConflict( tableName, BaseColumns._ID, record, SQLiteDatabase.CONFLICT_REPLACE );

			db.setTransactionSuccessful();

			Utils.logInfo( "record inserted into " + tableName );

			if( result > -1 )
			{
				notifyAllObservers( tableName );
			}

			return result;
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
			return -1;
		}
		finally
		{
			db.endTransaction();
		}
	}

	public int bulkInsert( String tableName, Iterable<ContentValues> records )
	{
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.beginTransaction();

		try
		{
			int count = 0;

			for( ContentValues record : records )
			{
				try
				{
					long result = db.insertWithOnConflict( tableName, BaseColumns._ID, record,
							SQLiteDatabase.CONFLICT_REPLACE );
					if( result > -1 )
					{
						++count;
					}
				}
				catch( Exception e )
				{
					Utils.printStackTrace( e );
				}
			}

			db.setTransactionSuccessful();

			Utils.logInfo( count + " records inserted into " + tableName );

			if( count > 0 )
			{
				notifyAllObservers( tableName );
			}

			return count;
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
			return 0;
		}
		finally
		{
			db.endTransaction();
		}
	}

	public int bulkInsertOrUpdate( String tableName, Iterable<ContentValues> records, String columnName )
	{
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.beginTransaction();

		try
		{
			int count = 0;

			for( ContentValues record : records )
			{
				try
				{
					long result = db.insertOrThrow( tableName, BaseColumns._ID, record );
					if( result > -1 )
					{
						++count;
					}
				}
				catch( SQLiteException e )
				{
					try
					{
						String value = record.getAsString( columnName );
						Utils.logInfo( columnName + " = " + value + " already exists! Updating..." );
						db.update( tableName, record, columnName + " = ?", new String[] { value } );
					}
					catch( Exception e1 )
					{
						Utils.printStackTrace( e1 );
					}
				}
				catch( Exception e )
				{
					Utils.printStackTrace( e );
				}
			}

			db.setTransactionSuccessful();

			Utils.logInfo( count + " records inserted into " + tableName );

			if( count > 0 )
			{
				notifyAllObservers( tableName );
			}

			return count;
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
			return 0;
		}
		finally
		{
			db.endTransaction();
		}
	}

	public int update( String tableName, ContentValues values, String selection, String[] selectionArgs )
	{
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.beginTransaction();

		try
		{
			int count = db.update( tableName, values, selection, selectionArgs );

			db.setTransactionSuccessful();

			Utils.logInfo( count + " records updated in " + tableName );

			if( count > 0 )
			{
				notifyAllObservers( tableName );
			}

			return count;
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
			return 0;
		}
		finally
		{
			db.endTransaction();
		}
	}

	public int delete( String tableName, String selection, String[] selectionArgs )
	{
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.beginTransaction();

		try
		{
			int count = db.delete( tableName, selection == null ? "1" : selection, selectionArgs );

			db.setTransactionSuccessful();

			Utils.logInfo( count + " records deleted from " + tableName );

			if( count > 0 )
			{
				notifyAllObservers( tableName );
			}

			return count;
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
			return 0;
		}
		finally
		{
			db.endTransaction();
		}
	}

	public static String buildUnionQuery( boolean distinct, String[] subQueries, String sortOrder, String limit )
	{
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		builder.setDistinct( distinct );

		return builder.buildUnionQuery( subQueries, sortOrder, limit );
	}

	public void clear()
	{
		mHelper.clearAllTables();
	}

	public void notifyAllObservers( String tableName )
	{
		synchronized( this )
		{
			for( DBObserver observer : mObservers )
			{
				observer.reload( tableName );
			}
		}
	}

	public static interface DBObserver
	{
		public void reload( String tableName );
	}
}
