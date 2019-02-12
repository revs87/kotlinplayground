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

    fun doMainStuff4() {

    }

    internal fun doSideStuff1() {

    }

    internal fun doSideStuff2() {

    }

    internal fun doSideStuff3() {

    }
}

fun ExtensionFunctionClass.inTransaction1(func: () -> Unit) {
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

fun ExtensionFunctionClass.inTransaction2(func: (ExtensionFunctionClass) -> Unit) {
    //do side stuff 1
    doSideStuff1()
    try {
        func(this)
        //do side stuff 2
        doSideStuff2()
    } finally {
        //do side stuff 3
        doSideStuff3()
    }
}

fun ExtensionFunctionClass.inTransaction3(func: ExtensionFunctionClass.() -> Unit) {
    //do side stuff 1
    doSideStuff1()
    try {
        this.func()
        //do side stuff 2
        doSideStuff2()
    } finally {
        //do side stuff 3
        doSideStuff3()
    }
}

inline fun ExtensionFunctionClass.inTransaction4(crossinline func: ExtensionFunctionClass.() -> Unit) {
    inTransaction3 {
        func()
    }
}

