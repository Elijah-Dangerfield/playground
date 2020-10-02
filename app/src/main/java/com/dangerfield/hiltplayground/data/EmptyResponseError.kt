package com.dangerfield.hiltplayground.data

import java.lang.Exception

class EmptyResponseError() : Exception("No results found") {
}