package com.dangerfield.hiltplayground.util.mtadapter.interfaces;

public interface Function2<Input1, Input2, Output> {
    Output invoke(Input1 input1, Input2 input2);
}