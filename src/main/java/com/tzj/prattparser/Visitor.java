package com.tzj.prattparser;

public interface Visitor<T> {
  T visit(Number number);
  T visit(UnaryExpr unary);
  T visit(BinaryExpr binary);
}
