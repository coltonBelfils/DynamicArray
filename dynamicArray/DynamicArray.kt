package com.example.colton.applicationname

class DynamicArray<E> {
    private var head = HeadNode<E>()

    private class HeadNode<H> {
        val zero: Node<H> = Node()
        val one: Node<H> = Node()
    }

    class Node<H> {
        var data: H? = null
        lateinit var zero: Node<H>
        lateinit var one: Node<H>

        fun makeChildren() {
            if(!this::zero.isInitialized) {
                this.zero = Node()
            }
            if(!this::one.isInitialized) {
                this.one = Node()
            }
        }
    }

    fun get(index: Int): E? {
        if(index < 0) {
            throw IllegalArgumentException("DynamicArray.get(index: Int): negative Ints not allowed")
        }

        val pos = this.getNode(index)
        if(pos.data == null) {
            return null
            //throw IllegalArgumentException("DynamicArray.get(index: Int): Index: $index does not have a value yet")
        }
        return pos.data
    }

    fun set(index: Int, data: E) {
        if(index < 0) {
            throw IllegalArgumentException("DynamicArray.set(index: Int, data: E): negative Ints not allowed")
        }
        if(data == null) {//this check is for use in Java code
            throw IllegalArgumentException("DynamicArray.set(index: Int, data E): null values not allowed")
        }

        val pos = this.getNode(index)
        pos.data = data
    }

    private fun toBin(dec: Int): Array<Int> {
        if(dec < 0) {
            throw IllegalArgumentException("DynamicArray.toBin(dec: Int): negative Ints not allowed")
        }
        if(dec == 0) {
            return arrayOf(0)
        }
        var d = dec
        var i = 0

        while(d > 0) {
            d /= 2
            i++
        }

        d = dec
        val bin = Array<Int>(i, {n -> 0})
        i = 0

        while(d > 0) {
            bin[i] = d%2
            d /= 2
            i++
        }

        return bin
    }

    private fun getNode(index: Int): Node<E> {
        val binIndex = this.toBin(index)
        lateinit var cur: Node<E>
        if(binIndex[0] == 0) {
            cur = this.head.zero
        } else {
            cur = this.head.one
        }

        for (i in 0 until binIndex.size) {
            cur.makeChildren()
            if(binIndex[i] == 0) {
                cur = cur.zero
            } else {
                cur = cur.one
            }
        }
        return cur
    }

    fun hasData(index: Int): Boolean {
        return this.getNode(index).data != null
    }
}