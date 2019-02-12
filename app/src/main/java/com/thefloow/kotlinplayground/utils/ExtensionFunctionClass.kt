package com.thefloow.kotlinplayground.utils


/**
 * Created by Rui Vieira on 12/02/2019.
 * The Floow Ltd
 * rui.vieira@thefloow.com
 */

class ExtensionFunctionClass {
    fun doMainStuff1() {

    }

    fun doMainStuff2() {

    }

    fun doMainStuff3() {

    }

    internal fun doSideStuff1() {

    }

    internal fun doSideStuff2() {

    }

    internal fun doSideStuff3() {

    }
}

fun ExtensionFunctionClass.inTransaction(func: () -> Unit) {
    //do side stuff 1
    doSideStuff1()
    try {
        func()
        //do side stuff 2
        doSideStuff2()
    } finally {
        //do side stuff 3
        doSideStuff3()
    }
}