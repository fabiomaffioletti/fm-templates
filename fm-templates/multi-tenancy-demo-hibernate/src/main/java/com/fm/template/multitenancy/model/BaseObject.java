package com.fm.template.multitenancy.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseObject implements Serializable {    

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();
}
