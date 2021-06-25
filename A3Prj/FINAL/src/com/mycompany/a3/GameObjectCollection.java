package com.mycompany.a3;

import java.util.ArrayList;
import com.mycompany.a3.GameObjects.*;
import com.mycompany.a3.Interfaces.*;

public class GameObjectCollection implements ICollection {

	private ArrayList<GameObject> list  = new ArrayList<GameObject>();

//-----------------------
	public class Iterator implements IIterator {
		private int currElementIndex;

		public Iterator() {
			currElementIndex = -1;
		}

		public boolean hasNext() {
			if (list.size ( ) <= 0) return false;
			if (currElementIndex == list.size() - 1 )
				return false;
			return true;
		}

		public Object getNext ( ) {
			currElementIndex ++ ;
			return list.get(currElementIndex);
		}
	}
//-----------------------

	public void add(Object object) {
		list.add((GameObject)object);
	}

	public void remove(Object object) {
		list.remove((GameObject)object);
	}

	@Override
	public IIterator getIterator() {
		return new Iterator();
	}

	public GameObject get(int i) {
		return list.get(i);
	}

}
