package com.dangerfield.hiltplayground.util.mtadapter.interfaces;

public interface TriConsumer<Input1, Input2, Input3> {
    void invoke(final Input1 input1, final Input2 input2, final Input3 input3);
}
