(ns prime-numbers.test.core
  (:use [clojure.test])
  (:use midje.sweet)
 )

 ;; (primes 1) -> (2)
 ;; (primes 2) -> (2 3)

(defn prime? [prev n]
	(if (empty? prev) true
  (and (prime? (rest prev) n) (not (zero? (rem n (first prev))))))
)

(fact (prime? [] 2) => true)
(fact (prime? [2] 3) => true)
(fact (prime? [2] 4) => false)
(fact (prime? [2 3] 9) =>  false)

(defn next-prime [l]
	(first
		(filter
			#(prime? l %1)
			(drop (last l) (range)))))

(fact (next-prime [2 3 5 7]) => 11)

(defn primes [n]
	(if (= 1 n)
		[2]
		(let [primes-so-far (primes (dec n))]
			  (concat primes-so-far [(next-prime primes-so-far)]))))

(fact (primes 1) => [2])
(fact (primes 2) => [2 3])
(fact (primes 3) => [2 3 5])
