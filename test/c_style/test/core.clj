(ns c-style.test.core
  (:require [clojure.test :refer :all]
            [c-style.core :refer :all]))

(deftest test-proc
  (is (= nil (macroexpand-1 '(c-style.core/proc))))

  (is (= 1 (macroexpand-1 '(c-style.core/proc 1))))

  (is (= nil (macroexpand-1 '(c-style.core/proc (:let a 1)))))

  (is (= '(let [a 1] a) (macroexpand-1 '(c-style.core/proc (:let a 1) a))))

  (is (= '(let [a (+ 1 2)] a)
         (macroexpand-1 '(c-style.core/proc (:let a (+ 1 2)) a))))

  (is (= '(let [a (+ 1 2)]
            (let [b (+ a 2)]
              (+ a b)))
         (macroexpand-1 '(c-style.core/proc
                          (:let a (+ 1 2))
                          (:let b (+ a 2))
                          (+ a b)))))

  (is (= '(let [a 1] (+ a 2))
         (macroexpand-1
          '(c-style.core/proc (:let a 1)
                 (+ a 2)))))

  (is (= '(let [[x & xs] [1 2 3]] [xs x])
         (macroexpand-1
          '(c-style.core/proc (:let [x & xs] [1 2 3])
                 [xs x]))))

  (is (= '(let [a "A"]
            (let [_ (println a)]
              (let [b "B"]
                (println b))))
         (macroexpand-1
          '(c-style.core/proc
            (:let a "A")
            (println a)
            (:let b "B")
            (println b)))))

  (is (= '(let [a "A"]
            (let [_ (println a)]
              nil))
         (macroexpand-1
          '(c-style.core/proc
            (:let a "A")
            (println a)
            (:let b "B")))))

  (is (= 100
          (c-style.core/proc
           (:let a 1)
           (+ a 99))))

  (is (= [2 1 3 4]
         (c-style.core/proc
          (:let [x y & xs] [1 2 3 4])
          (-> xs
               (conj x)
               (conj y))))))



