(ns c-style-let.core-test
  (:require [clojure.test :refer :all]
            [c-style-let.core :refer :all]))

(deftest test-code-block
  (assert (= (macroexpand-1 (code-block)) nil)))


;; (code-block
;;  (:var a "A"))

;; (code-block
;;  (:var a "A")
;;  (println a)
;;  (:var b "B")
;;  (println b))

;; (code-block
;;  (:var a "A")
;;  (println a)
;;  (:var b "B"))


(code-block
 (:var a 1)
 (:var b 2)
 (+ a b))

