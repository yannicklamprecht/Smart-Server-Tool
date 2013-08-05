package com.github.ysl3000.Utils;
public class StringArray {

	private String[] array;
	private int start;
	private int end;

	public StringArray(String[] array) {
		this.array = array;
	}

	public StringArray subStringArray(int start, int end) {
		this.start = start - 1;
		this.end = end;
		String[] subString = new String[this.end - this.start];

		for (int i = this.start; i < this.end; i++) {
			subString[i - this.start] = this.array[i];
		}
		this.array = subString;
		return new StringArray(array);
	}

	public StringArray subStringArray(int value, boolean isEndValue) {
		return (isEndValue) ? this.subStringArray(0, end) : this
				.subStringArray(start, array.length);
	}

	@Override
	public String toString() {
		String toString = "";
		for (String s : this.array) {
			toString= toString.concat(s+" ");
		}
		return toString;
	}
	
	public String[] toArray(){
		return this.array;
	}

}
