(ns c-style-let.core-test
  (:require [clojure.test :refer :all]
            [c-style-let.core :refer :all]))

(deftest test-code-block
  (assert (= (macroexpand-1 (code-block)) nil)))


;; (code-block
;;  (:let a "A"))

;; (code-block
;;  (:let a "A")
;;  (println a)
;;  (:let b "B")
;;  (println b))

;; (code-block
;;  (:let a "A")
;;  (println a)
;;  (:let b "B"))


(code-block
 (:let a 1)
 (:let b 2)
 (:let c (+ a b))
 (:let [x & xs] [1 2 3 4 5])
 [(+ a b c) x xs])


(code-block
 (:let f (fn [a] (+ a 999)))
 (f 1))




