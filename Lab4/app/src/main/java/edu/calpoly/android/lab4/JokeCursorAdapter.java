package edu.calpoly.android.lab4;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import edu.calpoly.android.lab4.JokeView.OnJokeChangeListener;

/**
 * Class that functions similarly to JokeListAdapter, but instead uses a Cursor.
 * A Cursor is a list of rows from a database that acts as a medium between the
 * database and a ViewGroup (in this case, a SQLite database table containing rows
 * of jokes and a ListView containing JokeViews).
 */
public class JokeCursorAdapter extends android.support.v4.widget.CursorAdapter {

	/** The OnJokeChangeListener that should be connected to each of the
	 * JokeViews created/managed by this Adapter. */
	private OnJokeChangeListener m_listener;

	/**
	 * Parameterized constructor that takes in the application Context in which
	 * it is being used and the Collection of Joke objects to which it is bound.
	 * 
	 * @param context
	 *            The application Context in which this JokeListAdapter is being
	 *            used.
	 * 
	 * @param jokeCursor
	 *            A Database Cursor containing a result set of Jokes which
	 *            should be bound to JokeViews.
	 *            
	 * @param flags
	 * 			  A list of flags that decide this adapter's behavior.
	 */
	public JokeCursorAdapter(Context context, Cursor jokeCursor, int flags) {
		super(context, jokeCursor, flags);
	}

	/**
	 * Bind an existing view to the data pointed to by cursor.
	 * @param view
	 * @param context
	 * @param cursor
     */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		Joke newJoke = new Joke(cursor.getString(JokeTable.JOKE_COL_TEXT),
									cursor.getString(JokeTable.JOKE_COL_AUTHOR),
										cursor.getInt(JokeTable.JOKE_COL_RATING),
											cursor.getLong(JokeTable.JOKE_COL_ID));

		JokeView jv = (JokeView)view;

		jv.setOnJokeChangeListener(null);
		jv.setJoke(newJoke);
		jv.setOnJokeChangeListener(m_listener);
	}

	/**
	 * Makes a new view to hold the data pointed to by cursor.
	 * @param context
	 * @param cursor
	 * @param parent
     * @return jv, the new JokeView object.
     */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		Joke newJoke = new Joke(cursor.getString(JokeTable.JOKE_COL_TEXT),
									cursor.getString(JokeTable.JOKE_COL_AUTHOR),
										cursor.getInt(JokeTable.JOKE_COL_RATING),
											cursor.getLong(JokeTable.JOKE_COL_ID));

		JokeView jv = new JokeView(context, newJoke);

		/*jv.setOnJokeChangeListener(null);
		jv.setJoke(newJoke);
		jv.setOnJokeChangeListener(m_listener);*/

		return jv;
	}

	/**
	 * Mutator method for changing the OnJokeChangeListener.
	 * 
	 * @param mListener
	 *            The OnJokeChangeListener that will be notified when the
	 *            internal state of any Joke contained in one of this Adapters
	 *            JokeViews is changed.
	 */
	public void setOnJokeChangeListener(OnJokeChangeListener mListener) {
		this.m_listener = mListener;
	}
}