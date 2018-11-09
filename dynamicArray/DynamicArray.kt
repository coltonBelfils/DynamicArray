package dynamicArray

class DynamicArray<E> {
    private var head = HeadNode<E>()

    private class HeadNode<H> {
        val zero: Node<H> = Node()
        val one: Node<H> = Node()
    }

    class Node<H>() {
        var data: H? = null
        lateinit var zero: Node<H>
        lateinit var one: Node<H>

        constructor(data: H?) : this() {
            this.data = data
        }

        fun makeChildren() {
            if(!this::zero.isInitialized) {
                this.zero = Node()
            }
            if(!this::one.isInitialized) {
                this.one = Node()
            }
        }
    }

    fun get(index: Long): E? {
        if(index < 0) {
            throw IllegalArgumentException("DynamicArray.get(index: Long): negative Longs not allowed")
        }

        val binIndex = this.toBin(index)
        lateinit var cur: Node<E>
        if(binIndex[0] == 0L) {
            cur = this.head.zero
        } else {
            cur = this.head.one
        }

        for (i in 1 until binIndex.size - 1) {
            cur.makeChildren()
            if(binIndex[i] == 0L) {
                cur = cur.zero
            } else {
                cur = cur.one
            }
        }
        return cur.data
    }

    fun set(index: Long, data: E) {
        if(index < 0) {
            throw IllegalArgumentException("DynamicArray.set(index: Long, data: E): negative Longs not allowed")
        }

        val binIndex = this.toBin(index)
        lateinit var cur: Node<E>
        if(binIndex[0] == 0L) {
            cur = this.head.zero
        } else {
            cur = this.head.one
        }

        for (i in 1 until binIndex.size - 1) {
            cur.makeChildren()
            if(binIndex[i] == 0L) {
                cur = cur.zero
            } else {
                cur = cur.one
            }
        }
        cur.data = data
    }

    private fun toBin(dec: Long): Array<Long> {
        if(dec < 0) {
            throw IllegalArgumentException("DynamicArray.toBin(dec: Long): negative Longs not allowed")
        }
        if(dec == 0L) {
            return arrayOf(0)
        }
        var d = dec
        var i = 0

        while(d > 0) {
            d /= 2
            i++
        }

        d = dec
        val bin = Array<Long>(i, {n -> 0})
        i = 0

        while(d > 0) {
            bin[i] = d%2L
            d /= 2
            i++
        }

        return bin
    }
}