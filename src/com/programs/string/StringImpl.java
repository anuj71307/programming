package com.programs.string;

import java.util.Arrays;

/**
 * string class implementation
 */
public final class StringImpl implements  CharSequence {
    private final char[] value;

    public StringImpl(char[] value) {
        this.value = value;
    }

    public StringImpl() {
        value = null;
    }

    public StringImpl(StringImpl org) {
        this.value = org.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringImpl)) return false;
        StringImpl that = (StringImpl) o;
        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        if(value ==null||value.length==0) return 0;

        int result = 1;
        for(char c: value){
            result = result*31+c;
        }

        return  result;
    }

    @Override
    public int length() {
        return value == null? 0: value.length;
    }

    @Override
    public char charAt(int index) {
        if(index<0) throw new StringIndexOutOfBoundsException();
        if (index>=value.length) throw new StringIndexOutOfBoundsException();
        return  value[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return subString(start, end);
    }

    @Override
    public String toString() {
        if(value==null) return null;
        StringBuilder builder = new StringBuilder();
        for(char c: value){
            builder.append(c);
        }

        return  builder.toString();
    }

    public StringImpl subString(int start, int end) {
        if(start<0 || end>value.length || end-start<0) throw  new StringIndexOutOfBoundsException();
        char temp[]= Arrays.copyOfRange(value, start, end);
        return new StringImpl(temp);
    }



}
