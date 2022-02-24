package com.tzj.prattparser;

public interface Visitable<T> {
  T accept(Visitor<T> visitor);
}
