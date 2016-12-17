package com.example.kosst.ebooksstore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.example.kosst.ebooksstore.MyApplication;
import com.example.kosst.ebooksstore.objectmodels.Utils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "knowledge.db";
	private static final int DATABASE_VERSION = 1;

	static DbHelper newInstance( boolean onSdcard )
	{
		Context context = MyApplication.getAppContext();
		String path = null;

		if( onSdcard )
		{
			path = context.getExternalFilesDir( null ).getAbsolutePath() + File.separator + DATABASE_NAME;
		}
		else
		{
			path = DATABASE_NAME;
		}

		return new DbHelper( context, path );
	}

	private DbHelper( Context context, String path )
	{
		super( context, path, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate( SQLiteDatabase db )
	{
		db.beginTransaction();
		try
		{
//			createTable( db, Topic.class, Topic.ID );
//			createTable( db, Knowledge.class, Knowledge.ID );
//			createTable( db, KnowledgeReaction.class, KnowledgeReaction.KNOWLEDGE_ID, KnowledgeReaction.PERSON_ID );
//			createTable( db, KnowledgeTopic.class, KnowledgeTopic.KNOWLEDGE_ID, KnowledgeTopic.TOPIC_ID );
//			createTable( db, KnowledgeKeyIssue.class, KnowledgeKeyIssue.KNOWLEDGE_ID, KnowledgeKeyIssue.KEY_ISSUE_ID );
//			createTable( db, KeyIssue.class, KeyIssue.ID );
//			createTable( db, Profile.class, Profile.ID );
//			createTable( db, TopicExpert.class, TopicExpert.TOPIC_ID, TopicExpert.EXPERT_ID );
//			createTable( db, ExpertFollowedTopics.class, ExpertFollowedTopics.TOPIC_ID,
//					ExpertFollowedTopics.EXPERT_ID );
//			createTable( db, TopicForWheel.class, TopicForWheel.ID, TopicForWheel.RELATES_TO );

			db.setTransactionSuccessful();
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
		}
		finally
		{
			db.endTransaction();
		}
	}

	@Override
	public void onOpen( SQLiteDatabase db )
	{
		super.onOpen( db );

		db.execSQL( "PRAGMA foreign_keys=ON;" );
	}

	@Override
	public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
	{
	}

	void clearAllTables()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();

		try
		{
			Cursor cursor = db.query( "sqlite_master", new String[] { "name" }, "type = ?", new String[] { "table" },
					null, null, null );
			if( cursor != null && !cursor.isClosed() && cursor.moveToFirst() )
			{
				{
					do
					{
						String tableName = cursor.getString( 0 );
						db.delete( tableName, null, null );
					}
					while( cursor.moveToNext() );
				}
				cursor.close();

				db.setTransactionSuccessful();
			}
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
		}
		finally
		{
			db.endTransaction();
		}
	}

	private void dropAllTables( SQLiteDatabase db )
	{
		db.beginTransaction();

		try
		{
			Cursor cursor = db.rawQuery( "SELECT * FROM sqlite_master WHERE type = ?", new String[] { "table" } );
			if( cursor != null && !cursor.isClosed() && cursor.moveToFirst() )
			{
				do
				{
					String tableName = cursor.getString( cursor.getColumnIndex( "name" ) );
					db.execSQL( "DROP TABLE IF EXISTS " + tableName );

				}
				while( cursor.moveToNext() );
			}

			db.setTransactionSuccessful();
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
		}
		finally
		{
			db.endTransaction();
		}
	}

	private static void createTable( SQLiteDatabase db, Class<? extends BaseColumns> tableClass,
			String... uniqueColumns )
	{
		try
		{
			String tableName = null;

			Field[] fields = tableClass.getFields();
			ArrayList<String> fieldsArray = new ArrayList<String>();

			fieldsArray.add( BaseColumns._ID + " INTEGER PRIMARY KEY" );

			for( Field field : fields )
			{
				Class<?> fieldClass = field.getDeclaringClass();
				if( fieldClass == tableClass )
				{
					String fieldValue = field.get( null ).toString();
					if( TextUtils.equals( field.getName(), "TABLE_NAME" ) )
					{
						tableName = fieldValue;
					}
					else
					{
						fieldValue += " TEXT";

						Annotation[] annotations = field.getAnnotations();
						if( annotations.length > 0 )
						{
							for( Annotation annotation : annotations )
							{
//								if( annotation instanceof ForeignKey )
//								{
//									ForeignKey foreignKey = (ForeignKey) annotation;
//									String table = foreignKey.table();
//									String column = foreignKey.column();
//									StringBuilder sbFieldValue = new StringBuilder( " REFERENCES " ).append( table )
//											.append( "(" ).append( column ).append( ")" );
//									boolean onDeleteCascade = foreignKey.onDeleteCascade();
//									if( onDeleteCascade )
//									{
//										sbFieldValue.append( " ON DELETE CASCADE" );
//									}
//
//									fieldValue += sbFieldValue.toString();
//									break;
//								}
							}
						}
						fieldsArray.add( fieldValue );
					}
				}
			}

			StringBuilder sb = new StringBuilder( "CREATE TABLE IF NOT EXISTS " ).append( tableName ).append( " (" )
					.append( Joiner.on( ", " ).join( fieldsArray ) );
			if( uniqueColumns != null )
			{
				sb.append( ", UNIQUE (" ).append( Joiner.on( ", " ).join( uniqueColumns ) ).append( ')' ).toString();
			}
			sb.append( ")" );

			db.execSQL( sb.toString() );
		}
		catch( Exception e )
		{
			Utils.printStackTrace( e );
		}
	}

	// ///////////////////////////// UTILS //////////////////////////////
	public static ArrayList<String> getColumnArrayList( Cursor cursor, String columnName )
	{
		ArrayList<String> records = new ArrayList<String>( cursor.getCount() );

		if( cursor != null && !cursor.isClosed() )
		{
			int initialPosition = cursor.getPosition();
			if( cursor.moveToFirst() )
			{
				int columnIndex = cursor.getColumnIndex( columnName );

				do
				{
					String record = cursor.getString( columnIndex );
					if( !TextUtils.isEmpty( record ) )
					{
						records.add( record );
					}
				}
				while( cursor.moveToNext() );
			}
			cursor.moveToPosition( initialPosition );
		}
		return records;
	}

	public static ContentValues getRecord( Cursor cursor )
	{
		ContentValues record = new ContentValues();

		if( cursor != null && !cursor.isClosed() )
		{
			for( int i = 0; i < cursor.getColumnCount(); i++ )
			{
				String key = cursor.getColumnName( i );
				String value = cursor.getString( i );
				record.put( key, value );
			}
		}

		return record;
	}

	public static ArrayList<ContentValues> getRecords( Cursor cursor )
	{
		ArrayList<ContentValues> records = new ArrayList<>();

		if( cursor != null && !cursor.isClosed() && cursor.moveToFirst() )
		{
			do
			{
				ContentValues record = new ContentValues();
				for( int i = 0; i < cursor.getColumnCount(); i++ )
				{
					String key = cursor.getColumnName( i );
					String value = cursor.getString( i );
					record.put( key, value );
				}
				records.add( record );
			}
			while( cursor.moveToNext() );

		}

		return records;
	}

	public static void textFilterSelection( String textFilter, StringBuilder sbSelection,
			ArrayList<String> arSelectionArgs, String... columnNames )
	{
		if( !TextUtils.isEmpty( textFilter ) )
		{
			Iterable<String> words = Splitter.onPattern( "[- ]+" ).omitEmptyStrings().split( textFilter );
			ArrayList<String> arSelection = new ArrayList<String>();

			for( int i = 0; i < columnNames.length; i++ )
			{
				ArrayList<String> arColumnSearch = new ArrayList<String>();
				for( String word : words )
				{
					String arg = '%' + word + '%';
					arColumnSearch.add( columnNames[i] + " LIKE ?" );
					arSelectionArgs.add( arg );
				}
				StringBuilder sb = new StringBuilder( "(" ).append( Joiner.on( " AND " ).join( arColumnSearch ) )
						.append( ")" );
				arSelection.add( sb.toString() );
			}

			if( sbSelection.length() > 0 )
			{
				sbSelection.append( " AND " );
			}
			sbSelection.append( "(" ).append( Joiner.on( " OR " ).join( arSelection ) ).append( ")" );
		}
	}

	public static class Tables
	{
		private StringBuilder mBuilder;

		private Tables()
		{
		}

		public static Tables from( String tableName )
		{
			Tables join = new Tables();
			join.mBuilder = new StringBuilder( "(" ).append( tableName ).append( ")" );
			return join;
		}

		public Tables alias( String name )
		{
			mBuilder.append( name );
			return this;
		}

		public Tables join( String tableName )
		{
			mBuilder.append( " JOIN " ).append( "(" ).append( tableName ).append( ")" );
			return this;
		}

		public Tables leftJoin( String tableName )
		{
			mBuilder.append( " LEFT JOIN " ).append( "(" ).append( tableName ).append( ")" );
			return this;
		}

		public Tables on( String field1, String field2 )
		{
			mBuilder.append( " ON " ).append( field1 ).append( " = " ).append( field2 );
			return this;
		}

		public Tables append( String text )
		{
			mBuilder.append( text );
			return this;
		}

		@Override
		public String toString()
		{
			return mBuilder.toString();
		}
	}

}