package com.mycompany.a2;

import java.util.ArrayList;
import com.mycompany.a2.GameObjects.*;
import com.mycompany.a2.Interfaces.*; 

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

	public IIterator getIterator() {
		return new Iterator();
	}

}