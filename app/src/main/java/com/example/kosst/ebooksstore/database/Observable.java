package com.example.kosst.ebooksstore.database;

import java.util.ArrayList;

public abstract class Observable<T>
{
	protected final ArrayList<T> mObservers = new ArrayList<T>();

	public void registerObserver( T observer )
	{
		synchronized( mObservers )
		{
			if( observer != null && !mObservers.contains( observer ) )
			{
				mObservers.add( observer );
			}
		}
	}

	public void unregisterObserver( T observer )
	{
		synchronized( mObservers )
		{
			if( observer != null && mObservers.contains( observer ) )
			{
				mObservers.remove( observer );
			}
		}
	}

	public void unregisterAll()
	{
		synchronized( mObservers )
		{
			mObservers.clear();
		}
	}
}
