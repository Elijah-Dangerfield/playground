package com.dangerfield.hiltplayground.util.mtadapter.interfaces;

/**
 * Created by Jonathan Muller on 4/26/17.
 */

public interface Supplier<Output> {
    Output invoke();
}
