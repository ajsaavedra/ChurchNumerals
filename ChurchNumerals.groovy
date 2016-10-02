class ChurchNumerals
{
    def static zero = { func, x -> x }
    def static one = { func, x -> func(x) }
    def static two = { func, x -> func(func(x)) }
    def static three = { func, x -> func(func(func(x))) }

    def static curryOne = { func, x -> func.curry(x) }
    def static curryTwo = { func, x -> func.curry(func.curry(x)) }
    def static curryThree = { func, x -> func.curry(func.curry(func.curry(x))) }

    def static addOne = { x -> x + 1 }
    def static sum = { cnum1, cnum2, func, x -> cnum1(func, cnum2(func, x)) }
    def static subtract = { cnum1, cnum2 -> cnum2(pred, cnum1) }
    def static product = { cnum1, cnum2 -> def c = sum.curry(cnum2); cnum1(c, zero) }
    def static succ = { cnum, func, x -> func(cnum(func, x)) }

    def static right = { a, b -> b }
    def static left = { a, b -> a }
    def static make_pair = { a, b, selector -> selector(a, b) }

    def static f1 = { p -> def r = p.call(right); make_pair.curry(r, succ.curry(r)) }
    def static pred = { cnum -> cnum(f1, make_pair.curry(zero, zero)).call(left) }

    def static cn_num = { cnum -> cnum(addOne, 0) }
    def static num_CN = { num, f, x -> (num == 0) ? x : num_CN.curry(num - 1, f,
                                                            one.curry(f, x).call()).call() }

    static void printNumerals()
    {
        println "Converting Church Numerals to regular numbers:"
        def numerals = [
            cn_num(zero),
            cn_num(one),
            cn_num(two),
            cn_num(three)]
        println numerals
    }

    static void printLambdas()
    {
        println "Converting lambda expressions:"
        def lambdas = [
            cn_num({func, x -> x}),
            cn_num({func, x -> func(func(x))}),
            cn_num({func, x -> func(func(func(func(func(func(x))))))})]
        println lambdas
    }

    static void printSuccessors()
    {
        println "Converting Church Numeral successors:"
        def successors = [
            cn_num(succ.curry(two)),
            cn_num(succ.curry(three)),
            cn_num(succ.curry(sum.curry(three, three)))]
        println successors
    }

    static void printSums()
    {
        println "Converting Church Numeral sums:"
        def sums = [
            cn_num(sum.curry(zero, zero)),
            cn_num(sum.curry(zero, one)),
            cn_num(sum.curry(two, zero)),
            cn_num(sum.curry(zero, three)),
            cn_num(sum.curry(one, three)),
            cn_num(sum.curry(three, two)),
            cn_num(sum.curry(three, three))]
         println sums
    }

    static void printSubtractions()
    {
        println "Subtracting Church Numerals:"
        def subtracted = [
            cn_num(subtract(one, one)),
            cn_num(subtract(two, one)),
            cn_num(subtract(three, one)),
            cn_num(subtract(num_CN.curry(15), three)),
            cn_num(subtract(num_CN.curry(13), num_CN.curry(7)))]
        println subtracted
    }

    static void printPredecessors()
    {
        println "Predecessors of Church Numerals:"
        def predecessors = [
            cn_num(pred(one)),
            cn_num(pred(two)),
            cn_num(pred(three)),
            cn_num(pred(num_CN.curry(4))),
            cn_num(pred(num_CN.curry(5))),
            cn_num(pred(product(curryTwo, num_CN.curry(15))))
        ]
        println predecessors
    }

    static void printProducts()
    {
        println "Product of Church Numerals:"
        def products = [
            cn_num(product(zero, three)),
            cn_num(product(curryOne, one)),
            cn_num(product(curryTwo, two)),
            cn_num(product(curryThree, two)),
            cn_num(product(curryThree, pred(num_CN.curry(5)))),
            cn_num(product({f, x -> f.curry(f.curry(f.curry(f.curry(f.curry(x)))))}, pred(num_CN.curry(5)))),
            cn_num(product(curryTwo, num_CN.curry(30)))]
        println products
    }

    static void printNumConversions()
    {
        println "Converting regular numbers to Church Numerals:"
        def converted = [
            num_CN(3, addOne, 2),
            num_CN(32, addOne, 4),
            cn_num(num_CN.curry(17)),
            cn_num(succ.curry(num_CN.curry(27))),
            cn_num(succ.curry(sum.curry(num_CN.curry(30), num_CN.curry(20))))]
        println converted
    }

    static void main(String[] args)
    {
        printNumerals()
        printLambdas()
        printSuccessors()
        printSums()
        printSubtractions()
        printPredecessors()
        printProducts()
        printNumConversions()
    }
}
